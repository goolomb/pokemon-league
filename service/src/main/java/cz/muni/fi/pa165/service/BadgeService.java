package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Badge;
import cz.muni.fi.pa165.entity.Trainer;

import java.util.List;

/**
 * Created by Marek Perichta.
 *
 * Interface to the service handling operations on badges.
 */
public interface BadgeService {

    /**
     * Returns badge from the persistance contextspecified by Id.
     *
     * @param id id of the Badge
     * @return badge specified by Id
     */
    Badge findById(Long id);

    /**
     * Returns collection of all badges from the persistence context.
     *
     * @return all badges
     */
    List<Badge> findAll();

    /**
     * Returns badges from the database belonging to specified trainer.
     *
     * @param trainer owner of badges
     * @return list of badges belonging to specified trainer
     * @exception IllegalArgumentException if trainer is null
     */
    List<Badge> findByTrainer(Trainer trainer);

    /**
     * Adds new badge to the persistence context.
     *
     * @param badge object to add
     * @exception IllegalArgumentException if badge is null
     */
    void create(Badge badge);

    /**
     * Deletes resource from the perisetence context.
     *
     * @param badge object to delete
     * @exception IllegalArgumentException if badge to delete is null
     */
    void delete(Badge badge);

    /**
     * Updates resource in the persistance context.
     *
     * @param badge object to update
     * @return updated badge
     * @exception IllegalArgumentException if badge to update is null
     */
    Badge update(Badge badge);
}
