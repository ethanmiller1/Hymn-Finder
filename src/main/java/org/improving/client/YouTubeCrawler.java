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

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.improving.utility.FileUtil.getKeys;

public class YouTubeCrawler {
    private static final String APPLICATION_NAME = "API code samples";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final LinkedList<String> DEVELOPER_KEY = getKeys();

    /**
     * Build and return an authorized API client service.
     *
     * @return an authorized API client service
     * @throws GeneralSecurityException, IOException
     */
    public static YouTube getService()
            throws GeneralSecurityException,
            IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new YouTube.Builder(httpTransport,
                JSON_FACTORY,
                null).setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static Sermon addYouTubeInfo(Sermon sermon)
            throws GeneralSecurityException,
            IOException {
        SearchResult firstResult = searchYouTube(String.format("%s \"%s\"",
                sermon.getPreacher(),
                sermon.getTitle()));
        sermon.setYouTubeInfo(new YouTubeInfo(firstResult.getId()
                .getVideoId(),
                firstResult.getSnippet()
                        .getChannelTitle(),
                firstResult.getSnippet()
                        .getDescription(),
                firstResult.getSnippet()
                        .getTitle()));
        return sermon;
    }

    public static YouTubeInfo getYouTubeInfo(String query)
            throws GeneralSecurityException,
            IOException {
        SearchResult firstResult = searchYouTube(query);
        return new YouTubeInfo(firstResult.getId()
                .getVideoId(),
                firstResult.getSnippet()
                        .getChannelTitle(),
                firstResult.getSnippet()
                        .getDescription(),
                firstResult.getSnippet()
                        .getTitle());
    }

    public static SearchResult searchYouTube(String query)
            throws IOException,
            GeneralSecurityException {
        YouTube youtubeService = getService();
        /* Define and execute the API request. */
        YouTube.Search.List request = youtubeService.search()
                .list(Arrays.asList("snippet"));
        while (true) {
            try {
                return request.setKey(DEVELOPER_KEY.getFirst())
                        .setMaxResults(1L)
                        .setQ(query)
                        .setType(Arrays.asList("video"))
                        .setVideoDefinition("high")
                        .execute()
                        .getItems()
                        .get(0);
            } catch (GoogleJsonResponseException e) {
                DEVELOPER_KEY.pop();
                e.printStackTrace();
                continue;
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
        }
    }
}
