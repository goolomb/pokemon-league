package cz.muni.fi.pa165.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
    private Stadium origin;
    
    @ManyToOne
    private Trainer trainer;

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
        hash = 17 * hash + (this.origin != null ? this.origin.hashCode() : 0);
        hash = 17 * hash + (this.trainer != null ? this.trainer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
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
