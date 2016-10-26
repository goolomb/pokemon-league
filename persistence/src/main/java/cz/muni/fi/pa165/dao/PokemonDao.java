package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Pokemon;

import java.util.List;

/**
 * Pokemon data access object interface
 *
 * @author Martina Minatova
 */
public interface PokemonDao {
    public Pokemon findById(Long id);
    public void create(Pokemon pokemon);
    public void delete(Pokemon pokemon);
    public List<Pokemon> findAll();
    public List<Pokemon> findByTrainer(Trainer trainer);
    public void update(Pokemon pokemon);
}
