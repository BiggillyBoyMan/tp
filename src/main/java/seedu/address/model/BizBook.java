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

    private final UniqueCompanyList company;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        company = new UniqueCompanyList();
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
    public void setCompanies(List<InternshipApplication> internshipApplications) {
        this.company.setCompanys(internshipApplications);
    }

    /**
     * Resets the existing data of this {@code BizBook} with {@code newData}.
     */
    public void resetData(ReadOnlyBizBook newData) {
        requireNonNull(newData);

        setCompanies(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(InternshipApplication internshipApplication) {
        requireNonNull(internshipApplication);
        return company.contains(internshipApplication);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addCompany(InternshipApplication p) {
        company.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setCompany(InternshipApplication target, InternshipApplication editedInternshipApplication) {
        requireNonNull(editedInternshipApplication);

        company.setCompany(target, editedInternshipApplication);
    }

    /**
     * Removes {@code key} from this {@code BizBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(InternshipApplication key) {
        company.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", company)
                .toString();
    }

    @Override
    public ObservableList<InternshipApplication> getPersonList() {
        return company.asUnmodifiableObservableList();
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
        return company.equals(otherBizBook.company);
    }

    @Override
    public int hashCode() {
        return company.hashCode();
    }
}
