package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AWS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AWS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDUSTRY_FINANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TYPE_DA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AWS;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CompanyBuilder;

public class InternshipApplicationTest {

    // Industry is no longer a list, so this test is removed
    // @Test
    // public void asObservableList_modifyList_throwsUnsupportedOperationException() {
    //     InternshipApplication internshipApplication = new CompanyBuilder().build();
    //     assertThrows(UnsupportedOperationException.class, () -> internshipApplication.getIndustry().remove(0));
    // }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSameApplication(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameApplication(null));

        // same name and job type, all other attributes different -> returns true
        InternshipApplication editedAlice = new CompanyBuilder(ALICE)
                .withEmail(VALID_EMAIL_AWS).withDescription(VALID_DESCRIPTION_AWS)
                .withIndustry(VALID_INDUSTRY_FINANCE).build();
        assertTrue(ALICE.isSameApplication(editedAlice));

        // same name, different job type -> returns false (allows multiple roles at same company)
        editedAlice = new CompanyBuilder(ALICE).withJobType(VALID_JOB_TYPE_DA).build();
        assertFalse(ALICE.isSameApplication(editedAlice));

        // different name, same job type -> returns false
        editedAlice = new CompanyBuilder(ALICE).withName(VALID_NAME_AWS).build();
        assertFalse(ALICE.isSameApplication(editedAlice));

        // name differs in case, all other attributes same -> returns false
        InternshipApplication editedBob = new CompanyBuilder(BOB).withName(VALID_NAME_AWS.toLowerCase()).build();
        assertFalse(BOB.isSameApplication(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_AWS + " ";
        editedBob = new CompanyBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameApplication(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        InternshipApplication aliceCopy = new CompanyBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        InternshipApplication editedAlice = new CompanyBuilder(ALICE).withName(VALID_NAME_AWS).build();
        assertFalse(ALICE.equals(editedAlice));

        // different job type -> returns false
        editedAlice = new CompanyBuilder(ALICE).withJobType(VALID_JOB_TYPE_DA).build();
        assertFalse(ALICE.equals(editedAlice));

        // Note: Email is not part of equals() comparison, so different emails are considered equal
        // This is intentional as email doesn't define application identity

        // different description -> returns false
        editedAlice = new CompanyBuilder(ALICE).withDescription(VALID_DESCRIPTION_AWS).build();
        assertFalse(ALICE.equals(editedAlice));

        // different industry -> returns false
        editedAlice = new CompanyBuilder(ALICE).withIndustry(VALID_INDUSTRY_FINANCE).build();
        assertFalse(ALICE.equals(editedAlice));

        // different status -> returns false
        editedAlice = new CompanyBuilder(ALICE).withStatus("Rejected").build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = InternshipApplication.class.getCanonicalName() + "{companyName=" + ALICE.getName()
                + ", industry=" + ALICE.getIndustry() + ", jobType=" + ALICE.getJobType()
                + ", description=" + ALICE.getDescription() + ", status=" + ALICE.getStatus()
                + ", email=" + ALICE.getEmail() + ", deadline=" + ALICE.getDeadline() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
