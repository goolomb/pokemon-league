package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BadgeDTO;

import java.util.List;

/**
 * Created by Marek Perichta.
 */
public interface BadgeFacade {
    public BadgeDTO findById (Long badgeId);

    public List<BadgeDTO> findAll();

    public List<BadgeDTO> findByTrainer(Long trainerId);

    public void create(BadgeDTO badgeDTO);

    public void delete(BadgeDTO badgeDTO);

    public void update (BadgeDTO badgeDTO);
}
