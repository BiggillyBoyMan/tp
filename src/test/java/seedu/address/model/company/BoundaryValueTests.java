package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


/**
 * Boundary value tests for company-related value objects:
 * Description, CompanyName, JobType and Email.
 *
 * These tests cover edge cases such as empty strings and maximum lengths.
 */
public class BoundaryValueTests {


    /**
     * Tests that a description exactly at the maximum allowed length (200)
     * is considered valid and can be constructed.
     */
    @Test
    public void description_exactlyAtMaxLength_valid() {
        // Description max length is 200 characters
        String exactlyMaxLength = "a".repeat(200);
        assertTrue(Description.isValidDescription(exactlyMaxLength));

        // Should successfully create Description
        Description description = new Description(exactlyMaxLength);
        assertTrue(description.value.length() == 200);
    }


    /**
     * Tests that a description one character over the maximum length (201)
     * is invalid and construction throws an IllegalArgumentException.
     */
    @Test
    public void description_oneCharacterOverMaxLength_invalid() {
        // 201 characters - exceeds max
        String overMaxLength = "a".repeat(201);
        assertFalse(Description.isValidDescription(overMaxLength));

        // Should throw exception
        assertThrows(IllegalArgumentException.class, () -> new Description(overMaxLength));
    }

    /**
     * Tests that a description one character under the maximum length (199)
     * is valid and can be constructed.
     */
    @Test
    public void description_oneCharacterUnderMaxLength_valid() {
        // 199 characters - under max
        String underMaxLength = "a".repeat(199);
        assertTrue(Description.isValidDescription(underMaxLength));

        Description description = new Description(underMaxLength);
        assertTrue(description.value.length() == 199);
    }

    /**
     * Tests that an empty description is invalid and construction throws an IllegalArgumentException.
     */
    @Test
    public void description_emptyString_invalid() {
        // Empty descriptions are not allowed
        assertFalse(Description.isValidDescription(""));
        assertFalse(Description.isValidDescription("   "));

        // Should throw exception
        assertThrows(IllegalArgumentException.class, () -> new Description(""));
        assertThrows(IllegalArgumentException.class, () -> new Description("   "));
    }

    /**
     * Tests that a single-character description is valid.
     */
    @Test
    public void description_singleCharacter_valid() {
        // Minimum non-empty length
        assertTrue(Description.isValidDescription("a"));

        Description description = new Description("a");
        assertTrue(description.value.equals("a"));
    }

    /**
     * Tests that an empty company name is invalid and construction throws.
     */
    @Test
    public void companyName_emptyString_invalid() {
        // Company name cannot be empty
        assertFalse(CompanyName.isValidName(""));
        assertThrows(IllegalArgumentException.class, () -> new CompanyName(""));
    }

    /**
     * Tests that a single character company name is valid.
     */
    @Test
    public void companyName_singleCharacter_valid() {
        // Single character is valid
        assertTrue(CompanyName.isValidName("A"));

        CompanyName name = new CompanyName("A");
        assertTrue(name.value.equals("A"));
    }

    /**
     * Tests that a company name starting with a special character is invalid.
     */
    @Test
    public void companyName_specialCharactersAtStart_invalid() {
        // Cannot start with special character
        assertFalse(CompanyName.isValidName("@Google"));
        assertThrows(IllegalArgumentException.class, () -> new CompanyName("@Google"));
    }

    /**
     * Tests that an empty job type is invalid and construction throws.
     */
    @Test
    public void jobType_emptyString_invalid() {
        assertFalse(JobType.isValidJobType(""));
        assertThrows(IllegalArgumentException.class, () -> new JobType(""));
    }

    /**
     * Tests that a single character job type is valid.
     */
    @Test
    public void jobType_singleCharacter_valid() {
        assertTrue(JobType.isValidJobType("A"));

        JobType jobType = new JobType("A");
        assertTrue(jobType.value.equals("A"));
    }

    /**
     * Tests that a job type at the maximum allowed length (50) is valid.
     */
    @Test
    public void jobType_maxLength_valid() {
        // JobType has max length of 50
        String maxLength = "a".repeat(50);
        assertTrue(JobType.isValidJobType(maxLength));

        JobType jobType = new JobType(maxLength);
        assertTrue(jobType.value.length() == 50);
    }

    /**
     * Tests that the minimum valid email with proper TLD (e.g., a@b.co) is accepted.
     */
    @Test
    public void email_minimumValid_accepted() {
        // Minimum valid email with TLD: a@b.co (6 characters)
        assertTrue(Email.isValidEmail("a@b.co"));

        Email email = new Email("a@b.co");
        assertTrue(email.value.equals("a@b.co"));
    }

    /**
     * Tests that an email with a very long local part is still valid.
     */
    @Test
    public void email_veryLongLocalPart_valid() {
        // Long local part should be valid
        String longLocal = "a".repeat(50);
        String longEmail = longLocal + "@example.com";
        assertTrue(Email.isValidEmail(longEmail));

        Email email = new Email(longEmail);
        assertTrue(email.value.equals(longEmail));
    }

    /**
     * Tests that a domain ending with at least two characters is valid.
     */
    @Test
    public void email_domainWithMinimumTwoChars_valid() {
        // Domain must end with at least 2 characters
        assertTrue(Email.isValidEmail("test@example.co"));
    }

    /**
     * Tests that a domain ending with only one character is invalid.
     */
    @Test
    public void email_domainWithOneChar_invalid() {
        // Domain ending with 1 character is invalid
        assertFalse(Email.isValidEmail("test@example.c"));
    }
}
