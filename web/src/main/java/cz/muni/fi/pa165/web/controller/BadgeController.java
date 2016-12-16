package cz.muni.fi.pa165.web.controller;

import cz.muni.fi.pa165.dto.BadgeDTO;
import cz.muni.fi.pa165.facade.BadgeFacade;
import cz.muni.fi.pa165.facade.TrainerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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


    @RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        List<BadgeDTO> badges = badgeFacade.findAll();
        model.addAttribute("badges", badges);

        return "badge/index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newBadge(Model model) {
        model.addAttribute("badgeCreate", new BadgeDTO());
        return "badge/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createBadge (
            @Valid @ModelAttribute("badgeCreate") BadgeDTO form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder)
    {
        if (bindingResult.hasErrors()) {
            for(FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "badge/new";
        }
        badgeFacade.create(form);
        redirectAttributes.addFlashAttribute("alert_success", "Pokemon was created");
        return "redirect:" + uriBuilder.path("/badge/list").toUriString();
    }



}
