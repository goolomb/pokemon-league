package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Pokemon;
import cz.muni.fi.pa165.entity.Trainer;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Implementation of Pokemon Data Access Object
 *
 * @author Martina Minatova
 */
@Repository
public class PokemonDaoImpl implements PokemonDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Pokemon findById(Long id) {
        return em.find(Pokemon.class, id);
    }

    @Override
    public void create(Pokemon pokemon) {
        em.persist(pokemon);
    }

    @Override
    public void delete(Pokemon pokemon) {
        //em.remove(pokemon);
        em.remove(em.contains(pokemon) ? pokemon : em.merge(pokemon));
    }

    @Override
    public List<Pokemon> findAll() {
        return em
                .createQuery("SELECT p FROM Pokemon p", Pokemon.class)
                .getResultList();
    }

    @Override
    public List<Pokemon> findByTrainer(Trainer trainer) {
        if (trainer == null) {
            throw new IllegalArgumentException("Cannot search with null trainer");
        }

        try{
            return em
                    .createQuery("SELECT p FROM Pokemon p WHERE p.trainer = :trainer", Pokemon.class)
                    .setParameter("trainer", trainer)
                    .getResultList();
        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public Pokemon update(Pokemon pokemon) {
        return em.merge(pokemon);
    }
}
