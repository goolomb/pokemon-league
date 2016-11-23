package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Badge;
import cz.muni.fi.pa165.entity.Trainer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * Data access object implementation for Badge.
 * 
 * @author Marek Perichta
 */
@Repository
@Transactional
public class BadgeDaoImpl implements BadgeDao{
 
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Badge findById(Long id) {
        return em.find(Badge.class, id);
    }

    @Override
    public List<Badge> findAll() {
        try{
            return em
                .createQuery("SELECT b FROM Badge b", Badge.class)
                .getResultList();
        }catch(NoResultException e){
            return null;
        }

    }

    @Override
    public List<Badge> findByTrainer(Trainer trainer) {
        if (trainer == null) {
            throw new IllegalArgumentException("Cannot search badges with null trainer");
        }

        try{
            return em
                    .createQuery("SELECT b FROM Badge b WHERE b.trainer = :trainer", Badge.class)
                    .setParameter("trainer", trainer)
                    .getResultList();
        }catch(NoResultException e){
            return null;
        }
    }
    
    @Override
    public void create(Badge badge) {
        if (badge == null) 
            throw new IllegalArgumentException("Badge to add is null.");
        try {
            em.persist(badge);   
        } catch (Exception e) {
            throw new PersistenceException("Could not add badge to database.", e);
        }

    }
    
    @Override
    public void delete(Badge badge) {
        try {
            em.remove(badge);
        } catch (Exception e) {
            throw new PersistenceException("Could not delete badge from database.", e);
        }
    }
    
    @Override
    public Badge update(Badge badge) {
        try {
            return em.merge(badge);
        } catch (Exception e) {
            throw new PersistenceException("Could not update badge.", e);
        }
    }
}
