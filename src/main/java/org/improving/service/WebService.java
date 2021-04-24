package org.improving.service;

import org.improving.client.ArchiveCrawler;
import org.improving.client.FaithfulWordCrawler;
import org.improving.client.HymnaryCrawler;
import org.improving.client.IFBTubeCrawler;
import org.improving.client.YouTubeCrawler;
import org.improving.dao.SermonRepository;
import org.improving.entity.ArchiveResource;
import org.improving.entity.Sermon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.improving.client.FaithfulWordCrawler.deserializePhoenixDate;
import static org.improving.client.YouTubeCrawler.getYouTubeInfo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class exists to invoke web crawls using JUnit.
 * Methods marked as tests are NOT actually tests.
 * They make actual calls to websites and the UPA datastore.
 */
public abstract class WebService {

    static class FaithfulWordCrawlerService {
        private FaithfulWordCrawler faithfulWordCrawler;

        @BeforeEach
        void setUp()
        {
            faithfulWordCrawler = new FaithfulWordCrawler();
        }

        @Test
        void findAllSermons()
                throws IOException
        {
            List<Sermon> sermons2018 = faithfulWordCrawler.findAllSermons().stream().filter(s -> {
                System.out.println( s.toString() );
                return s.getDate().matches( "^\\d*/\\d*/18.*$" );
            } ).collect( Collectors.toList() );
            assertEquals(
                    "The Rapture in Thessalonians by Pastor Anderson (12/30/18, Sun PM)",
                    sermons2018.get( 0 ).toString() );
        }

        @Test
        void listUrls()
                throws IOException
        {
            List<Sermon> sermons2018 = faithfulWordCrawler.findAllSermons()
                    .stream()
                    .filter( s -> s.getDate().matches( "^\\d*/\\d*/18.*$" ) )
                    .collect( Collectors.toList() );
            for( Sermon sermon : sermons2018 )
                System.out.println( sermon.getYouTubeInfo().getVideoId() );
        }
    }


    @SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
    static
    class ArchiveCrawlerService {

        private ArchiveCrawler archiveCrawler;

        @Autowired
        private SermonService sermonService;

        @Autowired
        private SermonServiceImpl sermonRepository;

        @BeforeEach
        void setUp()
        {
            archiveCrawler = new ArchiveCrawler();
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
            List<ArchiveResource> resource = archiveCrawler.retrieveVideosFromFilesPage(channel.PART1.url);
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
                resources.addAll(archiveCrawler.retrieveVideosFromFilesPage(part.url));
            List<Sermon> dbSermons = sermonRepository.findAll();

            for (Sermon dbSermon : dbSermons)
                for (ArchiveResource resource : resources)
                    if (resource.getTitle().contains(dbSermon.getTitle()))
                        sermonRepository.updateArchiveResourceById(dbSermon.getId(), resource.getLink());



            assertEquals("001 'Church' is NOT a building!-dLmK4S6pMYI.mp4", resources.get(0).getTitle());
        }
    }


    @SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
    static
    class IFBTubeCrawlerService {

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


    static class YouTubeCrawlerService {

        private FaithfulWordCrawler faithfulWordCrawler;

        @BeforeEach
        void setUp()
        {
            faithfulWordCrawler = new FaithfulWordCrawler();
        }

        @Test
        void addYouTubeInfo()
                throws IOException, GeneralSecurityException
        {
            Sermon sermon =
                    new Sermon( deserializePhoenixDate("12/30/18, Sun PM"), "The Rapture in Thessalonians", ".com", "Pastor Anderson" );
            Sermon updatedSermon = YouTubeCrawler.addYouTubeInfo( sermon );
            String result = updatedSermon.getYouTubeInfo().getVideoId();
            System.out.println( result );
            assertEquals( "https://www.youtube.com/watch?v=Cb1rO6ojwtw", result );
        }

