package org.improving.client;

import org.improving.entity.ArchiveResource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ArchiveFinder {

    public List<ArchiveResource> retrieveVideosFromFilesPage(String url) throws IOException {
        Document doc = Jsoup.connect( url )
                .get();
        return doc.select(".directory-listing-table > tbody > tr > td > a")
                .stream()
                .filter(e -> e.html().contains(".mp4"))
                .map(e -> new ArchiveResource(e.html(), e.attr("abs:href")))
                .collect(Collectors.toList());
    }

}
