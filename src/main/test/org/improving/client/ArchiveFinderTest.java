package org.improving.client;

import org.improving.entity.ArchiveResource;
import org.improving.entity.Sermon;
import org.improving.service.SermonService;
import org.improving.service.SermonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
class ArchiveFinderTest {

    private ArchiveFinder archiveFinder;

    @Autowired
    private SermonService sermonService;

    @Autowired
    private SermonServiceImpl sermonRepository;

    @BeforeEach
    void setUp()
    {
        archiveFinder = new ArchiveFinder();
    }

    private enum channel {
        PART1("https://archive.org/download/CompleteYouTubeChannelPart1of11/Pastor%20Anderson%27s%20Complete%20Channel%20Part%201%20of%2011/"),
        PART2("https://archive.org/download/CompleteYouTubeChannelPart2of11/Pastor%20Anderson%27s%20Complete%20Channel%20Part%202%20of%2011/"),
        PART3("https://archive.org/download/CompleteYouTubeChannelPart3of11/Pastor%20Anderson%27s%20Complete%20Channel%20Part%203%20of%2011/"),
        PART4("https://archive.org/download/CompleteYouTubeChannelPart4of11/Pastor%20Anderson%27s%20Complete%20Channel%20Part%204%20of%2011/"),
        PART5("https://archive.org/download/CompleteYouTubeChannelPart5of11/Pastor%20Anderson%27s%20Complete%20Channel%20Part%205%20of%2011/"),
        PART6("https://archive.org/download/CompleteYouTubeChannelPart6of11/Pastor%20Anderson%27s%20Complete%20Channel%20Part%206%20of%2011/"),
        PART7("https://archive.org/download/CompleteYouTubeChannelPart7of11/Pastor%20Anderson%27s%20Complete%20Channel%20Part%207%20of%2011/"),
        PART8("https://archive.org/download/CompleteYouTubeChannelPart8of11/Pastor%20Anderson%27s%20Complete%20Channel%20Part%208%20of%2011/"),
        PART85("https://archive.org/download/CompleteYouTubeChannelPart8.5of11/Pastor%20Anderson%27s%20Complete%20Channel%20Part%208.5%20of%2011/"),
        PART9("https://archive.org/download/CompleteYouTubeChannelPart9of11/Pastor%20Anderson%27s%20Complete%20Channel%20Part%209%20of%2011/"),
        PART10("https://archive.org/download/CompleteYouTubeChannelPart10of11/Pastor%20Anderson%27s%20Complete%20Channel%20Part%2010%20of%2011/"),
        PART11("https://archive.org/download/CompleteYouTubeChannelPart11of11/Pastor%20Anderson%27s%20Complete%20Channel%20Part%2011%20of%2011/");

        private String url;

        channel(String url) {
            this.url = url;
        }
    }

    @Test
    public void findVideoReturnsVideo() throws IOException {
        List<ArchiveResource> resource = archiveFinder.retrieveVideosFromFilesPage(channel.PART1.url);
        assertEquals("001 'Church' is NOT a building!-dLmK4S6pMYI.mp4", resource.get(0).getTitle());
    }

    @Test
    public void updateArchiveResourceByIdSetsNewLink() throws IOException {
        String newValue = "Teest1";
        sermonRepository.updateArchiveResourceById(1, newValue);
        assertEquals(newValue, sermonRepository.findById(1).getArchiveResource());
    }

    @Test
    public void findAllVideos() throws IOException {

        List<ArchiveResource> resources = new ArrayList<>();

        for( channel part : channel.values())
            resources.addAll(archiveFinder.retrieveVideosFromFilesPage(part.url));
        List<Sermon> dbSermons = sermonRepository.findAll();

        for (Sermon dbSermon : dbSermons)
            for (ArchiveResource resource : resources)
                if (resource.getTitle().contains(dbSermon.getTitle()))
                    sermonRepository.updateArchiveResourceById(dbSermon.getId(), resource.getLink());



        assertEquals("001 'Church' is NOT a building!-dLmK4S6pMYI.mp4", resources.get(0).getTitle());
    }
}