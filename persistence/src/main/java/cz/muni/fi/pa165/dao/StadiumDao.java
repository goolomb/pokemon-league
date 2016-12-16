package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Trainer;
import cz.muni.fi.pa165.entity.Stadium;
import java.util.List;

/**
 *DAO interface for Stadium
 * 
 * @author Jakub Holy
 */
public interface StadiumDao {
    
    /**
     * Find Stadium in database with specified id
     * @param id of wanted Stadium
     * @return Stadium with specific id
     */
    public Stadium findById(Long id);
    
    /**
     * Create new Stadium in database
     * @param stadium new Stadium
     */
    public void create(Stadium stadium);
    
    /**
     * Delete specific Stadium from database
     * @param stadium to delete
     */    
    public void delete(Stadium stadium);
    
    /**
     * Find all Stadiums in database
     * @return all Stadiums
     */
    public List<Stadium> findAll();
    
    /**
     * Find Stadium with trainer as leader in database
     * @param trainer as leader of Stadium
     * @return Stadium with trainer as leader
     */
    public Stadium findByTrainer(Trainer trainer);
    
    /**
     * Updates Stadium in database
     * @param stadium to be updated
     * @return updated Stadium
     */
    public Stadium update(Stadium stadium);
    
}
