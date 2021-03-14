package org.improving;

import org.improving.client.HymnFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class HymnFinderTest
{

   HymnFinder hymnFinder;

   @BeforeEach
   void setup()
   {
      // Arrange
      hymnFinder = new HymnFinder();
   }

   @Test
   void getStanzas_should_replace_breaks_with_spaces_afterwards()
         throws IOException
   {
      // Acts
      var hymn = hymnFinder.getStanzas( "Wonderful Peace" );
      System.out.println( hymn );

      //Assert
      assertFalse( hymn.contains( "\n " ) );
   }

   @Test
   void getStanzas_should_replace_numbers_with_periods()
         throws IOException
   {
      // Acts
      var hymn = hymnFinder.getStanzas( "Yesterday, Today, Forever" );
      System.out.println( hymn );

      //Assert
      assertFalse( hymn.contains( "1. " ) );

   }

   @Test
   void getStanzas_should_replace_quotes_with_two_quotes()
         throws IOException
   {
      // Acts
      var hymn = hymnFinder.getStanzas( "Go Tell it on the Mountain\n" );
      System.out.println( hymn );

      //Assert
      assertTrue( hymn.contains( "\"\"" ) );
   }

   @Test
   void getStanzas_should_replace_begin_and_end_with_quotes()
         throws IOException
   {
      // Acts
      var hymn = hymnFinder.getStanzas( "Master, the Tempest is Raging" );
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
      var hymn = hymnFinder.getStanzas( "Master, the Tempest is Raging" );
      System.out.println( hymn );

      //Assert
      assertTrue( hymn.contains( "deep?\n\nThe winds" ) );
   }

   @Test
   void getStanzas_when_only_one_verse()
         throws IOException
   {
      // Acts
      var hymn = hymnFinder.getStanzas( "Jesus Loves the Little Children" );
      System.out.println( hymn );

      //Assert
      assertTrue( hymn.contains( "Jesus loves the little children," ) );
   }

   @Test
   void getStanzas_when_no_paragraph_breaks()
         throws IOException
   {
      // Acts
      var hymn = hymnFinder.getStanzas( "Singing I Go" );
      System.out.println( hymn );

      //Assert
      assertTrue( hymn.contains( "ads!\n\nc\nSin" ) );
   }

   @Test
   void getMultipleHymns_should_be_formatted_correctly()
         throws IOException
   {
      // Arrange
      var searches = HymnFinder.SEARCHES.stream().limit( 5 ).collect( Collectors.toList() );

      // Act
      var hymns = hymnFinder.getMultipleHymns( searches );

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
      var author = hymnFinder.getAuthor( "Wonderful Peace" );

      //Assert
      assertEquals( "W. D. Cornell", author );
   }

   @Test
   void getMultipleAuthors_should_return_multiple_authors()
         throws IOException
   {
      // Arrange
      var searches = HymnFinder.SEARCHES.stream().limit( 5 ).collect( Collectors.toList() );

      // Act
      var hymns = hymnFinder.getMultipleAuthors( searches );

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