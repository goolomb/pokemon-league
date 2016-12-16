package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Stadium;
import cz.muni.fi.pa165.entity.Trainer;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jakub Holy
 */
@Repository
public class StadiumDaoImpl implements StadiumDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Stadium findById(Long id) {
        return em.find(Stadium.class, id);
    }

    @Override
    public void create(Stadium stadium) {
        em.persist(stadium);
    }

    @Override
    public void delete(Stadium stadium) {
        em.remove(stadium);
    }

    @Override
    public List<Stadium> findAll() {
        return em.createQuery("select s from Stadium s",
                Stadium.class).getResultList();
    }

    @Override
    public Stadium findByTrainer(Trainer trainer) {
        try{
        return em.createQuery("select s from Stadium s where leader = :trainer",
                Stadium.class).setParameter("trainer", trainer).getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
    }

    @Override
    public Stadium update(Stadium stadium) {
        return em.merge(stadium);
    }
    
    

}
