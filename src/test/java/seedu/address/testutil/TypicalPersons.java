package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_AWS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AWS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AWS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDUSTRY_FINANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDUSTRY_TECH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TYPE_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TYPE_DA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AWS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_APPLIED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_SAVED;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.company.InternshipApplication;

/**
 * A utility class containing a list of {@code InternshipApplication} objects to be used in tests.
 */
public class TypicalPersons {
    public static final InternshipApplication ALICE = new CompanyBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com").withJobType("SWE Intern")
            .withDescription("Platform team")
            .withIndustry("Technology").withStatus("Saved").build();
    public static final InternshipApplication BENSON = new CompanyBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withJobType("Data Analyst")
            .withDescription("Risk analytics").withIndustry("Finance").withStatus("Applied").build();
    public static final InternshipApplication CARL = new CompanyBuilder().withName("Carl Kurz")
            .withEmail("heinz@example.com").build();
    public static final InternshipApplication DANIEL = new CompanyBuilder().withName("Daniel Meier")
            .withEmail("cornelia@example.com").build();
    public static final InternshipApplication ELLE = new CompanyBuilder().withName("Elle Meyer")
            .withEmail("werner@example.com").build();
    public static final InternshipApplication FIONA = new CompanyBuilder().withName("Fiona Kunz")
            .withEmail("lydia@example.com").build();
    public static final InternshipApplication GEORGE = new CompanyBuilder().withName("George Best")
            .withEmail("anna@example.com").build();

    // Manually added
    public static final InternshipApplication HOON = new CompanyBuilder().withName("Hoon Meier")
            .withEmail("stefan@example.com").build();
    public static final InternshipApplication IDA = new CompanyBuilder().withName("Ida Mueller")
            .withEmail("hans@example.com").build();

    // Manually added - details found in {@code CommandTestUtil}
    public static final InternshipApplication AMY = new CompanyBuilder().withName(VALID_NAME_GOOGLE)
            .withJobType(VALID_JOB_TYPE_SWE).withEmail(VALID_EMAIL_GOOGLE)
            .withDescription(VALID_DESCRIPTION_GOOGLE).withIndustry(VALID_INDUSTRY_TECH)
            .withStatus(VALID_STATUS_APPLIED).withDeadline(VALID_DEADLINE_GOOGLE).build();
    public static final InternshipApplication BOB = new CompanyBuilder().withName(VALID_NAME_AWS)
            .withJobType(VALID_JOB_TYPE_DA).withEmail(VALID_EMAIL_AWS)
            .withDescription(VALID_DESCRIPTION_AWS).withIndustry(VALID_INDUSTRY_FINANCE)
            .withStatus(VALID_STATUS_SAVED).withDeadline(VALID_DEADLINE_AWS).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalBizBook() {
        AddressBook ab = new AddressBook();
        for (InternshipApplication internshipApplication : getTypicalPersons()) {
            ab.addPerson(internshipApplication);
        }
        return ab;
    }

    /**
     * Returns an Array List of typical persons
     */
    public static List<InternshipApplication> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
