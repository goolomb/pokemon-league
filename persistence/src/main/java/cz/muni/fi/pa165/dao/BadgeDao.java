package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Badge;
import cz.muni.fi.pa165.entity.Trainer;
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
    public Badge findBadgeById(Long id);
    
    /**
     * Returns all badges from the database.
     * @return all badges
     */
    public List<Badge> findAllBadges();

    /**
     * Returns badges from the database belonging to specified trainer. 
     * @param trainer owner of badges
     * @return list of badges belonging to specified trainer
     */
    public List<Badge> findBadgeByTrainer(Trainer trainer);
    
    /**
     * Adds new badge to the database.
     * @param badge object to add
     */
    public void addBadge(Badge badge);
    
    /**
     * Deletes resource from the database.
     * @param badge object to delete
     */    
    public void deleteBadge(Badge badge);
    
    /**
     * Updates resource in the database.
     * @param badge object to update
     * @return updated badge
     */
    public Badge updateBadge(Badge badge);
    
}
