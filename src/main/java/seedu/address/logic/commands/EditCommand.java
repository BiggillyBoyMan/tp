package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicationstatus.ApplicationStatus;
import seedu.address.model.company.CompanyName;
import seedu.address.model.company.Deadline;
import seedu.address.model.company.Description;
import seedu.address.model.company.Email;
import seedu.address.model.company.InternshipApplication;
import seedu.address.model.company.JobType;
import seedu.address.model.industry.Industry;



/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the application identified "
            + "by the index number used in the displayed application list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_COMPANY_NAME + "COMPANY_NAME] "
            + "[" + PREFIX_INDUSTRY + "INDUSTRY] "
            + "[" + PREFIX_JOB_TYPE + "JOB_ROLE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_DEADLINE + "DEADLINE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_JOB_TYPE + "SWE Intern "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Application: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON =
            "The application for this company and job role already exists in the application tracker";

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

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new CommandException(MESSAGE_NOT_EDITED);
        }

        InternshipApplication internshipApplicationToEdit = lastShownList.get(index.getZeroBased());
        InternshipApplication editedInternshipApplication = createEditedApplication(
                internshipApplicationToEdit, editPersonDescriptor);

        if (!internshipApplicationToEdit.isSameApplication(editedInternshipApplication)
                && model.hasPerson(editedInternshipApplication)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(internshipApplicationToEdit, editedInternshipApplication);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS,
                Messages.format(editedInternshipApplication)));
    }

    /**
     * Creates and returns a {@code InternshipApplication} with the details of {@code applicationToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static InternshipApplication createEditedApplication(
            InternshipApplication applicationToEdit,
            EditPersonDescriptor editPersonDescriptor) {
        assert applicationToEdit != null;

        CompanyName updatedCompanyName = editPersonDescriptor.getName()
                .orElse(applicationToEdit.getName());
        Industry updatedIndustry = editPersonDescriptor.getIndustry()
                .orElse(applicationToEdit.getIndustry());
        JobType updatedJobType = editPersonDescriptor.getJobType()
                .orElse(applicationToEdit.getJobType());
        Email updatedEmail = editPersonDescriptor.getEmail()
                .orElse(applicationToEdit.getEmail());
        Description updatedDescription = editPersonDescriptor.getDescription()
                .orElse(applicationToEdit.getDescription());
        ApplicationStatus updatedStatus = editPersonDescriptor.getStatus()
                .orElse(applicationToEdit.getStatus());
        Deadline updatedDeadline = editPersonDescriptor.getDeadline()
                .orElse(applicationToEdit.getDeadline());

        return new InternshipApplication(updatedCompanyName, updatedIndustry, updatedJobType,
                updatedDescription, updatedStatus, updatedEmail, updatedDeadline);
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
     * Stores the details to edit the application with. Each non-empty field value will replace the
     * corresponding field value of the application.
     */
    public static class EditPersonDescriptor {
        private CompanyName companyName;
        private Industry industry;
        private JobType jobType;
        private Email email;
        private Description description;
        private ApplicationStatus status;
        private Deadline deadline;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.companyName);
            setIndustry(toCopy.industry);
            setJobType(toCopy.jobType);
            setEmail(toCopy.email);
            setDescription(toCopy.description);
            setStatus(toCopy.status);
            setDeadline(toCopy.deadline);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(companyName, industry, jobType, email, description, status, deadline);
        }

        public void setName(CompanyName companyName) {
            this.companyName = companyName;
        }

        public Optional<CompanyName> getName() {
            return Optional.ofNullable(companyName);
        }

        public void setIndustry(Industry industry) {
            this.industry = industry;
        }

        public Optional<Industry> getIndustry() {
            return Optional.ofNullable(industry);
        }

        public void setJobType(JobType jobType) {
            this.jobType = jobType;
        }

        public Optional<JobType> getJobType() {
            return Optional.ofNullable(jobType);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setStatus(ApplicationStatus status) {
            this.status = status;
        }

        public Optional<ApplicationStatus> getStatus() {
            return Optional.ofNullable(status);
        }

        public void setDeadline(Deadline deadline) {
            this.deadline = deadline;
        }

        public Optional<Deadline> getDeadline() {
            return Optional.ofNullable(deadline);
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
                    && Objects.equals(industry, otherEditPersonDescriptor.industry)
                    && Objects.equals(jobType, otherEditPersonDescriptor.jobType)
                    && Objects.equals(email, otherEditPersonDescriptor.email)
                    && Objects.equals(description, otherEditPersonDescriptor.description)
                    && Objects.equals(status, otherEditPersonDescriptor.status)
                    && Objects.equals(deadline, otherEditPersonDescriptor.deadline);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("companyName", companyName)
                    .add("industry", industry)
                    .add("jobType", jobType)
                .add("email", email)
                .add("description", description)
                .add("status", status)
                .add("deadline", deadline)
                .toString();
        }
    }
}
