package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalBizBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.InternshipApplication;
import seedu.address.testutil.CompanyBuilder;


/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalBizBook(), new UserPrefs());
    }

    @Test
    public void execute_newApplication_success() {
        // We use the CompanyBuilder (as requested) to build a new, valid application
        InternshipApplication validApplication = new CompanyBuilder().withName("Netflix")
                .withJobType("Backend Engineer")
                .withIndustry("Technology").withStatus("Saved").build();

        Model expectedModel = new ModelManager(model.getBizBook(), new UserPrefs());
        expectedModel.addCompany(validApplication);

        assertCommandSuccess(new AddCommand(validApplication), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validApplication)),
                expectedModel);
    }

    @Test
    public void execute_duplicateApplication_throwsCommandException() {
        // This takes the first application from our sample data (Google)
        InternshipApplication applicationInList = model.getBizBook().getPersonList().get(0);
        // Try to add the same application again; identity is based on name and job type,
        // so this should be treated as a duplicate.
        assertCommandFailure(new AddCommand(applicationInList), model,
                AddCommand.MESSAGE_DUPLICATE_COMPANY);
    }

}
