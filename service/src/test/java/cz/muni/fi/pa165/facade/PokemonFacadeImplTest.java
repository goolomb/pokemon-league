package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.PokemonDTO;
import cz.muni.fi.pa165.dto.TrainerDTO;
import cz.muni.fi.pa165.entity.Pokemon;
import cz.muni.fi.pa165.entity.Trainer;
import cz.muni.fi.pa165.service.BeanMapperService;
import cz.muni.fi.pa165.service.PokemonService;
import cz.muni.fi.pa165.service.TrainerService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Martina Minatova
 */
public class PokemonFacadeImplTest  {

    @Mock
    private PokemonService pokemonService;

    @Mock
    private TrainerService trainerService;
    @Mock
    private BeanMapperService beanMapperService;

    @InjectMocks
    private PokemonFacade pokemonFacade = new PokemonFacadeImpl();

    private PokemonDTO pokemon1;
    private PokemonDTO pokemon2;

    private TrainerDTO trainerDTO;

    @BeforeClass
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUpMethod(){

        pokemon1 = new PokemonDTO();
        pokemon1.setId(015L);
        pokemon1.setName("Bulbasaur");
        pokemon1.setLevel(3);


        pokemon2 = new PokemonDTO();
        pokemon2.setId(21L);
        pokemon2.setName("Charmander");
        pokemon2.setLevel(6);

        trainerDTO = new TrainerDTO(05L);
        trainerDTO.addPokemon(pokemon1);
        pokemon1.setTrainer(trainerDTO);
    }

    @Test
    public void injectedBeanMapperAndPokemonServiceTest(){
        Assert.assertNotNull(beanMapperService);
        Assert.assertNotNull(pokemonService);
    }

    @Test
    public void findByIdTest() throws Exception {
        Pokemon pokemon = beanMapperService.mapTo(pokemon1,Pokemon.class);

        when(pokemonService.findById(pokemon1.getId())).thenReturn(pokemon);
        when(beanMapperService.mapTo(pokemon, PokemonDTO.class)).thenReturn(pokemon1);
        PokemonDTO pokemonDTO = pokemonFacade.findById(pokemon1.getId());

        verify(pokemonService).findById(pokemon1.getId());
        verify(beanMapperService).mapTo(pokemon, PokemonDTO.class);
        Assert.assertEquals(pokemonDTO,pokemon1);
        Assert.assertTrue(pokemonDTO.getId() == 015L);

    }

    @Test
    public void createTest() throws Exception {
        Pokemon pokemon = beanMapperService.mapTo(pokemon1,Pokemon.class);
        pokemonFacade.create(pokemon1);
        verify(pokemonService).create(pokemon);
        verifyNoMoreInteractions(pokemonService);
    }

    @Test
    public void deleteTest() throws Exception {
        Pokemon pokemon = beanMapperService.mapTo(pokemon1,Pokemon.class);
        pokemonFacade.delete(pokemon1);
        verify(pokemonService).delete(pokemon);
        verifyNoMoreInteractions(pokemonService);

    }

    @Test
    public void findAllTest() throws Exception {
        Pokemon p1 = beanMapperService.mapTo(pokemon1, Pokemon.class);
        Pokemon p2 = beanMapperService.mapTo(pokemon2, Pokemon.class);
        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        pokemons.add(p1);
        pokemons.add(p2);
        List<PokemonDTO> pokemonsDTO = new ArrayList<PokemonDTO>();
        pokemonsDTO.add(pokemon1);
        pokemonsDTO.add(pokemon2);

        when(pokemonService.findAll()).thenReturn(pokemons);
        when(beanMapperService.mapTo(pokemons, PokemonDTO.class)).thenReturn(pokemonsDTO);

        List<PokemonDTO> result = pokemonFacade.findAll();

        verify(pokemonService).findAll();
        verify(beanMapperService).mapTo(pokemons, PokemonDTO.class);
        Assert.assertEquals(result.size(),2);
        Assert.assertTrue(result.contains(pokemon1));
        Assert.assertTrue(result.contains(pokemon2));
    }

    @Test
    public void findByTrainerTest() throws Exception {
        Pokemon pokemon = beanMapperService.mapTo(pokemon1, Pokemon.class);
        Trainer trainer = beanMapperService.mapTo(trainerDTO, Trainer.class);

        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        pokemons.add(pokemon);
        List<PokemonDTO> pokemonsDTO = new ArrayList<PokemonDTO>();
        pokemonsDTO.add(pokemon1);

        when(pokemonService.findByTrainer(trainer)).thenReturn(pokemons);
        when(beanMapperService.mapTo(pokemons, PokemonDTO.class)).thenReturn(pokemonsDTO);

        List<PokemonDTO> result = pokemonFacade.findByTrainer(trainerDTO);

        verify(pokemonService).findByTrainer(trainer);
        verify(beanMapperService).mapTo(pokemons, PokemonDTO.class);
        Assert.assertEquals(result.size(),1);
        Assert.assertTrue(result.contains(pokemon1));
        Assert.assertFalse(result.contains(pokemon2));
    }

    @Test
    public void updateTest() throws Exception {
        Pokemon pokemon = beanMapperService.mapTo(pokemon1,Pokemon.class);
        pokemonFacade.update(pokemon1);
        verify(pokemonService).update(pokemon);
        verifyNoMoreInteractions(pokemonService);
    }



}
