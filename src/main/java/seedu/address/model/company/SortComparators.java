package seedu.address.model.company;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains static Comparator objects for sorting InternshipApplications by various fields.
 */
public class SortComparators {

    /**
     * Defines the logical workflow order for application statuses.
     * Order: Saved (1) → Applied (2) → Interviewing (3) → Offer (4) / Rejected (5)
     */
    private static final Map<String, Integer> STATUS_ORDER = new HashMap<>();
    static {
        STATUS_ORDER.put("Saved", 1);
        STATUS_ORDER.put("Applied", 2);
        STATUS_ORDER.put("Interviewing", 3);
        STATUS_ORDER.put("Offer", 4);
        STATUS_ORDER.put("Rejected", 5);
    }

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
}
