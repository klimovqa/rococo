package guru.qa.rococo.ex;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PaintingNotFoundException extends RuntimeException {
    public PaintingNotFoundException() {
        super();
    }

    public PaintingNotFoundException(String message) {
        super(message);
    }

    public PaintingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaintingNotFoundException(Throwable cause) {
        super(cause);
    }

    protected PaintingNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
