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
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_APPLIED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDUSTRY_TECH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_APPLIED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TYPE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.applicationstatus.ApplicationStatus;
import seedu.address.model.company.CompanyName;
import seedu.address.model.company.Description;
import seedu.address.model.company.Email;
import seedu.address.model.company.JobType;
import seedu.address.model.industry.Industry;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String INDUSTRY_EMPTY = " " + PREFIX_INDUSTRY;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 z/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, CompanyName.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_JOB_TYPE_DESC, JobType.MESSAGE_CONSTRAINTS); // invalid job type
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC,
                Description.MESSAGE_CONSTRAINTS); // invalid description
        assertParseFailure(parser, "1" + INVALID_INDUSTRY_DESC, Industry.MESSAGE_CONSTRAINTS); // invalid industry
        assertParseFailure(parser, "1" + INVALID_STATUS_DESC, ApplicationStatus.MESSAGE_CONSTRAINTS); // invalid status

        // invalid job type followed by valid email
        assertParseFailure(parser, "1" + INVALID_JOB_TYPE_DESC + EMAIL_DESC_AMY, JobType.MESSAGE_CONSTRAINTS);

        // In BizBook, industry is a single field, so parsing empty industry alone is valid (removes industry)
        // However, parsing empty industry with other industries results in duplicate prefix error

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_DESCRIPTION_AMY
                + VALID_JOB_TYPE_AMY, CompanyName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + JOB_TYPE_DESC_BOB + INDUSTRY_DESC_TECH
                + EMAIL_DESC_AMY + DESCRIPTION_DESC_AMY + NAME_DESC_AMY + STATUS_DESC_APPLIED;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withJobType(VALID_JOB_TYPE_BOB).withEmail(VALID_EMAIL_AMY).withDescription(VALID_DESCRIPTION_AMY)
                .withIndustry(VALID_INDUSTRY_TECH).withStatus(VALID_STATUS_APPLIED).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + JOB_TYPE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withJobType(VALID_JOB_TYPE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // job type
        userInput = targetIndex.getOneBased() + JOB_TYPE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withJobType(VALID_JOB_TYPE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withDescription(VALID_DESCRIPTION_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // industry
        userInput = targetIndex.getOneBased() + INDUSTRY_DESC_TECH;
        descriptor = new EditPersonDescriptorBuilder().withIndustry(VALID_INDUSTRY_TECH).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // status
        userInput = targetIndex.getOneBased() + STATUS_DESC_APPLIED;
        descriptor = new EditPersonDescriptorBuilder().withStatus(VALID_STATUS_APPLIED).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_JOB_TYPE_DESC + JOB_TYPE_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_JOB_TYPE));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + JOB_TYPE_DESC_BOB + INVALID_JOB_TYPE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_JOB_TYPE));

        // mulltiple valid fields repeated
        userInput = targetIndex.getOneBased() + JOB_TYPE_DESC_AMY + DESCRIPTION_DESC_AMY + EMAIL_DESC_AMY
                + INDUSTRY_DESC_TECH + JOB_TYPE_DESC_AMY + DESCRIPTION_DESC_AMY + EMAIL_DESC_AMY + INDUSTRY_DESC_TECH
                + JOB_TYPE_DESC_BOB + DESCRIPTION_DESC_BOB + EMAIL_DESC_BOB + INDUSTRY_DESC_FINANCE;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_JOB_TYPE, PREFIX_EMAIL,
                        PREFIX_DESCRIPTION, PREFIX_INDUSTRY));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_JOB_TYPE_DESC + INVALID_DESCRIPTION_DESC + INVALID_EMAIL_DESC
                + INVALID_JOB_TYPE_DESC + INVALID_DESCRIPTION_DESC + INVALID_EMAIL_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_JOB_TYPE, PREFIX_EMAIL, PREFIX_DESCRIPTION));
    }

    // In BizBook, industry is a single required field, so there's no "reset" functionality like tags
    // This test is removed as it's not applicable
    // @Test
    // public void parse_resetIndustry_success() {
    //     Index targetIndex = INDEX_THIRD_PERSON;
    //     String userInput = targetIndex.getOneBased() + INDUSTRY_EMPTY;
    //     EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withIndustry("").build();
    //     EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
    //     assertParseSuccess(parser, userInput, expectedCommand);
    // }
}
