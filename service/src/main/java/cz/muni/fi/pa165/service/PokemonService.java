package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Pokemon;
import cz.muni.fi.pa165.entity.Trainer;

import java.util.List;

/**
 * @author Martina Minatova
 */
public interface PokemonService {

    Pokemon findById(Long id);

    void create(Pokemon pokemon);

    void delete(Pokemon pokemon);

    List<Pokemon> findAll();

    List<Pokemon> findByTrainer(Trainer trainer);

    Pokemon update(Pokemon pokemon);

    void tradePokemon(Pokemon pokemon1, Pokemon pokemon2);
}
