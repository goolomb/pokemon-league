package cz.muni.fi.pa165.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Marek Perichta.
 */
public class BadgeCreateDTO {

    private Long badgeId;

    @NotNull(message = "Can't be empty")
    private Long origin;

    @NotNull(message = "Can't be empty")
    private Long trainer;

    public Long getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Long badgeId) {
        this.badgeId = badgeId;
    }

    public Long getOrigin() {
        return origin;
    }

    public void setOrigin(Long origin) {
        this.origin = origin;
    }

    public Long getTrainer() {
        return trainer;
    }

    public void setTrainer(Long trainer) {
        this.trainer = trainer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BadgeCreateDTO that = (BadgeCreateDTO) o;

        if (getBadgeId() != null ? !getBadgeId().equals(that.getBadgeId()) : that.getBadgeId() != null) return false;
        if (getOrigin() != null ? !getOrigin().equals(that.getOrigin()) : that.getOrigin() != null) return false;
        return getTrainer() != null ? getTrainer().equals(that.getTrainer()) : that.getTrainer() == null;

    }

    @Override
    public int hashCode() {
        int result = getBadgeId() != null ? getBadgeId().hashCode() : 0;
        result = 31 * result + (getOrigin() != null ? getOrigin().hashCode() : 0);
        result = 31 * result + (getTrainer() != null ? getTrainer().hashCode() : 0);
        return result;
    }
}
