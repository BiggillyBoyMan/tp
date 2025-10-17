package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.ApplicationStatus.ApplicationStatus;
import seedu.address.model.Company.CompanyName;
import seedu.address.model.Company.Description;
import seedu.address.model.Company.Email;
import seedu.address.model.Company.InternshipApplication;
import seedu.address.model.Company.JobType;
import seedu.address.model.Industry.Industry;

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
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(InternshipApplication internshipApplication) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(internshipApplication.getName());
        descriptor.setJobType(internshipApplication.getJobType());
        descriptor.setEmail(internshipApplication.getEmail());
        descriptor.setDescription(internshipApplication.getDescription());
        descriptor.setIndustry(internshipApplication.getIndustry());
        descriptor.setStatus(internshipApplication.getStatus());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
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
