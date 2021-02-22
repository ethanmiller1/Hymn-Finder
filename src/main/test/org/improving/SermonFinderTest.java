package org.improving;

import org.improving.entity.Sermon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class SermonFinderTest
{

   private SermonFinder sermonFinder;

   @BeforeEach
   void setUp()
   {
      sermonFinder = new SermonFinder();
   }

   @Test
   void findAllSermons()
         throws IOException
   {
      List<Sermon> sermons2018 = sermonFinder.findAllSermons().stream().filter( s -> {
         System.out.println( s.toString() );
         return s.getDate().matches( "^\\d*/\\d*/18.*$" );
      } ).collect( Collectors.toList() );
      assertEquals(
            "The Rapture in Thessalonians by Pastor Anderson (12/30/18, Sun PM)",
            sermons2018.get( 0 ).toString() );
   }

   @Test
   void addYouTubeInfo()
         throws IOException, GeneralSecurityException
   {
      Sermon sermon =
            new Sermon( "12/30/18, Sun PM", "The Rapture in Thessalonians", ".com", "Pastor Anderson" );
      Sermon updatedSermon = SermonFinder.addYouTubeInfo( sermon );
      String result = updatedSermon.getYouTubeInfo().getLink();
      System.out.println( result );
      assertEquals( "https://www.youtube.com/watch?v=Cb1rO6ojwtw", result );
   }

   @Test
   void buildPlaylist()
         throws IOException, GeneralSecurityException
   {
      List<Sermon> sermons2018 = sermonFinder.findAllSermons().stream().filter( s -> {
         System.out.println( s.toString() );
         return s.getDate().matches( "^\\d*/\\d*/18.*$" );
      } ).collect( Collectors.toList() ).subList( 0, 5 );

      StringBuilder sb = new StringBuilder();
      sb.append( "http://www.youtube.com/watch_videos?video_ids=" );
      for( Sermon sermon : sermons2018 )
      {
         sb.append( SermonFinder.addYouTubeInfo( sermon ).getYouTubeInfo().getVideoId() + "," );
         System.out.println( SermonFinder.addYouTubeInfo( sermon ).getYouTubeInfo().getVideoId() );
      }

      String result = sb.toString();
      System.out.println( result );

      assertEquals(
            "http://www.youtube.com/watch_videos?video_ids=Cb1rO6ojwtw,fhEF2q6Oo6Y,AojrCsAG_GI,dwfdKnTC53M,IXiAgoMKg-E,",
            result );
   }

   @Test
   void listUrls()
         throws IOException, GeneralSecurityException
   {
      List<Sermon> sermons2018 = sermonFinder.findAllSermons()
                                             .stream()
                                             .filter( s -> s.getDate().matches( "^\\d*/\\d*/18.*$" ) )
                                             .collect( Collectors.toList() );
      for( Sermon sermon : sermons2018 )
         System.out.println( SermonFinder.addYouTubeInfo( sermon ).getYouTubeInfo().getLink() );
   }
}