package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.company.InternshipApplication;
import seedu.address.model.company.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    // Valid InternshipApplication Fields
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_JOB_TYPE_AMY = "SWE Intern";
    public static final String VALID_JOB_TYPE_BOB = "Data Analyst";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_DESCRIPTION_AMY = "Backend microservices";
    public static final String VALID_DESCRIPTION_BOB = "Analytics team";
    public static final String VALID_INDUSTRY_TECH = "Technology";
    public static final String VALID_INDUSTRY_FINANCE = "Finance";
    public static final String VALID_STATUS_APPLIED = "Applied";
    public static final String VALID_STATUS_SAVED = "Saved";
    public static final String VALID_DEADLINE_AMY = "2024-12-31";
    public static final String VALID_DEADLINE_BOB = "2025-01-15";


    // Command Descriptors for Names
    public static final String NAME_DESC_AMY = " " + PREFIX_COMPANY_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_COMPANY_NAME + VALID_NAME_BOB;

    // Command Descriptors for Job Types
    public static final String JOB_TYPE_DESC_AMY = " " + PREFIX_JOB_TYPE + VALID_JOB_TYPE_AMY;
    public static final String JOB_TYPE_DESC_BOB = " " + PREFIX_JOB_TYPE + VALID_JOB_TYPE_BOB;

    // Command Descriptors for Emails
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;

    // Command Descriptors for Descriptions
    public static final String DESCRIPTION_DESC_AMY = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_AMY;
    public static final String DESCRIPTION_DESC_BOB = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_BOB;

    // Command Descriptors for Industries
    public static final String INDUSTRY_DESC_TECH = " " + PREFIX_INDUSTRY + VALID_INDUSTRY_TECH;
    public static final String INDUSTRY_DESC_FINANCE = " " + PREFIX_INDUSTRY + VALID_INDUSTRY_FINANCE;

    // Command Descriptors for Status
    public static final String STATUS_DESC_APPLIED = " " + PREFIX_STATUS + VALID_STATUS_APPLIED;
    public static final String STATUS_DESC_SAVED = " " + PREFIX_STATUS + VALID_STATUS_SAVED;

    // Command Descriptors for Deadline
    public static final String DEADLINE_DESC_AMY = " " + PREFIX_DEADLINE + VALID_DEADLINE_AMY;
    public static final String DEADLINE_DESC_BOB = " " + PREFIX_DEADLINE + VALID_DEADLINE_BOB;

    // Invalid Field Descriptors
    public static final String INVALID_NAME_DESC = " " + PREFIX_COMPANY_NAME + "James&"; //& not allowed
    public static final String INVALID_JOB_TYPE_DESC = " " + PREFIX_JOB_TYPE + "a*"; //* not allowed
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; //missing '@'
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION
            + "a".repeat(201); //exceeds max 200
    public static final String INVALID_INDUSTRY_DESC = " " + PREFIX_INDUSTRY
            + "not-a-valid-industry"; //not in list
    public static final String INVALID_STATUS_DESC = " " + PREFIX_STATUS + "pending"; //invalid
    public static final String INVALID_DEADLINE_DESC = " " + PREFIX_DEADLINE + "2024/12/31"; //wrong format


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withJobType(VALID_JOB_TYPE_AMY).withEmail(VALID_EMAIL_AMY).withDescription(VALID_DESCRIPTION_AMY)
                .withIndustry(VALID_INDUSTRY_TECH).withStatus(VALID_STATUS_APPLIED).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withJobType(VALID_JOB_TYPE_BOB).withEmail(VALID_EMAIL_BOB).withDescription(VALID_DESCRIPTION_BOB)
                .withIndustry(VALID_INDUSTRY_FINANCE).withStatus(VALID_STATUS_SAVED).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered application list and selected application in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<InternshipApplication> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the application at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showApplicationAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        InternshipApplication internshipApplication = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = internshipApplication.getName().toString().split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
