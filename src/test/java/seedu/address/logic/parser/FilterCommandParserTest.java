package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.company.ApplicationFilterPredicate;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noPrefix_throwsParseException() {
        assertParseFailure(parser, "Applied",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validStatusOnly_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommand =
                new FilterCommand(new ApplicationFilterPredicate(Optional.of("Applied"), Optional.empty()));
        assertParseSuccess(parser, " s/Applied", expectedFilterCommand);

        // multiple whitespaces before prefix
        assertParseSuccess(parser, "   s/Applied", expectedFilterCommand);
    }

    @Test
    public void parse_validIndustryOnly_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommand =
                new FilterCommand(new ApplicationFilterPredicate(Optional.empty(), Optional.of("Technology")));
        assertParseSuccess(parser, " i/Technology", expectedFilterCommand);

        // multiple whitespaces before prefix
        assertParseSuccess(parser, "   i/Technology", expectedFilterCommand);
    }

    @Test
    public void parse_validStatusAndIndustry_returnsFilterCommand() {
        // both filters present
        FilterCommand expectedFilterCommand =
                new FilterCommand(new ApplicationFilterPredicate(Optional.of("Applied"), Optional.of("Finance")));
        assertParseSuccess(parser, " s/Applied i/Finance", expectedFilterCommand);

        // reversed order
        assertParseSuccess(parser, " i/Finance s/Applied", expectedFilterCommand);

        // multiple whitespaces
        assertParseSuccess(parser, "   s/Applied   i/Finance  ", expectedFilterCommand);
    }

    @Test
    public void parse_emptyStatusValue_throwsParseException() {
        assertParseFailure(parser, " s/ i/Technology",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyIndustryValue_throwsParseException() {
        assertParseFailure(parser, " s/Applied i/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidStatus_throwsParseException() {
        // Invalid status value
        assertParseFailure(parser, " s/InvalidStatus",
                "Invalid status. Please use one of: Saved, Applied, Interviewing, Offer, Rejected.\n"
                + "Example: s/Applied");
    }

    @Test
    public void parse_invalidIndustry_throwsParseException() {
        // Invalid industry value
        assertParseFailure(parser, " i/InvalidIndustry",
                "Invalid Industry. Please use one of: Technology, Finance, Consulting, "
                        + "Healthcare, Marketing, Operations, Graphic Design.\n"
                        + "Example: i/Technology");
    }

    @Test
    public void parse_duplicateStatusPrefix_throwsParseException() {
        assertParseFailure(parser, " s/Applied s/Saved",
                "Multiple values specified for the following single-valued field(s): s/");
    }

    @Test
    public void parse_duplicateIndustryPrefix_throwsParseException() {
        assertParseFailure(parser, " i/Technology i/Finance",
                "Multiple values specified for the following single-valued field(s): i/");
    }

    @Test
    public void parse_preamblePresent_throwsParseException() {
        assertParseFailure(parser, " preamble s/Applied",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_caseInsensitiveStatus_returnsFilterCommand() {
        // Test that status parsing is case-insensitive
        FilterCommand expectedFilterCommand =
                new FilterCommand(new ApplicationFilterPredicate(Optional.of("Applied"), Optional.empty()));

        assertParseSuccess(parser, " s/applied", expectedFilterCommand);
        assertParseSuccess(parser, " s/APPLIED", expectedFilterCommand);
        assertParseSuccess(parser, " s/ApPlIeD", expectedFilterCommand);
    }

    @Test
    public void parse_caseInsensitiveIndustry_returnsFilterCommand() {
        // Test that industry parsing is case-insensitive
        FilterCommand expectedFilterCommand =
                new FilterCommand(new ApplicationFilterPredicate(Optional.empty(), Optional.of("Technology")));

        assertParseSuccess(parser, " i/technology", expectedFilterCommand);
        assertParseSuccess(parser, " i/TECHNOLOGY", expectedFilterCommand);
        assertParseSuccess(parser, " i/TeChnOLoGy", expectedFilterCommand);
    }
}

