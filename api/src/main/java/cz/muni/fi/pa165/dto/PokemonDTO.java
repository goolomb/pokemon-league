package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.PokemonType;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Martina Minatova
 */
public class PokemonDTO {
    private Long id;
    private String name;
    private String nickname;
    private Set<PokemonType> type = new HashSet<PokemonType>();
    private int level;
    private TrainerDTO trainer;

    public PokemonDTO() {
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

    public TrainerDTO getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerDTO trainer) {
        this.trainer = trainer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof PokemonDTO)) return false;

        PokemonDTO pokemon = (PokemonDTO) o;

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
        return result;
    }
}
