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
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "a".repeat(201); // 201 characters, exceeds max length
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid descriptions - exceeds 200 characters
        assertFalse(Description.isValidDescription("a".repeat(201))); // 201 characters

        // valid descriptions
        assertTrue(Description.isValidDescription("")); // empty string is valid
        assertTrue(Description.isValidDescription(" ")); // spaces only is valid
        assertTrue(Description.isValidDescription("Blk 456, Den Road, #01-355"));
        assertTrue(Description.isValidDescription("-")); // one character
        // long description
        assertTrue(Description.isValidDescription("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA"));
        assertTrue(Description.isValidDescription("a".repeat(200))); // exactly 200 characters is valid
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
