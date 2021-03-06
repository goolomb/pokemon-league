package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.StadiumDTO;
import cz.muni.fi.pa165.dto.TrainerDTO;
import cz.muni.fi.pa165.entity.Stadium;
import cz.muni.fi.pa165.entity.Trainer;
import cz.muni.fi.pa165.service.BeanMapperService;
import cz.muni.fi.pa165.service.StadiumService;
import cz.muni.fi.pa165.service.TrainerService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jakub Holy (saintjackie)
 */

@Service
@Transactional
public class StadiumFacadeImpl implements StadiumFacade{
    
    @Inject
    private StadiumService stadiumService;
    
    @Inject
    private TrainerService trainerService;
    
    @Inject
    private BeanMapperService beanMapperService;

    @Override
    public StadiumDTO findById(Long id) {
        return beanMapperService.mapTo(stadiumService.findById(id), StadiumDTO.class);
    }

    @Override
    public void create(StadiumDTO stadium) {
        stadiumService.create(beanMapperService.mapTo(stadium,Stadium.class));
    }

    @Override
    public void delete(Long id) {
        Stadium st = stadiumService.findById(id);
        stadiumService.delete(st);
    }

    @Override
    public List<StadiumDTO> findAll() {
        return beanMapperService.mapTo(stadiumService.findAll(), StadiumDTO.class);
    }

    @Override
    public StadiumDTO findByTrainer(Long trainerId) {                
        Trainer tr = trainerService.findById(trainerId);
        return beanMapperService.mapTo(stadiumService.findByTrainer(tr),StadiumDTO.class);
    }

    @Override
    public StadiumDTO update(StadiumDTO stadium) {
        Stadium st = beanMapperService.mapTo(stadium, Stadium.class);
        return beanMapperService.mapTo(stadiumService.update(st), StadiumDTO.class);
    }
    
    @Override
    public void giveBadgeToTrainer(StadiumDTO stadium, TrainerDTO trainer){
        stadiumService.giveBadgeToTrainer(beanMapperService.mapTo(stadium,Stadium.class),
                beanMapperService.mapTo(trainer,Trainer.class));
    }
}
