package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedCompany.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ApplicationStatus.ApplicationStatus;
import seedu.address.model.Company.CompanyName;
import seedu.address.model.Company.Description;
import seedu.address.model.Company.Email;
import seedu.address.model.Company.JobType;
import seedu.address.model.Industry.Industry;


public class JsonAdaptedCompanyTest {
    // --- Invalid Test Data (Adjusted for clarity and model constraints) ---
    private static final String INVALID_COMPANY_NAME = "Googl@ &"; // Invalid symbol in name
    private static final String INVALID_INDUSTRY = "Science"; // Not a valid category
    private static final String INVALID_JOB_TYPE = "S.E Intern!"; // Invalid symbol
    private static final String INVALID_EMAIL = "example.com"; // Missing '@'
    private static final String INVALID_DESCRIPTION = "a".repeat(201); // Too long (max 200)
    private static final String INVALID_STATUS = "Hired"; // Not a valid status

    // --- Valid Test Data (Derived from BENSON mock object) ---
    // Using explicit field names and corresponding getters
    private static final String VALID_COMPANY_NAME = BENSON.getName().toString();
    private static final String VALID_INDUSTRY = BENSON.getIndustry().toString();
    private static final String VALID_JOB_TYPE = BENSON.getJobType().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_DESCRIPTION = BENSON.getDescription().toString();
    private static final String VALID_STATUS = BENSON.getStatus().toString();

    // Helper for creating an adapted object for tests
    // Constructor order: companyName, email, industry, jobType, description, status
    private JsonAdaptedCompany getValidAdaptedApplication() {
        return new JsonAdaptedCompany(
                VALID_COMPANY_NAME, VALID_EMAIL, VALID_INDUSTRY, VALID_JOB_TYPE, VALID_DESCRIPTION, VALID_STATUS
        );
    }

    @Test
    public void toModelType_validApplicationDetails_returnsApplication() throws Exception {
        JsonAdaptedCompany application = new JsonAdaptedCompany(BENSON);
        assertEquals(BENSON, application.toModelType());
    }

    // ============================= COMPANY NAME TESTS (n/) ==============================

    @Test
    public void toModelType_invalidCompanyName_throwsIllegalValueException() {
        JsonAdaptedCompany application =
                new JsonAdaptedCompany(INVALID_COMPANY_NAME, VALID_EMAIL, VALID_INDUSTRY, VALID_JOB_TYPE,
                        VALID_DESCRIPTION, VALID_STATUS);
        String expectedMessage = CompanyName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullCompanyName_throwsIllegalValueException() {
        JsonAdaptedCompany application =
                new JsonAdaptedCompany(null, VALID_EMAIL, VALID_INDUSTRY, VALID_JOB_TYPE,
                        VALID_DESCRIPTION, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CompanyName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    // ============================= INDUSTRY TESTS (i/) ==============================

    @Test
    public void toModelType_invalidIndustry_throwsIllegalValueException() {
        JsonAdaptedCompany application =
                new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_EMAIL, INVALID_INDUSTRY, VALID_JOB_TYPE,
                        VALID_DESCRIPTION, VALID_STATUS);
        String expectedMessage = Industry.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullIndustry_throwsIllegalValueException() {
        JsonAdaptedCompany application =
                new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_EMAIL, null, VALID_JOB_TYPE,
                        VALID_DESCRIPTION, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Industry.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    // ============================= JOB TYPE TESTS (j/) ==============================

    @Test
    public void toModelType_invalidJobType_throwsIllegalValueException() {
        JsonAdaptedCompany application =
                new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_EMAIL, VALID_INDUSTRY, INVALID_JOB_TYPE,
                        VALID_DESCRIPTION, VALID_STATUS);
        String expectedMessage = JobType.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullJobType_throwsIllegalValueException() {
        JsonAdaptedCompany application =
                new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_EMAIL, VALID_INDUSTRY, null,
                        VALID_DESCRIPTION, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, JobType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    // ============================= EMAIL TESTS ==============================

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedCompany application =
                new JsonAdaptedCompany(VALID_COMPANY_NAME, INVALID_EMAIL, VALID_INDUSTRY, VALID_JOB_TYPE,
                        VALID_DESCRIPTION, VALID_STATUS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedCompany application =
                new JsonAdaptedCompany(VALID_COMPANY_NAME, null, VALID_INDUSTRY, VALID_JOB_TYPE,
                        VALID_DESCRIPTION, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    // ============================= DESCRIPTION TESTS (d/) ==============================

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedCompany application =
                new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_EMAIL, VALID_INDUSTRY, VALID_JOB_TYPE,
                        INVALID_DESCRIPTION, VALID_STATUS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedCompany application =
                new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_EMAIL, VALID_INDUSTRY, VALID_JOB_TYPE,
                        null, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    // ============================= STATUS TESTS (s/) ==============================

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedCompany application =
                new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_EMAIL, VALID_INDUSTRY, VALID_JOB_TYPE,
                        VALID_DESCRIPTION, INVALID_STATUS);
        String expectedMessage = ApplicationStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedCompany application =
                new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_EMAIL, VALID_INDUSTRY, VALID_JOB_TYPE,
                        VALID_DESCRIPTION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ApplicationStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }
}
