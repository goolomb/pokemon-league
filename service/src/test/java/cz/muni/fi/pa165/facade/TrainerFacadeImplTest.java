package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.TrainerDTO;
import cz.muni.fi.pa165.entity.Trainer;
import cz.muni.fi.pa165.service.BeanMapperService;
import cz.muni.fi.pa165.service.TrainerService;
import java.util.ArrayList;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Martin Golomb (goolomb)
 */
public class TrainerFacadeImplTest {
        
    @Mock
    private TrainerService trainerService;
    @Mock
    private BeanMapperService beanMapperService;
    
    @InjectMocks
    private TrainerFacade trainerFacade = new TrainerFacadeImpl();
        
    private TrainerDTO t1, t2;
    
    @BeforeMethod
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        
        t1 = new TrainerDTO(1L);
        t2 = new TrainerDTO(2L);
    }
    
    @Test
    public void injectedBeanMapperAndTrainerServiceTest(){
        Assert.assertNotNull(beanMapperService);
        Assert.assertNotNull(trainerService);
    }
    
    @Test
    public void trainerFacadeCreateTest(){
        Trainer t = beanMapperService.mapTo(t1, Trainer.class);
        trainerFacade.create(t1);
        verify(trainerService).create(t);
        verifyNoMoreInteractions(trainerService);
    }
    
    @Test
    public void trainerFacadeFindByIdTest(){
        Trainer t = beanMapperService.mapTo(t1, Trainer.class);
        
        when(trainerService.findById(t1.getId())).thenReturn(t);
        when(beanMapperService.mapTo(t, TrainerDTO.class)).thenReturn(t1);
        TrainerDTO s = trainerFacade.findById(t1.getId());
                
        verify(trainerService).findById(t1.getId());
        verify(beanMapperService).mapTo(t, TrainerDTO.class);
        Assert.assertEquals(t, t1);
        Assert.assertTrue(t.getId() == 1L);
    }
    
    @Test
    public void trainerFacadeDeleteTest(){
        Trainer t = beanMapperService.mapTo(t1, Trainer.class);
        trainerFacade.delete(t1);
        verify(trainerService).findById(t1.getId());
        verify(trainerService).delete(t);        
        verifyNoMoreInteractions(trainerService);
    }
    
    @Test
    public void trainerFacadeFindAllTest(){
        Trainer tr1 = beanMapperService.mapTo(t1, Trainer.class);
        Trainer tr2 = beanMapperService.mapTo(t2, Trainer.class);
        List<Trainer> trainers = new ArrayList<Trainer>();
        trainers.add(tr1);
        trainers.add(tr2);
        List<TrainerDTO> trainersDTO = new ArrayList<TrainerDTO>();
        trainersDTO.add(t1);
        trainersDTO.add(t2);
        
        when(trainerService.findAll()).thenReturn(trainers);
        when(beanMapperService.mapTo(trainers, TrainerDTO.class)).thenReturn(trainersDTO);
        
        List<TrainerDTO> result = trainerFacade.findAll();
        
        verify(trainerService).findAll();
        verify(beanMapperService).mapTo(trainers, TrainerDTO.class);
        Assert.assertEquals(result.size(), 2);
        Assert.assertTrue(result.contains(t1));
        Assert.assertTrue(result.contains(t2));        
    }
    
    @Test
    public void trainerFacadeUpdateTest(){
        Trainer t = beanMapperService.mapTo(t1, Trainer.class);
        trainerFacade.update(t1);
        verify(trainerService).update(t);
        verifyNoMoreInteractions(trainerService);
    }
}
