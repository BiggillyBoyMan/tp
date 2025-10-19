package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.company.InternshipApplication;

/**
 * A utility class for InternshipApplication.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code internshipApplication}.
     */
    public static String getAddCommand(InternshipApplication internshipApplication) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(internshipApplication);
    }

    /**
     * Returns the part of command string for the given {@code internshipApplication}'s details.
     */
    public static String getPersonDetails(InternshipApplication internshipApplication) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_COMPANY_NAME).append(internshipApplication.getName().value).append(" ");
        // Other version: .toString() after .getName()
        sb.append(PREFIX_JOB_TYPE).append(internshipApplication.getJobType().value).append(" ");
        sb.append(PREFIX_EMAIL).append(internshipApplication.getEmail().value).append(" ");
        sb.append(PREFIX_DESCRIPTION).append(internshipApplication.getDescription().value).append(" ");
        sb.append(PREFIX_STATUS).append(internshipApplication.getStatus().value).append(" ");
        // Corrected from .industryName to .value
        sb.append(PREFIX_INDUSTRY).append(internshipApplication.getIndustry().value).append(" ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_COMPANY_NAME)
                .append(name.toString()).append(" "));
        descriptor.getJobType().ifPresent(jobType -> sb.append(PREFIX_JOB_TYPE)
                .append(jobType.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL)
                .append(email.value).append(" "));
        descriptor.getDescription().ifPresent(description -> sb.append(PREFIX_DESCRIPTION)
                .append(description.value).append(" "));
        descriptor.getStatus().ifPresent(status -> sb.append(PREFIX_STATUS)
                .append(status.value).append(" "));
        descriptor.getIndustry().ifPresent(industry -> sb.append(PREFIX_INDUSTRY)
                .append(industry.value).append(" "));

        return sb.toString();
    }
}
