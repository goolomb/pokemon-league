package cz.muni.fi.pa165.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * Badge entity.
 * 
 * @author Marek Perichta
 */
@Entity
public class Badge {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @NotNull
    private Stadium origin;
    
    @ManyToOne
    @NotNull
    private Trainer trainer;

    public Badge () {}

    public Badge (Long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrigin(Stadium origin) {
        this.origin = origin;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Long getId() {
        return id;
    }

    public Stadium getOrigin() {
        return origin;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (getOrigin() != null ? getOrigin().hashCode() : 0);
        hash = 17 * hash + (getTrainer() != null ? getTrainer().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Badge)) {
            return false;
        }
        final Badge other = (Badge) obj;

        if (getOrigin() != null ? !getOrigin().equals(other.getOrigin()) : other.getOrigin() != null)
            return false;

        if (getTrainer() != null ? !getTrainer().equals(other.getTrainer()) : other.getTrainer() != null)
            return false;
        
        return true;
    }

}
