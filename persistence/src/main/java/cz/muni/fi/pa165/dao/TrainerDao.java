package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Trainer;
import java.util.List;

/**
 * 
 * @author Martin Golomb
 */
public interface TrainerDao {
    public Trainer findById(Long id);
    public List<Trainer> findAll();
    public void create(Trainer trainer);
    public void delete(Long id);
    public void update(Trainer trainer);
}
