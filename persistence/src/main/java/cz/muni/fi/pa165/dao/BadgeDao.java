package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Badge;
import java.util.List;

/**
 *
 * Data access object interface for Badge.
 * 
 * @author Marek Perichta
 */
public interface BadgeDao {

    /**
     * Returns badge from the database specified by Id.
     * @param id id of the Badge
     * @return badge specified by Id
     */
    public Badge findById(Long id);
    
    /**
     * Returns all badges from the database.
     * @return all badges
     */
    public List<Badge> findAll();
    
    /**
     * Adds new badge to the database.
     * @param badge object to add
     */
    public void create(Badge badge);
    
    /**
     * Deletes resource from the database.
     * @param badge object to delete
     */    
    public void delete(Badge badge);
    
    /**
     * Updates resource in the database.
     * @param badge object to update
     * @return updated badge
     */
    public Badge update(Badge badge);
    
}
