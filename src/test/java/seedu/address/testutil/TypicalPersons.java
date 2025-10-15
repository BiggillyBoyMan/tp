// // // package seedu.address.testutil;

// // // import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
// // // import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
// // // import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
// // // import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
// // // import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
// // // import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
// // // import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
// // // import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
// // // import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
// // // import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

// // // import java.util.ArrayList;
// // // import java.util.Arrays;
// // // import java.util.List;

// // // import seedu.address.model.AddressBook;
// // // import seedu.address.model.Company.InternshipApplication;

// // // /**
// // //  * A utility class containing a list of {@code Person} objects to be used in tests.
// // //  */
// // // public class TypicalPersons {

// // //     public static final InternshipApplication ALICE = new PersonBuilder().withName("Alice Pauline")
// // //             .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
// // //             .withPhone("94351253")
// // //             .withTags("friends").build();
// // //     public static final InternshipApplication BENSON = new PersonBuilder().withName("Benson Meier")
// // //             .withAddress("311, Clementi Ave 2, #02-25")
// // //             .withEmail("johnd@example.com").withPhone("98765432")
// // //             .withTags("owesMoney", "friends").build();
// // //     public static final InternshipApplication CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
// // //             .withEmail("heinz@example.com").withAddress("wall street").build();
// // //     public static final InternshipApplication DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
// // //             .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
// // //     public static final InternshipApplication ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
// // //             .withEmail("werner@example.com").withAddress("michegan ave").build();
// // //     public static final InternshipApplication FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
// // //             .withEmail("lydia@example.com").withAddress("little tokyo").build();
// // //     public static final InternshipApplication GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
// // //             .withEmail("anna@example.com").withAddress("4th street").build();

// // //     // Manually added
// // //     public static final InternshipApplication HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
// // //             .withEmail("stefan@example.com").withAddress("little india").build();
// // //     public static final InternshipApplication IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
// // //             .withEmail("hans@example.com").withAddress("chicago ave").build();

// // //     // Manually added - Person's details found in {@code CommandTestUtil}
// // //     public static final InternshipApplication AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
// // //             .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
// // //     public static final InternshipApplication BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
// // //             .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
// // //             .build();

// // //     public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

// // //     private TypicalPersons() {} // prevents instantiation

// // //     /**
// // //      * Returns an {@code AddressBook} with all the typical persons.
// // //      */
// // //     public static AddressBook getTypicalAddressBook() {
// // //         AddressBook ab = new AddressBook();
// // //         for (InternshipApplication internshipApplication : getTypicalPersons()) {
// // //             ab.addPerson(internshipApplication);
// // //         }
// // //         return ab;
// // //     }

// // //     public static List<InternshipApplication> getTypicalPersons() {
// // //         return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
// // //     }
// // // }

// // package seedu.address.testutil;

// // import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
// // import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
// // import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
// // import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
// // import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
// // import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
// // import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
// // import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
// // import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
// // import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

// // import java.util.ArrayList;
// // import java.util.Arrays;
// // import java.util.List;

// // import seedu.address.model.AddressBook;
// // import seedu.address.model.Company.InternshipApplication;

// // /**
// //  * A utility class containing a list of {@code InternshipApplication} objects to be used in tests.
// //  */
// // public class TypicalPersons {

// //     public static final InternshipApplication ALICE = new PersonBuilder().withName("Alice Pauline")
// //             .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
// //             .withPhone("94351253")
// //             .withIndustries("friends").build();
// //     public static final InternshipApplication BENSON = new PersonBuilder().withName("Benson Meier")
// //             .withAddress("311, Clementi Ave 2, #02-25")
// //             .withEmail("johnd@example.com").withPhone("98765432")
// //             .withIndustries("owesMoney", "friends").build();
// //     public static final InternshipApplication CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
// //             .withEmail("heinz@example.com").withAddress("wall street").build();
// //     public static final InternshipApplication DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
// //             .withEmail("cornelia@example.com").withAddress("10th street").withIndustries("friends").build();
// //     public static final InternshipApplication ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
// //             .withEmail("werner@example.com").withAddress("michegan ave").build();
// //     public static final InternshipApplication FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
// //             .withEmail("lydia@example.com").withAddress("little tokyo").build();
// //     public static final InternshipApplication GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
// //             .withEmail("anna@example.com").withAddress("4th street").build();

// //     // Manually added
// //     public static final InternshipApplication HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
// //             .withEmail("stefan@example.com").withAddress("little india").build();
// //     public static final InternshipApplication IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
// //             .withEmail("hans@example.com").withAddress("chicago ave").build();

// //     // Manually added - Person's details found in {@code CommandTestUtil}
// //     public static final InternshipApplication AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
// //             .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withIndustries(VALID_TAG_FRIEND).build();
// //     public static final InternshipApplication BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
// //             .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withIndustries(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
// //             .build();

// //     public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

// //     private TypicalPersons() {} // prevents instantiation

