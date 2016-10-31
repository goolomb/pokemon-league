package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Trainer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
* DAO implementation for Trainer
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
        if (trainer.getBirthDate() == null)
            throw new PersistenceException("Null birthDate");
        if (trainer.getFirstName() == null)
            throw new PersistenceException("Null firstName");
        if (trainer.getLastName() == null)
            throw new PersistenceException("Null lastName");
        
        em.persist(trainer);
    }

    @Override
    public void delete(Trainer trainer) {
        em.remove(trainer);
    }

    @Override
    public Trainer update(Trainer trainer) {
        return em.merge(trainer);
    }
    
}