        @Test
        void buildPlaylist()
                throws IOException, GeneralSecurityException
        {
            List<Sermon> sermons2018 = faithfulWordCrawler.findAllSermons().stream().filter(s -> {
                System.out.println( s.toString() );
                return s.getDate().matches( "^\\d*/\\d*/18.*$" );
            } ).collect( Collectors.toList() ).subList( 0, 5 );

            StringBuilder sb = new StringBuilder();
            sb.append( "http://www.youtube.com/watch_videos?video_ids=" );
            for( Sermon sermon : sermons2018 )
            {
                sb.append( YouTubeCrawler.addYouTubeInfo( sermon ).getYouTubeInfo().getVideoId() + "," );
                System.out.println( YouTubeCrawler.addYouTubeInfo( sermon ).getYouTubeInfo().getVideoId() );
            }

            String result = sb.toString();
            System.out.println( result );

            assertEquals(
                    "http://www.youtube.com/watch_videos?video_ids=Cb1rO6ojwtw,fhEF2q6Oo6Y,AojrCsAG_GI,dwfdKnTC53M,IXiAgoMKg-E,",
                    result );
        }

    }

    @SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
    static class SermonServiceCaller
    {

        @Autowired
        SermonService sermonService;

        @Autowired
        SermonRepository sermonRepository;

        private FaithfulWordCrawler faithfulWordCrawler;

        @BeforeEach
        void setUp()
        {
            faithfulWordCrawler = new FaithfulWordCrawler();
        }

        @Test
        void addSermon()
                throws IOException, GeneralSecurityException
        {
            List<Sermon> sermons2018 = faithfulWordCrawler.findAllSermons()
                    .stream()
                    .filter( s -> s.getDate().matches( "^\\d*/\\d*/18.*$" ) )
                    .collect( Collectors.toList() );

            sermonService.addSermon( YouTubeCrawler.addYouTubeInfo( sermons2018.get( 0 ) ) );
        }

        @Test
        void addSermon_should_not_add_duplicates()
                throws IOException, GeneralSecurityException
        {
            // Arrange
            Sermon sermon =
                    new Sermon( deserializePhoenixDate("12/30/18, Sun PM"), "The Rapture in Thessalonians", ".com", "Pastor Anderson" );
            List<Sermon> dbSermons = sermonService.findAll();
            int expected = dbSermons.size();

            // Act
            sermonService.addSermon( YouTubeCrawler.addYouTubeInfo( sermon ) );

            // Assert
            assertEquals( expected, dbSermons.size() );
        }

        @Test
        void findAll()
        {
            List<Sermon> sermons = sermonService.findAll();
            assertEquals(
                    "The Rapture in Thessalonians by Pastor Anderson (12/30/18, Sun PM)",
                    sermons.get( 0 ).toString() );
        }

        @Test
        void getSermon()
        {
            Sermon sermon = sermonService.findByTitle( "The Rapture in Thessalonians" );
            assertEquals( "The Rapture in Thessalonians by Pastor Anderson (12/30/18, Sun PM)", sermon.toString() );
        }

        @Test
        void add100Sermons()
                throws IOException
        {
            List<Sermon> sermons2018 = faithfulWordCrawler.findAllSermons()
                    .stream()
                    .filter( s -> s.getDate().matches( "^\\d*/\\d*/06.*$" ) )
                    .collect( Collectors.toList() );
            sermons2018.forEach( s -> sermonService.addSermon( s ) );
        }

        @Test
        void updateSermon() throws GeneralSecurityException, IOException {
            List<Sermon> dbSermons = sermonService.findAll();
            Sermon dbSermon = dbSermons.get(0);
            sermonService.updateYouTubeInfoById( getYouTubeInfo(String.format( "%s \"%s\"",
                    dbSermon.getPreacher(),
                    dbSermon.getTitle() )), dbSermon.getId() );
            assertNotNull( sermonService.findById(dbSermon.getId()).getYouTubeInfo() );
        }

        @Test
        void updateYouTubeInfo() throws GeneralSecurityException, IOException {
            List<String> queries = Arrays.asList(
                    "Pastor Steven Anderson",
                    "sanderson1611",
                    "IFB Database",
                    "Baptist Preaching Independent, Fundamental, KJV",
                    "Good King Asa" );

            sermonService.updateYouTubeInfoById( getYouTubeInfo(queries.get( 0 )), 799 );
            assertNotNull( sermonService.findById(799).getYouTubeInfo() );
        }

        @Test
        void listSermons()
        {
            sermonService.findAll().stream().filter( s -> {
                boolean matches = s.getDate().matches( "^\\d*/\\d*/18.*$" );
                if( matches )
                    System.out.println( s.getYouTubeInfo().getVideoId() );
                return matches;
            } ).collect( Collectors.toList() );
        }

        @Test
        void convertDate()
        {
            Sermon sermon = sermonService.findById(1);

            String dateString = "12/30/18, Sun PM";
            sermon.setDatetime(deserializePhoenixDate(dateString));
            sermonRepository.save(sermon);
        }

