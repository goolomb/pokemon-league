package cz.muni.fi.pa165.web.controller;

import cz.muni.fi.pa165.dto.PokemonCreateDTO;
import cz.muni.fi.pa165.dto.PokemonDTO;
import cz.muni.fi.pa165.dto.TrainerDTO;
import cz.muni.fi.pa165.enums.PokemonType;
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
import java.util.Collection;

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

    @ModelAttribute("types")
    public PokemonType[] types() {
        return PokemonType.values();
    }

    @ModelAttribute("trainers")
    public Collection<TrainerDTO> trainers() {
        Collection<TrainerDTO> collection = trainerFacade.findAll();
        collection.add(null);
        return collection;
    }

    @ModelAttribute("pokemons")
    public Collection<PokemonDTO> pokemons() {
        return pokemonFacade.findAll();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPokemon(Model model) {
        model.addAttribute("pokemonCreate", new PokemonCreateDTO());
        return "pokemon/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("pokemonCreate") PokemonCreateDTO form, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder)
    {
        if (bindingResult.hasErrors()) {
            for(FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            redirectAttributes.addFlashAttribute("alert_error", "Pokemon was not created.");
            return "pokemon/new";
        }
        PokemonDTO pokemonDTO = new PokemonDTO();
        pokemonDTO.setName(form.getName());
        pokemonDTO.setNickname(form.getNickname());
        pokemonDTO.setLevel(form.getLevel());
        pokemonDTO.setType(form.getType());
        pokemonDTO.setTrainer(form.getTrainer() == null ? null : trainerFacade.findById(form.getTrainer()));
        pokemonFacade.create(pokemonDTO);
        redirectAttributes.addFlashAttribute("alert_success", "Pokemon was created");
        return "redirect:" + uriBuilder.path("/pokemon/list").toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("pokemon", pokemonFacade.findById(id));
        return "/pokemon/view";
    }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("pokemons", pokemonFacade.findAll());
        return "pokemon/list";
    }

    /**
     * Filling form for edit
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPokemon(@PathVariable long id, Model m) {
        PokemonCreateDTO form = new PokemonCreateDTO();
        PokemonDTO pokemonDTO = pokemonFacade.findById(id);
        form.setId(pokemonDTO.getId());
        form.setType(pokemonDTO.getType());
        form.setLevel(pokemonDTO.getLevel());
        form.setNickname(pokemonDTO.getNickname());
        form.setName(pokemonDTO.getName());
        TrainerDTO trainer = pokemonDTO.getTrainer();
        form.setTrainer(trainer == null ? null : trainer.getId());
        m.addAttribute("pokemonEdit", form);
        return "pokemon/edit";
    }

    /**
     * Edit Pokemon
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("pokemonEdit") PokemonCreateDTO form, BindingResult bindingResult,
                       Model m, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                m.addAttribute(fe.getField() + "_error", true);
            }

            return "pokemon/edit";
        }
        PokemonDTO editedPokemon = new PokemonDTO();
        editedPokemon.setId(form.getId());
        editedPokemon.setType(form.getType());
        editedPokemon.setName(form.getName());
        editedPokemon.setLevel(form.getLevel());
        editedPokemon.setNickname(form.getNickname());
        Long trainer = form.getTrainer();
        editedPokemon.setTrainer(trainer == null ? null : trainerFacade.findById(trainer));
        pokemonFacade.update(editedPokemon);
        redirectAttributes.addFlashAttribute("alert_success", "Pokemon " + editedPokemon.getName() + " " + editedPokemon.getNickname() + " was edited");
        return "redirect:" + uriBuilder.path("/pokemon/list").toUriString();
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
