package mk.ukim.finki.wp.lab.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException() {
        super("Wrong password entered.");
    }
}
