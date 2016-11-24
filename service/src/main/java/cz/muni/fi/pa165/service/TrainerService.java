package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Trainer;
import java.util.List;

/**
 *
 * @author Martin Golomb (goolomb)
 */
public interface TrainerService {
    public Trainer findById(Long id);
    public List<Trainer> findAll();
    public void create(Trainer trainer);
    public void delete(Trainer trainer);
    public Trainer update(Trainer trainer);    
}
