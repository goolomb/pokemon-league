package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.BadgeDao;
import cz.muni.fi.pa165.entity.Badge;
import cz.muni.fi.pa165.entity.Trainer;

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
        return badgeDao.findById(id);
    }

    @Override
    public List<Badge> findAll() {
        return badgeDao.findAll();
    }

    @Override
    public List<Badge> findByTrainer (Trainer trainer) {
        if (trainer == null)
            throw new IllegalArgumentException("Cannot find badges for null trainer.");
        return badgeDao.findByTrainer(trainer);
    }

    @Override
    public void create (Badge badge) {
        if (badge == null)
            throw new IllegalArgumentException("The badge to create is null");
        badgeDao.create(badge);
    }

    @Override
    public void delete(Badge badge) {
        if (badge == null)
            throw new IllegalArgumentException("The badge to delete is null");
        badgeDao.delete(badge);
    }

    @Override
    public Badge update(Badge badge) {
        if(badge == null)
            throw new IllegalArgumentException("The badge to update is null.");

        return badgeDao.update(badge);
    }

}
