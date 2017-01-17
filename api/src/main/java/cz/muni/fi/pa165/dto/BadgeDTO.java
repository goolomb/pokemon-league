package cz.muni.fi.pa165.dto;


/**
 * Created by Marek Perichta.
 */
public class BadgeDTO {

    public BadgeDTO () {
    }

    public BadgeDTO (Long id) {
        this.id = id;
    }

    private Long id;

    private StadiumDTO origin;

    private TrainerDTO trainer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StadiumDTO getOrigin() {
        return origin;
    }

    public void setOrigin(StadiumDTO origin) {
        this.origin = origin;
    }

    public TrainerDTO getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerDTO trainer) {
        this.trainer = trainer;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof BadgeDTO)) {
            return false;
        }

        final BadgeDTO other = (BadgeDTO) obj;


        if (getOrigin() != null ? !getOrigin().equals(other.getOrigin()) : other.getOrigin() != null)
            return false;

        if (getTrainer() != null ? !getTrainer().equals(other.getTrainer()) : other.getTrainer() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((getOrigin() == null) ? 0 : getOrigin().hashCode());
        result = 31 * result + ((getOrigin() == null) ? 0 : getTrainer().hashCode());
        return result;
    }
}
