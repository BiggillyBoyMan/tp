package seedu.address.testutil;

import seedu.address.model.BizBook;
import seedu.address.model.company.InternshipApplication;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code BizBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private BizBook addressBook;

    public AddressBookBuilder() {
        addressBook = new BizBook();
    }

    public AddressBookBuilder(BizBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code BizBook} that we are building.
     */
    public AddressBookBuilder withPerson(InternshipApplication internshipApplication) {
        addressBook.addCompany(internshipApplication);
        return this;
    }

    public BizBook build() {
        return addressBook;
    }
}
