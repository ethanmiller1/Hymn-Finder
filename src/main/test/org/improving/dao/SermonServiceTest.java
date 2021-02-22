package org.improving.dao;

import org.improving.SermonFinder;
import org.improving.entity.Sermon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
class SermonServiceTest {

    private SermonFinder sermonFinder;

    @BeforeEach
    void setUp() {
        sermonFinder = new SermonFinder();
    }

    @Test
    void addSermon() throws IOException, GeneralSecurityException {
        List<Sermon> sermons2018 = sermonFinder.findAllSermons().stream().filter(s -> s.getDate().matches("^\\d*/\\d*/18.*$")).collect(Collectors.toList());

        SermonService.addSermon( SermonFinder.addYouTubeInfo( sermons2018.get( 0)));
    }

    @Test
    void addSermon_should_not_add_duplicates() throws IOException, GeneralSecurityException {
        // Arrange
        Sermon sermon = new Sermon("12/30/18, Sun PM", "The Rapture in Thessalonians", ".com", "Pastor Anderson");
        List<Sermon> dbSermons = SermonService.getSermons();
        int expected = dbSermons.size();

        // Act
        SermonService.addSermon(SermonFinder.addYouTubeInfo(sermon), dbSermons);

        // Assert
        assertEquals(expected, dbSermons.size());
    }

    @Test
    void getSermons() {
        List<Sermon> sermons = SermonService.getSermons();
        assertEquals("The Rapture in Thessalonians by Pastor Anderson (12/30/18, Sun PM)", sermons.get(0).toString());
    }

    @Test
    void getSermon() {
        Sermon sermon = SermonService.getSermon("The Rapture in Thessalonians");
        assertEquals("The Rapture in Thessalonians by Pastor Anderson (12/30/18, Sun PM)", sermon.toString());
    }

    @Test
    void add100Sermons() throws IOException {
        List<Sermon> sermons2018 = sermonFinder.findAllSermons().stream().filter(s -> s.getDate().matches("^\\d*/\\d*/06.*$")).collect(Collectors.toList());
        List<Sermon> dbSermons = SermonService.getSermons();
        sermons2018.forEach(s -> SermonService.addSermon(s, dbSermons));
    }

    @Test
    void updateSermon() {
        List<Sermon> dbSermons = SermonService.getSermons();
        Sermon sermon = SermonService.addYouTubeInfo(dbSermons.get(0));
        assertNotNull(sermon.getYouTubeInfo());
    }

    @Test
    void updateYouTubeInfo() {
        List<String> queries = Arrays.asList(
                "Pastor Steven Anderson",
                "sanderson1611",
                "IFB Database",
                "Baptist Preaching Independent, Fundamental, KJV",
                "Good King Asa"
        );
        Sermon sermon = SermonService.updateYouTubeInfo(799, queries.get(0));
        assertNotNull(sermon.getYouTubeInfo());
    }

    @Test
    void listSermons() {
        SermonService.getSermons().stream().filter(s -> {
            boolean matches = s.getDate().matches("^\\d*/\\d*/18.*$");
            if (matches)
                System.out.println(s.getYouTubeInfo().getLink());
            return matches;
        }).collect(Collectors.toList());
    }
}