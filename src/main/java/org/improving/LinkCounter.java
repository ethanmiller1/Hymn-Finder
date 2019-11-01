package org.improving;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Set;

public class LinkCounter {

    public void getLinks(String url, Set<String> linkSet) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");

        // Add each link to set
        for (Element link : links) {
            // Remove hashes from links
            var q = link.attr("abs:href");
            var b = q.split("#");
            var l = b[0];

            // Ensure the link is coming from improving
            if (l.contains("improving.com")) {
                try {
                    if (!linkSet.contains(l)) {
                        System.out.println("Checking " + l);
                        linkSet.add(l);
                        getLinks(l, linkSet);
                    }
                } catch (UnsupportedMimeTypeException ex) { // for PDFs
                    System.out.println("This is a PDF.");
                    linkSet.add(l);
                } catch (HttpStatusException ex) { // For 404 errors
                    System.out.println(l + " has responded with a 404 status code.");
                    linkSet.add(l);
                }
            }
        }

        System.out.println("Unique links: " + linkSet.size());
    }
}
