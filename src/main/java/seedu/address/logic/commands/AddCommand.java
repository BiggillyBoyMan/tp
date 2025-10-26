package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import seedu.address.model.Model;
import seedu.address.model.company.InternshipApplication;


/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_COMPANY_NAME + "COMPANY NAME "
            + PREFIX_JOB_TYPE + "JOB ROLE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_INDUSTRY + "INDUSTRY "
            + PREFIX_STATUS + "STATUS "
            + PREFIX_DEADLINE + "DEADLINE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMPANY_NAME + "XYZ PTE LTD "
            + PREFIX_JOB_TYPE + "SOFTWARE ENGINEER "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_DESCRIPTION + "Do SWE with experience needed for Java, Node.JS "
            + PREFIX_INDUSTRY + "Technology "
            + PREFIX_STATUS + "Saved "
            + PREFIX_DEADLINE + "2024-12-31";

    public static final String MESSAGE_SUCCESS = "New Company added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON =
            "The application for this company and job role already exists in the application tracker";

    private final InternshipApplication toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(InternshipApplication internshipApplication) {
        requireNonNull(internshipApplication);
        toAdd = internshipApplication;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
