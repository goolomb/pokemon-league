package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BadgeDTO;

import cz.muni.fi.pa165.entity.Badge;
import cz.muni.fi.pa165.entity.Trainer;
import cz.muni.fi.pa165.service.BadgeService;
import cz.muni.fi.pa165.service.BeanMapperService;
import cz.muni.fi.pa165.service.TrainerService;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marek Perichta.
 */
@Service
@Transactional
public class BadgeFacadeImpl implements BadgeFacade{

    @Inject
    BadgeService badgeService;

    @Inject
    TrainerService trainerService;

    @Inject
    private BeanMapperService beanMapperService;

    @Override
    public BadgeDTO findById (Long badgeId) {
        if (badgeId == null) {
            throw new IllegalArgumentException("BadgeId is null.");
        }
        return beanMapperService.mapTo(badgeService.findById(badgeId), BadgeDTO.class);
    }

    @Override
    public List<BadgeDTO> findAll() {
        List<BadgeDTO> badges;
        badges = beanMapperService.mapTo(badgeService.findAll(), BadgeDTO.class);
        return badges;
    }

    @Override
    public List<BadgeDTO> findByTrainer(Long trainerId) {
        if (trainerId == null) {
            throw new IllegalArgumentException("TrainerId is null.");
        }

        Trainer trainer = trainerService.findById(trainerId);
        if (trainer == null) {
            throw new IllegalArgumentException("Trainer with id: " + trainerId +" does not exist.");
        }

        return beanMapperService.mapTo(badgeService.findByTrainer(trainer), BadgeDTO.class);
    }

    @Override
    public void create(BadgeDTO badgeDTO) {
        if (badgeDTO == null) {
            throw new IllegalArgumentException("BadgeDTO is null.");
        }
        badgeService.create(beanMapperService.mapTo(badgeDTO, Badge.class));
    }

    @Override
    public void delete(BadgeDTO badgeDTO) {
        if (badgeDTO == null) {
            throw new IllegalArgumentException("BadgeDTO is null.");
        }
        if (badgeService.findById(badgeDTO.getId()) == null) {
            throw new IllegalArgumentException("Badge does not exist.");
        }
        badgeService.delete(badgeService.findById(badgeDTO.getId()));
    }

    @Override
    public BadgeDTO update (BadgeDTO badgeDTO) {
        if (badgeDTO == null) {
            throw new IllegalArgumentException("BadgeDTO is null.");
        }
        if (badgeService.findById(badgeDTO.getId()) == null) {
            throw new IllegalArgumentException("Can not update non-existing badge.");
        }
        Badge badge = beanMapperService.mapTo(badgeDTO, Badge.class);

        return beanMapperService.mapTo(badgeService.update(badge), BadgeDTO.class);
    }


}