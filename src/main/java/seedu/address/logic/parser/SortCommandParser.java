package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Comparator;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.company.InternshipApplication;
import seedu.address.model.company.SortComparators;

/**
 * Parses user input and creates a new {@link SortCommand} object for execution.
 * Ensures the field to sort by is valid and supported.
 */
public class SortCommandParser implements Parser<SortCommand> {

    public static final String MESSAGE_INVALID_FIELD =
            "Invalid field for sorting. Must be one of: name, status, deadline, industry";

    /**
     * Parses the given {@code String} of arguments in the context of the {@link SortCommand}
     * and returns a {@link SortCommand} object for execution.
     *
     * @param args User input arguments string.
     * @return SortCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim().toLowerCase();

        // Basic validation: ensure a field is specified and it's one of the valid ones.
        if (trimmedArgs.isEmpty() || !trimmedArgs.matches("name|status|deadline|industry")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        Comparator<InternshipApplication> comparator;
        String fieldName; // User-friendly field name for the success message

        switch (trimmedArgs) {
        case "name":
            comparator = SortComparators.NAME_COMPARATOR;
            fieldName = "company name";
            break;
        case "status":
            comparator = SortComparators.STATUS_COMPARATOR;
            fieldName = "application status";
            break;
        case "deadline":
            comparator = SortComparators.DEADLINE_COMPARATOR;
            fieldName = "deadline";
            break;
        case "industry":
            comparator = SortComparators.INDUSTRY_COMPARATOR;
            fieldName = "industry";
            break;
        default:
            // This case should not be reached due to the regex check, but included for safety.
            throw new ParseException(MESSAGE_INVALID_FIELD);
        }

        return new SortCommand(comparator, fieldName);
    }
}
