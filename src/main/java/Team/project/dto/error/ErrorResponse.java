package Team.project.dto.error;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ErrorResponse {

    private String message;
    private int status;
    private String stackTrace;

    public ErrorResponse(String message, int status, String stackTrace) {
        this.message = message;
        this.status = status;
        this.stackTrace = stackTrace;
    }

}