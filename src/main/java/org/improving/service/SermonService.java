package org.improving.service;

import org.improving.entity.Sermon;
import org.improving.entity.YouTubeInfo;

import java.util.List;

public interface SermonService
{

   // CREATE
   void addSermon( Sermon sermon );

   void save( Sermon theSermon );

   // READ
   List< Sermon > findAll();

   Sermon findById( long theId );

   Sermon findByTitle( String title );

   // UPDATE
   void updateYouTubeInfoById( YouTubeInfo youTubeInfo,
                               long id );

   // DELETE
   void deleteById( long theId );

}
