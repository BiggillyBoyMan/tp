package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CompanyBuilder;

public class ApplicationFilterPredicateTest {

    @Test
    public void equals() {
        ApplicationFilterPredicate firstPredicate =
                new ApplicationFilterPredicate(Optional.of("Applied"), Optional.of("Technology"));
        ApplicationFilterPredicate secondPredicate =
                new ApplicationFilterPredicate(Optional.of("Saved"), Optional.of("Finance"));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ApplicationFilterPredicate firstPredicateCopy =
                new ApplicationFilterPredicate(Optional.of("Applied"), Optional.of("Technology"));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different status -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_statusMatches_returnsTrue() {
        // Filter by status only - Applied
        ApplicationFilterPredicate predicate =
                new ApplicationFilterPredicate(Optional.of("Applied"), Optional.empty());
        assertTrue(predicate.test(new CompanyBuilder().withStatus("Applied").build()));
    }

    @Test
    public void test_statusDoesNotMatch_returnsFalse() {
        // Filter by status - looking for Applied but application is Saved
        ApplicationFilterPredicate predicate =
                new ApplicationFilterPredicate(Optional.of("Applied"), Optional.empty());
        assertFalse(predicate.test(new CompanyBuilder().withStatus("Saved").build()));
    }

    @Test
    public void test_industryMatches_returnsTrue() {
        // Filter by industry only - Technology
        ApplicationFilterPredicate predicate =
                new ApplicationFilterPredicate(Optional.empty(), Optional.of("Technology"));
        assertTrue(predicate.test(new CompanyBuilder().withIndustry("Technology").build()));
    }

    @Test
    public void test_industryDoesNotMatch_returnsFalse() {
        // Filter by industry - looking for Technology but application is Finance
        ApplicationFilterPredicate predicate =
                new ApplicationFilterPredicate(Optional.empty(), Optional.of("Technology"));
        assertFalse(predicate.test(new CompanyBuilder().withIndustry("Finance").build()));
    }

    @Test
    public void test_statusAndIndustryMatch_returnsTrue() {
        // Filter by both status and industry - both match
        ApplicationFilterPredicate predicate =
                new ApplicationFilterPredicate(Optional.of("Applied"), Optional.of("Technology"));
        assertTrue(predicate.test(new CompanyBuilder().withStatus("Applied").withIndustry("Technology").build()));
    }

    @Test
    public void test_statusMatchesButIndustryDoesNot_returnsFalse() {
        // Filter by both - status matches but industry doesn't
        ApplicationFilterPredicate predicate =
                new ApplicationFilterPredicate(Optional.of("Applied"), Optional.of("Technology"));
        assertFalse(predicate.test(new CompanyBuilder().withStatus("Applied").withIndustry("Finance").build()));
    }

    @Test
    public void test_industryMatchesButStatusDoesNot_returnsFalse() {
        // Filter by both - industry matches but status doesn't
        ApplicationFilterPredicate predicate =
                new ApplicationFilterPredicate(Optional.of("Applied"), Optional.of("Technology"));
        assertFalse(predicate.test(new CompanyBuilder().withStatus("Saved").withIndustry("Technology").build()));
    }

    @Test
    public void test_caseInsensitiveMatching_returnsTrue() {
        // Test case-insensitive matching for status
        ApplicationFilterPredicate statusPredicate =
                new ApplicationFilterPredicate(Optional.of("applied"), Optional.empty());
        assertTrue(statusPredicate.test(new CompanyBuilder().withStatus("Applied").build()));

        // Test case-insensitive matching for industry
        ApplicationFilterPredicate industryPredicate =
                new ApplicationFilterPredicate(Optional.empty(), Optional.of("technology"));
        assertTrue(industryPredicate.test(new CompanyBuilder().withIndustry("Technology").build()));
    }

    @Test
    public void test_noFilters_returnsTrue() {
        // When no filters are specified (edge case), should match everything
        ApplicationFilterPredicate predicate =
                new ApplicationFilterPredicate(Optional.empty(), Optional.empty());
        assertTrue(predicate.test(new CompanyBuilder().build()));
    }

    @Test
    public void toStringMethod() {
        ApplicationFilterPredicate predicate =
                new ApplicationFilterPredicate(Optional.of("Applied"), Optional.of("Technology"));
        String expected = ApplicationFilterPredicate.class.getCanonicalName()
                + "{status=Applied, industry=Technology}";
        assertEquals(expected, predicate.toString());
    }

    @Test
    public void toStringMethod_missingStatus() {
        ApplicationFilterPredicate predicate =
                new ApplicationFilterPredicate(Optional.empty(), Optional.of("Technology"));
        String expected = ApplicationFilterPredicate.class.getCanonicalName()
                + "{status=any, industry=Technology}";
        assertEquals(expected, predicate.toString());
    }

    @Test
    public void toStringMethod_missingIndustry() {
        ApplicationFilterPredicate predicate =
                new ApplicationFilterPredicate(Optional.of("Applied"), Optional.empty());
        String expected = ApplicationFilterPredicate.class.getCanonicalName()
                + "{status=Applied, industry=any}";
        assertEquals(expected, predicate.toString());
    }
}

