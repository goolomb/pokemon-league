package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.TrainerDao;
import cz.muni.fi.pa165.entity.Trainer;
import java.util.List;
import javax.inject.Inject;
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
        return trainerDao.findById(id);
    }

    @Override
    public List<Trainer> findAll() {
        return trainerDao.findAll();
    }

    @Override
    public void create(Trainer trainer) {
        if (trainer == null)
            throw new IllegalArgumentException("Trainer is null");
        trainerDao.create(trainer);
    }

    @Override
    public void delete(Trainer trainer) {
        if (trainer == null)
            throw new IllegalArgumentException("Trainer is null");
        trainerDao.delete(trainer);
    }

    @Override
    public Trainer update(Trainer trainer) {
        if (trainer == null)
            throw new IllegalArgumentException("Trainer is null");
        return trainerDao.update(trainer);
    }    
}
