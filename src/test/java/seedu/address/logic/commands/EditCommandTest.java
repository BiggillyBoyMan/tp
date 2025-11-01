package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDUSTRY_FINANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TYPE_DA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AWS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showApplicationAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_COMPANY;
import static seedu.address.testutil.TypicalPersons.getTypicalBizBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.BizBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.InternshipApplication;
import seedu.address.testutil.CompanyBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalBizBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        InternshipApplication applicationInList = model.getFilteredCompanyList()
                .get(INDEX_FIRST_COMPANY.getZeroBased());
        InternshipApplication editedApplication = new CompanyBuilder(applicationInList)
                .withName(VALID_NAME_AWS).build();
        EditCommand.EditCompanyDescriptor descriptor = new EditPersonDescriptorBuilder(editedApplication).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_COMPANY, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_COMPANY_SUCCESS,
                Messages.format(editedApplication));

        Model expectedModel = new ModelManager(new BizBook(model.getBizBook()),
                new UserPrefs());
        expectedModel.setCompany(model.getFilteredCompanyList().get(0), editedApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastApplication = Index.fromOneBased(model.getFilteredCompanyList().size());
        InternshipApplication lastApplication = model.getFilteredCompanyList()
                .get(indexLastApplication.getZeroBased());

        CompanyBuilder applicationInList = new CompanyBuilder(lastApplication);
        InternshipApplication editedApplication = applicationInList.withName(VALID_NAME_AWS)
                .withJobType(VALID_JOB_TYPE_DA).withIndustry(VALID_INDUSTRY_FINANCE).build();

        EditCommand.EditCompanyDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AWS)
                .withJobType(VALID_JOB_TYPE_DA).withIndustry(VALID_INDUSTRY_FINANCE).build();
        EditCommand editCommand = new EditCommand(indexLastApplication, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_COMPANY_SUCCESS,
                Messages.format(editedApplication));

        Model expectedModel = new ModelManager(new BizBook(model.getBizBook()),
                new UserPrefs());
        expectedModel.setCompany(lastApplication, editedApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_COMPANY, new EditCommand.EditCompanyDescriptor());
        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void execute_filteredList_success() {
        showApplicationAtIndex(model, INDEX_FIRST_COMPANY);

        InternshipApplication applicationInFilteredList = model.getFilteredCompanyList()
                .get(INDEX_FIRST_COMPANY.getZeroBased());
        InternshipApplication editedApplication = new CompanyBuilder(applicationInFilteredList)
                .withName(VALID_NAME_AWS).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_COMPANY,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_AWS).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_COMPANY_SUCCESS,
                Messages.format(editedApplication));

        Model expectedModel = new ModelManager(new BizBook(model.getBizBook()),
                new UserPrefs());
        expectedModel.setCompany(model.getFilteredCompanyList().get(0), editedApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateApplicationUnfilteredList_failure() {
        InternshipApplication firstApplication = model.getFilteredCompanyList()
                .get(INDEX_FIRST_COMPANY.getZeroBased());
        EditCommand.EditCompanyDescriptor descriptor = new EditPersonDescriptorBuilder(firstApplication).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_COMPANY, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_COMPANY);
    }

    @Test
    public void execute_duplicateApplicationFilteredList_failure() {
        showApplicationAtIndex(model, INDEX_FIRST_COMPANY);

        // edit application in filtered list into a duplicate in address book
        InternshipApplication applicationInList = model.getBizBook().getPersonList()
                .get(INDEX_SECOND_COMPANY.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_COMPANY,
                new EditPersonDescriptorBuilder(applicationInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_COMPANY);
    }

    @Test
    public void execute_invalidApplicationIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCompanyList().size() + 1);
        EditCommand.EditCompanyDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AWS)
                .build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidApplicationIndexFilteredList_failure() {
        showApplicationAtIndex(model, INDEX_FIRST_COMPANY);
        Index outOfBoundIndex = INDEX_SECOND_COMPANY;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getBizBook().getPersonList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_AWS).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_COMPANY, DESC_AMY);

        // same values -> returns true
        EditCommand.EditCompanyDescriptor copyDescriptor = new EditCommand.EditCompanyDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_COMPANY, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_COMPANY, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_COMPANY, DESC_BOB)));
    }
}
