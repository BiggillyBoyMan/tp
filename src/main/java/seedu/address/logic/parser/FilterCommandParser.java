package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Optional;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicationstatus.ApplicationStatus;
import seedu.address.model.company.ApplicationFilterPredicate;
import seedu.address.model.industry.Industry;

/**
 * Parses user input and creates a new {@link FilterCommand} object for execution.
 * Ensures at least one valid filter is present and normalizes input as needed.
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@link FilterCommand}
     * and returns a {@link FilterCommand} object for execution.
     *
     * @param args User input arguments string.
     * @return FilterCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STATUS, PREFIX_INDUSTRY);

        // Check that at least one prefix is present
        boolean hasStatus = argMultimap.getValue(PREFIX_STATUS).isPresent();
        boolean hasIndustry = argMultimap.getValue(PREFIX_INDUSTRY).isPresent();

        if (!hasStatus && !hasIndustry || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        // Verify no duplicate prefixes
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STATUS, PREFIX_INDUSTRY);

        Optional<String> status = Optional.empty();
        Optional<String> industry = Optional.empty();

        if (hasStatus) {
            String statusValue = argMultimap.getValue(PREFIX_STATUS).get().trim();
            if (statusValue.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
            }
            // Normalize and validate that the status is valid
            String normalizedStatus = normalizeStatus(statusValue);
            status = Optional.of(ParserUtil.parseStatus(normalizedStatus).value);
        }

        if (hasIndustry) {
            String industryValue = argMultimap.getValue(PREFIX_INDUSTRY).get().trim();
            if (industryValue.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
            }
            // Normalize and validate that the industry is valid
            String normalizedIndustry = normalizeIndustry(industryValue);
            industry = Optional.of(ParserUtil.parseIndustry(normalizedIndustry).value);
        }

        ApplicationFilterPredicate predicate = new ApplicationFilterPredicate(status, industry);
        return new FilterCommand(predicate);
    }

    /**
     * Normalizes the industry string to match the predefined capitalization.
     *
     * @param industryInput The input industry string from the user.
     * @return The normalized industry string if matched, otherwise the original input.
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
     *
     * @param statusInput The input status string from the user.
     * @return The normalized status string if matched, otherwise the original input.
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

