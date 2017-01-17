package cz.muni.fi.pa165.dto;

/**
 * @author Jakub Holy (saintjackie)
 */
public class TradePokemonDTO {
    
    private Long pokemon1Id;
    private Long pokemon2Id;

    public Long getPokemon1Id() {
        return pokemon1Id;
    }

    public void setPokemon1Id(Long pokemon1Id) {
        this.pokemon1Id = pokemon1Id;
    }

    public Long getPokemon2Id() {
        return pokemon2Id;
    }

    public void setPokemon2Id(Long pokemon2Id) {
        this.pokemon2Id = pokemon2Id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.pokemon1Id != null ? this.pokemon1Id.hashCode() : 0);
        hash = 23 * hash + (this.pokemon2Id != null ? this.pokemon2Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TradePokemonDTO other = (TradePokemonDTO) obj;
        if (this.pokemon1Id != other.pokemon1Id && (this.pokemon1Id == null || !this.pokemon1Id.equals(other.pokemon1Id))) {
            return false;
        }
        if (this.pokemon2Id != other.pokemon2Id && (this.pokemon2Id == null || !this.pokemon2Id.equals(other.pokemon2Id))) {
            return false;
        }
        return true;
    }
    
    
    
}
