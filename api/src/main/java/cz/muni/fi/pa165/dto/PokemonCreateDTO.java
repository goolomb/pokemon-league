package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.PokemonType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Martina Minatova
 */
public class PokemonCreateDTO {
    private Long id;
    @NotNull(message = "Can't be empty")
    private String name;
    private String nickname;

    @NotNull(message = "Can't be empty")
    private Set<PokemonType> type = new HashSet<PokemonType>();

    @NotNull(message = "Can't be empty")
    @Min(1)
    private int level;

    private Long trainerId;


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

    public void addType(PokemonType type) {
        this.type.add(type);
    }

    public void removeType(PokemonType type) {
        this.type.remove(type);
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

    public Long getTrainer() {
        return trainerId;
    }

    public void setTrainer(Long trainerId) {
        this.trainerId = trainerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof PokemonCreateDTO)) return false;

        PokemonCreateDTO pokemon = (PokemonCreateDTO) o;

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
        int result = 1;
        result = 31 * result + ((name == null) ? 0 : name.hashCode());
        result = 17 * result + ((nickname == null) ? 0 : nickname.hashCode());
        result = 53 * result + ((type == null) ? 0 : type.hashCode());
        result = 67 * result + level;
        result = 41 * result + (trainerId == null ? 0 : trainerId.hashCode());
        return result;
    }
}
