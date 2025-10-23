package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicationstatus.ApplicationStatus;
import seedu.address.model.industry.Industry;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_INDUSTRY, PREFIX_JOB_TYPE,
                        PREFIX_EMAIL, PREFIX_DESCRIPTION, PREFIX_STATUS, PREFIX_DEADLINE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COMPANY_NAME, PREFIX_INDUSTRY, PREFIX_JOB_TYPE,
                PREFIX_EMAIL, PREFIX_DESCRIPTION, PREFIX_STATUS, PREFIX_DEADLINE);

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        if (argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_COMPANY_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_INDUSTRY).isPresent()) {
            String industryStr = argMultimap.getValue(PREFIX_INDUSTRY).get();
            String normalizedIndustry = normalizeIndustry(industryStr);
            editPersonDescriptor.setIndustry(ParserUtil.parseIndustry(normalizedIndustry));
        }
        if (argMultimap.getValue(PREFIX_JOB_TYPE).isPresent()) {
            editPersonDescriptor.setJobType(ParserUtil.parseJobType(argMultimap.getValue(PREFIX_JOB_TYPE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editPersonDescriptor.setDescription(
                    ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            String statusStr = argMultimap.getValue(PREFIX_STATUS).get();
            String normalizedStatus = normalizeStatus(statusStr);
            editPersonDescriptor.setStatus(ParserUtil.parseStatus(normalizedStatus));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editPersonDescriptor.setDeadline(ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Normalizes the industry string to match the predefined capitalization.
     */
    private String normalizeIndustry(String industryInput) {
        for (String validIndustry : Industry.VALID_INDUSTRIES) {
            if (validIndustry.equalsIgnoreCase(industryInput)) {
                return validIndustry;
            }
        }
        return industryInput; // Let the validator in ParserUtil handle invalid input
    }

    /**
     * Normalizes the status string to match the predefined capitalization.
     */
    private String normalizeStatus(String statusInput) {
        for (String validStatus : ApplicationStatus.VALID_STATUSES) {
            if (validStatus.equalsIgnoreCase(statusInput)) {
                return validStatus;
            }
        }
        return statusInput; // Let the validator in ParserUtil handle invalid input
    }
}
