package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.enums.PokemonType;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

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
    
    @NotNull
    @Column(nullable = false, unique = true)
    private String city;
    

    @ElementCollection(targetClass=PokemonType.class)
    private Set<PokemonType> type = new HashSet<PokemonType>();
    
    @OneToOne
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

    public Set<PokemonType> getType() {
        return type;
    }

    public void addType(PokemonType type) {
        this.type.add(type);
    }
    
    public void removeType(PokemonType type) {
        this.type.remove(type);
    }

    public void setType(Set<PokemonType> type) {
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
