package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ApplicationStatus.ApplicationStatus;
import seedu.address.model.Company.CompanyName;
import seedu.address.model.Company.Description;
import seedu.address.model.Company.Email;
import seedu.address.model.Company.JobType;
import seedu.address.model.Industry.Industry;

public class ParserUtilTest {
    // Renamed constants for clarity and alignment with model fields
    private static final String INVALID_COMPANY_NAME = "R@chel";
    private static final String INVALID_JOB_TYPE = "S.E. Intern!"; // Example invalid JobType (symbols)
    private static final String INVALID_DESCRIPTION = "a".repeat(201); // 201 characters, exceeds max length of 200
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_INDUSTRY = "InvalidIndustryName"; // Not in predefined list
    private static final String INVALID_STATUS = "In Progress"; // Not in predefined list

    private static final String VALID_COMPANY_NAME = "Rachel Walker Co";
    private static final String VALID_JOB_TYPE = "Software Engineer Intern";
    private static final String VALID_DESCRIPTION = "123 Main Street Office";
    private static final String VALID_EMAIL = "rachel@example.com";
    // Using actual valid values for Industry
    private static final String VALID_INDUSTRY_1 = "Technology";
    private static final String VALID_INDUSTRY_2 = "Finance";
    private static final String VALID_STATUS = "Applied";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
                -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    // ============================= COMPANY NAME TESTS (replaces parseName) ==============================

    @Test
    public void parseCompanyName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseCompanyName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_COMPANY_NAME));
    }

    @Test
    public void parseCompanyName_validValueWithoutWhitespace_returnsCompanyName() throws Exception {
        CompanyName expectedCompanyName = new CompanyName(VALID_COMPANY_NAME);
        assertEquals(expectedCompanyName, ParserUtil.parseName(VALID_COMPANY_NAME));
    }

    @Test
    public void parseCompanyName_validValueWithWhitespace_returnsTrimmedCompanyName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_COMPANY_NAME + WHITESPACE;
        CompanyName expectedCompanyName = new CompanyName(VALID_COMPANY_NAME);
        assertEquals(expectedCompanyName, ParserUtil.parseName(nameWithWhitespace));
    }

    // ============================= JOB TYPE TESTS (replaces parsePhone) ==============================

    @Test
    public void parseJobType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseJobType((String) null));
    }

    @Test
    public void parseJobType_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseJobType(INVALID_JOB_TYPE));
    }

    @Test
    public void parseJobType_validValueWithoutWhitespace_returnsJobType() throws Exception {
        JobType expectedJobType = new JobType(VALID_JOB_TYPE);
        assertEquals(expectedJobType, ParserUtil.parseJobType(VALID_JOB_TYPE));
    }

    @Test
    public void parseJobType_validValueWithWhitespace_returnsTrimmedJobType() throws Exception {
        String jobTypeWithWhitespace = WHITESPACE + VALID_JOB_TYPE + WHITESPACE;
        JobType expectedJobType = new JobType(VALID_JOB_TYPE);
        assertEquals(expectedJobType, ParserUtil.parseJobType(jobTypeWithWhitespace));
    }

    // ============================= DESCRIPTION TESTS (replaces parseAddress) ==============================

    @Test
    public void parseDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription((String) null));
    }

    @Test
    public void parseDescription_invalidValue_throwsParseException() {
        // Assuming your Description class constraints prevent empty strings, or strings that violate max length
        assertThrows(ParseException.class, () -> ParserUtil.parseDescription(INVALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithoutWhitespace_returnsDescription() throws Exception {
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(VALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithWhitespace_returnsTrimmedDescription() throws Exception {
        // NOTE: The description value often keeps all internal whitespace after trimming,
        // as implemented in Address.java
        String descriptionWithWhitespace = WHITESPACE + VALID_DESCRIPTION + WHITESPACE;
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(descriptionWithWhitespace));
    }

    // ============================= EMAIL TESTS (retains parseEmail) ==============================

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    // ============================= INDUSTRY TESTS (replaces parseTag/single value) ==============================

    @Test
    public void parseIndustry_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIndustry(null));
    }

    @Test
    public void parseIndustry_invalidValue_throwsParseException() {
        // Testing against the fixed set of valid industries
        assertThrows(ParseException.class, () -> ParserUtil.parseIndustry(INVALID_INDUSTRY));
    }

    @Test
    public void parseIndustry_validValueWithoutWhitespace_returnsIndustry() throws Exception {
        Industry expectedIndustry = new Industry(VALID_INDUSTRY_1);
        assertEquals(expectedIndustry, ParserUtil.parseIndustry(VALID_INDUSTRY_1));
    }

    @Test
    public void parseIndustry_validValueWithWhitespace_returnsTrimmedIndustry() throws Exception {
        String industryWithWhitespace = WHITESPACE + VALID_INDUSTRY_2 + WHITESPACE;
        Industry expectedIndustry = new Industry(VALID_INDUSTRY_2);
        assertEquals(expectedIndustry, ParserUtil.parseIndustry(industryWithWhitespace));
    }

    // ============================= STATUS TESTS (new method) ==============================

    @Test
    public void parseStatus_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStatus(null));
    }

    @Test
    public void parseStatus_invalidValue_throwsParseException() {
        // Testing against the fixed set of valid statuses
        assertThrows(ParseException.class, () -> ParserUtil.parseStatus(INVALID_STATUS));
    }

    @Test
    public void parseStatus_validValueWithoutWhitespace_returnsStatus() throws Exception {
        ApplicationStatus expectedStatus = new ApplicationStatus(VALID_STATUS);
        assertEquals(expectedStatus, ParserUtil.parseStatus(VALID_STATUS));
    }

    @Test
    public void parseStatus_validValueWithWhitespace_returnsTrimmedStatus() throws Exception {
        String statusWithWhitespace = WHITESPACE + VALID_STATUS + WHITESPACE;
        ApplicationStatus expectedStatus = new ApplicationStatus(VALID_STATUS);
        assertEquals(expectedStatus, ParserUtil.parseStatus(statusWithWhitespace));
    }

    // ============================= OBSOLETE COLLECTION TESTS (REMOVED) ==============================
    // The tests for parseTags_null, parseTags_collectionWithInvalidTags, parseTags_emptyCollection,
    // and parseTags_collectionWithValidTags have been removed as 'Industry' is a single field.
}
