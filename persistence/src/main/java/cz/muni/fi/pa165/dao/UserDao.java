package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.User;

/**
 *
 * @author Martin Golomb (goolomb)
 */
public interface UserDao {
        /*
     * Creates user entity in the database.
     * 
     * @param user to be created
     */
    void create(User user);
    
    /*
     * Updates user entity in the database.
     * 
     * @param user to be updated
     */
    void update(User user);
    
    /*
     * Deletes user entity from the database.
     * 
     * @param user to be deleted
     */
    void delete(User user);
    
    /*
     * Finds user in the database by his id.
     * 
     * @param user id
     * @return user
     */
    User getById(Long id);

}
