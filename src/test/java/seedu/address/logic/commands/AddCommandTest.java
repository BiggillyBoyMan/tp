package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.BizBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyBizBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.company.InternshipApplication;
import seedu.address.testutil.CompanyBuilder;

/**
 * Adds an internship application into BizBook.
 */
public class AddCommandTest {

    @Test
    public void constructor_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_applicationAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingApplicationAdded modelStub = new ModelStubAcceptingApplicationAdded();
        InternshipApplication validApplication = new CompanyBuilder().build();

        CommandResult commandResult = new AddCommand(validApplication).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validApplication)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validApplication), modelStub.applicationsAdded);
    }

    @Test
    public void execute_duplicateApplication_throwsCommandException() {
        InternshipApplication validApplication = new CompanyBuilder().build();
        AddCommand addCommand = new AddCommand(validApplication);
        ModelStub modelStub = new ModelStubWithApplication(validApplication);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_COMPANY, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        InternshipApplication google = new CompanyBuilder().withName("Google").build();
        InternshipApplication meta = new CompanyBuilder().withName("Meta").build();
        AddCommand addGoogleCommand = new AddCommand(google);
        AddCommand addMetaCommand = new AddCommand(meta);

        // same object -> returns true
        assertTrue(addGoogleCommand.equals(addGoogleCommand));

        // same values -> returns true
        AddCommand addGoogleCommandCopy = new AddCommand(google);
        assertTrue(addGoogleCommand.equals(addGoogleCommandCopy));

        // different types -> returns false
        assertFalse(addGoogleCommand.equals(1));

        // null -> returns false
        assertFalse(addGoogleCommand.equals(null));

        // different application -> returns false
        assertFalse(addGoogleCommand.equals(addMetaCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(ALICE);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A default model stub that has all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getBizBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBizBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCompany(InternshipApplication internshipApplication) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBizBook(ReadOnlyBizBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyBizBook getBizBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCompany(InternshipApplication internshipApplication) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCompany(InternshipApplication target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCompany(InternshipApplication target, InternshipApplication editedInternshipApplication) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<InternshipApplication> getFilteredCompanyList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCompanyList(Predicate<InternshipApplication> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortFilteredCompanyList(Comparator<InternshipApplication> comparator) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single application.
     */
    private class ModelStubWithApplication extends ModelStub {
        private final InternshipApplication application;

        ModelStubWithApplication(InternshipApplication application) {
            requireNonNull(application);
            this.application = application;
        }

        @Override
        public boolean hasCompany(InternshipApplication application) {
            requireNonNull(application);
            return this.application.isSameApplication(application);
        }
    }

    /**
     * A Model stub that always accepts the application being added.
     */
    private class ModelStubAcceptingApplicationAdded extends ModelStub {
        final ArrayList<InternshipApplication> applicationsAdded = new ArrayList<>();

        @Override
        public boolean hasCompany(InternshipApplication application) {
            requireNonNull(application);
            return applicationsAdded.stream().anyMatch(application::isSameApplication);
        }

        @Override
        public void addCompany(InternshipApplication application) {
            requireNonNull(application);
            applicationsAdded.add(application);
        }

        @Override
        public ReadOnlyBizBook getBizBook() {
            return new BizBook();
        }
    }

}
