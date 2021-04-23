package org.improving.client;

import org.improving.entity.Sermon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class FaithfulWordCrawlerTest
{

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
         throws IOException, GeneralSecurityException
   {
      List<Sermon> sermons2018 = faithfulWordCrawler.findAllSermons()
                                             .stream()
                                             .filter( s -> s.getDate().matches( "^\\d*/\\d*/18.*$" ) )
                                             .collect( Collectors.toList() );
      for( Sermon sermon : sermons2018 )
         System.out.println( sermon.getYouTubeInfo().getVideoId() );
   }

    @Test
    void deserializeDateInfers530FromSundayPM() {
       String dateString = "12/30/18, Sun PM";
       Instant instant = FaithfulWordCrawler.deserializePhoenixDate(dateString);
       assertEquals("2018-12-31T00:30:00Z", instant.toString());
    }

   @Test
   void deserializeDateInfers1030FromSundayAM() {
      String dateString = "12/30/18, Sun AM";
      Instant instant = FaithfulWordCrawler.deserializePhoenixDate(dateString);
      assertEquals("2018-12-30T17:30:00Z", instant.toString());
   }

   @Test
   void deserializeDateInfers7FromPM() {
      String dateString = "12/26/18, Wed PM";
      Instant instant = FaithfulWordCrawler.deserializePhoenixDate(dateString);
      assertEquals("2018-12-27T02:00:00Z", instant.toString());
   }
}
