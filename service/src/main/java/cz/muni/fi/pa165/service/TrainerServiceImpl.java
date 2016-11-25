package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.TrainerDao;
import cz.muni.fi.pa165.entity.Trainer;
import java.util.List;
import javax.inject.Inject;

import cz.muni.fi.pa165.exception.PokemonLeagueDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martin Golomb (goolomb)
 */
@Service
public class TrainerServiceImpl implements TrainerService {

    @Inject
    private TrainerDao trainerDao;
    
    @Override
    public Trainer findById(Long id) {

        try {
            return trainerDao.findById(id);
        } catch (Throwable e) {
            throw new PokemonLeagueDataAccessException("Cannot find trainer", e);
        }

    }

    @Override
    public List<Trainer> findAll() {
        try {
            return trainerDao.findAll();
        } catch (Throwable e) {
            throw new PokemonLeagueDataAccessException("Cannot find all trainers", e);
        }
    }

    @Override
    public void create(Trainer trainer) {
        if (trainer == null)
            throw new IllegalArgumentException("Trainer is null");
        try {
            trainerDao.create(trainer);
        } catch (Throwable e) {
            throw new PokemonLeagueDataAccessException("Cannot create trainer", e);
        }
    }

    @Override
    public void delete(Trainer trainer) {
        if (trainer == null)
            throw new IllegalArgumentException("Trainer is null");
        try {
            trainerDao.delete(trainer);
        } catch (Throwable e) {
            throw new PokemonLeagueDataAccessException("Cannot delete trainer", e);
        }
    }

    @Override
    public Trainer update(Trainer trainer) {
        if (trainer == null)
            throw new IllegalArgumentException("Trainer is null");
        try {
            return trainerDao.update(trainer);
        } catch (Throwable e) {
            throw new PokemonLeagueDataAccessException("Cannot update trainer", e);
        }
    }
}
