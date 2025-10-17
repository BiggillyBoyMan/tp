package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ApplicationStatus.ApplicationStatus;
import seedu.address.model.Company.CompanyName;
import seedu.address.model.Company.Description;
import seedu.address.model.Company.Email;
import seedu.address.model.Company.JobType;
import seedu.address.model.Industry.Industry;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static CompanyName parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!CompanyName.isValidName(trimmedName)) {
            throw new ParseException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        return new CompanyName(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static JobType parseJobType(String jobTypeString) throws ParseException {
        requireNonNull(jobTypeString);
        String trimmedJobType = jobTypeString.trim();

        // 1. Check validity using the static method on the JobType class
        if (!JobType.isValidJobType(trimmedJobType)) {
            // 2. Throw with the correct constraint message
            throw new ParseException(JobType.MESSAGE_CONSTRAINTS);
        }

        // 3. Create and return the new JobType object
        return new JobType(trimmedJobType);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Industry parseIndustry(String industry) throws ParseException {
        requireNonNull(industry);
        String trimmedIndustry = industry.trim();
        if (!Industry.isValidIndustry(trimmedIndustry)) {
            throw new ParseException(Industry.MESSAGE_CONSTRAINTS);
        }
        return new Industry(trimmedIndustry);
    }

    /**
     * Parses a {@code String statusString} into an {@code ApplicationStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param statusString The status string to parse.
     * @return An ApplicationStatus object.
     * @throws ParseException if the given {@code statusString} is invalid.
     */
    public static ApplicationStatus parseStatus(String statusString) throws ParseException {
        requireNonNull(statusString);
        String trimmedStatus = statusString.trim();
        if (!ApplicationStatus.isValidStatus(trimmedStatus)) {
            // Check validity against the predefined set of statuses
            throw new ParseException(ApplicationStatus.MESSAGE_CONSTRAINTS);
        }
        return new ApplicationStatus(trimmedStatus);
    }
}
