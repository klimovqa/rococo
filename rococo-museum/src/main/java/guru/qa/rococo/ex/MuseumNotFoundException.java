package guru.qa.rococo.ex;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MuseumNotFoundException extends RuntimeException {
    public MuseumNotFoundException() {
        super();
    }

    public MuseumNotFoundException(String message) {
        super(message);
    }

    public MuseumNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MuseumNotFoundException(Throwable cause) {
        super(cause);
    }

    protected MuseumNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
