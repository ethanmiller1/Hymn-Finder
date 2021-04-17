package org.improving.client;

import org.improving.entity.ArchiveResource;
import org.improving.entity.Sermon;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class IFBTubeCrawler {

    /**
     * @return a list of {@link Sermon}s preached at Faithful Word Baptist Church
     * @throws IOException
     */
    public static ArchiveResource findSermon(Sermon sermon )
            throws IOException
    {
        try {
            /* Step 1: Search IFBTube. */
            String IFB_TUBE_URL = "https://www.ifbtube.com/";
            String searchUrl = String.format("%s?s=\"%s\"+%s", IFB_TUBE_URL, formatQuery(sermon.getTitle()), formatQuery(sermon.getPreacher().substring(sermon.getPreacher().indexOf(" ") + 1)));
            Document searchResultsPage = Jsoup.connect(searchUrl)
                    .timeout(10 * 1000)
                    .get();
            String sermonPageLink = searchResultsPage.select( ".post .entry-title a" )
                    .attr( "abs:href" );
            if(sermonPageLink.isEmpty()) {
                searchResultsPage = Jsoup.connect(searchUrl.substring(0,8) + searchUrl.substring(8).replaceAll("[\":,]",""))
                        .timeout(10 * 1000)
                        .get();
                sermonPageLink = searchResultsPage.select( ".post .entry-title a" )
                        .attr( "abs:href" );
            }

            /* Step 2: Visit the sermon page. */
            Document sermonPage = Jsoup.connect(sermonPageLink)
                    .timeout(10 * 1000)
                    .get();

            /* Step 3: Visit the Embedded iframe and get archive data. */
            String iframeLink = sermonPage.select("iframe").attr("src");
            Document iframePage = Jsoup.connect(iframeLink)
                    .timeout(10 * 1000)
                    .get();
            String videoSource = iframePage.select("video source").attr("abs:src");
            return new ArchiveResource(searchResultsPage.select(".entry-title a").html(), videoSource);
        } catch (IllegalArgumentException e) {
            return new ArchiveResource(null, null);
        }

    }

    private static String formatQuery(String query) {
        return query.toLowerCase().replace(" ", "+");
    }

}