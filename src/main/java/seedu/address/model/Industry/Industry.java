package seedu.address.model.Industry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship Application's Industry in the BizBook tracker.
 * Guarantees: immutable; value is valid as declared in {@link #isValidIndustry(String)}
 */
public class Industry {

    // NOTE: The message constraints below have been updated to match the values defined in VALID_INDUSTRIES.
    public static final String MESSAGE_CONSTRAINTS = "Invalid Industry. Please use one of: Technology, Finance, Consulting, Healthcare, Marketing, Operations, Graphic Design.";

    // The predefined list of valid industries, used for validation.
    public static final String[] VALID_INDUSTRIES = {
            "Technology", "Finance", "Consulting", "Healthcare", "Marketing", "Operations", "Graphic Design"
    };

    public final String value;

    /**
     * Constructs an {@code Industry}.
     *
     * @param industryName A valid industry name from the predefined list.
     */
    public Industry(String industryName) {
        requireNonNull(industryName);
        checkArgument(isValidIndustry(industryName), MESSAGE_CONSTRAINTS);
        this.value = industryName;
    }

    /**
     * Returns true if a given string is one of the valid industry names.
     */
    public static boolean isValidIndustry(String test) {
        for (String validIndustry : VALID_INDUSTRIES) {
            // Checks for an exact match against the predefined list.
            if (validIndustry.equals(test)) {
                return true;
            }
        }
        return false;
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
        if (!(other instanceof Industry)) {
            return false;
        }

        Industry otherIndustry = (Industry) other;
        // Compares the internal string value for equality
        return value.equals(otherIndustry.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}