package cz.muni.fi.pa165.web.controller;

import cz.muni.fi.pa165.dto.PokemonDTO;
import cz.muni.fi.pa165.dto.TrainerDTO;
import cz.muni.fi.pa165.facade.PokemonFacade;
import cz.muni.fi.pa165.facade.TrainerFacade;
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

import javax.validation.Valid;

/**
 * @author Martina Minatova
 */
@Controller
@RequestMapping("/pokemon")
@ComponentScan("cz.muni.fi.pa165.service")
public class PokemonController {

    @Autowired
    private PokemonFacade pokemonFacade;

    @Autowired
    private TrainerFacade trainerFacade;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPokemon(Model model) {
        model.addAttribute("pokemonCreate", new PokemonDTO());
        return "pokemon/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("pokemonCreate") PokemonDTO form, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder)
    {
        if (bindingResult.hasErrors()) {
            for(FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "pokemon/new";
        }
        pokemonFacade.create(form);
        redirectAttributes.addFlashAttribute("alert_success", "Pokemon was created");
        return "redirect:" + uriBuilder.path("/pokemon/list").toUriString();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("pokemons", pokemonFacade.findAll());
        return "pokemon/list";
    }

    @RequestMapping(value = "/withtrainer", method = RequestMethod.GET)
    public String findWithTrainer(@PathVariable Long trainerId, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (trainerId == null) {
            redirectAttributes.addFlashAttribute("alert_danger", "Unable to retrieve pokemon. Trainer ID is invalid.");
            return "redirect:" + uriBuilder.path("/pokemon/list").toUriString();
        }
        try {
            TrainerDTO byId = trainerFacade.findById(trainerId);
            model.addAttribute("pokemons", pokemonFacade.findByTrainer(byId));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("alert_danger", "Unable to retrieve pokemon.");
            return "redirect:" + uriBuilder.path("/pokemon/list").toUriString();
        }
        return "/pokemon/list";
    }
}
