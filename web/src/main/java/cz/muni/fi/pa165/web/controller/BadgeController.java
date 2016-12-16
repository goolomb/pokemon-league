package cz.muni.fi.pa165.web.controller;

import cz.muni.fi.pa165.facade.BadgeFacade;
import cz.muni.fi.pa165.facade.TrainerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Marek Perichta.
 */
@Controller
@RequestMapping(value = "/badge")
public class BadgeController {

    @Autowired
    private BadgeFacade badgeFacade;

    @Autowired
    private TrainerFacade trainerFacade;


}
