package Team.project.exception;

import Team.project.dto.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestControllerAdvice
public class ApiExceptionHandler {

    private String getStackTraceAsString(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        return stringWriter.toString();
    }

    @ExceptionHandler(SessionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerSessionNotFound(SessionNotFoundException ex){
        String stackTrace = getStackTraceAsString(ex);
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), stackTrace);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(InfoCustomException.class)
    public ResponseEntity<ErrorResponse> handlerInfoCustom(InfoCustomException ex){
        String stackTrace = getStackTraceAsString(ex);
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), stackTrace);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(LoginCustomException.class)
    public ResponseEntity<ErrorResponse> handlerLoginCustom(LoginCustomException ex){
        String stackTrace = getStackTraceAsString(ex);
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), stackTrace);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
