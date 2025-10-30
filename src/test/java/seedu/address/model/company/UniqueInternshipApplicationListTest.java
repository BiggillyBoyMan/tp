package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AWS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDUSTRY_FINANCE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.company.exceptions.CompanyNotFoundException;
import seedu.address.model.company.exceptions.DuplicateCompanyException;
import seedu.address.testutil.CompanyBuilder;

public class UniqueInternshipApplicationListTest {

    private final UniqueCompanyList uniqueCompanyList = new UniqueCompanyList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCompanyList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueCompanyList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueCompanyList.add(ALICE);
        assertTrue(uniqueCompanyList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueCompanyList.add(ALICE);
        InternshipApplication editedAlice = new CompanyBuilder(ALICE).withDescription(VALID_DESCRIPTION_AWS)
                .withIndustry(VALID_INDUSTRY_FINANCE).build();
        assertTrue(uniqueCompanyList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCompanyList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueCompanyList.add(ALICE);
        assertThrows(DuplicateCompanyException.class, () -> uniqueCompanyList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCompanyList.setCompany(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCompanyList.setCompany(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(CompanyNotFoundException.class, () -> uniqueCompanyList.setCompany(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueCompanyList.add(ALICE);
        uniqueCompanyList.setCompany(ALICE, ALICE);
        UniqueCompanyList expectedUniqueCompanyList = new UniqueCompanyList();
        expectedUniqueCompanyList.add(ALICE);
        assertEquals(expectedUniqueCompanyList, uniqueCompanyList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueCompanyList.add(ALICE);
        InternshipApplication editedAlice = new CompanyBuilder(ALICE).withDescription(VALID_DESCRIPTION_AWS)
                .withIndustry(VALID_INDUSTRY_FINANCE).build();
        uniqueCompanyList.setCompany(ALICE, editedAlice);
        UniqueCompanyList expectedUniqueCompanyList = new UniqueCompanyList();
        expectedUniqueCompanyList.add(editedAlice);
        assertEquals(expectedUniqueCompanyList, uniqueCompanyList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueCompanyList.add(ALICE);
        uniqueCompanyList.setCompany(ALICE, BOB);
        UniqueCompanyList expectedUniqueCompanyList = new UniqueCompanyList();
        expectedUniqueCompanyList.add(BOB);
        assertEquals(expectedUniqueCompanyList, uniqueCompanyList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueCompanyList.add(ALICE);
        uniqueCompanyList.add(BOB);
        assertThrows(DuplicateCompanyException.class, () -> uniqueCompanyList.setCompany(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCompanyList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(CompanyNotFoundException.class, () -> uniqueCompanyList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueCompanyList.add(ALICE);
        uniqueCompanyList.remove(ALICE);
        UniqueCompanyList expectedUniqueCompanyList = new UniqueCompanyList();
        assertEquals(expectedUniqueCompanyList, uniqueCompanyList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCompanyList.setCompanys((UniqueCompanyList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueCompanyList.add(ALICE);
        UniqueCompanyList expectedUniqueCompanyList = new UniqueCompanyList();
        expectedUniqueCompanyList.add(BOB);
        uniqueCompanyList.setCompanys(expectedUniqueCompanyList);
        assertEquals(expectedUniqueCompanyList, uniqueCompanyList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCompanyList.setCompanys(
                (List<InternshipApplication>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueCompanyList.add(ALICE);
        List<InternshipApplication> internshipApplicationList = Collections.singletonList(BOB);
        uniqueCompanyList.setCompanys(internshipApplicationList);
        UniqueCompanyList expectedUniqueCompanyList = new UniqueCompanyList();
        expectedUniqueCompanyList.add(BOB);
        assertEquals(expectedUniqueCompanyList, uniqueCompanyList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<InternshipApplication> listWithDuplicateInternshipApplications = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateCompanyException.class, () -> uniqueCompanyList.setCompanys(
                listWithDuplicateInternshipApplications));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueCompanyList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueCompanyList.asUnmodifiableObservableList().toString(), uniqueCompanyList.toString());
    }
}
