package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void isValidDeadline() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String tomorrow = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String yesterday = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Valid deadlines (today or future dates before 2030-01-01)
        assertTrue(Deadline.isValidDeadline(today)); // Today
        assertTrue(Deadline.isValidDeadline(tomorrow)); // Tomorrow
        assertTrue(Deadline.isValidDeadline("2029-12-31")); // Just before limit

        // Invalid deadlines
        assertFalse(Deadline.isValidDeadline(yesterday)); // Yesterday (past date)
        assertFalse(Deadline.isValidDeadline("1990-01-01")); // Past date
        assertFalse(Deadline.isValidDeadline("2030-01-01")); // Exactly at limit (not before)
        assertFalse(Deadline.isValidDeadline("2030-12-31")); // After limit
        assertFalse(Deadline.isValidDeadline("9999-12-31")); // Far future date
        assertFalse(Deadline.isValidDeadline("2024-13-01")); // Invalid month
        assertFalse(Deadline.isValidDeadline("2024-12-32")); // Invalid day
        assertFalse(Deadline.isValidDeadline("24-12-31")); // Wrong format
        assertFalse(Deadline.isValidDeadline("31-12-2024")); // Wrong format
        assertFalse(Deadline.isValidDeadline("")); // Empty string
        assertFalse(Deadline.isValidDeadline("not-a-date")); // Non-date string
    }
}
