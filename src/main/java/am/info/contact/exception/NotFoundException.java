package am.info.contact.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	public NotFoundException(String value, String entityName) {
		super(value + "' not found in entity '" + entityName + "'");
	}
}
