package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.StadiumDTO;
import cz.muni.fi.pa165.dto.TrainerDTO;
import java.util.List;

/**
 *
 * @author Jakub Holy (saintjackie)
 */
public interface StadiumFacade {
    
    StadiumDTO findById(Long id);
    
    void create(StadiumDTO stadium);
    
    void delete(Long id);
    
    List<StadiumDTO> findAll();
    
    StadiumDTO findByTrainer(Long trainerId);
    
    StadiumDTO update(StadiumDTO stadium);
    
    void giveBadgeToTrainer(StadiumDTO stadium, TrainerDTO trainer);
    
}
