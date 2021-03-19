package org.improving.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "sermon" )
@Data
@DynamicUpdate
public class Sermon
{

   @Id
   @GeneratedValue( strategy = GenerationType.IDENTITY )
   @Column( name = "id", unique = true )
   private long   id;
   @Column( name = "date" )
   private String date;
   @Column( name = "title" )
   private String title;
   @Column( name = "mp3" )
   private String mp3;
   @Column( name = "preacher" )
   private String preacher;
   @Column( name = "archive_resource" )
   private String archiveResource;

   @Embedded
   private YouTubeInfo youTubeInfo;

   public Sermon()
   {
   }

   public Sermon( String date,
                  String title,
                  String mp3,
                  String preacher )
   {
      this.date = date;
      this.title = title;
      this.mp3 = mp3;
      this.preacher = preacher;
   }

   public String toString()
   {
      return String.format( "%s by %s (%s)",
                            title,
                            preacher,
                            date );
   }
}
