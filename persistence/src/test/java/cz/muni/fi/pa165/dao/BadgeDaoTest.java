package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.PersistenceApplicationContext;
import cz.muni.fi.pa165.entity.Badge;
import cz.muni.fi.pa165.entity.Stadium;
import cz.muni.fi.pa165.entity.Trainer;
import cz.muni.fi.pa165.enums.PokemonType;
import java.util.Date;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for Badge Dao
 *
 * @author Martin Golomb
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class BadgeDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private BadgeDao badgeDao;
    
    @Autowired
    private TrainerDao trainerDao;
    
    @Autowired
    private StadiumDao stadiumDao;

    private Badge badge1;
    private Badge badge2;
    private Badge badge3;
    private Stadium stadium1;
    private Stadium stadium2;
    private Stadium stadium3;
    private Trainer trainer1;
    private Trainer trainer2;
    private Trainer trainer3;

    @BeforeMethod
    public void setUp() {
        trainer1 = new Trainer().
                setFirstName("firstName").
                setLastName("lastName").
                setBirthDate(new Date(new Long("1000000")));

        trainer2 = new Trainer().
                setFirstName("firstName2").
                setLastName("lastName2").
                setBirthDate(new Date(new Long("2000000")));

        trainer3 = new Trainer().
                setFirstName("firstName3").
                setLastName("lastName3").
                setBirthDate(new Date(new Long("3000000")));

        stadium1 = new Stadium();
        stadium1.setCity("city1");
        stadium1.addType(PokemonType.DRAGON); //opraveno
        stadium1.setLeader(trainer1);

        stadium2 = new Stadium();
        stadium2.setCity("city2");
        stadium2.addType(PokemonType.DARK); //opraveno
        stadium2.setLeader(trainer2);

        stadium3 = new Stadium();
        stadium3.setCity("city3");
        stadium3.addType(PokemonType.FIRE); //opraveno
        stadium3.setLeader(trainer3);

        badge1 = new Badge();
        badge1.setOrigin(stadium1);
        badge1.setTrainer(trainer1);

        badge2 = new Badge();
        badge2.setOrigin(stadium2);
        badge2.setTrainer(trainer2);

        badge3 = new Badge();
        badge3.setOrigin(stadium3);
        badge3.setTrainer(trainer3);

        trainerDao.create(trainer1);
        trainerDao.create(trainer2);
        trainerDao.create(trainer3);
        
        stadiumDao.create(stadium1);
        stadiumDao.create(stadium2);
        stadiumDao.create(stadium3);
        
        
        // zakomentovano, protoze bys zacinal kazdou testovaci metodu uz se 3 vytvorenymi badge
//        badgeDao.addBadge(badge1);
//        System.out.println(badgeDao.findBadgeById(badge1.getId()).getOrigin().getCity());
//        badgeDao.addBadge(badge2);
//        badgeDao.addBadge(badge3);
    }

//    @Test(expectedExceptions = ConstraintViolationException.class)
//    public void testAddBadgeWithNullOrigin() {
//        badge1.setOrigin(null);
//        badgeDao.addBadge(badge1);
//    }

//    @Test(expectedExceptions = ConstraintViolationException.class)
//    public void testAddBadgeWithNullTrainer() {
//        badge1.setTrainer(null);
//        badgeDao.addBadge(badge1);
//    }

    @Test
    public void testAddBadge() {
        badgeDao.addBadge(badge1);
        assertTrue(badgeDao.findAllBadges().contains(badge1));
        assertTrue(badgeDao.findAllBadges().size() == 1);

        badgeDao.addBadge(badge2);
        assertTrue(badgeDao.findAllBadges().contains(badge1));
        assertTrue(badgeDao.findAllBadges().size() == 2);

    }

    @Test
    public void testUpdate() {
        badgeDao.addBadge(badge1);
        badge1.setOrigin(stadium2);
        badgeDao.updateBadge(badge1);

//        assertEquals(badge2, badgeDao.findBadgeById(badge2.getId()));

        assertEquals(badgeDao.findBadgeById(badge1.getId()).getOrigin(), stadium2);
        assertEquals(badgeDao.findBadgeById(badge1.getId()).getTrainer(), trainer1);
    }

    @Test
    public void testRemove() {
        badgeDao.addBadge(badge1);
        assertTrue(badgeDao.findAllBadges().contains(badge1));
        assertTrue(badgeDao.findAllBadges().size() == 1);

        badgeDao.deleteBadge(badge1);
        assertTrue(badgeDao.findAllBadges().isEmpty());
    }

    @Test
    public void testFindById() {
        badgeDao.addBadge(badge1);
        assertDeepEquals(badgeDao.findBadgeById(badge1.getId()), badge1);
    }

    @Test
    public void testFindBadgeByTrainer() {
        badgeDao.addBadge(badge1);
        assertTrue(badgeDao.findBadgeByTrainer(badge1.getTrainer())
                .contains(badge1));
        
        badgeDao.addBadge(badge2);
        assertTrue(badgeDao.findBadgeByTrainer(badge2.getTrainer())
                .contains(badge2));
    }

    @Test
    public void testFindAll() {
        badgeDao.addBadge(badge1);
        badgeDao.addBadge(badge2);
        assertTrue(badgeDao.findAllBadges().contains(badge1));
        assertTrue(badgeDao.findAllBadges().contains(badge2));
        assertTrue(badgeDao.findAllBadges().size() == 2);
    }

    private void assertDeepEquals(Badge badge, Badge badge1) {
        assertEquals(badge, badge1);
        assertEquals(badge.getId(), badge1.getId());
        assertEquals(badge.getOrigin(), badge1.getOrigin());
        assertEquals(badge.getTrainer(), badge1.getTrainer());
    }
}
