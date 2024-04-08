package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.math.BigDecimal;
import java.math.RoundingMode;

import seedu.address.model.house.Price;

/**
 * Represents a Buyer's budget in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBudget(String)}
 */
public class Budget {

    public static final String MESSAGE_CONSTRAINTS = "Budget should be positive number.";
    public static final String VALIDATION_REGEX = "\\d+(\\.\\d{1,2})?"; // Allow up to 2 decimal places
    public final BigDecimal value;

    /**
     * Constructs a {@code Budget}.
     *
     * @param budget A valid budget amount.
     */
    public Budget(String budget) {
        requireNonNull(budget);
        checkArgument(isValidBudget(budget), MESSAGE_CONSTRAINTS);
        this.value = new BigDecimal(budget).setScale(2, RoundingMode.HALF_UP); // Round to 2 decimal places
    }

    /**
     * Returns true if a given string is a valid budget amount.
     */
    public static boolean isValidBudget(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false; // Not a valid number format
        }
        BigDecimal budgetValue = new BigDecimal(test);
        return budgetValue.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * Converts this budget to a Price.
     *
     * @return The Price equivalent of this budget.
     */
    public Price toPrice() {
        return new Price(value.toString());
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
        if (!(other instanceof Budget)) {
            return false;
        }

        Budget otherBudget = (Budget) other;
        return value.equals(otherBudget.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
