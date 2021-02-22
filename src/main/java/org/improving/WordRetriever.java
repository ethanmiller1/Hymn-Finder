package org.improving;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WordRetriever
{

   public static void main( String[] args )
      throws IOException
   {
      Document doc = Jsoup.connect( "https://randomword.com/" )
                          .get();
      var body = doc.body();
      var word = body.getElementById( "random_word" )
                     .html();
      System.out.println( word );
   }
}
