package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Company.*;
import seedu.address.model.Company.CompanyName;
import seedu.address.model.Industry.Industry;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_COMPANY_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<InternshipApplication> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        InternshipApplication internshipApplicationToEdit = lastShownList.get(index.getZeroBased());
        InternshipApplication editedInternshipApplication = createEditedPerson(internshipApplicationToEdit, editPersonDescriptor);

        if (!internshipApplicationToEdit.isSameApplication(editedInternshipApplication) && model.hasPerson(editedInternshipApplication)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(internshipApplicationToEdit, editedInternshipApplication);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedInternshipApplication)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static InternshipApplication createEditedPerson(InternshipApplication internshipApplicationToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert internshipApplicationToEdit != null;

        CompanyName updatedCompanyName = editPersonDescriptor.getName().orElse(internshipApplicationToEdit.getName());
        JobType updatedJobType = editPersonDescriptor.getPhone().orElse(internshipApplicationToEdit.getJobType());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(internshipApplicationToEdit.getEmail());
        Description updatedDescription = editPersonDescriptor.getAddress().orElse(internshipApplicationToEdit.getDescription());
        Set<Industry> updatedIndustries = editPersonDescriptor.getTags().orElse(internshipApplicationToEdit.getIndustry());

        return new InternshipApplication(updatedCompanyName, updatedJobType, updatedEmail, updatedDescription, updatedIndustries);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private CompanyName companyName;
        private JobType jobType;
        private Email email;
        private Description description;
        private Set<Industry> industries;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.companyName);
            setPhone(toCopy.jobType);
            setEmail(toCopy.email);
            setAddress(toCopy.description);
            setTags(toCopy.industries);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(companyName, jobType, email, description, industries);
        }

        public void setName(CompanyName companyName) {
            this.companyName = companyName;
        }

        public Optional<CompanyName> getName() {
            return Optional.ofNullable(companyName);
        }

        public void setPhone(JobType jobType) {
            this.jobType = jobType;
        }

        public Optional<JobType> getPhone() {
            return Optional.ofNullable(jobType);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Description description) {
            this.description = description;
        }

        public Optional<Description> getAddress() {
            return Optional.ofNullable(description);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Industry> industries) {
            this.industries = (industries != null) ? new HashSet<>(industries) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Industry>> getTags() {
            return (industries != null) ? Optional.of(Collections.unmodifiableSet(industries)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            EditPersonDescriptor otherEditPersonDescriptor = (EditPersonDescriptor) other;
            return Objects.equals(companyName, otherEditPersonDescriptor.companyName)
                    && Objects.equals(jobType, otherEditPersonDescriptor.jobType)
                    && Objects.equals(email, otherEditPersonDescriptor.email)
                    && Objects.equals(description, otherEditPersonDescriptor.description)
                    && Objects.equals(industries, otherEditPersonDescriptor.industries);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", companyName)
                    .add("phone", jobType)
                    .add("email", email)
                    .add("address", description)
                    .add("tags", industries)
                    .toString();
        }
    }
}
