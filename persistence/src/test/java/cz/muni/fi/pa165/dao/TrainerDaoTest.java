package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.PersistenceApplicationContext;
import cz.muni.fi.pa165.entity.Pokemon;
import cz.muni.fi.pa165.entity.Stadium;
import cz.muni.fi.pa165.entity.Trainer;
import cz.muni.fi.pa165.enums.PokemonType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marek Perichta
 */

@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TrainerDaoTest extends AbstractTestNGSpringContextTests {
   
    @Autowired
    TrainerDao trainerDao;
    
    @Autowired
    PokemonDao pokemonDao;
    
    @Autowired
    private StadiumDao stadiumDao;
    
    private Pokemon pokemon;
    private Trainer trainer;
    private Trainer trainer1;
    private Trainer trainer2;
    private Stadium stadium;
    
    @BeforeMethod
    public void setUp () throws ParseException {
        SimpleDateFormat date = new SimpleDateFormat("dd-mm-yyyy");
        
        trainer = new Trainer();
        trainer.setFirstName("Ash");
        trainer.setLastName("Ketchum");
        trainer.setBirthDate(date.parse("15-10-1990"));
        
        stadium = new Stadium();
        stadium.setCity("Black City");
        stadium.addType(PokemonType.DRAGON);
        stadium.setLeader(trainer);

        pokemon = new Pokemon();
        pokemon.setName("Pikachu");
        pokemon.setLevel(1);
        pokemon.setNickname("pika");
        pokemon.addType(PokemonType.NORMAL);
        pokemon.addType(PokemonType.ELECTRIC);
             
        trainer1 = new Trainer();
        trainer1.setFirstName("Misty");
        trainer1.setLastName("Twisty");
        trainer1.setBirthDate(date.parse("16-10-1991"));
        trainer1.addPokemon(pokemon);
        
        pokemonDao.create(pokemon);
        stadiumDao.create(stadium);
    }
    
    @Test
    public void testCreateTrainer() {
        Assert.assertNull(trainer.getId());
        trainerDao.create(trainer);
        Assert.assertNotNull(trainer.getId());
    }
    
    @Test
    public void testUpdateTrainer() {
        trainerDao.create(trainer);
        Trainer updated = trainerDao.update(trainer.setFirstName("Brock"));
        Assert.assertEquals(updated.getFirstName(),"Brock");
    }
    
    @Test
    public void testFindByIdTrainer() {
        trainerDao.create(trainer);
        Assert.assertEquals(trainerDao.findById(trainer.getId()),trainer);
    }
    
    @Test
    public void testDeleteTrainer() {
        trainer2 = new Trainer();
        trainer2.setFirstName("Honza");
        trainer2.setLastName("Ponza");
        trainer2.setBirthDate(new Date(new Long("1000000")));
        trainerDao.create(trainer);
        trainerDao.create(trainer1);
        trainerDao.create(trainer2);
        
        Assert.assertTrue(trainerDao.findAll().size() == 3);  
       
        trainerDao.delete(trainer2);
        Assert.assertTrue(trainerDao.findAll().size() == 2);
        Assert.assertNull(trainerDao.findById(trainer2.getId()));
    }
    
    @Test
    public void testFindAllBadges () {
        trainerDao.create(trainer);
        trainerDao.create(trainer1);
        
        List<Trainer> all = trainerDao.findAll();
        Assert.assertTrue(all.size() == 2);
        Assert.assertTrue(all.contains(trainer));
        Assert.assertTrue(all.contains(trainer1));
    }
   
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void firstNameCannotBeNull () {
        Trainer trainer3 = new Trainer();
        trainer3.setLastName("Ponza");
        trainer3.setBirthDate(new Date(new Long("1000000")));
        trainerDao.create(trainer3);
    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void lastNameCannotBeNull () {
        Trainer trainer3 = new Trainer();
        trainer3.setFirstName("Honza");
        trainer3.setBirthDate(new Date(new Long("1000000")));
        trainerDao.create(trainer3);
    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void birthdayCannotBeNull () {
        Trainer trainer3 = new Trainer();
        trainer3.setFirstName("Honza");
        trainer3.setLastName("Ponza");
        trainer3.setBirthDate(null);
        trainerDao.create(trainer3);
    }
}
