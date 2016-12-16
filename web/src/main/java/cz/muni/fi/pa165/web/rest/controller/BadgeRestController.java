package cz.muni.fi.pa165.web.rest.controller;
import cz.muni.fi.pa165.dto.BadgeDTO;
import cz.muni.fi.pa165.facade.BadgeFacade;
import cz.muni.fi.pa165.facade.TrainerFacade;
import cz.muni.fi.pa165.web.rest.ApiUris;

import cz.muni.fi.pa165.web.rest.exception.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;
/**
 * Created by Marek Perichta.
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_BADGE)
public class BadgeRestController {

    @Inject
    private BadgeFacade badgeFacade;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<BadgeDTO> getAllBadgees() {
        try {
            return badgeFacade.findAll();
        } catch(Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/withtrainer", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<BadgeDTO> getBadgeWithTrainer(@RequestBody Long id) {
        try {
            return badgeFacade.findByTrainer(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }
}

