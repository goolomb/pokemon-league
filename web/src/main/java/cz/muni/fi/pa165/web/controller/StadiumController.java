package cz.muni.fi.pa165.web.controller;

import cz.muni.fi.pa165.dto.StadiumDTO;
import cz.muni.fi.pa165.facade.StadiumFacade;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    
    /**
     * Prepares an empty form for creating new stadium.
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newStadium(Model m) {
        m.addAttribute("stadiumCreate", new StadiumDTO());
        return "stadium/new";
    }
    
    
    /**
     * Create new Stadium from form
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("stadiumCreate") StadiumDTO form, BindingResult bindingResult,
                         Model m, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder)
    {
        //check if any errors occur
        if (bindingResult.hasErrors()) {
            for(FieldError fe : bindingResult.getFieldErrors()) {
                m.addAttribute(fe.getField() + "_error", true);
            }
            return "stadium/new";
        }
        stadiumFacade.create(form);
        redirectAttributes.addFlashAttribute("alert_success", "Stadium was created");
        return "redirect:" + uriBuilder.path("/stadium/list").toUriString();
    }
    
    /**
     * Find all stadiums in database
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model m) {
        m.addAttribute("stadiums", stadiumFacade.findAll());
        return "stadium/list";
    }
    
    
    
}
