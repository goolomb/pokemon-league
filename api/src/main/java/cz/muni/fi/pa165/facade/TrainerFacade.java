package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.TrainerDTO;
import java.util.List;

/**
 *
 * @author Martin Golomb (goolomb)
 */
public interface TrainerFacade {
    public TrainerDTO findById(Long id);
    public List<TrainerDTO> findAll();
    public void create(TrainerDTO trainer);
    public void delete(TrainerDTO trainer);
    public TrainerDTO update(TrainerDTO trainer);
}
