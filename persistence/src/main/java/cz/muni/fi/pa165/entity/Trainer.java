package cz.muni.fi.pa165.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 * Entity class represents trainer with pokemons and badges
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
    @ElementCollection(targetClass=Badge.class)
    private Set<Badge> badges = new HashSet<Badge>();
    
    @OneToMany
    @ElementCollection(targetClass=Pokemon.class)
    private Set<Pokemon> pokemons = new HashSet<Pokemon>();

    
    /**
     * Constructor with parameter id
     * @param trainerId id to set new Trainer
     */
    public Trainer(Long trainerId) {
        this.id = trainerId;
    }
    
    /**
     * Constructor without parameters for new Trainer
     */
    public Trainer() {
    }

    /**
     * Method returns id of Trainer
     * @return id of Trainer
     */
    public Long getId() {
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }

    /**
     * Method returns first name of Trainer
     * @return firstName of Trainer
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Method sets first name of Trainer
     * @param firstName first name of Trainer
     * @return this Trainer
     */
    public Trainer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Method returns last name of Trainer
     * @return lastName of Trainer
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Method sets last name of Trainer
     * @param lastName last name of Trainer
     * @return this Trainer
     */
    public Trainer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Method return birth date of Trainer
     * @return birthDate of Trainer
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Method sets birth date of Trainer
     * @param birthDate birth date of Trainer
     * @return this Trainer
     */
    public Trainer setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     * Method returns badges of Trainer
     * @return collection of badges of Trainer
     */
    public Set<Badge> getBadges() {
        return badges;
    }

    /**
     * Method sets badges to Trainer
     * @param badges badges to Trainer
     * @return this Trainer
     */
    public Trainer setBadges(Set<Badge> badges) {
        this.badges = badges;
        return this;
    }
    
    /**
     * Method adds badge to Trainer
     * @param badge new badge to be added Trainer
     */
    public void addBadge(Badge badge) {
        badges.add(badge);
        badge.setTrainer(this);
    }
    
    /**
     * Method removes badge from Trainer
     * @param badge badge to be removed from Trainer
     */
    public void removeBadge(Badge badge) {
        badges.remove(badge);
        badge.setTrainer(null);
    }

    /**
     * Method returns pokemons of Trainer
     * @return collection of pokemons of Trainer
     */
    public Set<Pokemon> getPokemons() {
        return pokemons;
    }

    /**
     * Method sets pokemons to Trainer
     * @param pokemons pokemons to Trainer
     * @return this Trainer
     */
    public Trainer setPokemons(Set<Pokemon> pokemons) {
        this.pokemons = pokemons;
        return this;
    }

    /**
     * Method adds pokemon to Trainer
     * @param pokemon new pokemon to be added Trainer
     */
    public void addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
        pokemon.setTrainer(this);
    }
    
    /**
     * Method removes pokemon from Trainer
     * @param pokemon pokemon to be removed from Trainer
     */
    public void removePokemon(Pokemon pokemon) {
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
