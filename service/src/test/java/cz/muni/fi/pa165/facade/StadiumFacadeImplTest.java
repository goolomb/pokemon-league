package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.StadiumDTO;
import cz.muni.fi.pa165.entity.Stadium;
import cz.muni.fi.pa165.entity.Trainer;
import cz.muni.fi.pa165.service.BeanMapperService;
import cz.muni.fi.pa165.service.StadiumService;
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
 * @author Jakub Holy (saintjackie)
 */
public class StadiumFacadeImplTest {
    
    @Mock
    private StadiumService stadiumService;
    
    @Mock
    private TrainerService trainerService;
    @Mock
    private BeanMapperService beanMapperService;
    
    @InjectMocks
    private StadiumFacade stadiumFacade = new StadiumFacadeImpl();
        
    private StadiumDTO st1,st2;
    private Trainer t1;
    
    @BeforeMethod
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        
        st1 = new StadiumDTO();
        st1.setId(1L);
        st2 = new StadiumDTO();
        st2.setId(2L);
        
        t1 = new Trainer(1L);
    }
    
    @Test
    public void injectedBeanMapperAndStadiumServiceTest(){
        Assert.assertNotNull(beanMapperService);
        Assert.assertNotNull(stadiumService);
    }
    
    @Test
    public void stadiumFacadeCreateTest(){
        Stadium st = beanMapperService.mapTo(st1,Stadium.class);
        stadiumFacade.create(st1);
        verify(stadiumService).create(st);
        verifyNoMoreInteractions(stadiumService);
    }
    
    @Test
    public void stadiumFacadeFindByIdTest(){
        Stadium st = beanMapperService.mapTo(st1,Stadium.class);
        
        when(stadiumService.findById(st1.getId())).thenReturn(st);
        when(beanMapperService.mapTo(st, StadiumDTO.class)).thenReturn(st1);
        StadiumDTO s = stadiumFacade.findById(st1.getId());
                
        verify(stadiumService).findById(st1.getId());
        verify(beanMapperService).mapTo(st, StadiumDTO.class);
        Assert.assertEquals(s,st1);
        Assert.assertTrue(s.getId() == 1L);
    }
    
    @Test
    public void stadiumFacadeDeleteTest(){
        Stadium st = beanMapperService.mapTo(st1,Stadium.class);
        stadiumFacade.delete(st1.getId());
        verify(stadiumService).findById(st1.getId());
        verify(stadiumService).delete(st);        
        verifyNoMoreInteractions(stadiumService);
    }
    
    @Test
    public void stadiumFacadeFindAllTest(){
        Stadium s1 = beanMapperService.mapTo(st1, Stadium.class);
        Stadium s2 = beanMapperService.mapTo(st2, Stadium.class);
        List<Stadium> stadiums = new ArrayList<Stadium>();
        stadiums.add(s1);
        stadiums.add(s2);
        List<StadiumDTO> stadiumsDTO = new ArrayList<StadiumDTO>();
        stadiumsDTO.add(st1);
        stadiumsDTO.add(st2);
        
        when(stadiumService.findAll()).thenReturn(stadiums);
        when(beanMapperService.mapTo(stadiums, StadiumDTO.class)).thenReturn(stadiumsDTO);
        
        List<StadiumDTO> result = stadiumFacade.findAll();
        
        verify(stadiumService).findAll();
        verify(beanMapperService).mapTo(stadiums, StadiumDTO.class);
        Assert.assertEquals(result.size(),2);
        Assert.assertTrue(result.contains(st1));
        Assert.assertTrue(result.contains(st2));        
    }
    
    @Test
    public void stadiumFacadeFindByTrainerTest(){
        Stadium st = beanMapperService.mapTo(st1,Stadium.class);
        when(trainerService.findById(t1.getId())).thenReturn(t1);
        when(stadiumService.findByTrainer(t1)).thenReturn(st);
        when(beanMapperService.mapTo(st, StadiumDTO.class)).thenReturn(st1);
        
        StadiumDTO s = stadiumFacade.findByTrainer(t1.getId());
        
        verify(trainerService).findById(t1.getId());
        verify(stadiumService).findByTrainer(t1);
        verify(beanMapperService).mapTo(st,StadiumDTO.class);
        Assert.assertEquals(s, st1);
    }
    
    @Test
    public void stadiumFacadeUpdateTest(){
        Stadium st = beanMapperService.mapTo(st1,Stadium.class);
        stadiumFacade.update(st1);
        verify(stadiumService).update(st);
        verifyNoMoreInteractions(stadiumService);
    }
    
    
    
}
