package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Badge;
import java.util.List;
import javax.persistence.EntityManager;
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
        return em
            .createQuery("SELECT b FROM Badge b", Badge.class)
            .getResultList();
    }

    @Override
    public void create(Badge badge) {
        em.persist(badge);
    }
    
    @Override
    public void delete(Badge badge) {
        em.remove(badge);
    }
    
    @Override
    public Badge update(Badge badge) {
        return em.merge(badge);
    }
}
