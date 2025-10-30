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

    private final BizBook BizBook;
    private final UserPrefs userPrefs;
    private final FilteredList<InternshipApplication> internalFilteredList;
    private final SortedList<InternshipApplication> filteredInternshipApplications;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyBizBook BizBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(BizBook, userPrefs);

        logger.fine("Initializing with address book: " + BizBook + " and user prefs " + userPrefs);

        this.BizBook = new BizBook(BizBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.internalFilteredList = new FilteredList<>(this.BizBook.getCompanyList());
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
        return userPrefs.getBizBookFilePath();
    }

    @Override
    public void setBizBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setBizBookFilePath(addressBookFilePath);
    }

    //=========== BizBook ================================================================================

    @Override
    public void setBizBook(ReadOnlyBizBook addressBook) {
        this.BizBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyBizBook getBizBook() {
        return BizBook;
    }

    @Override
    public boolean hasCompany(InternshipApplication internshipApplication) {
        requireNonNull(internshipApplication);
        return BizBook.hasCompany(internshipApplication);
    }

    @Override
    public void deleteCompany(InternshipApplication target) {
        BizBook.removeCompany(target);
    }

    @Override
    public void addCompany(InternshipApplication internshipApplication) {
        BizBook.addCompany(internshipApplication);
        updateFilteredCompanyList(PREDICATE_SHOW_ALL_COMPANY);
        filteredInternshipApplications.setComparator(SortComparators.NAME_COMPARATOR);
    }

    @Override
    public void setCompany(InternshipApplication target, InternshipApplication editedInternshipApplication) {
        requireAllNonNull(target, editedInternshipApplication);

        BizBook.setCompany(target, editedInternshipApplication);
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
        return BizBook.equals(otherModelManager.BizBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                // Compare the underlying FilteredList predicate and the SortedList comparator
                && internalFilteredList.equals(otherModelManager.internalFilteredList);
    }

}
