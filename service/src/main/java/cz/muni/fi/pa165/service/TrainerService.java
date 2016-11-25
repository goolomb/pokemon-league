package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Trainer;
import java.util.List;

/**
 *
 * @author Martin Golomb (goolomb)
 */
public interface TrainerService {
    Trainer findById(Long id);
    List<Trainer> findAll();
    void create(Trainer trainer);
    void delete(Trainer trainer);
    Trainer update(Trainer trainer);
}
