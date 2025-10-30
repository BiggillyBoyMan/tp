package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.BizBook;
import seedu.address.model.ReadOnlyBizBook;
import seedu.address.model.company.InternshipApplication;


/**
 * An Immutable BizBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableBizBook {

    public static final String MESSAGE_DUPLICATE_COMPANY = "Persons list contains duplicate company(s).";

    private final List<JsonAdaptedCompany> company = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableBizBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableBizBook(@JsonProperty("persons") List<JsonAdaptedCompany> company) {
        this.company.addAll(company);
    }

    /**
     * Converts a given {@code ReadOnlyBizBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableBizBook}.
     */
    public JsonSerializableBizBook(ReadOnlyBizBook source) {
        company.addAll(source.getCompanyList().stream().map(JsonAdaptedCompany::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code BizBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public BizBook toModelType() throws IllegalValueException {
        BizBook addressBook = new BizBook();
        for (JsonAdaptedCompany jsonAdaptedCompany : company) {
            InternshipApplication internshipApplication = jsonAdaptedCompany.toModelType();
            if (addressBook.hasCompany(internshipApplication)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_COMPANY);
            }
            addressBook.addCompany(internshipApplication);
        }
        return addressBook;
    }

}
