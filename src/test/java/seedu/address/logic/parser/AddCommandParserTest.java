package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INDUSTRY_DESC_FINANCE;
import static seedu.address.logic.commands.CommandTestUtil.INDUSTRY_DESC_TECH;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INDUSTRY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_JOB_TYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.JOB_TYPE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.JOB_TYPE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_APPLIED;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_SAVED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDUSTRY_FINANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDUSTRY_TECH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_SAVED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.ApplicationStatus.ApplicationStatus;
import seedu.address.model.Company.CompanyName;
import seedu.address.model.Company.Description;
import seedu.address.model.Company.Email;
import seedu.address.model.Company.InternshipApplication;
import seedu.address.model.Company.JobType;
import seedu.address.model.Industry.Industry;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        InternshipApplication expectedInternshipApplication = new PersonBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + JOB_TYPE_DESC_BOB
                + EMAIL_DESC_BOB + DESCRIPTION_DESC_BOB + INDUSTRY_DESC_FINANCE + STATUS_DESC_SAVED,
                new AddCommand(expectedInternshipApplication));

        // multiple names - all but last rejected (duplicate prefix error handled in separate test)
        InternshipApplication expectedInternshipApplicationDifferentIndustry =
                new PersonBuilder(BOB).withIndustry(VALID_INDUSTRY_TECH).build();
        assertParseSuccess(parser, NAME_DESC_BOB + JOB_TYPE_DESC_BOB + EMAIL_DESC_BOB
                + DESCRIPTION_DESC_BOB + INDUSTRY_DESC_TECH + STATUS_DESC_SAVED,
                new AddCommand(expectedInternshipApplicationDifferentIndustry));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + JOB_TYPE_DESC_BOB + EMAIL_DESC_BOB
                + DESCRIPTION_DESC_BOB + INDUSTRY_DESC_FINANCE + STATUS_DESC_SAVED;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY_NAME));

        // multiple job types
        assertParseFailure(parser, JOB_TYPE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_JOB_TYPE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple descriptions
        assertParseFailure(parser, DESCRIPTION_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION));

        // multiple industries
        assertParseFailure(parser, INDUSTRY_DESC_TECH + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INDUSTRY));

        // multiple statuses
        assertParseFailure(parser, STATUS_DESC_APPLIED + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STATUS));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + JOB_TYPE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY
                        + DESCRIPTION_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY_NAME, PREFIX_JOB_TYPE,
                        PREFIX_EMAIL, PREFIX_DESCRIPTION, PREFIX_INDUSTRY, PREFIX_STATUS));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY_NAME));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid job type
        assertParseFailure(parser, INVALID_JOB_TYPE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_JOB_TYPE));

        // invalid description
        assertParseFailure(parser, INVALID_DESCRIPTION_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedPersonString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid job type
        assertParseFailure(parser, validExpectedPersonString + INVALID_JOB_TYPE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_JOB_TYPE));

        // invalid description
        assertParseFailure(parser, validExpectedPersonString + INVALID_DESCRIPTION_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION));
    }

    // All fields are now compulsory in BizBook, so the optional fields test is removed

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + JOB_TYPE_DESC_BOB + EMAIL_DESC_BOB + DESCRIPTION_DESC_BOB
                + INDUSTRY_DESC_FINANCE + STATUS_DESC_SAVED, expectedMessage);

        // missing job type prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_JOB_TYPE_BOB + EMAIL_DESC_BOB + DESCRIPTION_DESC_BOB
                + INDUSTRY_DESC_FINANCE + STATUS_DESC_SAVED, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + JOB_TYPE_DESC_BOB + VALID_EMAIL_BOB + DESCRIPTION_DESC_BOB
                + INDUSTRY_DESC_FINANCE + STATUS_DESC_SAVED, expectedMessage);

        // missing description prefix
        assertParseFailure(parser, NAME_DESC_BOB + JOB_TYPE_DESC_BOB + EMAIL_DESC_BOB + VALID_DESCRIPTION_BOB
                + INDUSTRY_DESC_FINANCE + STATUS_DESC_SAVED, expectedMessage);

        // missing industry prefix
        assertParseFailure(parser, NAME_DESC_BOB + JOB_TYPE_DESC_BOB + EMAIL_DESC_BOB + DESCRIPTION_DESC_BOB
                + VALID_INDUSTRY_FINANCE + STATUS_DESC_SAVED, expectedMessage);

        // missing status prefix
        assertParseFailure(parser, NAME_DESC_BOB + JOB_TYPE_DESC_BOB + EMAIL_DESC_BOB + DESCRIPTION_DESC_BOB
                + INDUSTRY_DESC_FINANCE + VALID_STATUS_SAVED, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_JOB_TYPE_BOB + VALID_EMAIL_BOB + VALID_DESCRIPTION_BOB
                + VALID_INDUSTRY_FINANCE + VALID_STATUS_SAVED, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + JOB_TYPE_DESC_BOB + EMAIL_DESC_BOB + DESCRIPTION_DESC_BOB
                + INDUSTRY_DESC_FINANCE + STATUS_DESC_SAVED, CompanyName.MESSAGE_CONSTRAINTS);

        // invalid job type
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_JOB_TYPE_DESC + EMAIL_DESC_BOB + DESCRIPTION_DESC_BOB
                + INDUSTRY_DESC_FINANCE + STATUS_DESC_SAVED, JobType.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + JOB_TYPE_DESC_BOB + INVALID_EMAIL_DESC + DESCRIPTION_DESC_BOB
                + INDUSTRY_DESC_FINANCE + STATUS_DESC_SAVED, Email.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, NAME_DESC_BOB + JOB_TYPE_DESC_BOB + EMAIL_DESC_BOB + INVALID_DESCRIPTION_DESC
                + INDUSTRY_DESC_FINANCE + STATUS_DESC_SAVED, Description.MESSAGE_CONSTRAINTS);

        // invalid industry
        assertParseFailure(parser, NAME_DESC_BOB + JOB_TYPE_DESC_BOB + EMAIL_DESC_BOB + DESCRIPTION_DESC_BOB
                + INVALID_INDUSTRY_DESC + STATUS_DESC_SAVED, Industry.MESSAGE_CONSTRAINTS);

        // invalid status
        assertParseFailure(parser, NAME_DESC_BOB + JOB_TYPE_DESC_BOB + EMAIL_DESC_BOB + DESCRIPTION_DESC_BOB
                + INDUSTRY_DESC_FINANCE + INVALID_STATUS_DESC, ApplicationStatus.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + JOB_TYPE_DESC_BOB + EMAIL_DESC_BOB + INVALID_DESCRIPTION_DESC
                + INDUSTRY_DESC_FINANCE + STATUS_DESC_SAVED, CompanyName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + JOB_TYPE_DESC_BOB + EMAIL_DESC_BOB
                + DESCRIPTION_DESC_BOB + INDUSTRY_DESC_FINANCE + STATUS_DESC_SAVED,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
