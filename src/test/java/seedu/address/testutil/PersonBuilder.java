package seedu.address.testutil;

import seedu.address.model.ApplicationStatus.ApplicationStatus;
import seedu.address.model.Company.CompanyName;
import seedu.address.model.Company.Description;
import seedu.address.model.Company.Email;
import seedu.address.model.Company.InternshipApplication;
import seedu.address.model.Company.JobType;
import seedu.address.model.Industry.Industry;

/**
 * A utility class to help with building InternshipApplication objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_JOB_TYPE = "Software Engineer";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_DESCRIPTION = "Backend microservices";
    public static final String DEFAULT_INDUSTRY = "Technology";
    public static final String DEFAULT_STATUS = "Saved";

    private CompanyName companyName;
    private JobType jobType;
    private Email email;
    private Description description;
    private Industry industry;
    private ApplicationStatus status;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        companyName = new CompanyName(DEFAULT_NAME);
        jobType = new JobType(DEFAULT_JOB_TYPE);
        email = new Email(DEFAULT_EMAIL);
        description = new Description(DEFAULT_DESCRIPTION);
        industry = new Industry(DEFAULT_INDUSTRY);
        status = new ApplicationStatus(DEFAULT_STATUS);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code applicationToCopy}.
     */
    public PersonBuilder(InternshipApplication applicationToCopy) {
        companyName = applicationToCopy.getName();
        jobType = applicationToCopy.getJobType();
        email = applicationToCopy.getEmail();
        description = applicationToCopy.getDescription();
        industry = applicationToCopy.getIndustry();
        status = applicationToCopy.getStatus();
    }

    /**
     * Sets the {@code CompanyName} of the {@code InternshipApplication} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.companyName = new CompanyName(name);
        return this;
    }

    /**
     * Sets the {@code JobType} of the {@code InternshipApplication} that we are building.
     */
    public PersonBuilder withJobType(String jobType) {
        this.jobType = new JobType(jobType);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code InternshipApplication} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code InternshipApplication} that we are building.
     */
    public PersonBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Industry} of the {@code InternshipApplication} that we are building.
     */
    public PersonBuilder withIndustry(String industry) {
        this.industry = new Industry(industry);
        return this;
    }

    /**
     * Sets the {@code ApplicationStatus} of the {@code InternshipApplication} that we are building.
     */
    public PersonBuilder withStatus(String status) {
        this.status = new ApplicationStatus(status);
        return this;
    }

    public InternshipApplication build() {
        return new InternshipApplication(companyName, industry, jobType, description, status, email);
    }
}
