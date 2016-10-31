package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.PersistenceApplicationContext;
import cz.muni.fi.pa165.entity.Stadium;
import cz.muni.fi.pa165.entity.Trainer;
import cz.muni.fi.pa165.enums.PokemonType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 * Tests for Stadium Data Access Object
 *
 * @author Martina Minatova
 */

@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class StadiumDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private StadiumDao stadiumDao;
    @Autowired
    private TrainerDao trainerDao;


    @Test
    public void testCreate() {
        Stadium stadium = new Stadium();
        stadium.setCity("Emerald");
        stadium.addType(PokemonType.BUG);

        Assert.assertNull(stadium.getId());

        stadiumDao.create(stadium);
        Stadium byId = stadiumDao.findById(stadium.getId());
        Assert.assertEquals(stadium, byId);
    }

    @Test
    public void testDelete() {
        Stadium stadium = new Stadium();
        stadium.setCity("Pallet");
        stadium.addType(PokemonType.DARK);
        Stadium toBeRemoved = new Stadium();
        toBeRemoved.setCity("Viridian");
        toBeRemoved.addType(PokemonType.BUG);
        stadiumDao.create(stadium);
        stadiumDao.create(toBeRemoved);

        Assert.assertTrue(stadiumDao.findAll().size() == 2);

        stadiumDao.delete(toBeRemoved);

        Assert.assertTrue(stadiumDao.findAll().size() == 1);
        Assert.assertEquals(stadium, stadiumDao.findById(stadium.getId()));
        Assert.assertNull(stadiumDao.findById(toBeRemoved.getId()));

    }

    @Test
    public void testFindAll() {
        Stadium stadium1 = new Stadium();
        stadium1.setCity("Lavender");
        stadium1.addType(PokemonType.DRAGON);
        Stadium stadium2 = new Stadium();
        stadium2.setCity("Fuchsia");
        stadium2.addType(PokemonType.FAIRY);

        stadiumDao.create(stadium1);
        stadiumDao.create(stadium2);

        List<Stadium> all = stadiumDao.findAll();

        Assert.assertTrue(all.size() == 2);
        Assert.assertTrue(all.contains(stadium1));
        Assert.assertTrue(all.contains(stadium2));
    }

    @Test
    public void testFindByTrainer() {
        Trainer trainer = new Trainer();
        trainer.setFirstName("Ash");
        trainer.setLastName("Ketchun");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000,10,30); //YYYY,MM,DD
        trainer.setBirthDate(calendar.getTime());
        trainerDao.create(trainer);
        
        Stadium stadium1 = new Stadium();
        stadium1.setCity("Saffron");
        stadium1.addType(PokemonType.FIRE);
        Stadium stadium2 = new Stadium();
        stadium2.setCity("Pewter");
        stadium2.setLeader(trainer);
        stadium2.addType(PokemonType.FAIRY);

        stadiumDao.create(stadium1);
        stadiumDao.create(stadium2);

        Stadium byTrainer = stadiumDao.findByTrainer(trainer);
        Assert.assertEquals(stadium2,byTrainer);
    }

    @Test
    public void testUpdate() {
        Stadium stadium1 = new Stadium();
        stadium1.setCity("Red");
        stadium1.addType(PokemonType.FAIRY);

        Stadium stadium2 = new Stadium();
        stadium2.setCity("Blue");
        stadium2.addType(PokemonType.FIRE);

        stadiumDao.create(stadium1);
        stadiumDao.create(stadium2);

        stadium1.setCity("Yellow");

        stadiumDao.update(stadium1);

        Assert.assertEquals(stadium2, stadiumDao.findById(stadium2.getId()));
        Stadium changedStadium = stadiumDao.findById(stadium1.getId());

        Assert.assertEquals(stadium1.getCity(), changedStadium.getCity());
        Assert.assertEquals(stadium1, changedStadium);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testCreateStadiumWithNullCity() {
        Stadium stadium = new Stadium();
        stadium.setCity(null);
        stadium.addType(PokemonType.FIRE);

        stadiumDao.create(stadium);
    }
    //Test should work
    
//    @Test(expectedExceptions = ConstraintViolationException.class)
//    public void testCreateStadiumNoType() {
//        Stadium stadium = new Stadium();
//        stadium.setCity("Pebble");
//
//        stadiumDao.create(stadium);
//    }

    @Test(expectedExceptions = PersistenceException.class)
    public void testCreateStadiumUniqueness() {
        Stadium stadium1 = new Stadium();
        stadium1.setCity("Black");
        stadium1.addType(PokemonType.DARK);
        Stadium stadium2 = new Stadium();
        stadium2.setCity("Black");
        stadium2.addType(PokemonType.GHOST);

        stadiumDao.create(stadium1);
        stadiumDao.create(stadium2);
    }


}