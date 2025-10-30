package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.company.InternshipApplication;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<InternshipApplication> PREDICATE_SHOW_ALL_COMPANY = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getBizBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setBizBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setBizBook(ReadOnlyBizBook addressBook);

    /** Returns the BizBook */
    ReadOnlyBizBook getBizBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasCompany(InternshipApplication internshipApplication);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteCompany(InternshipApplication target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addCompany(InternshipApplication internshipApplication);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setCompany(InternshipApplication target, InternshipApplication editedInternshipApplication);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<InternshipApplication> getFilteredCompanyList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCompanyList(Predicate<InternshipApplication> predicate);

    /**
     * Sorts the filtered person list using the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void sortFilteredCompanyList(Comparator<InternshipApplication> comparator);
}
