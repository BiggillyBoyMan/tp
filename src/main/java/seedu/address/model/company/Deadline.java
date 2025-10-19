package seedu.address.model.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline date for an internship application.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline {

    public static final String MESSAGE_CONSTRAINTS =
            "Deadline should be a valid date in the format YYYY-MM-DD (e.g., 2024-12-31)";
    public static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public final LocalDate value;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline date string.
     */
    public Deadline(String deadline) {
        assert deadline != null : "Deadline string cannot be null";
        assert isValidDeadline(deadline) : "Deadline must be in valid format YYYY-MM-DD";
        value = LocalDate.parse(deadline, INPUT_FORMAT);
    }

    /**
     * Constructs a {@code Deadline} from a LocalDate.
     *
     * @param date A valid LocalDate.
     */
    public Deadline(LocalDate date) {
        assert date != null : "LocalDate cannot be null";
        value = date;
    }

    /**
     * Returns true if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String test) {
        assert test != null : "Test string cannot be null";
        try {
            LocalDate.parse(test, INPUT_FORMAT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns the deadline in storage format (YYYY-MM-DD).
     */
    public String toStorageString() {
        return value.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return value.format(DISPLAY_FORMAT);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Deadline)) {
            return false;
        }

        Deadline otherDeadline = (Deadline) other;
        return value.equals(otherDeadline.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

