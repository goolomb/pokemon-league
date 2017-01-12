package cz.muni.fi.pa165.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Trainer data transfer object
 * 
 * @author Martin Golomb (goolomb)
 */
public class TrainerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Set<BadgeDTO> badges = new HashSet<BadgeDTO>();
    private Set<PokemonDTO> pokemons = new HashSet<PokemonDTO>();

    public TrainerDTO(Long trainerId) {
        this.id = trainerId;
    }
    
    public TrainerDTO() {
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public TrainerDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public TrainerDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public TrainerDTO setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Set<BadgeDTO> getBadges() {
        return badges;
    }

    public TrainerDTO setBadges(Set<BadgeDTO> badges) {
        this.badges = badges;
        return this;
    }
    
    public void addBadge(BadgeDTO badge) {
        badges.add(badge);
        badge.setTrainer(this);
    }
    
    public void removeBadge(BadgeDTO badge) {
        badges.remove(badge);
        badge.setTrainer(null);
    }

    public Set<PokemonDTO> getPokemons() {
        return pokemons;
    }

    public TrainerDTO setPokemons(Set<PokemonDTO> pokemons) {
        this.pokemons = pokemons;
        return this;
    }

    public void addPokemon(PokemonDTO pokemon) {
        pokemons.add(pokemon);
        pokemon.setTrainer(this);
    }
    
    public void removePokemon(PokemonDTO pokemon) {
        pokemons.remove(pokemon);
        pokemon.setTrainer(null);
    }

    @Override
    public int hashCode() {
        final int prime = 43;
        int hash = 7;
        hash = prime * hash + (getFirstName() != null ? getFirstName().hashCode() : 0);
        hash = prime * hash + (getLastName() != null ? getLastName().hashCode() : 0);
        hash = prime * hash + (getBirthDate() != null ? getBirthDate().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof TrainerDTO)) {
            return false;
        }
        
        TrainerDTO other = (TrainerDTO) obj;
        if ((getFirstName() == null) ? (other.getFirstName() != null) : !getFirstName().equals(other.getFirstName())) {
            return false;
        }
        if ((getLastName() == null) ? (other.getLastName() != null) : !getLastName().equals(other.getLastName())) {
            return false;
        }
        if (getBirthDate() != other.getBirthDate() && (getBirthDate() == null || !getBirthDate().equals(other.getBirthDate()))) {
            return false;
        }
        return true;
    }    
}
