package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.StadiumDao;
import cz.muni.fi.pa165.entity.Stadium;
import cz.muni.fi.pa165.entity.Trainer;
import java.util.Arrays;
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
public class StadiumServiceImplTest {
    
    @Mock
    private StadiumDao stadiumDao;
    
    @InjectMocks
    private StadiumService stadiumService = new StadiumServiceImpl();
    
    private Stadium st1,st2;
    private Trainer t;
    
    @BeforeMethod
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        
        st1 = new Stadium();
        st1.setId(1L);
        st2 = new Stadium();
        st2.setId(2L);
        
        t = new Trainer(3L);
    }
    
    @Test
    public void injectedStadiumDaoTest(){
        Assert.assertNotNull(stadiumDao);
    }
    
    @Test
    public void stadiumServiceCreateTest(){
        stadiumService.create(st1);
        verify(stadiumDao).create(st1);
        verifyNoMoreInteractions(stadiumDao);
    }
    
    @Test(expectedExceptions=IllegalArgumentException.class)
    public void stadiumServiceCreateNullTest(){
        stadiumService.create(null);
    }
    
    @Test
    public void stadiumServiceFindByIdTest(){
        when(stadiumDao.findById(st1.getId())).thenReturn(st1);
        Stadium s = stadiumService.findById(st1.getId());
        verify(stadiumDao).findById(st1.getId());
        Assert.assertEquals(s,st1);        
    }
    
    @Test
    public void stadiumServiceDeleteTest(){
        stadiumService.delete(st1);
        verify(stadiumDao).delete(st1);
        verifyNoMoreInteractions(stadiumDao);
    }
    
    @Test(expectedExceptions=IllegalArgumentException.class)
    public void stadiumServiceDeleteNullTest(){
        stadiumService.delete(null);
    }
    
    @Test
    public void stadiumServiceFindAllTest(){
        when(stadiumDao.findAll()).thenReturn(Arrays.asList(st1,st2));
        List<Stadium> stadiums = stadiumService.findAll();
        
        verify(stadiumDao).findAll();
        Assert.assertEquals(2, stadiums.size());
        Assert.assertTrue(stadiums.contains(st1));
        Assert.assertTrue(stadiums.contains(st2));
    }
    
    @Test
    public void stadiumServiceFindByTrainerTest(){
        when(stadiumDao.findByTrainer(t)).thenReturn(st1);
        Stadium s = stadiumService.findByTrainer(t);
        
        verify(stadiumDao).findByTrainer(t);
        Assert.assertEquals(s,st1);        
    }
    
    @Test(expectedExceptions=IllegalArgumentException.class)
    public void stadiumServiceFindByTrainerNullTest(){
        stadiumService.findByTrainer(null);        
    }
    
    @Test
    public void stadiumServiceUpdateTest(){
        stadiumService.update(st1);
        verify(stadiumDao).update(st1);
        verifyNoMoreInteractions(stadiumDao);
    }
    
    @Test(expectedExceptions=IllegalArgumentException.class)
    public void stadiumServiceUpdateNullTest(){
        stadiumService.update(null);
    }
    
    
    
    
}