// //     /**
// //      * Returns an {@code AddressBook} with all the typical persons.
// //      */
// //     public static AddressBook getTypicalAddressBook() {
// //         AddressBook ab = new AddressBook();
// //         for (InternshipApplication internshipApplication : getTypicalPersons()) {
// //             ab.addPerson(internshipApplication);
// //         }
// //         return ab;
// //     }
    
// //     public static List<InternshipApplication> getTypicalPersons() {
// //         return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
// //     }
// // }

// package seedu.address.testutil;

// import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
// import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
// import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
// import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
// import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
// import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
// import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
// import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
// import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
// import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

// import seedu.address.model.AddressBook;
// import seedu.address.model.Company.InternshipApplication;

// /**
//  * A utility class containing a list of {@code InternshipApplication} objects to be used in tests.
//  */
// public class TypicalPersons {

//     public static final InternshipApplication ALICE = new PersonBuilder().withName("Alice Pauline")
//             .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
//             .withPhone("94351253")
//             .withIndustry("friends").build();
//     public static final InternshipApplication BENSON = new PersonBuilder().withName("Benson Meier")
//             .withAddress("311, Clementi Ave 2, #02-25")
//             .withEmail("johnd@example.com").withPhone("98765432")
//             .withIndustry("owesMoney").build(); // Now only takes the first one
//     public static final InternshipApplication CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
//             .withEmail("heinz@example.com").withAddress("wall street").build();
//     public static final InternshipApplication DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
//             .withEmail("cornelia@example.com").withAddress("10th street").withIndustry("friends").build();
//     public static final InternshipApplication ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
//             .withEmail("werner@example.com").withAddress("michegan ave").build();
//     public static final InternshipApplication FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
//             .withEmail("lydia@example.com").withAddress("little tokyo").build();
//     public static final InternshipApplication GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
//             .withEmail("anna@example.com").withAddress("4th street").build();

//     // Manually added
//     public static final InternshipApplication HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
//             .withEmail("stefan@example.com").withAddress("little india").build();
//     public static final InternshipApplication IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
//             .withEmail("hans@example.com").withAddress("chicago ave").build();

//     // Manually added - Person's details found in {@code CommandTestUtil}
//     public static final InternshipApplication AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
//             .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withIndustry(VALID_TAG_FRIEND).build();
//     public static final InternshipApplication BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
//             .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withIndustry(VALID_TAG_HUSBAND) // Now only takes the first one
//             .build();

//     public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

//     private TypicalPersons() {} // prevents instantiation

//     /**
//      * Returns an {@code AddressBook} with all the typical persons.
//      */
//     public static AddressBook getTypicalAddressBook() {
//         AddressBook ab = new AddressBook();
//         for (InternshipApplication internshipApplication : getTypicalPersons()) {
//             ab.addPerson(internshipApplication);
//         }
//         return ab;
//     }
    
//     public static List<InternshipApplication> getTypicalPersons() {
//         return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
//     }
// }

package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

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
            .withDescription("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withJobType("94351253") // Using the new method name
            .withIndustry("friends").build();
    public static final InternshipApplication BENSON = new PersonBuilder().withName("Benson Meier")
            .withDescription("311, Clementi Ave 2, #02-25") // Using the new method name
            .withEmail("johnd@example.com").withJobType("98765432")
            .withIndustry("owesMoney").build();
    public static final InternshipApplication CARL = new PersonBuilder().withName("Carl Kurz").withJobType("95352563")
            .withEmail("heinz@example.com").withDescription("wall street").build();
    public static final InternshipApplication DANIEL = new PersonBuilder().withName("Daniel Meier").withJobType("87652533")
            .withEmail("cornelia@example.com").withDescription("10th street").withIndustry("friends").build();
    public static final InternshipApplication ELLE = new PersonBuilder().withName("Elle Meyer").withJobType("9482224")
            .withEmail("werner@example.com").withDescription("michegan ave").build();
    public static final InternshipApplication FIONA = new PersonBuilder().withName("Fiona Kunz").withJobType("9482427")
            .withEmail("lydia@example.com").withDescription("little tokyo").build();
    public static final InternshipApplication GEORGE = new PersonBuilder().withName("George Best").withJobType("9482442")
            .withEmail("anna@example.com").withDescription("4th street").build();

    // Manually added
    public static final InternshipApplication HOON = new PersonBuilder().withName("Hoon Meier").withJobType("8482424")
            .withEmail("stefan@example.com").withDescription("little india").build();
    public static final InternshipApplication IDA = new PersonBuilder().withName("Ida Mueller").withJobType("8482131")
            .withEmail("hans@example.com").withDescription("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final InternshipApplication AMY = new PersonBuilder().withName(VALID_NAME_AMY).withJobType(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withDescription(VALID_ADDRESS_AMY).withIndustry(VALID_TAG_FRIEND).build();
    public static final InternshipApplication BOB = new PersonBuilder().withName(VALID_NAME_BOB).withJobType(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withDescription(VALID_ADDRESS_BOB).withIndustry(VALID_TAG_HUSBAND)
            .build();

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