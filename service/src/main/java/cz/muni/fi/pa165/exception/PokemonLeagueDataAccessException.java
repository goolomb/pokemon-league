package cz.muni.fi.pa165.exception;

import org.springframework.dao.DataAccessException;

/**
 * @author Martina Minatova
 */

public class PokemonLeagueDataAccessException extends DataAccessException {
    public PokemonLeagueDataAccessException(String msg) {
        super(msg);
    }

    public PokemonLeagueDataAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }

}