package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AWS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AWS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDUSTRY_FINANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TYPE_DA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AWS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditCompanyDescriptor;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditInternshipApplicationDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCompanyDescriptor descriptorWithSameValues = new EditCompanyDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditCompanyDescriptor editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withName(VALID_NAME_AWS).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different job type -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withJobType(VALID_JOB_TYPE_DA).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_AWS).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different description -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withDescription(VALID_DESCRIPTION_AWS).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different industry -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withIndustry(VALID_INDUSTRY_FINANCE).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditCompanyDescriptor editPersonDescriptor = new EditCompanyDescriptor();
        String expected = EditCompanyDescriptor.class.getCanonicalName() + "{"
                + "companyName=" + editPersonDescriptor.getName().orElse(null) + ", "
                + "industry=" + editPersonDescriptor.getIndustry().orElse(null) + ", "
                + "jobType=" + editPersonDescriptor.getJobType().orElse(null) + ", "
                + "email=" + editPersonDescriptor.getEmail().orElse(null) + ", "
                + "description=" + editPersonDescriptor.getDescription().orElse(null) + ", "
                + "status=" + editPersonDescriptor.getStatus().orElse(null) + ", "
                + "deadline=" + editPersonDescriptor.getDeadline().orElse(null) + "}";
        assertEquals(expected, editPersonDescriptor.toString());
    }
}
