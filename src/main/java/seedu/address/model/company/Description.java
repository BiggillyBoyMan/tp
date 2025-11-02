package seedu.address.model.company;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship Application's description in BizBook.
 * Guarantees: immutable; satisfies the length constraint as declared in {@link #isValidDescription(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS = "Description should not be empty and cannot exceed 200 characters";
    public static final int MAX_LENGTH = 200;

    public final String value;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        value = description;
    }
    /**
     * Returns true if a given string is a valid description.
     * A valid description must not be blank (after trimming) and cannot exceed 200 characters.
     */
    public static boolean isValidDescription(String test) {
        return !test.trim().isEmpty() && test.length() <= MAX_LENGTH;
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
