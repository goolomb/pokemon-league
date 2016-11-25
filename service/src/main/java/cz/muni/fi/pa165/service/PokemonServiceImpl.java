package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.PokemonDao;
import cz.muni.fi.pa165.entity.Pokemon;
import cz.muni.fi.pa165.entity.Trainer;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Martina Minatova
 */
public class PokemonServiceImpl implements PokemonService {
    @Inject
    private PokemonDao pokemonDao;

    @Override
    public Pokemon findById(Long id) {
        return pokemonDao.findById(id);
    }

    @Override
    public void create(Pokemon pokemon) {
        if (pokemon == null)
            throw new IllegalArgumentException("pokemon cant be null");
        pokemonDao.create(pokemon);
    }

    @Override
    public void delete(Pokemon pokemon) {
        if (pokemon == null)
            throw new IllegalArgumentException("pokemon cant be null");
        pokemonDao.delete(pokemon);
    }

    @Override
    public List<Pokemon> findAll() {
        return pokemonDao.findAll();
    }

    @Override
    public List<Pokemon> findByTrainer(Trainer trainer) {
        if (trainer == null)
            throw new IllegalArgumentException("trainer cant be null");
        return pokemonDao.findByTrainer(trainer);
    }

    @Override
    public Pokemon update(Pokemon pokemon) {
        if (pokemon == null)
            throw new IllegalArgumentException("pokemon cant be null");
        return pokemonDao.update(pokemon);
    }
}
