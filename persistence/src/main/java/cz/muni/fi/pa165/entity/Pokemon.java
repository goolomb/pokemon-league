package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.enums.PokemonType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Pokemon entity
 *
 * @author Martina Minatova
 */
@Entity
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false, unique = true)
    private String nickname;

    @NotNull
    @ElementCollection(targetClass=PokemonType.class)
    private Set<PokemonType> type = new HashSet<PokemonType>();

    @Column
    private int level;

    @ManyToOne
    private Trainer trainer;

    public Pokemon() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Set<PokemonType> getType() {
        return type;
    }

    public void setType(Set<PokemonType> type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Pokemon)) return false;

        Pokemon pokemon = (Pokemon) o;

        if (level != pokemon.level) return false;
        if (getName() == null ? pokemon.getName() != null : !getName().equals(pokemon.getName())) {
            return false;
        }
        if (getNickname() == null ? pokemon.getNickname() != null : !getNickname().equals(pokemon.getNickname())) {
            return false;
        }
        if (!getType().equals(pokemon.getType())) {
            return false;
        }
        if (getTrainer() == null ? pokemon.getTrainer() != null : !getTrainer().equals(pokemon.getTrainer())) {
            return false;
        }

        return true;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 17 * result + nickname.hashCode();
        result = 53 * result + type.hashCode();
        result = 67 * result + level;
        result = 41 * result + (trainer != null ? trainer.hashCode() : 0);
        return result;
    }
}