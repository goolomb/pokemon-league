package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.TrainerDao;
import cz.muni.fi.pa165.entity.Badge;
import cz.muni.fi.pa165.entity.Pokemon;
import cz.muni.fi.pa165.entity.Trainer;
import java.util.Arrays;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Martin Golomb (goolomb)
 */
public class TrainerServiceImplTest {
    @Mock
    private TrainerDao trainerDao;
    
    @InjectMocks
    private TrainerService trainerService = new TrainerServiceImpl();
    
    private Trainer t1, t2;
    
    @BeforeMethod
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        
        t1 = new Trainer(1L);
        t2 = new Trainer(2L);
    }
    
    @Test
    public void injectedTrainerDaoTest(){
        Assert.assertNotNull(trainerDao);
    }
    
    @Test
    public void trainerServiceCreateTest(){
        trainerService.create(t1);
        verify(trainerDao).create(t1);
        verifyNoMoreInteractions(trainerDao);
    }
    
    @Test(expectedExceptions=IllegalArgumentException.class)
    public void trainerServiceCreateNullTest(){
        trainerService.create(null);
    }
    
    @Test
    public void trainerServiceFindByIdTest(){
        when(trainerDao.findById(t1.getId())).thenReturn(t1);
        Trainer t = trainerService.findById(t1.getId());
        verify(trainerDao).findById(t1.getId());
        Assert.assertEquals(t, t1);
    }
    
    @Test
    public void trainerServiceDeleteTest(){
        trainerService.delete(t1);
        verify(trainerDao).delete(t1);
        verifyNoMoreInteractions(trainerDao);
    }
    
    @Test(expectedExceptions=IllegalArgumentException.class)
    public void trainerServiceDeleteNullTest(){
        trainerService.delete(null);
    }
    
    @Test
    public void trainerServiceFindAllTest(){
        when(trainerDao.findAll()).thenReturn(Arrays.asList(t1,t2));
        List<Trainer> trainers = trainerService.findAll();
        
        verify(trainerDao).findAll();
        Assert.assertEquals(2, trainers.size());
        Assert.assertTrue(trainers.contains(t1));
        Assert.assertTrue(trainers.contains(t2));
    }
    
    @Test
    public void trainerServiceUpdateTest(){
        trainerService.update(t1);
        verify(trainerDao).update(t1);
        verifyNoMoreInteractions(trainerDao);
    }
    
    @Test(expectedExceptions=IllegalArgumentException.class)
    public void trainerServiceUpdateNullTest(){
        trainerService.update(null);
    }

}
