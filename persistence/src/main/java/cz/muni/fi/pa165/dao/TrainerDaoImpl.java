package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Trainer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
* DAO implementation for Trainer
*
 * @author Martin Golomb
 */
@Repository
public class TrainerDaoImpl implements TrainerDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Trainer findById(Long id) {
        return em.find(Trainer.class, id);
    }

    @Override
    public List<Trainer> findAll() {
        return em.createQuery("SELECT t FROM Trainer t", Trainer.class).getResultList();
    }

    @Override
    public void create(Trainer trainer) {
        em.persist(trainer);
    }

    @Override
    public void delete(Trainer trainer) {
        em.remove(em.contains(trainer) ? trainer : em.merge(trainer));
    }

    @Override
    public Trainer update(Trainer trainer) {
        return em.merge(trainer);
    }
    
}
