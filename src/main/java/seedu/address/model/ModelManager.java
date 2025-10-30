package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.company.InternshipApplication;
import seedu.address.model.company.SortComparators;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final BizBook bizBook;
    private final UserPrefs userPrefs;
    private final FilteredList<InternshipApplication> internalFilteredList;
    private final SortedList<InternshipApplication> filteredInternshipApplications;

    /**
     * Initializes a ModelManager with the given bizBook and userPrefs.
     */
    public ModelManager(ReadOnlyBizBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.bizBook = new BizBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.internalFilteredList = new FilteredList<>(this.bizBook.getPersonList());
        // Wrap the FilteredList with a SortedList, default sort by name
        this.filteredInternshipApplications = new SortedList<>(internalFilteredList, SortComparators.NAME_COMPARATOR);
    }

    public ModelManager() {
        this(new BizBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getBizBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setBizBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== BizBook ================================================================================

    @Override
    public void setBizBook(ReadOnlyBizBook addressBook) {
        this.bizBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyBizBook getBizBook() {
        return bizBook;
    }

    @Override
    public boolean hasCompany(InternshipApplication internshipApplication) {
        requireNonNull(internshipApplication);
        return bizBook.hasPerson(internshipApplication);
    }

    @Override
    public void deleteCompany(InternshipApplication target) {
        bizBook.removePerson(target);
    }

    @Override
    public void addCompany(InternshipApplication internshipApplication) {
        bizBook.addCompany(internshipApplication);
        updateFilteredCompanyList(PREDICATE_SHOW_ALL_COMPANY);
        filteredInternshipApplications.setComparator(SortComparators.NAME_COMPARATOR);
    }

    @Override
    public void setCompany(InternshipApplication target, InternshipApplication editedInternshipApplication) {
        requireAllNonNull(target, editedInternshipApplication);

        bizBook.setCompany(target, editedInternshipApplication);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<InternshipApplication> getFilteredCompanyList() {
        return filteredInternshipApplications;
    }

    @Override
    public void updateFilteredCompanyList(Predicate<InternshipApplication> predicate) {
        requireNonNull(predicate);
        // Apply the predicate to the underlying FilteredList
        internalFilteredList.setPredicate(predicate);
    }

    @Override
    public void sortFilteredCompanyList(Comparator<InternshipApplication> comparator) {
        requireNonNull(comparator);
        // Apply the comparator to the SortedList
        filteredInternshipApplications.setComparator(comparator);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return bizBook.equals(otherModelManager.bizBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                // Compare the underlying FilteredList predicate and the SortedList comparator
                && internalFilteredList.equals(otherModelManager.internalFilteredList);
    }

}
