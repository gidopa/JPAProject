package Team.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GradeNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(GradeNotFoundException ex, WebRequest request) {
        StringBuilder details = new StringBuilder();
        for (StackTraceElement element : ex.getStackTrace()) {
            details.append(element.toString()).append("\n");
        }
        ErrorDetails errorResponse = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), details.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException ex) {
        StringBuilder details = new StringBuilder();
        for (StackTraceElement element : ex.getStackTrace()) {
            details.append(element.toString()).append("\n");
        }
        ErrorDetails errorResponse = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), details.toString());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

  /*  @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
*/
    @ExceptionHandler(AssignGradeNotFoundException.class)
    public ResponseEntity<?> handleAssignGradeNotFoundException(AssignGradeNotFoundException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getStackTrace());
    }



}
