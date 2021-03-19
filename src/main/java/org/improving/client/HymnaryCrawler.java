package org.improving.client;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class HymnaryCrawler
{

   static public final List< String > SEARCHES  = Arrays.asList( "Caught Up Together",
                                                                 "Face to Face",
                                                                 "We'll Never Say Goodbye",
                                                                 "Saved by Grace",
                                                                 "What a Day That Will Be",
                                                                 "At Calvary",
                                                                 "The Pearly White City",
                                                                 "Remembering in Heaven",
                                                                 "We Shall Shine as the Stars",
                                                                 "Will Jesus Find Us Watching?",
                                                                 "Some Golden Daybreak",
                                                                 "Christ Returneth",
                                                                 "On Jordan's Stormy Banks",
                                                                 "Behold, He Comes!",
                                                                 "When We See Christ",
                                                                 "When He Cometh",
                                                                 "I'm Going Higher",
                                                                 "Jesus is Coming Again",
                                                                 "Jesus! Jesus! Jesus!",
                                                                 "In the Garden",
                                                                 "Just When I Need Him Most",
                                                                 "Some Time We'll Understand",
                                                                 "Does Jesus Care?",
                                                                 "Jesus Lover of My Soul",
                                                                 "Hiding in Thee",
                                                                 "How Can I Be Lonely",
                                                                 "Art Thou Weary, Art Thou Languid?",
                                                                 "The Lord is My Shepherd",
                                                                 "God Leads Us Along",
                                                                 "I Need Thee Every Hour",
                                                                 "The Name of Jesus",
                                                                 "Come Ye Disconsolate",
                                                                 "Day by Day",
                                                                 "Jesus Never Fails",
                                                                 "He Hideth My Soul",
                                                                 "Lean on His Arms",
                                                                 "All That Thrills My Soul",
                                                                 "Praise the Saviour",
                                                                 "Sun of My Soul",
                                                                 "Saviour, Like a Shepherd Lead Us",
                                                                 "All the Way My Saviour Leads Me",
                                                                 "Be Still My Soul",
                                                                 "Wonderful Peace",
                                                                 "The Great Physician",
                                                                 "No One Ever Cared for Me Like Jesus",
                                                                 "He Leadeth Me",
                                                                 "Till the Storm Passes By",
                                                                 "Jesus Saviour Pilot Me",
                                                                 "Like a River Glorious",
                                                                 "O God, Our Help",
                                                                 "The Christian's Goodnight",
                                                                 "God Will Take Care of You",
                                                                 "The Solid Rock",
                                                                 "Rock of Ages",
                                                                 "Yesterday, Today, Forever",
                                                                 "Christ is All I Need",
                                                                 "Safe Wherever I Go",
                                                                 "My Anchor Holds",
                                                                 "I Know I Am Saved",
                                                                 "Master, the Tempest is Raging",
                                                                 "In Times Like These",
                                                                 "The Haven of Rest",
                                                                 "I Know Whom I Have Believed",
                                                                 "We Have an Anchor",
                                                                 "A Mighty Fortress is Our God",
                                                                 "It Is Well With My Soul",
                                                                 "A Shelter in the Time of Storm",
                                                                 "Trusting Jesus",
                                                                 "My Faith Has Found a Resting Place",
                                                                 "Security",
                                                                 "Blest Be the Tie That Binds",
                                                                 "Jesus is the Sweetest Name I Know",
                                                                 "Come, Thou Almighty King",
                                                                 "O For a Thousand Tongues to Sing",
                                                                 "Blessed Be the Name",
                                                                 "Our Great Saviour",
                                                                 "To God be the Glory",
                                                                 "Praise Him! Praise Him!",
                                                                 "O Worship the King",
                                                                 "I Will Praise Him",
                                                                 "All Hail the Power",
                                                                 "All Hail the Power of Jesus' Name",
                                                                 "Hallelujah, What a Saviour!",
                                                                 "My Jesus, I Love Thee",
                                                                 "It's Just Like His Great Love",
                                                                 "Jesus Loves the Little Children",
                                                                 "When Love Shines In",
                                                                 "Jesus Loves Even Me",
                                                                 "Such Love",
                                                                 "Isn't the Love of Jesus Something Wonderful?",
                                                                 "I Love Him",
                                                                 "Wonderful Story of Love",
                                                                 "O How I Love Jesus",
                                                                 "I Love Thee, My Jesus",
                                                                 "My Saviour's Love",
                                                                 "Jesus Loves Me",
                                                                 "The Love of God",
                                                                 "I Never Will Cease to Love Him",
                                                                 "Singing I Go",
                                                                 "Joy Unspeakable",
                                                                 "He Keeps Me Singing",
                                                                 "O! Say, But Im Glad",
                                                                 "Only a Sinner",
                                                                 "Grace Greater Than Our Sin",
                                                                 "Sunshine in the Soul",
                                                                 "O Happy Day",
                                                                 "There Shall Be Showers of Blessing",
                                                                 "I Know I Love Thee Better, Lord",
                                                                 "More Love to Thee",
                                                                 "From Every Stormy Wind",
                                                                 "Standing on the Promises",
                                                                 "Throw Out the Lifeline",
                                                                 "Let the Lower Lights Be Burning",
                                                                 "Sound the Battle Cry",
                                                                 "There's a Song in the Air",
                                                                 "Thou Didst Leave Thy Throne",
                                                                 "No Room in the Inn",
                                                                 "While Shepherds Watched Their Flocks",
                                                                 "Angels from the Realms of Glory",
                                                                 "O Little Town of Bethlehem",
                                                                 "O Perfect Love",
                                                                 "From Every Stormy Wind",
                                                                 "This is My Father's World",
                                                                 "Ship Ahoy!" );
   static private final String        DELIMITER = "\"";

   static public void printAllHymns( List< String > hymns )
   {
      System.out.println( "\n" );
      hymns.forEach( h -> System.out.println( h ) );
   }

   public List< String > getMultipleHymns( List< String > searches )
   {
      var hymns = new ArrayList< String >();

      searches.forEach( s ->
      {
         try
         {
            hymns.add( getStanzas( s ) );
         }
         catch( IOException | IndexOutOfBoundsException e )
         {
            hymns.add( "\"Add lyrics manually\"" );
         }
      } );

      return hymns;
   }

   public String getFirstResult( String query )
      throws IOException
   {

      // Get all links on search page
      Document doc = Jsoup.connect( "https://hymnary.org/search?qu=" +
                                    query.replace( " ",
                                                   "+" ) )
                          .get();
      Elements elements = doc.select( "a[href]" );
      var links = elements.stream()
                          .map( l -> l.attr( "abs:href" ) )
                          .filter( l -> l.contains( "result" ) )
                          .collect( Collectors.toList() );

      return links.get( 0 );
   }

   public String getStanzas( String query )
      throws IOException
   {

      var firstresult = getFirstResult( query );

      // Get document for first result or hardcode "Get it later"
      Document doc = Jsoup.connect( firstresult )
                          .get();
      var element = doc.getElementsByClass( "authority_columns" )
                       .get( 0 );
      var stanzaElements = element.children()
                                  .stream()
                                  .map( e -> e.html()
                                              .replaceAll( "<br><br>Source:.*",
                                                           "" )
                                              .replace( "\"",
                                                        "\"\"" )
                                              .replaceAll( "\\s?<br\\s?/?>\\s?",
                                                           "\n" )
                                              .replaceAll( "1.? ",
                                                           "" )
                                              .replaceAll( "\\d.? ",
                                                           "\n" )
                                              .replace( "Chorus:",
                                                        "\nc" )
                                              .replace( "Refrain:",
                                                        "\nc" ) )
                                  .collect( Collectors.toList() );

      // Build the Stanzas string
      var sb = new StringBuilder();
      sb.append( DELIMITER );

      for( var stanza : stanzaElements )
         sb.append( stanza );

      var last = sb.lastIndexOf( "\n" );
      sb.replace( last,
                  sb.length(),
                  DELIMITER );

      // Print a dot to alert the user that the program is running.
      System.out.print( "." );
      return sb.toString();
   }

   public String getAuthor( String title )
      throws IOException
   {

      var firstresult = getFirstResult( title );

      // Get document for first result or hardcode "Get it later"
      Document doc = Jsoup.connect( firstresult )
                          .get();
      System.out.print( "." );
      return doc.select( "a[href]" )
                .stream()
                .filter( l -> l.attr( "href" )
                               .equalsIgnoreCase( "#Author" ) )
                .filter( e -> e.html()
                               .length() > 6 )
                .findFirst()
                .get()
                .html()
                .replace( "Author: ",
                          "" );
   }

   public List< String > getMultipleAuthors( List< String > searches )
   {
      var hymns = new ArrayList< String >();

      searches.forEach( s ->
      {
         try
         {
            hymns.add( getAuthor( s ) );
         }
         catch( IOException | IndexOutOfBoundsException | NoSuchElementException e )
         {
            hymns.add( "Anonymous" );
         }
      } );

      return hymns;
   }
}
