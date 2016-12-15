package cz.muni.fi.pa165.web.rest.controller;

import cz.muni.fi.pa165.dto.PokemonDTO;
import cz.muni.fi.pa165.facade.PokemonFacade;
import cz.muni.fi.pa165.facade.TrainerFacade;
import cz.muni.fi.pa165.web.rest.ApiUris;
import cz.muni.fi.pa165.web.rest.exception.InvalidParameterException;
import cz.muni.fi.pa165.web.rest.exception.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Collection;

/**
 * @author Martina Minatova
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_POKEMON)
public class PokemonRestController {

    @Inject
    private PokemonFacade pokemonFacade;

    @Inject
    private TrainerFacade trainerFacade;


    @RequestMapping(
            value = "/create",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PokemonDTO createPokemon(@Valid @RequestBody PokemonDTO pokemon) {
        try {
            pokemonFacade.create(pokemon);
            return pokemonFacade.findById(pokemon.getId());
        }
        catch (Exception e) {
            throw new InvalidParameterException();
        }
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public void DeletePokemon(@PathVariable("id") Long id) {
        try {
            pokemonFacade.delete(pokemonFacade.findById(id));
        } catch (Exception e) {
            throw new InvalidParameterException();
        }
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PokemonDTO getPokemon(@PathVariable("id") Long id) {
        try {
            PokemonDTO byId = pokemonFacade.findById(id);
            if (byId == null) {
                throw new ResourceNotFoundException();
            }
            return byId;
        } catch(Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(
            value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Collection<PokemonDTO> getAllPokemons() {
        try {
            return pokemonFacade.findAll();
        } catch(Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(
            value = "/withtrainer",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Collection<PokemonDTO> getPokemonWithTrainer(@RequestBody Long id) {
        try {
            return pokemonFacade.findByTrainer(trainerFacade.findById(id));
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }
}
