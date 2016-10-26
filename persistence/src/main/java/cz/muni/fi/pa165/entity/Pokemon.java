package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.enums.PokemonType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

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
    private List<PokemonType> type;

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

    public Collection<PokemonType> getType() {
        return type;
    }

    public void setType(Collection<PokemonType> type) {
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
        if (o == null || getClass() != o.getClass()) return false;

        Pokemon pokemon = (Pokemon) o;

        if (level != pokemon.level) return false;
        if (!id.equals(pokemon.id)) return false;
        if (!name.equals(pokemon.name)) return false;
        if (!nickname.equals(pokemon.nickname)) return false;
        if (!type.equals(pokemon.type)) return false;
        return trainer != null ? trainer.equals(pokemon.trainer) : pokemon.trainer == null;

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