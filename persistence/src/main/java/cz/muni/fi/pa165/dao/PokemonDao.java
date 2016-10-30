package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Pokemon;
import cz.muni.fi.pa165.entity.Trainer;

import java.util.List;

/**
 * Pokemon data access object interface
 *
 * @author Martina Minatova
 */
public interface PokemonDao {
    /**
     * Returns pokemon from database specified by ID
     * @param id ID of pokemon
     * @return found pokemon or null if not found
     */
    public Pokemon findById(Long id);

    /**
     * Adds pokemon to database
     * @param pokemon pokemon to be added
     */
    public void create(Pokemon pokemon);

    /**
     * Deletes pokemon from database
     * @param pokemon pokemon to be deleted
     */
    public void delete(Pokemon pokemon);

    /**
     * Returns all pokemon stored in database
     * @return list of pokemon
     */
    public List<Pokemon> findAll();

    /**
     * Returns all pokemon belonging to trainer from database
     * @param trainer trainer of pokemon
     * @return list of pokemon belonging to trainer
     */
    public List<Pokemon> findByTrainer(Trainer trainer);

    /**
     * Updates given pokemon in database
     * @param pokemon pokemon to be updated
     * @return updated Pokemon
     */
    public Pokemon update(Pokemon pokemon);
}
