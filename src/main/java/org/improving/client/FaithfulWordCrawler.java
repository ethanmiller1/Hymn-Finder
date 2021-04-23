package org.improving.client;

import org.improving.entity.Sermon;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FaithfulWordCrawler
{

   private String FAITHFUL_WORD_URL = "http://www.faithfulwordbaptist.org/page5.html";

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

   public static Instant deserializePhoenixDate(String date) {
      return LocalDateTime.parse(
              String.format("%s %s", date, getNthWord(date,2).equalsIgnoreCase("Sun") ? getNthWord(date,3).equals("AM") ? "10:30" : "05:30" : "07:00"),
              DateTimeFormatter.ofPattern("MM/dd/uu, EEE a hh:mm", Locale.US))
              .atZone(ZoneId.of("-07:00"))
              .toInstant();
   }

   public static String getNthWord(String fullString, int nth)
   {
      return fullString.split("\\s")[nth - 1];
   }
}
