package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.applicationstatus.ApplicationStatus;
import seedu.address.model.company.CompanyName;
import seedu.address.model.company.Deadline;
import seedu.address.model.company.Description;
import seedu.address.model.company.Email;
import seedu.address.model.company.InternshipApplication;
import seedu.address.model.company.JobType;
import seedu.address.model.industry.Industry;

/**
 * Jackson-friendly version of {@link InternshipApplication}.
 * This class maps the 5 core application fields to simple String fields for JSON serialization.
 */
class JsonAdaptedCompany {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Application's %s field is missing!";

    // Mapped fields corresponding to the core model fields
    private final String companyName;
    private final String email;
    private final String industry;
    private final String jobType;
    private final String description;
    private final String status;
    private final String deadline;

    /**
     * Constructs a {@code JsonAdaptedInternshipApplication} with the given application details.
     * This constructor is used by Jackson when reading from the JSON file (Deserialization).
     */
    @JsonCreator
    public JsonAdaptedCompany(
            @JsonProperty("companyName") String companyName,
            @JsonProperty("Email") String email,
            @JsonProperty("industry") String industry,
            @JsonProperty("jobType") String jobType,
            @JsonProperty("description") String description,
            @JsonProperty("status") String status,
            @JsonProperty("deadline") String deadline) {
        this.companyName = companyName;
        this.email = email;
        this.industry = industry;
        this.jobType = jobType;
        this.description = description;
        this.status = status;
        this.deadline = deadline;
    }

    /**
     * Converts a given {@code InternshipApplication} model object into this class for Jackson use.
     * This is used when writing to the JSON file (Serialization).
     */
    public JsonAdaptedCompany(InternshipApplication source) {
        // Map model object fields to JSON-friendly String fields
        companyName = source.getName().value;
        industry = source.getIndustry().value;
        jobType = source.getJobType().value;
        description = source.getDescription().value;
        status = source.getStatus().value;
        email = source.getEmail().value;
        deadline = source.getDeadline().toStorageString();
    }

    /**
     * Converts this Jackson-friendly adapted application object into the model's {@code InternshipApplication} object.
     * This is the core logic that reconstructs and validates the data upon reading from the JSON file.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted application.
     */
    public InternshipApplication toModelType() throws IllegalValueException {

        // 1. CompanyName (Required)
        if (companyName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompanyName.class.getSimpleName()));
        }
        // Assuming CompanyName.isValidName is the correct validation method.
        if (!CompanyName.isValidName(companyName)) {
            throw new IllegalValueException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        final CompanyName modelCompanyName = new CompanyName(companyName);

        // 2. Industry (Required)
        if (industry == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Industry.class.getSimpleName()));
        }
        if (!Industry.isValidIndustry(industry)) {
            throw new IllegalValueException(Industry.MESSAGE_CONSTRAINTS);
        }
        final Industry modelIndustry = new Industry(industry);

        // 3. JobType (Required)
        if (jobType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    JobType.class.getSimpleName()));
        }
        // Assuming JobType.isValidJobType is the correct validation method.
        if (!JobType.isValidJobType(jobType)) {
            throw new IllegalValueException(JobType.MESSAGE_CONSTRAINTS);
        }
        final JobType modelJobType = new JobType(jobType);

        // 4. Description (Required by Model, even if the user input is optional,
        // here we enforce Model non-null contract)
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        // Assuming Description.isValidDescription is the correct validation method.
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        // 5. ApplicationStatus (Required)
        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ApplicationStatus.class.getSimpleName()));
        }
        if (!ApplicationStatus.isValidStatus(status)) {
            throw new IllegalValueException(ApplicationStatus.MESSAGE_CONSTRAINTS);
        }
        final ApplicationStatus modelStatus = new ApplicationStatus(status);

        // 6. Email (Validation block)
        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        // 7. Deadline (Required)
        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }
        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline modelDeadline = new Deadline(deadline);

        // Return the reconstructed and validated model object
        return new InternshipApplication(modelCompanyName, modelIndustry, modelJobType,
                modelDescription, modelStatus, modelEmail, modelDeadline);
    }
}
