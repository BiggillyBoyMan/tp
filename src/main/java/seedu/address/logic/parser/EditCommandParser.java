package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Industry.Industry;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_DESCRIPTION, PREFIX_EMAIL, PREFIX_INDUSTRY, PREFIX_JOB_TYPE, PREFIX_STATUS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COMPANY_NAME, PREFIX_DESCRIPTION, PREFIX_EMAIL, PREFIX_INDUSTRY, PREFIX_JOB_TYPE, PREFIX_STATUS);

        EditPersonDescriptor editApplicationDescriptor = new EditPersonDescriptor();

        if (argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()) {
            editApplicationDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_COMPANY_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_JOB_TYPE).isPresent()) {
            editApplicationDescriptor.setPhone(ParserUtil.parseJobType(argMultimap.getValue(PREFIX_JOB_TYPE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editApplicationDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editApplicationDescriptor.setDescription(ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_INDUSTRY).isPresent()) {

        }


        if (!editApplicationDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editApplicationDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
//    private Optional<Set<Industry>> parseTagsForEdit(Collection<String> tags) throws ParseException {
//        assert tags != null;
//
//        if (tags.isEmpty()) {
//            return Optional.empty();
//        }
//        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
//        return Optional.of(ParserUtil.parseTags(tagSet));
//    }

}
