package cz.muni.fi.pa165.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Stadium entity
 *
 * @author Jakub Holy
 */
@Entity
public class Stadium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String type;
    private Trainer leader;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Trainer getLeader() {
        return leader;
    }

    public void setLeader(Trainer leader) {
        this.leader = leader;
    }

    @Override
    public int hashCode() {
        final int prime = 53;
        int result = 1;
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((leader == null) ? 0 : leader.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Stadium)) {
            return false;
        }
        final Stadium other = (Stadium) obj;
        //city check
        if (city == null) {
            if (other.getCity() != null) {
                return false;
            }
        } else if (!city.equals(other.getCity())) {
            return false;
        }
        //type check
        if(type == null){
            if(other.getType() != null){
                return false;
            }
        }else if (!type.equals(other.getType())){
            return false;
        }
        //leader check
        if(leader == null){
            if(other.getLeader() != null){
                return false;
            }
        }else if (!leader.equals(other.getLeader())){
            return false;
        }
        return true;
    }

}
