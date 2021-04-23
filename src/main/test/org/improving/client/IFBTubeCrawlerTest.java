package org.improving.client;

import org.improving.entity.ArchiveResource;
import org.improving.entity.Sermon;
import org.improving.service.SermonServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
class IFBTubeCrawlerTest {

    @Autowired
    private SermonServiceImpl sermonRepository;

    @Test
    public void test() throws IOException {
        ArchiveResource archiveResource = IFBTubeCrawler.findSermon(new Sermon(null, "Judges 9", "", "Pastor Anderson"));
        assertEquals(new ArchiveResource("Judges 9 Steven Anderson", "https://archive.org/download/judges-9-steven-anderson/Judges%209%20Steven%20Anderson.mp4"), archiveResource);
    }

    @Test
    void updateWorksFor1Video() throws IOException {
        sermonRepository.updateArchiveResourceById(2, IFBTubeCrawler.findSermon(sermonRepository.findById(2)).getLink());
        assertEquals("https://archive.org/download/TheEveningAndTheMorningByStevenAnderson/The%20Evening%20and%20the%20Morning%20by%20Steven%20Anderson.mp4", sermonRepository.findById(2).getArchiveResource());
    }

    @Test
    void colonIsReplacedIfSearchFailsOnce() throws IOException {
        sermonRepository.updateArchiveResourceById(7, IFBTubeCrawler.findSermon(sermonRepository.findById(7)).getLink());
        assertEquals("https://archive.org/download/TheEveningAndTheMorningByStevenAnderson/The%20Evening%20and%20the%20Morning%20by%20Steven%20Anderson.mp4", sermonRepository.findById(2).getArchiveResource());
    }

    @Test
    void specialCharactersAreReplacedIfSearchFailsOnce() throws IOException {
        sermonRepository.updateArchiveResourceById(10, IFBTubeCrawler.findSermon(sermonRepository.findById(10)).getLink());
    }

    @Test
    public void findAllVideos() throws IOException {

        List<Sermon> dbSermons = sermonRepository.findAll();

        for (Sermon dbSermon : dbSermons.subList(9, dbSermons.size() - 1))
            sermonRepository.updateArchiveResourceById(dbSermon.getId(), IFBTubeCrawler.findSermon(dbSermon).getLink());
    }
}
