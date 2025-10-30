package seedu.address.testutil;

import seedu.address.model.BizBook;
import seedu.address.model.company.InternshipApplication;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code BizBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private BizBook bizBook;

    public AddressBookBuilder() {
        bizBook = new BizBook();
    }

    public AddressBookBuilder(BizBook bizBook) {
        this.bizBook = bizBook;
    }

    /**
     * Adds a new {@code Person} to the {@code BizBook} that we are building.
     */
    public AddressBookBuilder withPerson(InternshipApplication internshipApplication) {
        bizBook.addCompany(internshipApplication);
        return this;
    }

    public BizBook build() {
        return bizBook;
    }
}
