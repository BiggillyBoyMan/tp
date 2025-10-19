package seedu.address.model.company;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship Application's optional description in the address book.
 * Guarantees: immutable; satisfies the length constraint as declared in {@link #isValidDescription(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS = "Description cannot exceed 200 characters";
    public static final int MAX_LENGTH = 200;

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public Description(String address) {
        requireNonNull(address);
        checkArgument(isValidDescription(address), MESSAGE_CONSTRAINTS);
        value = address;
    }
    /**
     * Returns true if a given string is a valid description.
     * The only constraint is the maximum length of 200 characters.
     */
    public static boolean isValidDescription(String test) {
        return test.length() <= MAX_LENGTH;
    }
    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Description)) {
            return false;
        }

        Description otherDescription = (Description) other;
        return value.equals(otherDescription.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
