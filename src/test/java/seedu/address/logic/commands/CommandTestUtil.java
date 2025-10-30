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
import seedu.address.model.BizBook;
import seedu.address.model.Model;
import seedu.address.model.company.InternshipApplication;
import seedu.address.model.company.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    // Valid InternshipApplication Fields
    public static final String VALID_NAME_GOOGLE = "Google";
    public static final String VALID_NAME_AWS = "AWS";
    public static final String VALID_JOB_TYPE_SWE = "SWE Intern";
    public static final String VALID_JOB_TYPE_DA = "Data Analyst";
    public static final String VALID_EMAIL_GOOGLE = "amy@example.com";
    public static final String VALID_EMAIL_AWS = "bob@example.com";
    public static final String VALID_DESCRIPTION_GOOGLE = "Backend microservices";
    public static final String VALID_DESCRIPTION_AWS = "Analytics team";
    public static final String VALID_INDUSTRY_TECH = "Technology";
    public static final String VALID_INDUSTRY_FINANCE = "Finance";
    public static final String VALID_STATUS_APPLIED = "Applied";
    public static final String VALID_STATUS_SAVED = "Saved";
    public static final String VALID_DEADLINE_GOOGLE = "2024-12-31";
    public static final String VALID_DEADLINE_AWS = "2025-01-15";


    // Command Descriptors for Names
    public static final String NAME_DESC_AMY = " " + PREFIX_COMPANY_NAME + VALID_NAME_GOOGLE;
    public static final String NAME_DESC_BOB = " " + PREFIX_COMPANY_NAME + VALID_NAME_AWS;

    // Command Descriptors for Job Types
    public static final String JOB_TYPE_DESC_AMY = " " + PREFIX_JOB_TYPE + VALID_JOB_TYPE_SWE;
    public static final String JOB_TYPE_DESC_BOB = " " + PREFIX_JOB_TYPE + VALID_JOB_TYPE_DA;

    // Command Descriptors for Emails
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_GOOGLE;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_AWS;

    // Command Descriptors for Descriptions
    public static final String DESCRIPTION_DESC_AMY = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_GOOGLE;
    public static final String DESCRIPTION_DESC_BOB = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_AWS;

    // Command Descriptors for Industries
    public static final String INDUSTRY_DESC_TECH = " " + PREFIX_INDUSTRY + VALID_INDUSTRY_TECH;
    public static final String INDUSTRY_DESC_FINANCE = " " + PREFIX_INDUSTRY + VALID_INDUSTRY_FINANCE;

    // Command Descriptors for Status
    public static final String STATUS_DESC_APPLIED = " " + PREFIX_STATUS + VALID_STATUS_APPLIED;
    public static final String STATUS_DESC_SAVED = " " + PREFIX_STATUS + VALID_STATUS_SAVED;

    // Command Descriptors for Deadline
    public static final String DEADLINE_DESC_AMY = " " + PREFIX_DEADLINE + VALID_DEADLINE_GOOGLE;
    public static final String DEADLINE_DESC_BOB = " " + PREFIX_DEADLINE + VALID_DEADLINE_AWS;

    // Invalid Field Descriptors
    public static final String INVALID_NAME_DESC = " " + PREFIX_COMPANY_NAME + "James@"; //@ not allowed
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

    public static final EditCommand.EditCompanyDescriptor DESC_AMY;
    public static final EditCommand.EditCompanyDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_GOOGLE)
                .withJobType(VALID_JOB_TYPE_SWE).withEmail(VALID_EMAIL_GOOGLE).withDescription(VALID_DESCRIPTION_GOOGLE)
                .withIndustry(VALID_INDUSTRY_TECH).withStatus(VALID_STATUS_APPLIED).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_AWS)
                .withJobType(VALID_JOB_TYPE_DA).withEmail(VALID_EMAIL_AWS).withDescription(VALID_DESCRIPTION_AWS)
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
        BizBook expectedBizBook = new BizBook(actualModel.getBizBook());
        List<InternshipApplication> expectedFilteredList = new ArrayList<>(actualModel.getFilteredCompanyList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedBizBook, actualModel.getBizBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredCompanyList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the application at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showApplicationAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredCompanyList().size());

        InternshipApplication internshipApplication = model.getFilteredCompanyList().get(targetIndex.getZeroBased());
        final String[] splitName = internshipApplication.getName().toString().split("\\s+");
        model.updateFilteredCompanyList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredCompanyList().size());
    }

}
