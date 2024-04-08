package seedu.address.model.house;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents a House's Price amount.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class Price implements Comparable<Price> {
    public static final String MESSAGE_CONSTRAINTS = "Price should be a positive number.";
    public static final String VALIDATION_REGEX = "\\d+(\\.\\d{1,2})?"; // Allow up to 2 decimal places
    public static final BigDecimal MAX_PRICE = new BigDecimal("1000000000000");
    public final BigDecimal value;

    /**
     * Constructs a {@code Price}.
     *
     * @param price A valid price amount.
     */
    public Price(String price) {
        requireNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        this.value = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP); // Round to 2 decimal places
    }

    /**
     * Returns true if a given String is a valid price amount.
     */
    public static boolean isValidPrice(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false; // Not a valid number format
        }
        BigDecimal priceValue = new BigDecimal(test);
        return priceValue.compareTo(BigDecimal.ZERO) > 0 && priceValue.compareTo(MAX_PRICE) <= 0;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Price)) {
            return false;
        }

        Price otherPrice = (Price) other;
        return value.equals(otherPrice.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Price other) {
        return value.compareTo(other.value);
    }
}
