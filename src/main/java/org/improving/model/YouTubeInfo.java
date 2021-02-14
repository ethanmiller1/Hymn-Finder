package org.improving.model;

import lombok.Data;

@Data
public class YouTubeInfo {
    String link;
    String videoId;
    String channelTitle;
    String description;
    String title;

    public YouTubeInfo(String link, String videoId, String channelTitle, String description, String title) {
        this.link = link;
        this.videoId = videoId;
        this.channelTitle = channelTitle;
        this.description = description;
        this.title = title;
    }
}
