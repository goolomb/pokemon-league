package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.PokemonDTO;

import java.util.List;

/**
 * @author Martina Minatova
 */
public interface PokemonFacade {

    PokemonDTO findById(Long id);

    void create(PokemonDTO pokemon);

    void delete(PokemonDTO pokemon);

    List<PokemonDTO> findAll();

    List<PokemonDTO> findByTrainer(TrainerDTO trainer);

    PokemonDTO update(PokemonDTO pokemon);
}
