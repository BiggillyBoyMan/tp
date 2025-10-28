package seedu.address.model.company;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class CompanyName {

    public static final String MESSAGE_CONSTRAINTS =
            "Company names should only contain alphanumeric characters, spaces, and common special characters "
            + "(& . - ' ,), and it should not be blank";

    /*
     * The first character must not be a whitespace, otherwise " " (a blank string) becomes a valid input.
     * Allows alphanumeric characters and common special characters: & . - ' ,
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} &.,'\\-]*";

    public final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public CompanyName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.value = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
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
        if (!(other instanceof seedu.address.model.company.CompanyName)) {
            return false;
        }

        seedu.address.model.company.CompanyName otherCompanyName = (seedu.address.model.company.CompanyName) other;
        return value.equals(otherCompanyName.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
