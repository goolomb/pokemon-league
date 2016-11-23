package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.config.ServiceConfig;
import cz.muni.fi.pa165.dao.BadgeDao;
import cz.muni.fi.pa165.entity.Badge;
import cz.muni.fi.pa165.entity.Stadium;
import cz.muni.fi.pa165.entity.Trainer;
import cz.muni.fi.pa165.enums.PokemonType;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

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
    private Trainer trainer1, trainer2;

    @Captor
    ArgumentCaptor<Badge> argumentCaptor;

    @BeforeMethod
    public void initBadges() {
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

        badge2 = new Badge();
        badge2.setOrigin(stadium2);
        badge2.setTrainer(trainer2);
    }

    @BeforeClass
    public void setUp () {
        MockitoAnnotations.initMocks(this);
    }

/*
    @Test
    public void createBadge () {

    }
*/


}
