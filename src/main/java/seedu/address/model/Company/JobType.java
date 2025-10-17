package seedu.address.model.Company;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship Application's Job Type (Title) in the address book.
 * Guarantees: immutable; value is valid as declared in {@link #isValidJobType(String)}
 */
public class JobType {


    public static final String MESSAGE_CONSTRAINTS =
            "Job types should only contain alphanumeric characters and spaces, and should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public static final int MAX_LENGTH = 50;
    public final String value;

    /**
     * Constructs a {@code JobType}.
     *
     * @param JobType A valid Job Type.
     */
    public JobType(String JobType) {
        requireNonNull(JobType);
        checkArgument(isValidJobType(JobType), MESSAGE_CONSTRAINTS);
        value = JobType;
    }

    /**
     * Returns true if a given string is a valid job type.
     */
    public static boolean isValidJobType(String test) {
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
        if (!(other instanceof JobType)) {
            return false;
        }

        JobType otherJobType = (JobType) other;
        return value.equals(otherJobType.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
