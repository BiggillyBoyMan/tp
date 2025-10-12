package seedu.address.testutil;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.Company.InternshipApplication;
import seedu.address.model.Industry.Industry;

import static seedu.address.logic.parser.CliSyntax.*;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(InternshipApplication internshipApplication) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(internshipApplication);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(InternshipApplication internshipApplication) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_COMPANY_NAME + internshipApplication.getName().CompanyName + " ");
        sb.append(PREFIX_JOB_TYPE + internshipApplication.getJobType().value + " ");
        sb.append(PREFIX_EMAIL + internshipApplication.getEmail().value + " ");
        sb.append(PREFIX_DESCRIPTION + internshipApplication.getDescription().value + " ");
        sb.append(PREFIX_STATUS + internshipApplication.getStatus().value + " ");
        sb.append(PREFIX_INDUSTRY + internshipApplication.getIndustry().value + " ");

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_COMPANY_NAME).append(name.CompanyName).append(" "));
        descriptor.getPhone().ifPresent(jobType -> sb.append(PREFIX_JOB_TYPE).append(jobType.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(description -> sb.append(PREFIX_DESCRIPTION).append(description.value).append(" "));
        descriptor.getAddress().ifPresent(status -> sb.append(PREFIX_STATUS).append(status.value).append(" "));
        descriptor.getAddress().ifPresent(industry -> sb.append(PREFIX_INDUSTRY).append(industry.value).append(" "));

        return sb.toString();
    }
}
