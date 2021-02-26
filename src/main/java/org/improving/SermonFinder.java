package org.improving;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResult;
import org.improving.entity.Sermon;
import org.improving.entity.YouTubeInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.improving.utility.FileUtil.getKeys;

public class SermonFinder
{

   private static final String               APPLICATION_NAME  = "API code samples";
   private static final JsonFactory          JSON_FACTORY      = JacksonFactory.getDefaultInstance();
   private static final LinkedList< String > DEVELOPER_KEY     = getKeys();
   private String                            FAITHFUL_WORD_URL =
      "http://www.faithfulwordbaptist.org/page5.html";

   /**
    * Build and return an authorized API client service.
    *
    * @return an authorized API client service
    * @throws GeneralSecurityException, IOException
    */
   public static YouTube getService()
      throws GeneralSecurityException,
      IOException
   {
      final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
      return new YouTube.Builder( httpTransport,
                                  JSON_FACTORY,
                                  null ).setApplicationName( APPLICATION_NAME )
                                        .build();
   }

   public static Sermon addYouTubeInfo( Sermon sermon )
      throws GeneralSecurityException,
      IOException
   {
      SearchResult firstResult = searchYouTube( String.format( "%s \"%s\"",
                                                               sermon.getPreacher(),
                                                               sermon.getTitle() ) );
      sermon.setYouTubeInfo( new YouTubeInfo( firstResult.getId()
                                                         .getVideoId(),
                                              firstResult.getSnippet()
                                                         .getChannelTitle(),
                                              firstResult.getSnippet()
                                                         .getDescription(),
                                              firstResult.getSnippet()
                                                         .getTitle() ) );
      return sermon;
   }

   public static SearchResult searchYouTube( String query )
      throws IOException,
      GeneralSecurityException
   {
      YouTube youtubeService = getService();
      // Define and execute the API request
      YouTube.Search.List request = youtubeService.search()
                                                  .list( Arrays.asList( "snippet" ) );
      while( true )
      {
         try
         {
            return request.setKey( DEVELOPER_KEY.getFirst() )
                          .setMaxResults( 1L )
                          .setQ( query )
                          .setType( Arrays.asList( "video" ) )
                          .setVideoDefinition( "high" )
                          .execute()
                          .getItems()
                          .get( 0 );
         }
         catch( GoogleJsonResponseException e )
         {
            DEVELOPER_KEY.pop();
            e.printStackTrace();
            continue;
         }
         catch( NoSuchElementException e )
         {
            e.printStackTrace();
         }
      }
   }

   /**
    * @return a list of {@link Sermon}s preached at Faithful Word Baptist Church
    * @throws IOException
    */
   public List< Sermon > findAllSermons()
      throws IOException
   {
      Document doc = Jsoup.connect( FAITHFUL_WORD_URL )
                          .get();
      Elements sermons = doc.select( ".list" )
                            .first()
                            .children();
      List< Sermon > result = new ArrayList<>();
      for( var sermon : sermons )
      {
         try
         {
            Elements info = sermon.children();
            String date = info.get( 0 )
                              .html();
            String title = info.get( 1 )
                               .html();
            String mp3 = info.get( 2 )
                             .children()
                             .stream()
                             .filter( link -> link.html()
                                                  .contains( "mp3" ) )
                             .findFirst()
                             .orElse( null )
                             .attr( "abs:href" );
            String preacher = info.get( 3 )
                                  .html();
            result.add( new Sermon( date,
                                    title,
                                    mp3,
                                    preacher ) );
         }
         catch( Exception e )
         {
            continue;
         }
      }

      return result;
   }
}
