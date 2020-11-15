package ua.kiev.prog.exceptions;

public class CommandErrorException extends RuntimeException {
    public CommandErrorException(String message) {
        super(message);
    }
}
