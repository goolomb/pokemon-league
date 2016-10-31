package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.PersistenceApplicationContext;
import cz.muni.fi.pa165.entity.Badge;
import cz.muni.fi.pa165.entity.Stadium;
import cz.muni.fi.pa165.entity.Trainer;
import cz.muni.fi.pa165.enums.PokemonType;
import java.util.Date;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
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

    private Badge badge1, badge2;
    private Stadium stadium1, stadium2;
    private Trainer trainer1, trainer2;

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

        stadium1 = new Stadium();
        stadium1.setCity("city1");
        stadium1.addType(PokemonType.DRAGON); //opraveno
        stadium1.setLeader(trainer1);

        stadium2 = new Stadium();
        stadium2.setCity("city2");
        stadium2.addType(PokemonType.DARK); //opraveno
        stadium2.setLeader(trainer2);

        badge1 = new Badge();
        badge1.setOrigin(stadium1);
        badge1.setTrainer(trainer1);

        badge2 = new Badge();
        badge2.setOrigin(stadium2);
        badge2.setTrainer(trainer2);

        trainerDao.create(trainer1);
        trainerDao.create(trainer2);
        
        stadiumDao.create(stadium1);
        stadiumDao.create(stadium2);
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void testAddBadgeWithNullOrigin() {
        badge1.setOrigin(null);
        badgeDao.addBadge(badge1);
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void testAddBadgeWithNullTrainer() {
        badge1.setTrainer(null);
        badgeDao.addBadge(badge1);
    }

    @Test
    public void testAddBadge() {
        assertNull(badge1.getId());
        badgeDao.addBadge(badge1);
        assertTrue(badgeDao.findAllBadges().contains(badge1));
        assertTrue(badgeDao.findAllBadges().size() == 1);
    }

    @Test
    public void testUpdate() {
        badgeDao.addBadge(badge1);
        badge1.setOrigin(stadium2);
        Badge b = badgeDao.updateBadge(badge1);

        assertEquals(badgeDao.findBadgeById(badge1.getId()), b);
    }

    @Test
    public void testRemove() {
        badgeDao.addBadge(badge1);
        badgeDao.addBadge(badge2);
        assertTrue(badgeDao.findAllBadges().size() == 2);

        badgeDao.deleteBadge(badge1);
        assertTrue(badgeDao.findAllBadges().size() == 1);
        assertTrue(badgeDao.findAllBadges().contains(badge2));
        assertNull(badgeDao.findBadgeById(badge1.getId()));
    }

    @Test
    public void testFindById() {
        badgeDao.addBadge(badge1);
        assertDeepEquals(badgeDao.findBadgeById(badge1.getId()), badge1);
    }

    @Test
    public void testFindBadgeByTrainer() {
        badgeDao.addBadge(badge1);
        assertTrue(badgeDao.findBadgeByTrainer(trainer1)
                .contains(badge1));
        
        badge2.setTrainer(trainer1);
        badgeDao.addBadge(badge2);
        assertTrue(badgeDao.findBadgeByTrainer(trainer1).size() == 2);
        assertTrue(badgeDao.findBadgeByTrainer(trainer1)
                .contains(badge2));
    }

    @Test
    public void testFindAll() {
        badgeDao.addBadge(badge1);
        badgeDao.addBadge(badge2);
        
        List<Badge> all = badgeDao.findAllBadges();
        assertTrue(all.size() == 2);
        assertTrue(all.contains(badge1));
        assertTrue(all.contains(badge2));
    }

    private void assertDeepEquals(Badge badge, Badge badge1) {
        assertEquals(badge, badge1);
        assertEquals(badge.getId(), badge1.getId());
        assertEquals(badge.getOrigin(), badge1.getOrigin());
        assertEquals(badge.getTrainer(), badge1.getTrainer());
    }
}
