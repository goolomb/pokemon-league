package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.TrainerDTO;
import cz.muni.fi.pa165.entity.Trainer;
import cz.muni.fi.pa165.service.BeanMapperService;
import cz.muni.fi.pa165.service.TrainerService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Martin Golomb (goolomb)
 */
@Service
@Transactional
public class TrainerFacadeImpl implements TrainerFacade {

    @Inject
    TrainerService trainerService;

    @Inject
    private BeanMapperService beanMapperService;

    @Override
    public TrainerDTO findById(Long id) {
        return beanMapperService.mapTo(trainerService.findById(id), TrainerDTO.class);
    }

    @Override
    public List<TrainerDTO> findAll() {
        return beanMapperService.mapTo(trainerService.findAll(), TrainerDTO.class);
    }

    @Override
    public void create(TrainerDTO trainer) {
        trainerService.create(beanMapperService.mapTo(trainer, Trainer.class));
    }

    @Override
    public void delete(TrainerDTO trainer) {
        trainerService.delete(beanMapperService.mapTo(trainer, Trainer.class));
    }

    @Override
    public TrainerDTO update(TrainerDTO trainer) {
        Trainer t = beanMapperService.mapTo(trainer, Trainer.class);
        return beanMapperService.mapTo(trainerService.update(t), TrainerDTO.class);
    }
    
}
