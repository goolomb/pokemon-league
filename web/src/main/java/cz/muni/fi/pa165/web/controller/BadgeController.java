package cz.muni.fi.pa165.web.controller;

import cz.muni.fi.pa165.dto.BadgeDTO;
import cz.muni.fi.pa165.dto.BadgeCreateDTO;
import cz.muni.fi.pa165.facade.BadgeFacade;
import cz.muni.fi.pa165.facade.StadiumFacade;
import cz.muni.fi.pa165.facade.TrainerFacade;
import cz.muni.fi.pa165.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marek Perichta.
 */
@Controller
@RequestMapping(value = "/badge")
@ComponentScan("cz.muni.fi.pa165.service")
public class BadgeController {

    @Autowired
    private BadgeFacade badgeFacade;

    @Autowired
    private TrainerFacade trainerFacade;

    @Autowired
    private StadiumFacade stadiumFacade;


    /**
     * Default view for badges with list
     *
     * @return jsp /index
     */
    @RequestMapping(value = {"","/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        List<BadgeDTO> badges = badgeFacade.findAll();
        model.addAttribute("badges", badges);
        return "badge/index";
    }

    /**
     * Pepares and shows view for badge creation
     *
     * @return jsp /create
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newBadge(Model model) {
        model.addAttribute("badgeCreate", new BadgeDTO());
        model.addAttribute("trainers",trainerFacade.findAll());
        model.addAttribute("origins", stadiumFacade.findAll());
        return "badge/create";
    }

    /**
     * Creates badge
     *
     * @param form of the created badge
     * @return redirection to default view
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createBadge (
            @Valid @ModelAttribute("badgeCreate") BadgeCreateDTO form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder)
    {
        if (bindingResult.hasErrors()) {
            for(FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            model.addAttribute("trainers",trainerFacade.findAll());
            model.addAttribute("origins", stadiumFacade.findAll());
            return "badge/create";
        }
        BadgeDTO badgeToCreate = new BadgeDTO();
        badgeToCreate.setTrainer(trainerFacade.findById(form.getTrainer()));
        badgeToCreate.setOrigin(stadiumFacade.findById(form.getOrigin()));
        badgeFacade.create(badgeToCreate);
        redirectAttributes.addFlashAttribute("alert_success", "Pokemon was created");
        return "redirect:" + uriBuilder.path("/badge/index").toUriString();
    }

    /**
     * Removes badge
     *
     * @param badgeId to remove
     * @return redirection to default view
     */
    @RequestMapping(value = "/remove/{badgeId}", method = RequestMethod.POST)
    public String removeBadge(
            Model model,
            @PathVariable Long badgeId,
            UriComponentsBuilder uriBuilder)
    {
        badgeFacade.delete(new BadgeDTO(badgeId));
        return "redirect:" + uriBuilder.path("/badge/index").toUriString();
    }


}