        @Test
        void convertAllDates()
        {
            sermonService.findAll().forEach(sermon -> {
                sermon.setDatetime(deserializePhoenixDate(sermon.getDate()));
                sermonRepository.save(sermon);
            });
        }
    }

    static class HymnaryCrawlerService
    {

        HymnaryCrawler hymnaryCrawler;

        @BeforeEach
        void setup()
        {
            // Arrange
            hymnaryCrawler = new HymnaryCrawler();
        }

        @Test
        void getStanzas_should_replace_breaks_with_spaces_afterwards()
                throws IOException
        {
            // Acts
            var hymn = hymnaryCrawler.getStanzas( "Wonderful Peace" );
            System.out.println( hymn );

            //Assert
            assertFalse( hymn.contains( "\n " ) );
        }

        @Test
        void getStanzas_should_replace_numbers_with_periods()
                throws IOException
        {
            // Acts
            var hymn = hymnaryCrawler.getStanzas( "Yesterday, Today, Forever" );
            System.out.println( hymn );

            //Assert
            assertFalse( hymn.contains( "1. " ) );

        }

        @Test
        void getStanzas_should_replace_quotes_with_two_quotes()
                throws IOException
        {
            // Acts
            var hymn = hymnaryCrawler.getStanzas( "Go Tell it on the Mountain\n" );
            System.out.println( hymn );

            //Assert
            assertTrue( hymn.contains( "\"\"" ) );
        }

        @Test
        void getStanzas_should_replace_begin_and_end_with_quotes()
                throws IOException
        {
            // Acts
            var hymn = hymnaryCrawler.getStanzas( "Master, the Tempest is Raging" );
            System.out.println( hymn );

            //Assert
            assertTrue( hymn.startsWith( "\"" ) );
            assertTrue( hymn.endsWith( "\"" ) );
        }

        @Test
        void getStanzas_should_separate_verses_without_numbers_in_input()
                throws IOException
        {
            // Acts
            var hymn = hymnaryCrawler.getStanzas( "Master, the Tempest is Raging" );
            System.out.println( hymn );

            //Assert
            assertTrue( hymn.contains( "deep?\n\nThe winds" ) );
        }

        @Test
        void getStanzas_when_only_one_verse()
                throws IOException
        {
            // Acts
            var hymn = hymnaryCrawler.getStanzas( "Jesus Loves the Little Children" );
            System.out.println( hymn );

            //Assert
            assertTrue( hymn.contains( "Jesus loves the little children," ) );
        }

        @Test
        void getStanzas_when_no_paragraph_breaks()
                throws IOException
        {
            // Acts
            var hymn = hymnaryCrawler.getStanzas( "Singing I Go" );
            System.out.println( hymn );

            //Assert
            assertTrue( hymn.contains( "ads!\n\nc\nSin" ) );
        }

        @Test
        void getMultipleHymns_should_be_formatted_correctly()
                throws IOException
        {
            // Arrange
            var searches = HymnaryCrawler.SEARCHES.stream().limit( 5 ).collect( Collectors.toList() );

            // Act
            var hymns = hymnaryCrawler.getMultipleHymns( searches );

            System.out.println( "\n" );
            var sb = new StringBuilder();
            hymns.forEach( h -> {
                sb.append( h );
                sb.append( "\n" );
            } );
            System.out.println( sb.toString() );

            //Assert
            assertTrue( sb.toString().contains( "gladness\"\n\"Some" ) );
        }

        @Test
        void getAuthor_should_return_author()
                throws IOException
        {
            // Acts
            var author = hymnaryCrawler.getAuthor( "Wonderful Peace" );

            //Assert
            assertEquals( "W. D. Cornell", author );
        }

        @Test
        void getMultipleAuthors_should_return_multiple_authors()
                throws IOException
        {
            // Arrange
            var searches = HymnaryCrawler.SEARCHES.stream().limit( 5 ).collect( Collectors.toList() );

            // Act
            var hymns = hymnaryCrawler.getMultipleAuthors( searches );

            System.out.println( "\n" );
            var sb = new StringBuilder();
            hymns.forEach( h -> {
                sb.append( h );
                sb.append( "\n" );
            } );
            System.out.println( sb.toString() );

            //Assert
            assertTrue( sb.toString().contains( "Fanny Crosby (1891)" ) );
        }

    }

}
