package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.BizBook;
import seedu.address.model.ReadOnlyBizBook;
import seedu.address.model.applicationstatus.ApplicationStatus;
import seedu.address.model.company.CompanyName;
import seedu.address.model.company.Deadline;
import seedu.address.model.company.Description;
import seedu.address.model.company.Email;
import seedu.address.model.company.InternshipApplication;
import seedu.address.model.company.JobType;
import seedu.address.model.industry.Industry;


/**
 * Contains utility methods for populating {@code BizBook} with sample data.
 */
public class SampleDataUtil {
    public static InternshipApplication[] getSamplePersons() {
        return new InternshipApplication[] {
            new InternshipApplication(new CompanyName("XYZ PTE LTD"), new Industry("Finance"),
                    new JobType("SWE Intern"), new Description("Need to be proficient in c++"),
                    new ApplicationStatus("Saved"), new Email("XYZ@gmail.com"), new Deadline("2024-12-31"))
        };
    }

    public static ReadOnlyBizBook getSampleAddressBook() {
        BizBook sampleAb = new BizBook();
        for (InternshipApplication sampleInternshipApplication : getSamplePersons()) {
            sampleAb.addCompany(sampleInternshipApplication);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Industry> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Industry::new)
                .collect(Collectors.toSet());
    }

}
