package org.improving.client;

import org.improving.entity.Sermon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.stream.Collectors;

import static org.improving.client.FaithfulWordCrawler.deserializePhoenixDate;
import static org.junit.jupiter.api.Assertions.*;

class YouTubeCrawlerTest {

    private FaithfulWordCrawler faithfulWordCrawler;

    @BeforeEach
    void setUp()
    {
        faithfulWordCrawler = new FaithfulWordCrawler();
    }

    @Test
    void addYouTubeInfo()
            throws IOException, GeneralSecurityException
    {
        Sermon sermon =
                new Sermon( deserializePhoenixDate("12/30/18, Sun PM"), "The Rapture in Thessalonians", ".com", "Pastor Anderson" );
        Sermon updatedSermon = YouTubeCrawler.addYouTubeInfo( sermon );
        String result = updatedSermon.getYouTubeInfo().getVideoId();
        System.out.println( result );
        assertEquals( "https://www.youtube.com/watch?v=Cb1rO6ojwtw", result );
    }

    @Test
    void buildPlaylist()
            throws IOException, GeneralSecurityException
    {
        List<Sermon> sermons2018 = faithfulWordCrawler.findAllSermons().stream().filter(s -> {
            System.out.println( s.toString() );
            return s.getDate().matches( "^\\d*/\\d*/18.*$" );
        } ).collect( Collectors.toList() ).subList( 0, 5 );

        StringBuilder sb = new StringBuilder();
        sb.append( "http://www.youtube.com/watch_videos?video_ids=" );
        for( Sermon sermon : sermons2018 )
        {
            sb.append( YouTubeCrawler.addYouTubeInfo( sermon ).getYouTubeInfo().getVideoId() + "," );
            System.out.println( YouTubeCrawler.addYouTubeInfo( sermon ).getYouTubeInfo().getVideoId() );
        }

        String result = sb.toString();
        System.out.println( result );

        assertEquals(
                "http://www.youtube.com/watch_videos?video_ids=Cb1rO6ojwtw,fhEF2q6Oo6Y,AojrCsAG_GI,dwfdKnTC53M,IXiAgoMKg-E,",
                result );
    }

}
