package org.improving.dao;

import org.improving.client.FaithfulWordCrawler;
import org.improving.client.YouTubeCrawler;
import org.improving.entity.Sermon;
import org.improving.service.SermonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.improving.client.FaithfulWordCrawler.deserializePhoenixDate;
import static org.improving.client.YouTubeCrawler.getYouTubeInfo;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
class SermonServiceTest
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
