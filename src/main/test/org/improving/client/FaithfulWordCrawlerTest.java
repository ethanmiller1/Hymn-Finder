package org.improving.client;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FaithfulWordCrawlerTest
{

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

   @Test
   void serializeDateInfers7FromPM() {
      String dateString = "12/26/18, Wed PM";
      Instant instant = FaithfulWordCrawler.deserializePhoenixDate(dateString);
      LocalDateTime.ofInstant(instant, ZoneId.of("-07:00"));
      assertEquals("2018-12-27T02:00:00Z", instant.toString());
   }

   @Test
   void serializeDateInfers7byDefault() {
      String dateString = "08/04/17, Toronto";
      Instant instant = FaithfulWordCrawler.deserializePhoenixDate(dateString);
      LocalDateTime.ofInstant(instant, ZoneId.of("-07:00"));
      assertEquals("2017-08-05T02:00:00Z", instant.toString());
   }

   @Test
   void serializeDateReturnsNullOnNonsenseDate() {
      String dateString = "Blueberries, Toronto";
      Instant instant = FaithfulWordCrawler.deserializePhoenixDate(dateString);
      assertNull(instant);
   }

}
