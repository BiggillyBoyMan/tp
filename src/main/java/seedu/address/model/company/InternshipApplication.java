package seedu.address.model.company;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.applicationstatus.ApplicationStatus;
import seedu.address.model.industry.Industry;

/**
 * Represents an Internship Application in BizBook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class InternshipApplication {

    // Identity fields
    private final CompanyName companyName;
    private final JobType jobType;
    private final Email email;

    // Data fields
    private final Description description;
    private final Industry industry;
    private final Deadline deadline;

    private final ApplicationStatus status;

    /**
     * Every field must be present and not null.
     */
    public InternshipApplication(CompanyName companyName, Industry industry, JobType jobType,
                                 Description description, ApplicationStatus status, Email email, Deadline deadline) {
        requireAllNonNull(companyName, industry, jobType, description, status, email, deadline);
        this.companyName = companyName;
        this.email = email;
        this.industry = industry; // Correctly assigned as a single object
        this.jobType = jobType;
        this.description = description;
        this.status = status;
        this.deadline = deadline;
    }


    public CompanyName getName() {
        return companyName;
    }

    public JobType getJobType() {
        return jobType;
    }

    public Email getEmail() {
        return email;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Industry getIndustry() {
        return industry;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public Deadline getDeadline() {
        return deadline;
    }



    /**
     * Returns true if both applications have the same company name, industry, and job type.
     * This defines a weaker notion of equality between two applications.
     * This allows multiple applications to the same company for different roles (different job types),
     * or the same role in different departments (different industries).
     * Description is not included in duplicate detection as it is free-form text that may vary
     * for the same job posting.
     * Comparisons are case-insensitive to prevent duplicates with different letter cases.
     */
    public boolean isSameApplication(InternshipApplication otherInternshipApplication) {
        if (otherInternshipApplication == this) {
            return true;
        }

        return otherInternshipApplication != null
                && otherInternshipApplication.getName().value.trim().equalsIgnoreCase(getName().value.trim())
                && otherInternshipApplication.getIndustry().equals(getIndustry())
                && otherInternshipApplication.getJobType().value.trim().equalsIgnoreCase(getJobType().value.trim());
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipApplication)) {
            return false;
        }

        InternshipApplication otherApplication = (InternshipApplication) other;
        return companyName.equals(otherApplication.companyName)
                && industry.equals(otherApplication.industry) // Compares single Industry objects
                && jobType.equals(otherApplication.jobType)
                && description.equals(otherApplication.description)
                && status.equals(otherApplication.status)
                && email.equals(otherApplication.email)
                && deadline.equals(otherApplication.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, industry, jobType, description, status, email, deadline);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("companyName", companyName)
                .add("industry", industry) // Corrected toString output
                .add("jobType", jobType)
                .add("description", description)
                .add("status", status)
                .add("email", email)
                .add("deadline", deadline)
                .toString();
    }
}
