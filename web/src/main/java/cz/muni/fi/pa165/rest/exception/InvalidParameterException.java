package cz.muni.fi.pa165.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Martina Minatova
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Invalid parameter.")
public class InvalidParameterException extends RuntimeException {
}
