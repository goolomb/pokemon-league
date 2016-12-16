package cz.muni.fi.pa165.web.config;

import cz.muni.fi.pa165.entity.Badge;
import cz.muni.fi.pa165.entity.Pokemon;
import cz.muni.fi.pa165.entity.Stadium;
import cz.muni.fi.pa165.entity.Trainer;
import cz.muni.fi.pa165.enums.PokemonType;
import cz.muni.fi.pa165.service.BadgeService;
import cz.muni.fi.pa165.service.PokemonService;
import cz.muni.fi.pa165.service.StadiumService;
import cz.muni.fi.pa165.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
    private Badge badge1;
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

    public void loadData()  {
        //TODO: security settings

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

        pokemon2 = new Pokemon();
        pokemon2.setName("Bulbasaur");
        pokemon2.setLevel(10);
        pokemon2.setNickname("bulbik");
        pokemon2.addType(PokemonType.GRASS);

        pokemonService.create(pokemon1);
        pokemonService.create(pokemon2);

        badge1 = new Badge();
        badge1.setOrigin(stadium1);
        badge1.setTrainer(trainer1);

        badgeService.create(badge1);
    }
}
