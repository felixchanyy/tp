package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in Missing houses
 * identity).
 */
public class MissingHouseException extends RuntimeException {
    public MissingHouseException() {
        super("Seller does not own this house!");
    }
}
