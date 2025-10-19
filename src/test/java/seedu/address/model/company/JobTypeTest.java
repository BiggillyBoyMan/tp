package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class JobTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JobType(null));
    }

    @Test
    public void constructor_invalidJobType_throwsIllegalArgumentException() {
        String invalidJobType = "";
        assertThrows(IllegalArgumentException.class, () -> new JobType(invalidJobType));
    }

    @Test
    public void isValidJobType() {
        // null job type
        assertThrows(NullPointerException.class, () -> JobType.isValidJobType(null));

        // invalid job types
        assertFalse(JobType.isValidJobType("")); // empty string
        assertFalse(JobType.isValidJobType(" ")); // spaces only
        assertFalse(JobType.isValidJobType("Software@Engineer")); // contains special characters
        assertFalse(JobType.isValidJobType("Data-Analyst")); // contains dash

        // valid job types
        assertTrue(JobType.isValidJobType("Software Engineer")); // alphanumeric with spaces
        assertTrue(JobType.isValidJobType("SWE Intern")); // alphanumeric with spaces
        assertTrue(JobType.isValidJobType("Data Scientist 2023")); // alphanumeric with numbers and spaces
        assertTrue(JobType.isValidJobType("Intern")); // single word
    }

    @Test
    public void equals() {
        JobType jobType = new JobType("Software Engineer");

        // same values -> returns true
        assertTrue(jobType.equals(new JobType("Software Engineer")));

        // same object -> returns true
        assertTrue(jobType.equals(jobType));

        // null -> returns false
        assertFalse(jobType.equals(null));

        // different types -> returns false
        assertFalse(jobType.equals(5.0f));

        // different values -> returns false
        assertFalse(jobType.equals(new JobType("Data Analyst")));
    }
}
