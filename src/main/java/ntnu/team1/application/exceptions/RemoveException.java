package ntnu.team1.application.exceptions;

/**
 * Exception class, called when something cant be removed
 */

public class RemoveException extends Exception {
    /**
     * Id
     */
    private static final long serialVersionUID = 1L;
    public RemoveException(String e){super(e);}
}
