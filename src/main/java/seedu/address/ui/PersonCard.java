package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.Company.InternshipApplication;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    public final InternshipApplication internshipApplication;

    @FXML
    private HBox cardPane;
    @FXML
    private Label companyName;
    @FXML
    private Label id;
    @FXML
    private Label jobType;
    @FXML
    private Label description;
    @FXML
    private Label email;
    @FXML
    private Label industries;
    @FXML
    private Label status;
    // --- END OF CHANGES ---

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(InternshipApplication internshipApplication, int displayedIndex) {
        super(FXML);
        this.internshipApplication = internshipApplication;
        id.setText(displayedIndex + ". ");
        companyName.setText(internshipApplication.getName().CompanyName);
        jobType.setText(internshipApplication.getJobType().value);
        description.setText(internshipApplication.getDescription().value);
        email.setText(internshipApplication.getEmail().value);
        industries.setText(internshipApplication.getIndustry().value);
        status.setText(internshipApplication.getStatus().value);
    }
}
