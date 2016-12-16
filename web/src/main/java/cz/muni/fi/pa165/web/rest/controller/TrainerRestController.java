package cz.muni.fi.pa165.web.rest.controller;

import cz.muni.fi.pa165.dto.TrainerDTO;
import cz.muni.fi.pa165.facade.TrainerFacade;
import cz.muni.fi.pa165.web.rest.ApiUris;
import cz.muni.fi.pa165.web.rest.exception.ResourceNotFoundException;
import java.util.Collection;
import javax.inject.Inject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Martin Golomb (goolomb)
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_TRAINER)
public class TrainerRestController {
    
    @Inject
    private TrainerFacade trainerFacade;
    
    @RequestMapping(
            value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Collection<TrainerDTO> getAllTrainers() {
        try {
            return trainerFacade.findAll();
        } catch(Exception e) {
            throw new ResourceNotFoundException();
        }
    }
}
