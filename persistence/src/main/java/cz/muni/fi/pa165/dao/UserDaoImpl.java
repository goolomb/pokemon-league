package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;
import javax.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.NonTransientDataAccessResourceException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Martin Golomb (goolomb)
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;
    
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Override
    public void create(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            System.out.println(user.getPassword());
            em.persist(user);
            em.flush();
        } catch (TransactionRequiredException e) {
            throw new NonTransientDataAccessResourceException("Unable to create user due to database access failure.", e);
        } catch (IllegalStateException e) {
            throw new NonTransientDataAccessResourceException("Unable to create user due to database access failure.", e);
        } catch (ValidationException e) {
            throw new DataIntegrityViolationException("Unable to create user due to integrity violation.", e);
        } catch (PersistenceException e) {
            throw new DataIntegrityViolationException("Unable to create user due to integrity violation.", e);
        } catch (IllegalArgumentException iae) {
            throw new InvalidDataAccessApiUsageException("Unable to create object, because received object is not an entity.", iae);
        }
    }

    @Override
    public void update(User user) {
        try {
            em.merge(user);
            em.flush();
        } catch (TransactionRequiredException e) {
            throw new NonTransientDataAccessResourceException("Unable to update user due to database access failure.", e);
        } catch (IllegalStateException e) {
            throw new NonTransientDataAccessResourceException("Unable to update user due to database access failure.", e);
        } catch (ValidationException e) {
            throw new DataIntegrityViolationException("Unable to update user due to integrity violation.", e);
        } catch (PersistenceException e) {
            throw new DataIntegrityViolationException("Unable to update user due to integrity violation.", e);
        } catch (IllegalArgumentException iae) {
            throw new InvalidDataAccessApiUsageException("Unable to update object, because received object is not an entity.", iae);
        }
    }

    @Override
    public void delete(User user) {
        try {
            em.remove(em.find(User.class, user.getId()));
            em.flush();
        } catch (TransactionRequiredException e) {
            throw new NonTransientDataAccessResourceException("Unable to delete user due to database access failure.", e);
        } catch (IllegalStateException e) {
            throw new NonTransientDataAccessResourceException("Unable to delete user due to database access failure.", e);
        } catch (ValidationException e) {
            throw new DataIntegrityViolationException("Unable to delete user due to error in database layer.", e);
        } catch (PersistenceException e) {
            throw new DataIntegrityViolationException("Unable to delete user due to error in database layer.", e);
        } catch (IllegalArgumentException iae) {
            throw new InvalidDataAccessApiUsageException("Unable to delete object, because received object is not an entity.", iae);
        }
    }

    @Override
    public User getById(Long id) {
        try {
            return em.find(User.class, id);
        } catch (IllegalStateException e) {
            throw new NonTransientDataAccessResourceException("Unable to retrieve user due to database access failure.", e);
        } catch (IllegalArgumentException iae) {
            throw new InvalidDataAccessApiUsageException("Unable to retrieve user, because the given key is not a valid primary key.", iae);
        }
    }    
}
