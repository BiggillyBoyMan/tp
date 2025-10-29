package seedu.address.testutil;

import seedu.address.model.applicationstatus.ApplicationStatus;
import seedu.address.model.company.CompanyName;
import seedu.address.model.company.Deadline;
import seedu.address.model.company.Description;
import seedu.address.model.company.Email;
import seedu.address.model.company.InternshipApplication;
import seedu.address.model.company.JobType;
import seedu.address.model.industry.Industry;

/**
 * A utility class to help with building InternshipApplication objects.
 */
public class CompanyBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_JOB_TYPE = "Software Engineer";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_DESCRIPTION = "Backend microservices";
    public static final String DEFAULT_INDUSTRY = "Technology";
    public static final String DEFAULT_STATUS = "Saved";
    public static final String DEFAULT_DEADLINE = "2024-12-31";

    private CompanyName companyName;
    private JobType jobType;
    private Email email;
    private Description description;
    private Industry industry;
    private ApplicationStatus status;
    private Deadline deadline;

    /**
     * Creates a {@code CompanyBuilder} with the default details.
     */
    public CompanyBuilder() {
        companyName = new CompanyName(DEFAULT_NAME);
        jobType = new JobType(DEFAULT_JOB_TYPE);
        email = new Email(DEFAULT_EMAIL);
        description = new Description(DEFAULT_DESCRIPTION);
        industry = new Industry(DEFAULT_INDUSTRY);
        status = new ApplicationStatus(DEFAULT_STATUS);
        deadline = new Deadline(DEFAULT_DEADLINE);
    }

    /**
     * Initializes the CompanyBuilder with the data of {@code applicationToCopy}.
     */
    public CompanyBuilder(InternshipApplication applicationToCopy) {
        companyName = applicationToCopy.getName();
        jobType = applicationToCopy.getJobType();
        email = applicationToCopy.getEmail();
        description = applicationToCopy.getDescription();
        industry = applicationToCopy.getIndustry();
        status = applicationToCopy.getStatus();
        deadline = applicationToCopy.getDeadline();
    }

    /**
     * Sets the {@code CompanyName} of the {@code InternshipApplication} that we are building.
     */
    public CompanyBuilder withName(String name) {
        this.companyName = new CompanyName(name);
        return this;
    }

    /**
     * Sets the {@code JobType} of the {@code InternshipApplication} that we are building.
     */
    public CompanyBuilder withJobType(String jobType) {
        this.jobType = new JobType(jobType);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code InternshipApplication} that we are building.
     */
    public CompanyBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code InternshipApplication} that we are building.
     */
    public CompanyBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Industry} of the {@code InternshipApplication} that we are building.
     */
    public CompanyBuilder withIndustry(String industry) {
        this.industry = new Industry(industry);
        return this;
    }

    /**
     * Sets the {@code ApplicationStatus} of the {@code InternshipApplication} that we are building.
     */
    public CompanyBuilder withStatus(String status) {
        this.status = new ApplicationStatus(status);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code InternshipApplication} that we are building.
     */
    public CompanyBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    public InternshipApplication build() {
        return new InternshipApplication(companyName, industry, jobType, description, status, email, deadline);
    }
}
