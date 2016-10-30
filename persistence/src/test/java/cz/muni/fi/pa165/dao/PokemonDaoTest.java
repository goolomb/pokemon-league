package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.PersistenceApplicationContext;
import cz.muni.fi.pa165.entity.Pokemon;
import cz.muni.fi.pa165.entity.Trainer;
import cz.muni.fi.pa165.enums.PokemonType;
import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jakub Holy
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PokemonDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    private PokemonDao pokemonDao;
    
    @Autowired
    private TrainerDao trainerDao;

    private Pokemon p1, p2;
    private Trainer t;

    @BeforeMethod
    public void setUp() {
        p1 = new Pokemon();

        p1.setName("Pikachu");
        p1.setLevel(3);
        p1.setNickname("pika");
        p1.addType(PokemonType.NORMAL);
        p1.addType(PokemonType.ELECTRIC);

        p2 = new Pokemon();

        p2.setName("Charmander");
        p2.setLevel(5);
        p2.setNickname("charmi");
        p2.addType(PokemonType.FIGHTING);
        p2.addType(PokemonType.FIRE);

        t = new Trainer();
        t.setFirstName("Jake");
        t.setLastName("Peralta");
        t.setBirthDate(Date.valueOf("1991-7-9"));
    }

    /**
     * Test of findById method, of class PokemonDao.
     */
    @Test
    public void testFindById() {
        pokemonDao.create(p1);
        pokemonDao.create(p2);        
        
        Pokemon p = pokemonDao.findById(p1.getId());

        Assert.assertEquals(p1, p);

    }

    /**
     * Test of create method, of class PokemonDao.
     */
    @Test
    public void testCreate() {
        pokemonDao.create(p2);
        Pokemon p = pokemonDao.findById(p2.getId());

        Assert.assertEquals(p2, p);
    }

    /**
     * Test of delete method, of class PokemonDao.
     */
    @Test
    public void testDelete() {
        pokemonDao.create(p1);
        pokemonDao.create(p2);
        pokemonDao.delete(p2);
        List<Pokemon> list = pokemonDao.findAll();
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(p1, list.get(0));
    }

    /**
     * Test of findAll method, of class PokemonDao.
     */
    @Test
    public void testFindAll() {
        pokemonDao.create(p1);
        pokemonDao.create(p2);
        List<Pokemon> list = pokemonDao.findAll();

        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(p1));
        Assert.assertTrue(list.contains(p2));
    }

    /**
     * Test of findByTrainer method, of class PokemonDao.
     */
    @Test
    public void testFindByTrainer() {
        trainerDao.create(t);
        p1.setTrainer(t);
        pokemonDao.create(p1);
        pokemonDao.create(p2);
        List<Pokemon> list = pokemonDao.findByTrainer(t);

        Assert.assertEquals(1, list.size());
        Assert.assertEquals(p1,list.get(0));
    }

    /**
     * Test of update method, of class PokemonDao.
     */
    @Test
    public void testUpdate() {
        pokemonDao.create(p1);
        pokemonDao.create(p2);
        p1.setName("Raichu");
        pokemonDao.update(p1);
        Pokemon p = pokemonDao.findById(p1.getId());

        Assert.assertEquals(p1, p);
        Assert.assertEquals("Raichu", p.getName());
    }

}
