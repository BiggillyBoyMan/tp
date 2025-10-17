package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDUSTRY_FINANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDUSTRY_TECH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_APPLIED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_SAVED;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.Company.InternshipApplication;

/**
 * A utility class containing a list of {@code InternshipApplication} objects to be used in tests.
 */
public class TypicalPersons {

    public static final InternshipApplication ALICE = new PersonBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com").withJobType("SWE Intern")
            .withDescription("Platform team")
            .withIndustry("Technology").withStatus("Saved").build();
    public static final InternshipApplication BENSON = new PersonBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withJobType("Data Analyst")
            .withDescription("Risk analytics").withIndustry("Finance").withStatus("Applied").build();
    public static final InternshipApplication CARL = new PersonBuilder().withName("Carl Kurz")
            .withEmail("heinz@example.com").build();
    public static final InternshipApplication DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withEmail("cornelia@example.com").build();
    public static final InternshipApplication ELLE = new PersonBuilder().withName("Elle Meyer")
            .withEmail("werner@example.com").build();
    public static final InternshipApplication FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withEmail("lydia@example.com").build();
    public static final InternshipApplication GEORGE = new PersonBuilder().withName("George Best")
            .withEmail("anna@example.com").build();

    // Manually added
    public static final InternshipApplication HOON = new PersonBuilder().withName("Hoon Meier")
            .withEmail("stefan@example.com").build();
    public static final InternshipApplication IDA = new PersonBuilder().withName("Ida Mueller")
            .withEmail("hans@example.com").build();

    // Manually added - details found in {@code CommandTestUtil}
    public static final InternshipApplication AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withJobType(VALID_JOB_TYPE_AMY).withEmail(VALID_EMAIL_AMY)
            .withDescription(VALID_DESCRIPTION_AMY).withIndustry(VALID_INDUSTRY_TECH)
            .withStatus(VALID_STATUS_APPLIED).build();
    public static final InternshipApplication BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withJobType(VALID_JOB_TYPE_BOB).withEmail(VALID_EMAIL_BOB)
            .withDescription(VALID_DESCRIPTION_BOB).withIndustry(VALID_INDUSTRY_FINANCE)
            .withStatus(VALID_STATUS_SAVED).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (InternshipApplication internshipApplication : getTypicalPersons()) {
            ab.addPerson(internshipApplication);
        }
        return ab;
    }

    public static List<InternshipApplication> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
