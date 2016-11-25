package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Stadium;
import cz.muni.fi.pa165.entity.Trainer;
import java.util.List;

/**
 *
 * @author Jakub Holy (saintjackie)
 */
public interface StadiumService {
    
    Stadium findById(Long id);
    
    void create(Stadium stadium);
    
    void delete(Stadium stadium);
    
    List<Stadium> findAll();
    
    Stadium findByTrainer(Trainer trainer);
    
    Stadium update(Stadium stadium);
    
    void giveBadgeToTrainer(Stadium stadium, Trainer trainer);
}
