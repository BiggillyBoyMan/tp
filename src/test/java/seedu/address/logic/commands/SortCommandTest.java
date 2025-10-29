package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showApplicationAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.InternshipApplication;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortCommand.
 * In integration tests, we use a real ModelManager to verify the actual state
 */
public class SortCommandTest {

    // Model used for testing
    private Model model;
    // Model expected to be the result after a command
    private Model expectedModel;

    // Comparators for different sorting strategies
    private Comparator<InternshipApplication> nameComparator;
    private Comparator<InternshipApplication> statusComparator;
    private Comparator<InternshipApplication> deadlineComparator;

    /**
     * Sets up the models and comparators for each test.
     */
    @BeforeEach
    public void setUp() {
        // Initialize real models with typical data
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // Initialize real comparators
        nameComparator = (app1, app2) ->
                app1.getName().value.compareToIgnoreCase(app2.getName().value);
        statusComparator = (app1, app2) ->
                app1.getStatus().toString().compareToIgnoreCase(app2.getStatus().toString());
        deadlineComparator = (app1, app2) ->
                app1.getDeadline().value.compareTo(app2.getDeadline().value);
    }

    // --- Unit tests for constructor and equals (no model interaction) ---

    /**
     * Tests that the constructor throws an AssertionError if the comparator is null.
     */
    @Test
    void constructor_nullComparator_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new SortCommand(null, "name"));
    }

    /**
     * Tests that the constructor throws an AssertionError if the fieldName is null.
     */
    @Test
    void constructor_nullFieldName_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new SortCommand(nameComparator, null));
    }

    /**
     * Tests that the equals method returns true for two commands with the same fieldName.
     * <p>
     * This test implies that the equals method only checks the fieldName and not
     * the comparator instance.
     */
    @Test
    void equals_sameFieldName_returnsTrue() {
        SortCommand a = new SortCommand(nameComparator, "name");
        SortCommand b = new SortCommand(statusComparator, "name"); // Different comparator, same fieldName
        assertEquals(a, b);
    }

    /**
     * Tests that the equals method returns false for two commands with different fieldNames.
     */
    @Test
    void equals_differentFieldName_returnsFalse() {
        SortCommand a = new SortCommand(nameComparator, "name");
        SortCommand b = new SortCommand(nameComparator, "status"); // Same comparator, different fieldName
        assertNotEquals(a, b);
    }

    // --- Integration tests for execute (interaction with Model) ---

    /**
     * Tests that the execute method successfully sorts an unfiltered list by company name.
     */
    @Test
    public void execute_sortByName_success() {
        // 1. Arrange: Define the expected success message and the command
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "name");
        SortCommand command = new SortCommand(nameComparator, "name");
       
        // 2. Arrange expected model: Manually sort it in the same way
        expectedModel.sortFilteredPersonList(nameComparator);

        // 3. Act & Assert: Run command on 'model' and compare its state to 'expectedModel'
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    /**
     * Tests that the execute method successfully sorts an unfiltered list by status.
     */
    @Test
    public void execute_sortByStatus_success() {
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "status");
        SortCommand command = new SortCommand(statusComparator, "status");

        expectedModel.sortFilteredPersonList(statusComparator);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    /**
     * Tests that the execute method successfully sorts an unfiltered list by deadline.
     */
    @Test
    public void execute_sortByDeadline_success() {
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "deadline");
        SortCommand command = new SortCommand(deadlineComparator, "deadline");

        expectedModel.sortFilteredPersonList(deadlineComparator);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    /**
     * Tests that the execute method successfully sorts an already-filtered list.
     * This mirrors the 'execute_listIsFiltered_showsEverything' test from ListCommandTest,
     * ensuring that sorting operates correctly on the list provided by the filter.
     */
    @Test
    public void execute_sortByName_listIsFiltered_showsSortedFilteredList() {
        // 1. Arrange: Filter both models to show only one person
        showApplicationAtIndex(model, INDEX_FIRST_PERSON);
        showApplicationAtIndex(expectedModel, INDEX_FIRST_PERSON);
       
        // 2. Arrange command and expected message
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "name");
        SortCommand command = new SortCommand(nameComparator, "name");
       
        // 3. Arrange expected model: sort its (filtered) list
        expectedModel.sortFilteredPersonList(nameComparator);

        // 4. Act & Assert: Sorting a filtered list should still work
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
