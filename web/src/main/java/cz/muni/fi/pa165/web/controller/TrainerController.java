package cz.muni.fi.pa165.web.controller;

import cz.muni.fi.pa165.dto.BadgeDTO;
import cz.muni.fi.pa165.dto.PokemonDTO;
import cz.muni.fi.pa165.dto.StadiumCreateDTO;
import cz.muni.fi.pa165.dto.StadiumDTO;
import cz.muni.fi.pa165.dto.TrainerDTO;
import cz.muni.fi.pa165.enums.PokemonType;
import cz.muni.fi.pa165.facade.BadgeFacade;
import cz.muni.fi.pa165.facade.PokemonFacade;
import cz.muni.fi.pa165.facade.StadiumFacade;
import cz.muni.fi.pa165.facade.TrainerFacade;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Martin Golomb (goolomb)
 */
@Controller
@RequestMapping("/trainer")
@ComponentScan("cz.muni.fi.pa165.service")
public class TrainerController {

    @Autowired
    private TrainerFacade trainerFacade;

    @Autowired
    private PokemonFacade pokemonFacade;

    @Autowired
    private BadgeFacade badgeFacade;

    @Autowired
    private StadiumFacade stadiumFacade;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newTrainer(Model model) {
        model.addAttribute("trainerCreate", new TrainerDTO());
        return "trainer/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("trainerCreate") TrainerDTO form, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                System.out.println("ObjectError: {}" + ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                System.out.println("FieldError: {}" + fe);
            }

            /*model.addAttribute("pokemons", pokemonFacade.findAll());
            model.addAttribute("stadiums", stadiumFacade.findAll());
            model.addAttribute("badges", badgeFacade.findAll());
             */
            return "trainer/new";
        }
        TrainerDTO trainerDTO = new TrainerDTO();
        trainerDTO.setFirstName(form.getFirstName());
        trainerDTO.setLastName(form.getLastName());
        trainerDTO.setBirthDate(form.getBirthDate());
        /*trainerDTO.setBadges(form.getBadges() == null ? null : (form.getBadges()));
        trainerDTO.setPokemons(form.getPokemons() == null ? null : (form.getPokemons()));
         */
        trainerFacade.create(trainerDTO);

        redirectAttributes.addFlashAttribute("alert_success", "Trainer was created");
        return "redirect:" + uriBuilder.path("/trainer/list").toUriString();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("trainers", trainerFacade.findAll());
        model.addAttribute("badgesCount", badgesCount());
        return "trainer/list";
    }

    private Map<Long, Integer> badgesCount() {
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        for (TrainerDTO t : trainerFacade.findAll()) {
            map.put(t.getId(), t.getBadges().size());
        }
        return map;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getTrainer(@PathVariable("id") long id, Model model) {
        TrainerDTO trainer = trainerFacade.findById(id);

        model.addAttribute("trainer", trainer);
        model.addAttribute("pokemons", trainer.getPokemons());
        model.addAttribute("badges", trainer.getBadges());

        return "trainer/detail";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model m, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        TrainerDTO trainer = trainerFacade.findById(id);

        try {
            /*Collection<PokemonDTO> pokemons = trainer.getPokemons();
            if (pokemons != null) {
                System.out.println(pokemons.size() + " num of pokemonsssssss");
                for (PokemonDTO pokemonDTO : pokemons) {
                    pokemonDTO.setTrainer(null);
                    pokemonFacade.update(pokemonDTO);
                }
            }

            Collection<BadgeDTO> badges = trainer.getBadges();
            if (badges != null) {
                for (BadgeDTO b : badges) {
                    b.setTrainer(null);
                    badgeFacade.update(b);
                }
            }*/
            System.out.println("heerree");
            trainerFacade.delete(trainer);
            System.out.println("thheerree");
            
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_error", "Trainer not deleted: " + ex.getMessage());
            return "redirect:" + "/trainer/list";
        }
        redirectAttributes.addFlashAttribute("alert_success", "Trainer was deleted successfully.");
        return "redirect:" + uriBuilder.path("/trainer/list").toUriString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editTrainer(@PathVariable long id, Model model) {
        TrainerDTO trainer = trainerFacade.findById(id);
        /*if (trainer == null) {
            return "redirect:/trainer";
        }*/
        model.addAttribute("trainer", trainer);
        return "trainer/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editTrainer(@ModelAttribute("editTrainer") TrainerDTO form, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                System.out.println("ObjectError: {}" + ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                System.out.println("FieldError: {}" + fe);
            }

            model.addAttribute("trainer", trainerFacade.findById(form.getId()));
            redirectAttributes.addFlashAttribute("alert_error", "There was a problem with updating trainer.");
            return "trainer/edit";
        }
        try {
            TrainerDTO toUpdate = new TrainerDTO();

            toUpdate.setFirstName(form.getFirstName());
            toUpdate.setLastName(form.getLastName());
            toUpdate.setBirthDate(form.getBirthDate());
            toUpdate.setId(form.getId());
            toUpdate.setBadges(form.getBadges());
            toUpdate.setPokemons(form.getPokemons());

            trainerFacade.update(form);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_error", "There was a problem with updating trainer: " + ex.getMessage());
            return "redirect:" + "trainer/edit";
        }
        redirectAttributes.addFlashAttribute("alert_success", "Trainer was updated successfully.");
        return "redirect:" + uriBuilder.path("/trainer/list").toUriString();
    }

}
