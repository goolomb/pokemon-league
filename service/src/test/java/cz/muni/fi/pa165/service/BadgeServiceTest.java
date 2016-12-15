package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.web.config.ServiceConfig;
import cz.muni.fi.pa165.dao.BadgeDao;
import cz.muni.fi.pa165.entity.Badge;
import cz.muni.fi.pa165.entity.Stadium;
import cz.muni.fi.pa165.entity.Trainer;
import cz.muni.fi.pa165.enums.PokemonType;
import cz.muni.fi.pa165.exception.PokemonLeagueDataAccessException;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityExistsException;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

/**
 *
 * Created by Marek Perichta.
 **/

@ContextConfiguration(classes = ServiceConfig.class)
public class BadgeServiceTest {
    @Mock
    private BadgeDao badgeDao;

    @InjectMocks
    private BadgeService badgeService = new BadgeServiceImpl();

    private Badge badge1, badge2;
    private Stadium stadium1, stadium2;
    private Trainer trainer1, trainer2, notCreatedTrainer;

    private long createdBadgeId1 = 12;
    private long idForCreation = 13;
    private long notCreatedId = 123;

    @BeforeMethod
    public void initBadges() {
        notCreatedTrainer = new Trainer().
                setFirstName("not").
                setLastName("created").
                setBirthDate(new Date(new Long("5000000")));
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
        stadium1.addType(PokemonType.DRAGON);
        stadium1.setLeader(trainer1);

        stadium2 = new Stadium();
        stadium2.setCity("city2");
        stadium2.addType(PokemonType.DARK);
        stadium2.setLeader(trainer2);

        badge1 = new Badge();
        badge1.setOrigin(stadium1);
        badge1.setTrainer(trainer1);
        badge1.setId(createdBadgeId1);

        badge2 = new Badge();
        badge2.setOrigin(stadium2);
        badge2.setTrainer(trainer2);

        doAnswer(new Answer<List<Badge>>() {
            public List<Badge> answer(InvocationOnMock invocation) {
                Trainer trainer = (Trainer)invocation.getArguments()[0];
                if (trainer == null)
                    throw new IllegalArgumentException("Null trainer.");

                //found badges for trainer
                if (trainer.equals(trainer1))
                    return Arrays.asList(badge1);
                return null;
            }
        }).when(badgeDao).findByTrainer(any(Trainer.class));

        doAnswer(new Answer<Badge>() {
            public Badge answer(InvocationOnMock invocation) {
                Long badgeId = (Long)invocation.getArguments()[0];
                if (badgeId == null)
                    throw new IllegalArgumentException("Id being looked for is null.");

                //found badge for id
                if (badgeId == createdBadgeId1)
                    return badge1;

                return null;
            }
        }).when(badgeDao).findById(any(Long.class));

        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                Badge badge = (Badge)invocation.getArguments()[0];

                if (badge == null)
                    throw new IllegalArgumentException("Badge to create is null.");

                if (badge.getId() != null && badge.getId() == createdBadgeId1 ) {
                    throw new EntityExistsException("Entity already exists.");
                }

                if (badge.getTrainer() == null || badge.getOrigin() == null)
                    throw new ConstraintViolationException("Trainer or origin is null", null);
                badge.setId((idForCreation));
                return null;
            }
        }).when(badgeDao).create(any(Badge.class));

        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
            Badge badge = (Badge)invocation.getArguments()[0];
                if (badge == null)
                    throw new IllegalArgumentException("Cannot delete null badge.");

                if (badge.getId().equals(notCreatedId))
                    throw new IllegalArgumentException("Entity does not exist");

                if (badge.getId().equals(createdBadgeId1))
                    return null;
                return null;
            }
        }).when(badgeDao).delete(any(Badge.class));

        doAnswer(new Answer<Badge>() {
            public Badge answer(InvocationOnMock invocation) {
                Badge badge = (Badge)invocation.getArguments()[0];
                if (badge == null) {
                    throw new IllegalArgumentException("Badge to update is null.");
                }

                if (badge.getTrainer() == null || badge.getOrigin() == null)
                    throw new ConstraintViolationException("Trainer or origin is null", null);

                if (badge.getId() == null)
                    badge.setId(idForCreation);
                return badge;
            }
        }).when(badgeDao).update(any(Badge.class));


    }

    @BeforeClass
    public void setUp () {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void findById() {
        Badge badge = badgeService.findById(createdBadgeId1);
        verify(badgeDao).findById(createdBadgeId1);
        assertEquals(badge, badge1);
    }

    @Test(expectedExceptions = PokemonLeagueDataAccessException.class)
    public void findByNullId () {
        badgeService.findById(null);
    }

    @Test
    public void findNotCreatedById () {
        Badge badge = badgeService.findById(notCreatedId);
        assertNull(badge);
    }

    @Test
    public void findAll () {
        when(badgeDao.findAll()).thenReturn(Arrays.asList(badge1));

        List<Badge> badges = badgeService.findAll();
        assertEquals(badges.size(), 1);
        assertEquals(badges.get(0), badge1);
    }

    @Test
    public void findByTrainer () {
        List<Badge> badges = badgeService.findByTrainer(trainer1);
        verify(badgeDao).findByTrainer(trainer1);
        assertEquals(badges.size(), 1);
        assertEquals(badges.get(0), badge1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByNullTrainer () {
        badgeService.findByTrainer(null);
    }

    @Test
    public void findByNotCreatedTrainer () {
        Trainer trainer = new Trainer();
        List<Badge> badges = badgeService.findByTrainer(trainer);
        assertNull(badges);
    }

    @Test
    public void createBadge() {
        badgeService.create(badge2);
        verify(badgeDao).create(badge2);
        assertNotNull(badge2);
        assertEquals((long) badge2.getId(), idForCreation);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullBadge() {
        badgeDao.create(null);
    }

    @Test(expectedExceptions = PokemonLeagueDataAccessException.class)
    public void createExistingBadge () {
        badgeService.create(badge1);
    }

    @Test(expectedExceptions = PokemonLeagueDataAccessException.class)
    public void createBadgeNullTrainer () {
        badge2.setTrainer(null);
        badgeService.create(badge2);
    }

    @Test(expectedExceptions = PokemonLeagueDataAccessException.class)
    public void createBadgeNullOrigin () {
        badge2.setOrigin(null);
        badgeService.create(badge2);
    }

    @Test
    public void updateBadge () {
        Badge badge = badgeService.update(badge1);
        verify(badgeDao).update(badge1);
        assertEquals(badge, badge1);
    }

    @Test
    public void updateBadgeTrainer () {
        badge1.setTrainer(trainer2);
        Badge badge = badgeService.update(badge1);
        verify(badgeDao).update(badge1);
        assertEquals(badge, badge1);
        assertEquals(badge.getId(), badge1.getId());
    }

    @Test
    public void updateBadgeOrigin () {
        badge1.setOrigin(stadium2);
        Badge badge = badgeService.update(badge1);
        verify(badgeDao).update(badge1);
        assertEquals(badge, badge1);
        assertEquals(badge.getId(), badge1.getId());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullBadge () {
        badgeService.update(null);
    }
}
