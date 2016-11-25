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

    @Inject
    private TrainerService trainerService;

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

    @Override
    public void tradePokemon(Pokemon pokemon1, Pokemon pokemon2) {
        if (pokemon1 == null || pokemon2 == null)
            throw new IllegalArgumentException("pokemon cant be null");
        if (pokemon1.getTrainer() == null || pokemon2.getTrainer() == null)
            throw new IllegalArgumentException("pokemon must have trainer");

        Trainer trainer1 = pokemon1.getTrainer();
        Trainer trainer2 = pokemon2.getTrainer();

        trainer1.removePokemon(pokemon1);
        trainer2.removePokemon(pokemon2);

        trainer1.addPokemon(pokemon2);
        trainer2.addPokemon(pokemon1);

        pokemon1.setTrainer(trainer2);
        pokemon2.setTrainer(trainer1);

        pokemonDao.update(pokemon1);
        pokemonDao.update(pokemon2);

        trainerService.update(trainer1);
        trainerService.update(trainer2);
    }
}
