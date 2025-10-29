package seedu.address.model.industry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IndustryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Industry(null));
    }

    @Test
    public void isValidIndustry() {

        // invalid industries
        assertFalse(Industry.isValidIndustry("")); // empty string
        assertFalse(Industry.isValidIndustry(" ")); // spaces only
        assertFalse(Industry.isValidIndustry("Science")); // not in valid list
        assertFalse(Industry.isValidIndustry("Engineering")); // not in valid list

        // valid industries (exact match, case-insensitive)
        assertTrue(Industry.isValidIndustry("Technology"));
        assertTrue(Industry.isValidIndustry("Finance"));
        assertTrue(Industry.isValidIndustry("Consulting"));
        assertTrue(Industry.isValidIndustry("Healthcare"));
        assertTrue(Industry.isValidIndustry("Marketing"));
        assertTrue(Industry.isValidIndustry("Operations"));
        assertTrue(Industry.isValidIndustry("Graphic Design"));

        // Test case insensitivity
        assertTrue(Industry.isValidIndustry("TECHNOLOGY"));
        assertTrue(Industry.isValidIndustry("finance"));
        assertTrue(Industry.isValidIndustry("CoNsUlTiNg"));

        // Test with whitespace
        assertTrue(Industry.isValidIndustry("  Technology  ")); // leading/trailing spaces
    }

    @Test
    public void equals() {
        Industry industry = new Industry("Technology");

        // same values -> returns true
        assertTrue(industry.equals(new Industry("Technology")));

        // same object -> returns true
        assertTrue(industry.equals(industry));

        // null -> returns false
        assertFalse(industry.equals(null));

        // different types -> returns false
        assertFalse(industry.equals(5.0f));

        // different values -> returns false
        assertFalse(industry.equals(new Industry("Finance")));
    }

    @Test
    public void hashCode_sameIndustry_sameHashCode() {
        Industry industry1 = new Industry("Finance");
        Industry industry2 = new Industry("Finance");

        assertTrue(industry1.hashCode() == industry2.hashCode());
    }

    @Test
    public void toString_correctStringRepresentation() {
        Industry industry = new Industry("Healthcare");
        assertTrue(industry.toString().equals("Healthcare"));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Industry(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Industry.isValidIndustry(null));
    }

    @Test
    public void constructor_caseInsensitiveInput_normalizedToProperCase() {
        // Test that lowercase input gets normalized
        Industry industry1 = new Industry("technology");
        assertTrue(industry1.toString().equals("Technology"));

        // Test that uppercase input gets normalized
        Industry industry2 = new Industry("FINANCE");
        assertTrue(industry2.toString().equals("Finance"));

        // Test that mixed case input gets normalized
        Industry industry3 = new Industry("CoNsUlTiNg");
        assertTrue(industry3.toString().equals("Consulting"));

        // Test that input with whitespace gets normalized
        Industry industry4 = new Industry("  Healthcare  ");
        assertTrue(industry4.toString().equals("Healthcare"));

        // Test that normalized industries are equal
        Industry industry5 = new Industry("marketing");
        Industry industry6 = new Industry("Marketing");
        assertTrue(industry5.equals(industry6));
    }
}
