package org.improving.service;

import org.improving.SermonFinder;
import org.improving.entity.Sermon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class SermonServiceTest {

    private SermonFinder sermonFinder;

    @BeforeEach
    void setUp() {
        sermonFinder = new SermonFinder();
    }

    @Test
    void addSermon() throws IOException, GeneralSecurityException {
        List<Sermon> sermons2018 = sermonFinder.findAllSermons().stream().filter(s -> s.getDate().matches("^\\d*/\\d*/18.*$")).collect(Collectors.toList());

        SermonService.addSermon(sermons2018.get(0));
    }
}