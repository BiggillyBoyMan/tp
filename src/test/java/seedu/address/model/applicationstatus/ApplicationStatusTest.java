package seedu.address.model.applicationstatus;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ApplicationStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ApplicationStatus(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        String invalidStatus = "Pending"; // Not in valid list
        assertThrows(IllegalArgumentException.class, () -> new ApplicationStatus(invalidStatus));
    }

    @Test
    public void isValidStatus() {

        // invalid statuses
        assertFalse(ApplicationStatus.isValidStatus("")); // empty string
        assertFalse(ApplicationStatus.isValidStatus(" ")); // spaces only
        assertFalse(ApplicationStatus.isValidStatus("Pending")); // not in valid list
        assertFalse(ApplicationStatus.isValidStatus("In Progress")); // not in valid list
        assertFalse(ApplicationStatus.isValidStatus("saved")); // wrong case (case-sensitive)

        // valid statuses
        assertTrue(ApplicationStatus.isValidStatus("Saved"));
        assertTrue(ApplicationStatus.isValidStatus("Applied"));
        assertTrue(ApplicationStatus.isValidStatus("Interviewing"));
        assertTrue(ApplicationStatus.isValidStatus("Offer"));
        assertTrue(ApplicationStatus.isValidStatus("Rejected"));
    }

    @Test
    public void equals() {
        ApplicationStatus status = new ApplicationStatus("Applied");

        // same values -> returns true
        assertTrue(status.equals(new ApplicationStatus("Applied")));

        // same object -> returns true
        assertTrue(status.equals(status));

        // null -> returns false
        assertFalse(status.equals(null));

        // different types -> returns false
        assertFalse(status.equals(5.0f));

        // different values -> returns false
        assertFalse(status.equals(new ApplicationStatus("Saved")));
    }

    @Test
    public void hashCode_sameStatus_sameHashCode() {
        ApplicationStatus status1 = new ApplicationStatus("Applied");
        ApplicationStatus status2 = new ApplicationStatus("Applied");

        assertTrue(status1.hashCode() == status2.hashCode());
    }

    @Test
    public void toString_correctStringRepresentation() {
        ApplicationStatus status = new ApplicationStatus("Interviewing");
        assertTrue(status.toString().equals("Interviewing"));
    }
}
