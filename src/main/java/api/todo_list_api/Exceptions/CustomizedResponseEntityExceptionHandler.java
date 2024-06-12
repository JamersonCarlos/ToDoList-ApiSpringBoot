package api.todo_list_api.Exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
        
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionModel> handleAllExceptions(Exception ex, WebRequest web) { 
        ExceptionModel exceptionModel = new ExceptionModel(new Date(), ex.getMessage(), web.getDescription(false));
        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InvalidTokenAccessException.class)
    public final ResponseEntity<ExceptionModel> handleInvalidTokenException(Exception ex, WebRequest web) { 
        ExceptionModel exceptionModel = new ExceptionModel(new Date(), ex.getMessage(), web.getDescription(false));
        return new ResponseEntity<>(exceptionModel, HttpStatus.FORBIDDEN);
    }
    

}
