package org.improving.client;

import org.improving.entity.ArchiveResource;
import org.improving.entity.Sermon;
import org.improving.service.SermonServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
class IFBTubeCrawlerTest {

    @Autowired
    private SermonServiceImpl sermonRepository;

    @Test
    public void test() throws IOException {
        ArchiveResource archiveResource = IFBTubeCrawler.findSermon(new Sermon("", "Judges 9", "", "Pastor Anderson"));
        assertEquals(new ArchiveResource("Judges 9 Steven Anderson", "https://archive.org/download/judges-9-steven-anderson/Judges%209%20Steven%20Anderson.mp4"), archiveResource);
    }

    @Test
    void updateWorksFor1Video() throws IOException {
        sermonRepository.updateArchiveResourceById(2, IFBTubeCrawler.findSermon(sermonRepository.findById(2)).getLink());
        assertEquals("https://archive.org/download/TheEveningAndTheMorningByStevenAnderson/The%20Evening%20and%20the%20Morning%20by%20Steven%20Anderson.mp4", sermonRepository.findById(2).getArchiveResource());
    }

}