package org.improving;

import org.improving.model.Sermon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class SermonFinderTest {

    private SermonFinder sermonFinder;

    @BeforeEach
    void setUp() {
        sermonFinder = new SermonFinder();
    }

    @Test
    void findAllSermons() throws IOException {
        List<Sermon> sermons2018 = sermonFinder.findAllSermons().stream().filter(s -> {
            System.out.println(s.toString());
            return s.getDate().matches("^\\d*/\\d*/18.*$");
        }).collect(Collectors.toList());
        assertEquals("The Rapture in Thessalonians by Pastor Anderson (12/30/18, Sun PM)", sermons2018.get(0).toString());
    }

    @Test
    void searchForSermon() throws IOException {
        Sermon sermon = new Sermon("12/30/18, Sun PM", "The Rapture in Thessalonians", ".com", "Pastor Anderson");

        sermonFinder.searchForSermon(sermon, "");

//        assertEquals("The Rapture in Thessalonians by Pastor Anderson (12/30/18, Sun PM)", sermons.get(0).toString());
    }
}