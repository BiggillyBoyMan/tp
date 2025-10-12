package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.Company.*;
import seedu.address.model.Company.CompanyName;
import seedu.address.model.Industry.Industry;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private CompanyName companyName;
    private JobType jobType;
    private Email email;
    private Description description;
    private Set<Industry> industries;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        companyName = new CompanyName(DEFAULT_NAME);
        jobType = new JobType(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        description = new Description(DEFAULT_ADDRESS);
        industries = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(InternshipApplication internshipApplicationToCopy) {
        companyName = internshipApplicationToCopy.getName();
        jobType = internshipApplicationToCopy.getJobType();
        email = internshipApplicationToCopy.getEmail();
        description = internshipApplicationToCopy.getDescription();
        industries = new HashSet<>(internshipApplicationToCopy.getIndustry());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.companyName = new CompanyName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.industries = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.description = new Description(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.jobType = new JobType(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public InternshipApplication build() {
        return new InternshipApplication(companyName, jobType, email, description, industries);
    }

}
