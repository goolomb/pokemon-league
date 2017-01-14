package cz.muni.fi.pa165.web.controller;

import cz.muni.fi.pa165.dto.StadiumCreateDTO;
import cz.muni.fi.pa165.dto.StadiumDTO;
import cz.muni.fi.pa165.dto.TrainerDTO;
import cz.muni.fi.pa165.enums.PokemonType;
import cz.muni.fi.pa165.facade.StadiumFacade;
import cz.muni.fi.pa165.facade.TrainerFacade;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Jakub Holy (saintjackie)
 */
@Controller
@RequestMapping("/stadium")
@ComponentScan("cz.muni.fi.pa165.service")
public class StadiumController {

    @Autowired
    private StadiumFacade stadiumFacade;

    @Autowired
    private TrainerFacade trainerFacade;

    /**
     * Prepares an empty form for creating new stadium
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newStadium(Model m) {
        m.addAttribute("stadiumCreate", new StadiumCreateDTO());
        m.addAttribute("pokemonTypes", PokemonType.values());
        m.addAttribute("trainers", trainerFacade.findAll());
        return "stadium/new";
    }

    /**
     * Create new Stadium from form
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("stadiumCreate") StadiumCreateDTO form, BindingResult bindingResult,
            Model m, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        //check if any errors occur
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                m.addAttribute(fe.getField() + "_error", true);
            }
            m.addAttribute("pokemonTypes", PokemonType.values());
            m.addAttribute("trainers", trainerFacade.findAll());

            return "stadium/new";
        }
        StadiumDTO newStadium = new StadiumDTO();
        newStadium.setType(form.getType());
        newStadium.setCity(form.getCity());
        newStadium.setLeader(trainerFacade.findById(form.getLeaderId()));
        stadiumFacade.create(newStadium);
        redirectAttributes.addFlashAttribute("alert_success", "Stadium \"" + newStadium.getCity() + "\" was created");
        return "redirect:" + uriBuilder.path("/stadium/list").toUriString();
    }

    /**
     * Find all stadiums
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model m) {
        m.addAttribute("stadiums", stadiumFacade.findAll());
        return "stadium/list";
    }

    /**
     * Delete stadium with id
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model m, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        StadiumDTO stadium = stadiumFacade.findById(id);
        try{        
        stadiumFacade.delete(id);
        redirectAttributes.addFlashAttribute("alert_success", "Stadium \"" + stadium.getCity() + "\" was deleted.");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("alert_danger", "Stadium \"" + stadium.getCity() + "\" cannot be deleted due to usage in badge.");
        }
        return "redirect:" + uriBuilder.path("/stadium/list").toUriString();
    }

    /**
     * Prepares form for editing stadium
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editStadium(@PathVariable long id, Model m) {
        StadiumDTO stadiumDTO = stadiumFacade.findById(id);
        StadiumCreateDTO stadiumEdit = new StadiumCreateDTO();
        stadiumEdit.setId(stadiumDTO.getId());
        stadiumEdit.setCity(stadiumDTO.getCity());
        stadiumEdit.setType(stadiumDTO.getType());
        stadiumEdit.setLeaderId(stadiumDTO.getLeader().getId());
        m.addAttribute("stadiumEdit", stadiumEdit);
        m.addAttribute("pokemonTypes", PokemonType.values());
        List<TrainerDTO> trainers = trainerFacade.findAll();
        trainers.remove(stadiumDTO.getLeader());
        m.addAttribute("trainers", trainers);
        m.addAttribute("chosenTrainer", stadiumDTO.getLeader());
        return "stadium/edit";
    }

    /**
     * Edit Stadium from form
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("stadiumEdit") StadiumCreateDTO form, BindingResult bindingResult,
            Model m, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        //check if any errors occur
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                m.addAttribute(fe.getField() + "_error", true);
            }


            m.addAttribute("pokemonTypes", PokemonType.values());
            List<TrainerDTO> trainers = trainerFacade.findAll();
            TrainerDTO chosenTrainer = trainerFacade.findById(form.getLeaderId());
            trainers.remove(chosenTrainer);
            m.addAttribute("trainers", trainers);
            m.addAttribute("chosenTrainer", chosenTrainer);

            return "stadium/edit";
        }
        StadiumDTO editedStadium = new StadiumDTO();
        editedStadium.setId(form.getId());
        editedStadium.setType(form.getType());
        editedStadium.setCity(form.getCity());
        editedStadium.setLeader(trainerFacade.findById(form.getLeaderId()));
        stadiumFacade.update(editedStadium);
        redirectAttributes.addFlashAttribute("alert_success", "Stadium \"" + editedStadium.getCity() + "\" was edited");
        return "redirect:" + uriBuilder.path("/stadium/list").toUriString();
    }

}
