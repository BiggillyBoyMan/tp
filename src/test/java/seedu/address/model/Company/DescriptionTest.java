package seedu.address.model.Company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Description.isValidAddress(null));

        // invalid addresses
        assertFalse(Description.isValidAddress("")); // empty string
        assertFalse(Description.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(Description.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Description.isValidAddress("-")); // one character
        assertTrue(Description.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }

    @Test
    public void equals() {
        Description description = new Description("Valid Address");

        // same values -> returns true
        assertTrue(description.equals(new Description("Valid Address")));

        // same object -> returns true
        assertTrue(description.equals(description));

        // null -> returns false
        assertFalse(description.equals(null));

        // different types -> returns false
        assertFalse(description.equals(5.0f));

        // different values -> returns false
        assertFalse(description.equals(new Description("Other Valid Address")));
    }
}
