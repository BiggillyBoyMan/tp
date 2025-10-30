package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.company.InternshipApplication;
import seedu.address.model.company.UniqueCompanyList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class BizBook implements ReadOnlyBizBook {

    private final UniqueCompanyList Company;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        Company = new UniqueCompanyList();
    }

    public BizBook() {}

    /**
     * Creates an BizBook using the Persons in the {@code toBeCopied}
     */
    public BizBook(ReadOnlyBizBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setCompany(List<InternshipApplication> internshipApplications) {
        this.Company.setPersons(internshipApplications);
    }

    /**
     * Resets the existing data of this {@code BizBook} with {@code newData}.
     */
    public void resetData(ReadOnlyBizBook newData) {
        requireNonNull(newData);

        setCompany(newData.getCompanyList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasCompany(InternshipApplication internshipApplication) {
        requireNonNull(internshipApplication);
        return Company.contains(internshipApplication);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addCompany(InternshipApplication p) {
        Company.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setCompany(InternshipApplication target, InternshipApplication editedInternshipApplication) {
        requireNonNull(editedInternshipApplication);

        Company.setCompany(target, editedInternshipApplication);
    }

    /**
     * Removes {@code key} from this {@code BizBook}.
     * {@code key} must exist in the address book.
     */
    public void removeCompany(InternshipApplication key) {
        Company.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", Company)
                .toString();
    }

    @Override
    public ObservableList<InternshipApplication> getCompanyList() {
        return Company.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BizBook)) {
            return false;
        }

        BizBook otherBizBook = (BizBook) other;
        return Company.equals(otherBizBook.Company);
    }

    @Override
    public int hashCode() {
        return Company.hashCode();
    }
}
