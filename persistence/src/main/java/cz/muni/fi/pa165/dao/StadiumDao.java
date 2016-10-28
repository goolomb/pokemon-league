package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Trainer;
import cz.muni.fi.pa165.entity.Stadium;
import java.util.List;

/**
 *
 * @author Jakub Holy
 */
public interface StadiumDao {
    
    public Stadium findById(Long id);
    public void create(Stadium stadium);
    public void delete(Stadium stadium);
    public List<Stadium> findAll();
    public Stadium findByTrainer(Trainer trainer);
    public void update(Stadium stadium);
    
}
