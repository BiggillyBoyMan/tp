package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicationstatus.ApplicationStatus;
import seedu.address.model.company.CompanyName;
import seedu.address.model.company.Deadline;
import seedu.address.model.company.Description;
import seedu.address.model.company.Email;
import seedu.address.model.company.InternshipApplication;
import seedu.address.model.company.JobType;
import seedu.address.model.industry.Industry;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @param args User input arguments string.
     * @return AddCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME,
                PREFIX_DESCRIPTION, PREFIX_EMAIL, PREFIX_INDUSTRY, PREFIX_JOB_TYPE, PREFIX_STATUS, PREFIX_DEADLINE);

        if (!arePrefixesPresent(argMultimap, PREFIX_COMPANY_NAME, PREFIX_DESCRIPTION, PREFIX_EMAIL,
                PREFIX_INDUSTRY, PREFIX_JOB_TYPE, PREFIX_STATUS, PREFIX_DEADLINE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COMPANY_NAME, PREFIX_DESCRIPTION,
                PREFIX_EMAIL, PREFIX_INDUSTRY, PREFIX_JOB_TYPE, PREFIX_STATUS, PREFIX_DEADLINE);

        CompanyName companyName = ParserUtil.parseName(argMultimap.getValue(PREFIX_COMPANY_NAME).get());
        JobType jobType = ParserUtil.parseJobType(argMultimap.getValue(PREFIX_JOB_TYPE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        String descriptionString = argMultimap.getValue(PREFIX_DESCRIPTION).orElse("");
        Description description = ParserUtil.parseDescription(descriptionString);
        Industry industry = ParserUtil.parseIndustry(argMultimap.getValue(PREFIX_INDUSTRY).get());
        Deadline deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());

        String statusString = argMultimap.getValue(PREFIX_STATUS)
                .orElse(ApplicationStatus.DEFAULT_STATUS);
        ApplicationStatus status = ParserUtil.parseStatus(statusString);

        InternshipApplication internshipApplication = new InternshipApplication(
                companyName, industry, jobType, description, status, email, deadline);
        return new AddCommand(internshipApplication);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
