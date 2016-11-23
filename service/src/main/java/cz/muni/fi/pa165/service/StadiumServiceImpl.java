package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.StadiumDao;
import cz.muni.fi.pa165.entity.Stadium;
import cz.muni.fi.pa165.entity.Trainer;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jakub Holy (saintjackie)
 */

@Service
public class StadiumServiceImpl implements StadiumService{

    @Inject
    private StadiumDao stadiumDao;
        
    @Override
    public Stadium findById(Long id) {
        return stadiumDao.findById(id);
    }

    @Override
    public void create(Stadium stadium) {
        if(stadium == null)
            throw new IllegalArgumentException("stadium should not be null.");
        stadiumDao.create(stadium);
    }

    @Override
    public void delete(Stadium stadium) {
        if(stadium == null)
            throw new IllegalArgumentException("stadium should not be null.");
        stadiumDao.delete(stadium);
    }

    @Override
    public List<Stadium> findAll() {
        return stadiumDao.findAll();
    }

    @Override
    public Stadium findByTrainer(Trainer trainer) {
        if(trainer == null)
            throw new IllegalArgumentException("trainer should not be null.");
        return stadiumDao.findByTrainer(trainer);
    }

    @Override
    public Stadium update(Stadium stadium) {
        if(stadium == null)
            throw new IllegalArgumentException("stadium should not be null.");
        return stadiumDao.update(stadium);
    }
    
}
