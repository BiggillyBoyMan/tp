package seedu.address.model.company;

import java.util.Comparator;

/**
 * Contains static Comparator objects for sorting InternshipApplications by various fields.
 */
public class SortComparators {

    /** Sorts applications by company name (alphabetical, case-insensitive). */
    public static final Comparator<InternshipApplication> NAME_COMPARATOR = Comparator
            .comparing(app -> app.getName().value, String.CASE_INSENSITIVE_ORDER);

    /** Sorts applications by application status (alphabetical, case-insensitive). */
    public static final Comparator<InternshipApplication> STATUS_COMPARATOR = Comparator
            .comparing(app -> app.getStatus().value, String.CASE_INSENSITIVE_ORDER);

    /** Sorts applications by deadline (chronological). */
    public static final Comparator<InternshipApplication> DEADLINE_COMPARATOR = Comparator
            .comparing(app -> app.getDeadline().value); // LocalDate is naturally comparable
}
