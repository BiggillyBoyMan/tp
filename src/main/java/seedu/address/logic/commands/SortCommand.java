package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.company.InternshipApplication;

/**
 * Sorts all internship applications in the visible list according to the specified field.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the application list by the specified field.\n"
            + "Parameters: FIELD_NAME (must be one of: name, status, deadline)\n"
            + "Example: " + COMMAND_WORD + " name";

    public static final String MESSAGE_SUCCESS = "Sorted all applications by %s";

    private final Comparator<InternshipApplication> comparator;
    private final String fieldName; // To provide user feedback on the sorted field

    /**
     * Creates a SortCommand to sort the list using the specified comparator.
     * @param comparator The comparator to apply.
     * @param fieldName The name of the field being sorted (for user feedback).
     */
    public SortCommand(Comparator<InternshipApplication> comparator, String fieldName) {
        assert comparator != null : "Comparator must not be null";
        assert fieldName != null && !fieldName.isEmpty() : "Field name must not be null or empty";
        this.comparator = comparator;
        this.fieldName = fieldName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert comparator != null : "Comparator must be set before execution";
        assert fieldName != null && !fieldName.isEmpty() : "Field name must be valid during execution";
        model.sortFilteredPersonList(comparator); // Call the new model method
        return new CommandResult(String.format(MESSAGE_SUCCESS, fieldName));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SortCommand)) {
            return false;
        }
        SortCommand otherSortCommand = (SortCommand) other;
        // Comparing fieldName is a proxy for comparing the underlying static comparators
        return fieldName.equals(otherSortCommand.fieldName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("fieldName", fieldName)
                .toString();
    }
}
