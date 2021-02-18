package org.improving.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "youtubeinfo")
@Data
public class YouTubeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true)
    private int id;
    @Column(name = "Link")
    String link;
    @Column(name = "VideoId")
    String videoId;
    @Column(name = "ChannelTitle")
    String channelTitle;
    @Column(name = "Description")
    String description;
    @Column(name = "Title")
    String title;

    public YouTubeInfo() {
    }

    public YouTubeInfo(String link, String videoId, String channelTitle, String description, String title) {
        this.link = link;
        this.videoId = videoId;
        this.channelTitle = channelTitle;
        this.description = description;
        this.title = title;
    }

    public void updateValues(String link, String videoId, String channelTitle, String description, String title) {
        setLink(link);
        setVideoId(videoId);
        setChannelTitle(channelTitle);
        setDescription(description);
        setTitle(title);
    }
}
