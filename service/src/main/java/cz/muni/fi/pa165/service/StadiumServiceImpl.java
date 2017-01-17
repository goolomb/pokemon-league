package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.StadiumDao;
import cz.muni.fi.pa165.entity.Badge;
import cz.muni.fi.pa165.entity.Stadium;
import cz.muni.fi.pa165.entity.Trainer;
import java.util.List;
import javax.inject.Inject;

import cz.muni.fi.pa165.exception.PokemonLeagueDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jakub Holy (saintjackie)
 */

@Service
public class StadiumServiceImpl implements StadiumService{

    @Inject
    private StadiumDao stadiumDao;
        
    @Inject
    private BadgeService badgeService;
    
    @Inject
    private TrainerService trainerService;
    
    @Override
    public Stadium findById(Long id) {
        return stadiumDao.findById(id);
    }

    @Override
    public void create(Stadium stadium) {
        if(stadium == null)
            throw new IllegalArgumentException("stadium should not be null.");
        try {
            stadiumDao.create(stadium);
        } catch (Throwable e) {
            throw new PokemonLeagueDataAccessException("Cannot create stadium", e);
        }

    }

    @Override
    public void delete(Stadium stadium) {
        if(stadium == null)
            throw new IllegalArgumentException("stadium should not be null.");
        try {
            stadiumDao.delete(stadium);
        } catch (Throwable e) {
            throw new PokemonLeagueDataAccessException("Cannot delete stadium", e);
        }

    }

    @Override
    public List<Stadium> findAll() {

        try {
            return stadiumDao.findAll();
        } catch (Throwable e) {
            throw new PokemonLeagueDataAccessException("Cannot find all stadiums", e);
        }

    }

    @Override
    public Stadium findByTrainer(Trainer trainer) {
        if(trainer == null)
            throw new IllegalArgumentException("trainer should not be null.");
        try {
            return stadiumDao.findByTrainer(trainer);
        } catch (Throwable e) {
            throw new PokemonLeagueDataAccessException("Cannot find stadium by trainer", e);
        }

    }

    @Override
    public Stadium update(Stadium stadium) {
        if(stadium == null)
            throw new IllegalArgumentException("stadium should not be null.");
        try {
            return stadiumDao.update(stadium);
        } catch (Throwable e) {
            throw new PokemonLeagueDataAccessException("Cannot update stadium", e);
        }
    }

    @Override
    public void giveBadgeToTrainer(Stadium stadium, Trainer trainer) {
        if (trainer == null)
            throw new IllegalArgumentException("Trainer is null");
        if (stadium == null)
            throw new IllegalArgumentException("Stadium is null");
        
        Trainer leader = stadium.getLeader();

        if (trainer.equals(leader))
            throw new IllegalArgumentException("Trainer cant get badge of stadium where he is leader");
        
        Badge b = new Badge();
        b.setOrigin(stadium);
        b.setTrainer(trainer);
                
        if (trainer.getBadges().contains(b))
            throw new IllegalArgumentException("Already has this badge");
        
        trainer.addBadge(b);
        
        badgeService.create(b);
        trainerService.update(trainer);
    }    
}
