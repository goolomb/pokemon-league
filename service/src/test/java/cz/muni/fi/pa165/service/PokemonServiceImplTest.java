package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.PokemonDao;
import cz.muni.fi.pa165.entity.Pokemon;
import cz.muni.fi.pa165.entity.Trainer;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * @author Martina Minatova
 */
public class PokemonServiceImplTest {
    @Mock
    private PokemonDao pokemonDao;

    @InjectMocks
    private PokemonService pokemonService = new PokemonServiceImpl();

    private Pokemon pokemon1, pokemon2;
    private Trainer trainer;

    @BeforeMethod
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        pokemon1 = new Pokemon();
        pokemon1.setId(015L);
        pokemon1.setName("Bulbasaur");
        pokemon1.setLevel(3);

        pokemon2 = new Pokemon();
        pokemon2.setId(21L);
        pokemon2.setName("Charmander");
        pokemon2.setLevel(6);

        trainer = new Trainer(3L);
        trainer.setFirstName("Ash");
        trainer.setLastName("Ketchum");
        trainer.addPokemon(pokemon1);
        pokemon1.setTrainer(trainer);
    }

    @Test
    public void injectedPokemonDaoTest(){
        Assert.assertNotNull(pokemonDao);
    }


    @Test
    public void pokemonServiceCreateTest(){
        pokemonService.create(pokemon1);
        verify(pokemonDao).create(pokemon1);
        verifyNoMoreInteractions(pokemonDao);
    }

    @Test(expectedExceptions=IllegalArgumentException.class)
    public void pokemonServiceCreateNullTest(){
        pokemonService.create(null);
    }

    @Test
    public void pokemonServiceFindByIdTest(){
        when(pokemonDao.findById(pokemon1.getId())).thenReturn(pokemon1);
        Pokemon p = pokemonService.findById(pokemon1.getId());
        verify(pokemonDao).findById(pokemon1.getId());
        Assert.assertEquals(p, pokemon1);
    }

    @Test
    public void pokemonServiceDeleteTest(){
        pokemonService.delete(pokemon1);
        verify(pokemonDao).delete(pokemon1);
        verifyNoMoreInteractions(pokemonDao);
    }

    @Test(expectedExceptions=IllegalArgumentException.class)
    public void pokemonServiceDeleteNullTest(){
        pokemonService.delete(null);
    }

    @Test
    public void pokemonServiceFindAllTest(){
        when(pokemonDao.findAll()).thenReturn(Arrays.asList(pokemon1,pokemon2));
        List<Pokemon> pokemons = pokemonService.findAll();

        verify(pokemonDao).findAll();
        Assert.assertEquals(2, pokemons.size());
        Assert.assertTrue(pokemons.contains(pokemon1));
        Assert.assertTrue(pokemons.contains(pokemon2));
    }

    @Test
    public void pokemonServiceFindByTrainerTest(){
        when(pokemonDao.findByTrainer(trainer)).thenReturn(Arrays.asList(pokemon1));
        List<Pokemon> pokemons = pokemonService.findByTrainer(trainer);

        verify(pokemonDao).findByTrainer(trainer);
        Assert.assertEquals(1, pokemons.size());
        Assert.assertTrue(pokemons.contains(pokemon1));
        Assert.assertFalse(pokemons.contains(pokemon2));
    }

    @Test
    public void pokemonServiceUpdateTest(){
        pokemonService.update(pokemon1);
        verify(pokemonDao).update(pokemon1);
        verifyNoMoreInteractions(pokemonDao);


    }

    @Test(expectedExceptions=IllegalArgumentException.class)
    public void pokemonServiceUpdateNullTest(){
        pokemonService.update(null);
    }

}
