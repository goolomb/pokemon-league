package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.BadgeDao;
import cz.muni.fi.pa165.entity.Badge;
import cz.muni.fi.pa165.entity.Trainer;
import cz.muni.fi.pa165.exception.PokemonLeagueDataAccessException;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Marek Perichta.
 */
public class BadgeServiceImpl implements BadgeService {
    @Inject
    private BadgeDao badgeDao;

    @Override
    public Badge findById(Long id) {
        try {
            return badgeDao.findById(id);
        } catch (Throwable e) {
            throw new PokemonLeagueDataAccessException("Cannot find badge", e);
        }
    }

    @Override
    public List<Badge> findAll() {
        return badgeDao.findAll();
    }

    @Override
    public List<Badge> findByTrainer (Trainer trainer) {
        if (trainer == null)
            throw new IllegalArgumentException("Cannot find badges for null trainer.");
        try {
            return badgeDao.findByTrainer(trainer);
        } catch (Throwable e) {
            throw new PokemonLeagueDataAccessException("Cannot find badge by trainer", e);
        }
    }

    @Override
    public void create (Badge badge) {
        if (badge == null)
            throw new IllegalArgumentException("The badge to create is null");
        try {
            badgeDao.create(badge);
        } catch (Throwable e) {
            throw new PokemonLeagueDataAccessException("Cannot create badge", e);
        }
    }

    @Override
    public void delete(Badge badge) {
        if (badge == null)
            throw new IllegalArgumentException("The badge to delete is null");
        try {
            badgeDao.delete(badge);
        } catch (Throwable e) {
            throw new PokemonLeagueDataAccessException("Cannot delete badge", e);
        }
    }

    @Override
    public Badge update(Badge badge) {
        if(badge == null)
            throw new IllegalArgumentException("The badge to update is null.");
        try {
            return badgeDao.update(badge);
        } catch (Throwable e) {
            throw new PokemonLeagueDataAccessException("Cannot update badge", e);
        }
    }

}
