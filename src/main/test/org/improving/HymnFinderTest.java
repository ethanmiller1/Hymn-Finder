package org.improving;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class HymnFinderTest {

    HymnFinder hymnFinder;

    @BeforeEach
    void setup() {
        // Arrange
        hymnFinder = new HymnFinder();
    }

    @Test
    void getStanzas_should_replace_breaks_with_spaces_afterwards() throws IOException {
        // Acts
        var hymn = hymnFinder.getStanzas("Wonderful Peace");
        System.out.println(hymn);

        //Assert
        assertFalse(hymn.contains("\n "));
    }

    @Test
    void getStanzas_should_replace_numbers_with_periods() throws IOException {
        // Acts
        var hymn = hymnFinder.getStanzas("Yesterday, Today, Forever");
        System.out.println(hymn);

        //Assert
        assertFalse(hymn.contains("1. "));

    }

    @Test
    void getStanzas_should_replace_quotes_with_two_quotes() throws IOException {
        // Acts
        var hymn = hymnFinder.getStanzas("Art thou weary, art thou languid");
        System.out.println(hymn);

        //Assert
        assertTrue(hymn.contains("\"\""));
    }

    @Test
    void getStanzas_should_replace_begin_and_end_with_quotes() throws IOException {
        // Acts
        var hymn = hymnFinder.getStanzas("Master, the Tempest is Raging");
        System.out.println(hymn);

        //Assert
        assertTrue(hymn.startsWith("\""));
        assertTrue(hymn.endsWith("\""));
    }

    @Test
    void getStanzas_should_separate_verses_without_numbers_in_input() throws IOException {
        // Acts
        var hymn = hymnFinder.getStanzas("Master, the Tempest is Raging");
        System.out.println(hymn);

        //Assert
        assertTrue(hymn.contains("deep?\n\nThe winds"));
    }

    @Test
    void getMultipleHymns_should_be_formatted_correctly() throws IOException {
        // Arrange
        var searches = HymnFinder.SEARCHES.stream().limit(5).collect(Collectors.toList());

        // Act
        var hymns = hymnFinder.getMultipleHymns(searches);

        System.out.println("\n");
        var sb = new StringBuilder();
        hymns.forEach(h -> {
            sb.append(h);
            sb.append("\n");
        });
        System.out.println(sb.toString());

        //Assert
        assertTrue(sb.toString().contains("gladness\"\n\"Some"));
    }




}