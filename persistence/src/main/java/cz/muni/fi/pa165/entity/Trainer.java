package cz.muni.fi.pa165.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 * Trainer Entity
 *
 * @author Martin Golomb
 */

@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String firstName;
    
    @NotNull
    @Column(nullable = false)
    private String lastName;
    
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @Past
    private Date birthDate;
    
    @OneToMany
    @NotNull
    private List<Badge> badges = new ArrayList<Badge>();
    
    @OneToMany
    @NotNull
    private List<Pokemon> pokemons = new ArrayList<Pokemon>();

    
    public Trainer() {
    }

    
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Trainer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Trainer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Trainer setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public List<Badge> getBadges() {
        return Collections.unmodifiableList(badges);
    }

    public Trainer setBadges(List<Badge> badges) {
        this.badges = badges;
        return this;
    }
    
    /*public void addBadge(Badge badge) {
        badges.add(badge);
    }*/

    public List<Pokemon> getPokemons() {
        return Collections.unmodifiableList(pokemons);
    }

    public Trainer setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
        return this;
    }

    /*public void addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
    }*/

    @Override
    public int hashCode() {
        final int prime = 43;
        int hash = 7;
        hash = prime * hash + getFirstName().hashCode();
        hash = prime * hash + getLastName().hashCode();
        hash = prime * hash + getBirthDate().hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Trainer)) {
            return false;
        }
        
        Trainer other = (Trainer) obj;
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
