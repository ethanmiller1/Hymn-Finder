package org.improving.model;

import lombok.Data;

@Data
public class Sermon {
    private String date;
    private String title;
    private String mp3;
    private String preacher;
    private YouTubeInfo youTubeInfo;

    public Sermon(String date, String title, String mp3, String preacher) {
        this.date = date;
        this.title = title;
        this.mp3 = mp3;
        this.preacher = preacher;
    }

    public String toString() {
        return String.format("%s by %s (%s)", title, preacher, date);
    }
}
