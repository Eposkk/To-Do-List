package ntnu.team1.backend.exceptions;

/**
 * Exception class, called when something cant be removed
 */

public class RemoveException extends Exception {
    /**
     * Id
     */
    private static final long serialVersionUID = 1L;

    /**
     * Gets thrown if it cant be removed
     * @param e String
     */
    public RemoveException(String e){super(e);}
}
