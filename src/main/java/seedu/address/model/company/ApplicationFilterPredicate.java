package seedu.address.model.company;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that an {@code InternshipApplication}'s {@code Status} and/or {@code Industry}
 * matches the specified filter criteria.
 */
public class ApplicationFilterPredicate implements Predicate<InternshipApplication> {
    private final Optional<String> status;
    private final Optional<String> industry;

    /**
     * Constructs an ApplicationFilterPredicate with optional status and industry filters.
     * At least one of the parameters should be present.
     *
     * @param status Optional status to filter by (case-insensitive).
     * @param industry Optional industry to filter by (case-insensitive).
     */
    public ApplicationFilterPredicate(Optional<String> status, Optional<String> industry) {
        this.status = status;
        this.industry = industry;
    }

    @Override
    public boolean test(InternshipApplication application) {
        boolean statusMatches = status.isEmpty()
                || application.getStatus().value.equalsIgnoreCase(status.get());
        boolean industryMatches = industry.isEmpty()
                || application.getIndustry().value.equalsIgnoreCase(industry.get());

        return statusMatches && industryMatches;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApplicationFilterPredicate)) {
            return false;
        }

        ApplicationFilterPredicate otherPredicate = (ApplicationFilterPredicate) other;
        return status.equals(otherPredicate.status)
                && industry.equals(otherPredicate.industry);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("status", status.orElse("any"))
                .add("industry", industry.orElse("any"))
                .toString();
    }
}

