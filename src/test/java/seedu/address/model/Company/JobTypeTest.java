package seedu.address.model.Company;

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
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new JobType(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> JobType.isValidJobType(null));

        // invalid phone numbers
        assertFalse(JobType.isValidJobType("")); // empty string
        assertFalse(JobType.isValidJobType(" ")); // spaces only
        assertFalse(JobType.isValidJobType("91")); // less than 3 numbers
        assertFalse(JobType.isValidJobType("phone")); // non-numeric
        assertFalse(JobType.isValidJobType("9011p041")); // alphabets within digits
        assertFalse(JobType.isValidJobType("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(JobType.isValidJobType("911")); // exactly 3 numbers
        assertTrue(JobType.isValidJobType("93121534"));
        assertTrue(JobType.isValidJobType("124293842033123")); // long phone numbers
    }

    @Test
    public void equals() {
        JobType jobType = new JobType("999");

        // same values -> returns true
        assertTrue(jobType.equals(new JobType("999")));

        // same object -> returns true
        assertTrue(jobType.equals(jobType));

        // null -> returns false
        assertFalse(jobType.equals(null));

        // different types -> returns false
        assertFalse(jobType.equals(5.0f));

        // different values -> returns false
        assertFalse(jobType.equals(new JobType("995")));
    }
}
