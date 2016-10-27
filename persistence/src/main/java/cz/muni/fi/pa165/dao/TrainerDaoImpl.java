package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Trainer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Martin Golomb
 */
@Repository
@Transactional
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
    public void delete(Long id) {
        em.remove(findById(id));
    }

    @Override
    public void update(Trainer trainer) {
        em.merge(trainer);
    }
    
}
