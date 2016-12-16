/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.web.rest.controller;

import cz.muni.fi.pa165.dto.PokemonDTO;
import cz.muni.fi.pa165.dto.StadiumDTO;
import cz.muni.fi.pa165.facade.StadiumFacade;
import cz.muni.fi.pa165.web.rest.ApiUris;
import cz.muni.fi.pa165.web.rest.exception.InvalidParameterException;
import cz.muni.fi.pa165.web.rest.exception.ResourceNotFoundException;
import java.util.Collection;
import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jakub Holy (saintjackie)
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_STADIUM)
public class StadiumRestController {
    
    @Inject
    private StadiumFacade stadiumFacade;
    
    @RequestMapping(
            value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Collection<StadiumDTO> getAllStadiums() {
        try {
            return stadiumFacade.findAll();
        } catch(Exception e) {
            throw new ResourceNotFoundException();
        }
    }
}
