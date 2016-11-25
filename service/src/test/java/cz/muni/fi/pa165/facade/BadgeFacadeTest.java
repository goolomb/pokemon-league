package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BadgeDTO;
import cz.muni.fi.pa165.dto.StadiumDTO;
import cz.muni.fi.pa165.dto.TrainerDTO;
import cz.muni.fi.pa165.entity.Badge;
import cz.muni.fi.pa165.entity.Stadium;
import cz.muni.fi.pa165.entity.Trainer;
import cz.muni.fi.pa165.service.BadgeService;
import cz.muni.fi.pa165.service.BeanMapperService;
import cz.muni.fi.pa165.service.StadiumService;
import cz.muni.fi.pa165.service.TrainerService;
import org.dozer.inject.Inject;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Created by Marek Perichta.
 */
public class BadgeFacadeTest {

    @Mock
    private TrainerService trainerService;

    @Mock
    private BadgeService badgeService;

    @Mock
    private BeanMapperService beanMapperService;

    @InjectMocks
    private BadgeFacade badgeFacade = new BadgeFacadeImpl();

    private BadgeDTO badgeDTO1, badgeDTO2;
    private TrainerDTO trainerDTO1, trainerDTO2;
    private StadiumDTO stadiumDTO1, stadiumDTO2;
    private Badge badge1, badge2;
    private Trainer trainer1;

    private long createdBadgeId = 12;
    private long notCreatedBadgeId = 123;




    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        badgeDTO1 = new BadgeDTO(createdBadgeId);
        badgeDTO2 = new BadgeDTO(notCreatedBadgeId);

        trainerDTO1 = new TrainerDTO(1L);
        stadiumDTO1 = new StadiumDTO(2L);
        trainerDTO2 = new TrainerDTO(3L);
        stadiumDTO2 = new StadiumDTO(4L);

        badgeDTO1.setOrigin(stadiumDTO1);
        badgeDTO1.setTrainer(trainerDTO1);
        badgeDTO2.setOrigin(stadiumDTO2);
        badgeDTO2.setTrainer(trainerDTO2);

        badge1 = new Badge (createdBadgeId);
        badge2 = new Badge (notCreatedBadgeId);

        trainer1 = new Trainer(5L);
        badge1.setTrainer(trainer1);
    }

    @Test
    public void createBadge(){
        Badge badge = beanMapperService.mapTo(badgeDTO2,Badge.class);
        badgeFacade.create(badgeDTO2);
        verify(badgeService).create(badge);
        verifyNoMoreInteractions(badgeService);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullBadge () {
        badgeFacade.create(null);
    }

    @Test
    public void badgeFindById(){
        Badge badge = beanMapperService.mapTo(badgeDTO1 ,Badge.class);

        when(badgeService.findById(badgeDTO1.getId())).thenReturn(badge);
        when(beanMapperService.mapTo(badge, BadgeDTO.class)).thenReturn(badgeDTO1);
        BadgeDTO badgeDTO = badgeFacade.findById(badgeDTO1.getId());

        verify(badgeService).findById(badgeDTO1.getId());
        verify(beanMapperService).mapTo(badge, BadgeDTO.class);
        assertEquals(badgeDTO,badgeDTO1);
        assertTrue(badgeDTO.getId() == createdBadgeId);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void badgeFindByNullId () {
        badgeFacade.findById(null);
    }

    @Test
    public void deleteCreatedBadge() {
        assertEquals((long)badgeDTO1.getId(), createdBadgeId);
        when(badgeService.findById(createdBadgeId)).thenReturn(badge1);

        badgeFacade.delete(badgeDTO1);
        verify(badgeService).findById(badgeDTO1.getId());
        verify(badgeService).delete(beanMapperService.mapTo(badgeDTO1, Badge.class));
        verifyNoMoreInteractions(badgeService);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNonCreatedBadge() {
        when(badgeService.findById(notCreatedBadgeId)).thenReturn(null);
        badgeFacade.delete(badgeDTO2);
    }

    @Test
    public void findAllBadges(){

        List<Badge> badges = Arrays.asList(badge1, badge2);
        List<BadgeDTO> badgeDTOs = Arrays.asList(badgeDTO1, badgeDTO2);

        when(badgeService.findAll()).thenReturn(badges);
        when(beanMapperService.mapTo(badges, BadgeDTO.class)).thenReturn(badgeDTOs);

        List<BadgeDTO> result = badgeFacade.findAll();

        verify(badgeService).findAll();

        assertEquals(result.size(),2);
        assertTrue(result.contains(badgeDTO1));
        assertTrue(result.contains(badgeDTO2));
    }

    @Test
    public void findBadgeByTrainer(){
        List<Badge> badges = Arrays.asList(badge1);
        List<BadgeDTO> badgesDTOs = Arrays.asList(badgeDTO1);

        when(trainerService.findById(trainer1.getId())).thenReturn(trainer1);
        when(badgeService.findByTrainer(trainer1)).thenReturn(badges);
        when(beanMapperService.mapTo(badges, BadgeDTO.class)).thenReturn(badgesDTOs);

        List<BadgeDTO> returned = badgeFacade.findByTrainer(trainer1.getId());

        verify(trainerService).findById(trainer1.getId());
        verify(badgeService).findByTrainer(trainer1);
        assertEquals(badgesDTOs, returned);
    }


    @Test
    public void updateBadge(){
        Badge badge = beanMapperService.mapTo(badgeDTO1, Badge.class);
        when(badgeService.findById(badgeDTO1.getId())).thenReturn(badge1);
        badgeFacade.update(badgeDTO1);
        verify(badgeService).update(badge);
    }
}
