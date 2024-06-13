package Team.project.exception;

public class NoStateChangeException extends IllegalArgumentException{
    public NoStateChangeException(String message) {
        super(message);
    }
}
