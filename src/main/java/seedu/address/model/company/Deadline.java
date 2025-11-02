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
            "Deadline should be in YYYY-MM-DD format and between 2020-01-01 and before 2030-01-01.";
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
            LocalDate parsedDate = LocalDate.parse(test, INPUT_FORMAT);
            // Strict validation: ensure the parsed date matches the input exactly
            String reformattedDate = parsedDate.format(INPUT_FORMAT);
            if (!test.equals(reformattedDate)) {
                return false;
            }
            LocalDate minAllowed = LocalDate.of(2020, 1, 1);
            LocalDate maxAllowed = LocalDate.of(2030, 1, 1);
            return !parsedDate.isBefore(minAllowed) && parsedDate.isBefore(maxAllowed);
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

