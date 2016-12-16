package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Trainer;
import java.util.List;

/**
 * DAO interface for Trainer
 * 
 * @author Martin Golomb
 */
public interface TrainerDao {
    
    /**
     * Returns trainer from database with specified id
     * @param id of wanted trainer
     * @return Trainer with specific id
     */
    public Trainer findById(Long id);
    
    /**
     * Returns all trainers from database
     * @return all trainers
     */
    public List<Trainer> findAll();
    
    /**
     * Create new trainer in the database
     * @param trainer new trainer
     */
    public void create(Trainer trainer);
    
    /**
     * Deletes trainer from database
     * @param trainer to delete
     */
    public void delete(Trainer trainer);

    /**
     * Updates trainer in database
     * @param trainer to be updated
     * @return updated Trainer
     */
    public Trainer update(Trainer trainer);
}
