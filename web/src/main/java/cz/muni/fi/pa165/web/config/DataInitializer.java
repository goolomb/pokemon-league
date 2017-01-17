package cz.muni.fi.pa165.web.config;

import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.entity.Badge;
import cz.muni.fi.pa165.entity.Pokemon;
import cz.muni.fi.pa165.entity.Stadium;
import cz.muni.fi.pa165.entity.Trainer;
import cz.muni.fi.pa165.entity.User;
import cz.muni.fi.pa165.enums.PokemonType;
import cz.muni.fi.pa165.service.BadgeService;
import cz.muni.fi.pa165.service.PokemonService;
import cz.muni.fi.pa165.service.StadiumService;
import cz.muni.fi.pa165.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

/**
 * @author Martina Minatova
 */
@Component
@Transactional
public class DataInitializer {
    private Trainer trainer1;
    private Trainer trainer2;
    private Trainer leader1;
    private Trainer leader2;
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private Pokemon pokemon3;
    private Pokemon pokemon4;
    private Badge badge1;
    private Badge badge2;
    private Stadium stadium1;
    private Stadium stadium2;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private BadgeService badgeService;

    @Autowired
    private StadiumService stadiumService;

    @Autowired
    private UserDao userDao;
    
    public void loadData()  {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRole("ROLE_ADMIN");
        userDao.create(admin);
        
        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        user.setRole("ROLE_USER");
        userDao.create(user);
        
        trainer1 = new Trainer();
        trainer1.setFirstName("Ash");
        trainer1.setLastName("Ketchum");
        trainer1.setBirthDate(Date.valueOf("1990-1-1"));

        trainer2 = new Trainer();
        trainer2.setFirstName("Misty");
        trainer2.setLastName("Twisty");
        trainer2.setBirthDate(Date.valueOf("1993-4-3"));

        leader1 = new Trainer();
        leader1.setFirstName("Blanka");
        leader1.setLastName("Matragi");
        leader1.setBirthDate(Date.valueOf("1980-11-8"));

        leader2 = new Trainer();
        leader2.setFirstName("Honza");
        leader2.setLastName("Novak");
        leader2.setBirthDate(Date.valueOf("2000-3-7"));


        trainerService.create(trainer1);
        trainerService.create(trainer2);
        trainerService.create(leader1);
        trainerService.create(leader2);

        stadium1 = new Stadium();
        stadium1.setCity("Black City");
        stadium1.addType(PokemonType.DRAGON);
        stadium1.setLeader(leader1);

        stadium2 = new Stadium();
        stadium2.setCity("White City");
        stadium2.addType(PokemonType.FAIRY);
        stadium2.setLeader(leader2);

        stadiumService.create(stadium1);
        stadiumService.create(stadium2);

        pokemon1 = new Pokemon();
        pokemon1.setName("Pikachu");
        pokemon1.setLevel(1);
        pokemon1.setNickname("pika");
        pokemon1.addType(PokemonType.NORMAL);
        pokemon1.addType(PokemonType.ELECTRIC);
        pokemon1.setTrainer(trainer1);

        pokemon2 = new Pokemon();
        pokemon2.setName("Bulbasaur");
        pokemon2.setLevel(10);
        pokemon2.setNickname("bulbik");
        pokemon2.addType(PokemonType.GRASS);
        
        pokemon3 = new Pokemon();
        pokemon3.setName("Charizard");
        pokemon3.setLevel(5);
        pokemon3.setNickname("chari");
        pokemon3.addType(PokemonType.FIRE);
        pokemon3.addType(PokemonType.DRAGON);
        pokemon3.setTrainer(leader1);
        
        pokemon4 = new Pokemon();
        pokemon4.setName("Pidgey");
        pokemon4.setLevel(15);
        pokemon4.setNickname("poppy");
        pokemon4.addType(PokemonType.NORMAL);
        pokemon4.addType(PokemonType.FLYING);
        pokemon4.setTrainer(leader2);

        pokemonService.create(pokemon1);
        pokemonService.create(pokemon2);
        pokemonService.create(pokemon3);
        pokemonService.create(pokemon4);

        stadiumService.giveBadgeToTrainer(stadium1, trainer1);
        stadiumService.giveBadgeToTrainer(stadium2, trainer2);

    }
}
