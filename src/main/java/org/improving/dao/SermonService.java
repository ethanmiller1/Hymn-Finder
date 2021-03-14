package org.improving.dao;

import com.google.api.services.youtube.model.SearchResult;
import org.improving.client.SermonFinder;
import org.improving.entity.Sermon;
import org.improving.entity.YouTubeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class SermonService
{

   private static EntityManager entityManager;

   @Autowired
   public SermonService( EntityManager entityManager )
   {
      SermonService.entityManager = entityManager;
   }

   // CREATE

   public static void addSermon( Sermon sermon )
   {
      EntityTransaction et = null;
      try
      {
         et = entityManager.getTransaction();
         et.begin();
         entityManager.persist( sermon );
         et.commit();
      }
      catch( Exception ex )
      {
         if( et != null )
         {
            et.rollback();
         }
         ex.printStackTrace();
      }
   }

   public static void addSermon( Sermon sermon,
                                 List< Sermon > dbSermons )
   {
      if( dbSermons.stream()
                   .noneMatch( s ->
                   {
                      boolean isMatch = s.getTitle()
                                         .equals( sermon.getTitle() ) &&
                                        s.getPreacher()
                                         .equals( sermon.getPreacher() );
                      if( isMatch && s.getYouTubeInfo() == null )
                         addYouTubeInfo( s );
                      return isMatch;
                   } ) )
         addSermon( sermon );

   }

   // READ

   public static Sermon getSermon( String title )
   {
      // :Title is a paramaterized query
      String query = "select s from Sermon s where s.title = :Title";

      TypedQuery< Sermon > tq = entityManager.createQuery( query,
                                                           Sermon.class );
      tq.setParameter( "Title",
                       title );
      Sermon sermon = null;
      try
      {
         sermon = tq.getSingleResult();
         System.out.println( sermon.toString() );
      }
      catch( NonUniqueResultException ex )
      {
         sermon = tq.getResultList()
                    .get( 0 );
         System.out.println( sermon.toString() );
      }
      catch( NoResultException ex )
      {
         ex.printStackTrace();
      }
      return sermon;
   }

   public static List< Sermon > getSermons()
   {
      String query = "select s from Sermon as s where s.id is not null";
      TypedQuery< Sermon > tq = entityManager.createQuery( query,
                                                           Sermon.class );
      List< Sermon > sermons = null;
      try
      {
         sermons = tq.getResultList();
      }
      catch( NoResultException ex )
      {
         ex.printStackTrace();
      }

      return sermons;
   }

   // UPDATE

   public static Sermon addYouTubeInfo( Sermon dbSermon )
   {
      EntityTransaction et = null;
      Sermon sermon = null;
      try
      {
         et = entityManager.getTransaction();
         et.begin();
         sermon = entityManager.find( Sermon.class,
                                      dbSermon.getId() );
         SermonFinder.addYouTubeInfo( sermon );
         entityManager.persist( sermon );
         et.commit();
      }
      catch( Exception ex )
      {
         if( et != null )
         {
            et.rollback();
         }
         ex.printStackTrace();
      }
      return sermon;
   }

   public static Sermon updateYouTubeInfo( int id,
                                           String query )
   {
      EntityTransaction et = null;
      Sermon sermon = null;
      YouTubeInfo youTubeInfo = null;
      try
      {
         et = entityManager.getTransaction();
         et.begin();
         sermon = entityManager.find( Sermon.class,
                                      id );
         if( sermon.getYouTubeInfo() == null )
         {
            youTubeInfo = new YouTubeInfo();
            sermon.setYouTubeInfo( youTubeInfo );
         }
         else
            youTubeInfo = sermon.getYouTubeInfo();
         SearchResult response = SermonFinder.searchYouTube( String.format( "\"%s\" %s",
                                                                            sermon.getTitle(),
                                                                            query ) );

         youTubeInfo.updateValues( response.getId()
                                           .getVideoId(),
                                   response.getSnippet()
                                           .getChannelTitle(),
                                   response.getSnippet()
                                           .getDescription(),
                                   response.getSnippet()
                                           .getTitle() );
         entityManager.persist( youTubeInfo );
         entityManager.persist( sermon );
         et.commit();
      }
      catch( Exception ex )
      {
         if( et != null )
         {
            et.rollback();
         }
         ex.printStackTrace();
      }
      return sermon;
   }

}
