package org.improving.client;

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

public class FaithfulWordCrawler
{

   private String                            FAITHFUL_WORD_URL =
           "http://www.faithfulwordbaptist.org/page5.html";

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
