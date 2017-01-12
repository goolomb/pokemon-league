package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.PokemonType;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * StadiumCreate data transfer object
 *
 * @author Jakub Holy (saintjackie)
 */
public class StadiumCreateDTO {

    private Long id;
    @NotNull(message = "Can't be empty")
    @Size(min = 2, max = 32, message = "min 2, max 32 characters")
    private String city;

    @NotNull(message = "Can't be empty")
    private Set<PokemonType> type = new HashSet<PokemonType>();

    @NotNull(message = "Can't be empty")
    private Long leaderId;

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

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    @Override
    public int hashCode() {
        final int prime = 53;
        int result = 1;
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getLeaderId() == null) ? 0 : getLeaderId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || (getClass() != obj.getClass())) {
            return false;
        }

        final StadiumCreateDTO other = (StadiumCreateDTO) obj;
        //city check
        if (getCity() == null) {
            if (other.getCity() != null) {
                return false;
            }
        } else if (!getCity().equals(other.getCity())) {
            return false;
        }
        //type check
        if (getType() == null) {
            if (other.getType() != null) {
                return false;
            }
        } else if (!getType().equals(other.getType())) {
            return false;
        }
        //leader check
        if (getLeaderId() == null) {
            if (other.getLeaderId() != null) {
                return false;
            }
        } else if (!getLeaderId().equals(other.getLeaderId())) {
            return false;
        }
        return true;
    }

}
