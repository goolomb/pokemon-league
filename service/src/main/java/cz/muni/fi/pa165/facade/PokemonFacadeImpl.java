package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.PokemonDTO;
import cz.muni.fi.pa165.dto.TrainerDTO;
import cz.muni.fi.pa165.entity.Pokemon;
import cz.muni.fi.pa165.entity.Trainer;
import cz.muni.fi.pa165.service.BeanMapperService;
import cz.muni.fi.pa165.service.PokemonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Martina Minatova
 */

@Service
@Transactional
public class PokemonFacadeImpl implements PokemonFacade {
    @Inject
    private PokemonService pokemonService;

    @Inject
    private BeanMapperService beanMapperService;

    @Override
    public PokemonDTO findById(Long id) {
        return beanMapperService.mapTo(pokemonService.findById(id), PokemonDTO.class);
    }

    @Override
    public void create(PokemonDTO pokemon) {
        pokemonService.create(beanMapperService.mapTo(pokemon,Pokemon.class));
    }

    @Override
    public void delete(PokemonDTO pokemon) {
        pokemonService.delete(beanMapperService.mapTo(pokemon,Pokemon.class));
    }

    @Override
    public List<PokemonDTO> findAll() {
        return beanMapperService.mapTo(pokemonService.findAll(), PokemonDTO.class);
    }

    @Override
    public List<PokemonDTO> findByTrainer(TrainerDTO trainer) {
        Trainer tr = beanMapperService.mapTo(trainer, Trainer.class);
        return beanMapperService.mapTo(pokemonService.findByTrainer(tr),PokemonDTO.class);
    }

    @Override
    public PokemonDTO update(PokemonDTO pokemon) {
        Pokemon p = beanMapperService.mapTo(pokemon,Pokemon.class);
        return beanMapperService.mapTo(pokemonService.update(p), PokemonDTO.class);
    }
}
