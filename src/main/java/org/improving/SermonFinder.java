package org.improving;

import org.improving.model.Sermon;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SermonFinder {

    private String FAITHFUL_WORD_URL = "http://www.faithfulwordbaptist.org/page5.html";

    public List<Sermon> findAllSermons() throws IOException {
        Document doc = Jsoup.connect(FAITHFUL_WORD_URL).get();
        Elements sermons = doc.select(".list").first().children();
        List<Sermon> result = new ArrayList<>();
        for (var sermon : sermons) {
            try {
                Elements info = sermon.children();
                String date = info.get(0).html();
                String title = info.get(1).html();
                String mp3 = info.get(2).children().stream().filter(link -> link.html().contains("mp3")).findFirst().orElse(null).attr("abs:href");
                String preacher = info.get(3).html();
                result.add(new Sermon(date, title, mp3, preacher));
            } catch (Exception e) {
                continue;
            }
        }

        return result;
    }
}
