package org.improving.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table( name = "sermon" )
@Data
public class Sermon
{

   @Id
   @GeneratedValue( strategy = GenerationType.IDENTITY )
   @Column( name = "Id", unique = true )
   private int    id;
   @Column( name = "Date" )
   private String date;
   @Column( name = "Title" )
   private String title;
   @Column( name = "Mp3" )
   private String mp3;
   @Column( name = "Preacher" )
   private String preacher;

   @OneToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
   @JoinColumn( name = "YouTubeInfoID" )
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
