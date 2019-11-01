package org.improving;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class LinkCounter {
    public static void main(String[] args) throws IOException {
        var links = getLinks("https://improving.com");

        // Use second set to avoid ConcurrentModificationException modify to for I structure
        var totalLinks = new HashSet<>();

        for (var link : links) {
                totalLinks.addAll(getLinks(link));
        }

        // Add initial set to total set
        totalLinks.addAll(links);
        for (var link : totalLinks) {
            System.out.println(link);
        }
        System.out.println("\nTotal unique links at improving.com: " + totalLinks.size());
    }

    // TODO: write function that calls itself for recursion

    private static Set<String> getLinks(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");

        // Add each link to set
        Set<String> uniqueLinks = new HashSet<>();
        for (Element link : links) {
            if (link.attr("abs:href").contains("improving.com")) {
                uniqueLinks.add(link.attr("abs:href"));
            }
        }

        System.out.println("Unique links: " + uniqueLinks.size());
        return uniqueLinks;
    }
}
