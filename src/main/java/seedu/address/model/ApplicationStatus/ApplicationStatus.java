package seedu.address.model.ApplicationStatus;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the current status of an Internship Application in the tracker.
 * Guarantees: immutable; value is valid as declared in {@link #isValidStatus(String)}
 */
public class ApplicationStatus {

    public static final String MESSAGE_CONSTRAINTS = "Invalid status. Please use one of: "
            + "Saved, Applied, Interviewing, Offer, Rejected.";

    // The predefined list of valid application statuses.
    public static final String[] VALID_STATUSES = {
        "Saved", "Applied", "Interviewing", "Offer", "Rejected"
    };
    public static final String DEFAULT_STATUS = "Saved";

    public final String value;

    /**
     * Constructs an {@code ApplicationStatus}.
     *
     * @param statusName A valid application status name.
     */
    public ApplicationStatus(String statusName) {
        requireNonNull(statusName);
        checkArgument(isValidStatus(statusName), MESSAGE_CONSTRAINTS);
        this.value = statusName;
    }

    /**
     * Returns true if a given string is one of the valid application statuses.
     */
    public static boolean isValidStatus(String test) {
        for (String validStatus : VALID_STATUSES) {
            if (validStatus.equals(test)) {
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
        if (!(other instanceof ApplicationStatus)) {
            return false;
        }

        ApplicationStatus otherStatus = (ApplicationStatus) other;
        return value.equals(otherStatus.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
