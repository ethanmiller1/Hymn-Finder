package org.improving.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class YouTubeInfo
{

   @Column( name = "video_id" )
   String videoId;
   @Column( name = "channel_title" )
   String channelTitle;
   @Column( name = "description" )
   String description;
   @Column( name = "you_tube_title" )
   String title;

   public YouTubeInfo()
   {
   }

   public YouTubeInfo( String videoId,
                       String channelTitle,
                       String description,
                       String title )
   {
      this.videoId = videoId;
      this.channelTitle = channelTitle;
      this.description = description;
      this.title = title;
   }

   public void updateValues( String videoId,
                             String channelTitle,
                             String description,
                             String title )
   {
      setVideoId( videoId );
      setChannelTitle( channelTitle );
      setDescription( description );
      setTitle( title );
   }

   public String getVideoId()
   {
      return videoId;
   }
}
