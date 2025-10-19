package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.applicationstatus.ApplicationStatus;
import seedu.address.model.company.CompanyName;
import seedu.address.model.company.Description;
import seedu.address.model.company.Email;
import seedu.address.model.company.InternshipApplication;
import seedu.address.model.company.JobType;
import seedu.address.model.industry.Industry;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code internshipApplication}'s details
     */
    public EditPersonDescriptorBuilder(InternshipApplication internshipApplication) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(internshipApplication.getName());
        // Corrected from setPhone to setJobType
        descriptor.setJobType(internshipApplication.getJobType());
        descriptor.setEmail(internshipApplication.getEmail());
        descriptor.setDescription(internshipApplication.getDescription());
        // Corrected from setIndustries to setIndustry
        descriptor.setIndustry(internshipApplication.getIndustry());
        descriptor.setStatus(internshipApplication.getStatus());
    }

    /**
     * Sets the {@code CompanyName} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new CompanyName(name));
        return this;
    }

    /**
     * Sets the {@code JobType} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withJobType(String jobType) {
        descriptor.setJobType(new JobType(jobType));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Industry} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withIndustry(String industry) {
        // Corrected from setIndustries to setIndustry
        descriptor.setIndustry(new Industry(industry));
        return this;
    }

    /**
     * Sets the {@code ApplicationStatus} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(new ApplicationStatus(status));
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
