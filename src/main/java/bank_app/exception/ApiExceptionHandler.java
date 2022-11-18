package bank_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ApiExceptionHandler {

    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {

        //Create payload containing exception details

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                e.getMessage(),
                e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));

        //Return response entity
        return new ResponseEntity<>(apiException, badRequest);
    }
}
