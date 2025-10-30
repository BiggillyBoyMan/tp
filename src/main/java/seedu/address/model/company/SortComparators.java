package seedu.address.model.company;

import java.util.Comparator;
import java.util.Map;

/**
 * Contains static Comparator objects for sorting InternshipApplications by various fields.
 */
public class SortComparators {

    /**
     * Defines the logical workflow order for application statuses.
     * Order: Saved (1) → Applied (2) → Interviewing (3) → Offer (4) / Rejected (5)
     */
    public static final Map<String, Integer> STATUS_ORDER = Map.of(
            "Saved", 1,
            "Applied", 2,
            "Interviewing", 3,
            "Offer", 4,
            "Rejected", 5
    );

    /** Sorts applications by company name (alphabetical, case-insensitive). */
    public static final Comparator<InternshipApplication> NAME_COMPARATOR = Comparator
            .comparing(app -> app.getName().value, String.CASE_INSENSITIVE_ORDER);

    /**
     * Sorts applications by application status in logical workflow order.
     * Order: Saved → Applied → Interviewing → Offer → Rejected
     */
    public static final Comparator<InternshipApplication> STATUS_COMPARATOR = Comparator
            .comparing(app -> STATUS_ORDER.getOrDefault(app.getStatus().value, Integer.MAX_VALUE));

    /** Sorts applications by deadline (chronological). */
    public static final Comparator<InternshipApplication> DEADLINE_COMPARATOR = Comparator
            .comparing(app -> app.getDeadline().value); // LocalDate is naturally comparable

    public static final Comparator<InternshipApplication> INDUSTRY_COMPARATOR = Comparator
            .comparing(app -> app.getIndustry().toString(), String.CASE_INSENSITIVE_ORDER);
}
