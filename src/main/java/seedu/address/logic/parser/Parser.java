package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a parser that is able to parse user input into a {@link Command} of type {@code T}.
 */
public interface Parser<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput The raw user input string to parse.
     * @return The parsed command of type {@code T}.
     * @throws ParseException if {@code userInput} does not conform to the expected format.
     */
    T parse(String userInput) throws ParseException;

}
