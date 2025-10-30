package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AWS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDUSTRY_FINANCE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalBizBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.company.InternshipApplication;
import seedu.address.model.company.exceptions.DuplicateCompanyException;
import seedu.address.testutil.CompanyBuilder;

public class DescriptionBookTest {

    private final BizBook bizBook = new BizBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), bizBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bizBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        BizBook newData = getTypicalBizBook();
        bizBook.resetData(newData);
        assertEquals(newData, bizBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields (same name)
        InternshipApplication editedAlice = new CompanyBuilder(ALICE).withDescription(VALID_DESCRIPTION_AWS)
                .withIndustry(VALID_INDUSTRY_FINANCE).build();
        List<InternshipApplication> newInternshipApplications = Arrays.asList(ALICE, editedAlice);
        BizBookStub newData = new BizBookStub(newInternshipApplications);

        assertThrows(DuplicateCompanyException.class, () -> bizBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bizBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(bizBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        bizBook.addCompany(ALICE);
        assertTrue(bizBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        bizBook.addCompany(ALICE);
        InternshipApplication editedAlice = new CompanyBuilder(ALICE).withDescription(VALID_DESCRIPTION_AWS)
                .withIndustry(VALID_INDUSTRY_FINANCE).build();
        assertTrue(bizBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> bizBook.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = BizBook.class.getCanonicalName() + "{persons=" + bizBook.getPersonList() + "}";
        assertEquals(expected, bizBook.toString());
    }

    /**
     * A stub ReadOnlyBizBook whose persons list can violate interface constraints.
     */
    private static class BizBookStub implements ReadOnlyBizBook {
        private final ObservableList<InternshipApplication> internshipApplications =
                FXCollections.observableArrayList();

        BizBookStub(Collection<InternshipApplication> internshipApplications) {
            this.internshipApplications.setAll(internshipApplications);
        }

        @Override
        public ObservableList<InternshipApplication> getPersonList() {
            return internshipApplications;
        }
    }

}
