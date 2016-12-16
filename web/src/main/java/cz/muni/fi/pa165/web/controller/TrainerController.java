package cz.muni.fi.pa165.web.controller;

import cz.muni.fi.pa165.dto.BadgeDTO;
import cz.muni.fi.pa165.dto.StadiumDTO;
import cz.muni.fi.pa165.dto.TrainerDTO;
import cz.muni.fi.pa165.facade.BadgeFacade;
import cz.muni.fi.pa165.facade.PokemonFacade;
import cz.muni.fi.pa165.facade.StadiumFacade;
import cz.muni.fi.pa165.facade.TrainerFacade;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "trainer/new";
        }
        trainerFacade.create(form);
        redirectAttributes.addFlashAttribute("alert_success", "Trainer was created");
        return "redirect:" + uriBuilder.path("/trainer/list").toUriString();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("trainers", trainerFacade.findAll());
        return "trainer/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getTrainer(@PathVariable("id") long id, Model model) {
        TrainerDTO trainer = trainerFacade.findById(id);

        model.addAttribute("trainer", trainer);
        model.addAttribute("pokemons", trainer.getPokemons());
        model.addAttribute("badges", trainer.getBadges());

        return "trainer/detail";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder) {
        trainerFacade.delete(trainerFacade.findById(id));
        return "redirect:" + uriBuilder.path("/trainer").toUriString();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateTrainer(@PathVariable long id, Model model) {
        TrainerDTO trainer = trainerFacade.findById(id);
        if (trainer == null) {
            return "redirect:/trainer";
        }
        model.addAttribute("trainer", trainer);
        return "trainer/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateTrainer(@ModelAttribute("trainer") TrainerDTO trainer, @PathVariable("id") long id,
            Model model, UriComponentsBuilder uriBuilder) {
        trainerFacade.update(trainerFacade.findById(id));
        return "redirect:" + uriBuilder.path("/trainer").toUriString();
    }

}
