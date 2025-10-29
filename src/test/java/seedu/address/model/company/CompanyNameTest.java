package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompanyNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompanyName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new CompanyName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> CompanyName.isValidName(null));

        // invalid name
        assertFalse(CompanyName.isValidName("")); // empty string
        assertFalse(CompanyName.isValidName(" ")); // spaces only
        assertFalse(CompanyName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(CompanyName.isValidName("peter*")); // contains invalid special characters
        assertFalse(CompanyName.isValidName("peter@jack")); // @ is not allowed
        assertFalse(CompanyName.isValidName("Company#1")); // # is not allowed

        // valid name
        assertTrue(CompanyName.isValidName("peter jack")); // alphabets only
        assertTrue(CompanyName.isValidName("12345")); // numbers only
        assertTrue(CompanyName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(CompanyName.isValidName("Capital Tan")); // with capital letters
        assertTrue(CompanyName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names

        // valid names with allowed special characters
        assertTrue(CompanyName.isValidName("AT&T")); // ampersand
        assertTrue(CompanyName.isValidName("Procter & Gamble")); // ampersand with spaces
        assertTrue(CompanyName.isValidName("McDonald's")); // apostrophe
        assertTrue(CompanyName.isValidName("H&M")); // ampersand
        assertTrue(CompanyName.isValidName("Johnson & Johnson")); // ampersand with spaces
        assertTrue(CompanyName.isValidName("Apple Inc.")); // period
        assertTrue(CompanyName.isValidName("Coca-Cola")); // hyphen
        assertTrue(CompanyName.isValidName("Ernst & Young, LLP")); // comma and ampersand
        assertTrue(CompanyName.isValidName("PwC-Singapore")); // hyphen
        assertTrue(CompanyName.isValidName("Ben's Company")); // apostrophe in regular name
    }

    @Test
    public void equals() {
        CompanyName companyName = new CompanyName("Valid Name");

        // same values -> returns true
        assertTrue(companyName.equals(new CompanyName("Valid Name")));

        // same object -> returns true
        assertTrue(companyName.equals(companyName));

        // null -> returns false
        assertFalse(companyName.equals(null));

        // different types -> returns false
        assertFalse(companyName.equals(5.0f));

        // different values -> returns false
        assertFalse(companyName.equals(new CompanyName("Other Valid Name")));
    }
}
