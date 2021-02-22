package org.improving.dao;

import com.google.api.services.youtube.model.SearchResult;
import org.improving.SermonFinder;
import org.improving.utility.JPAUtility;
import org.improving.entity.Sermon;
import org.improving.entity.YouTubeInfo;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class SermonService {

    // CREATE

    public static void addSermon(Sermon sermon) {
        EntityManager em = JPAUtility.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(sermon);
            et.commit();
        } catch (Exception ex) {
            if(et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        }
    }

    public static void addSermon(Sermon sermon, List<Sermon> dbSermons) {
        if(dbSermons.stream().noneMatch(s -> {
            boolean isMatch = s.getTitle().equals(sermon.getTitle()) && s.getPreacher().equals(sermon.getPreacher());
            if (isMatch && s.getYouTubeInfo() == null)
                addYouTubeInfo(s);
            return isMatch;
        }))
            addSermon(sermon);

    }

    // READ

    public static Sermon getSermon(String title) {
        EntityManager em = JPAUtility.getEntityManager();
        // :Title is a paramaterized query
        String query = "select s from Sermon s where s.title = :Title";

        TypedQuery<Sermon> tq = em.createQuery(query, Sermon.class);
        tq.setParameter("Title", title);
        Sermon sermon = null;
        try {
            sermon = tq.getSingleResult();
            System.out.println(sermon.toString());
        } catch (NonUniqueResultException ex) {
            sermon = tq.getResultList().get(0);
            System.out.println(sermon.toString());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        return sermon;
    }

    public static List<Sermon> getSermons() {
        EntityManager em = JPAUtility.getEntityManager();
        String query = "select s from Sermon as s where s.id is not null";
        TypedQuery<Sermon> tq = em.createQuery(query, Sermon.class);
        List<Sermon> sermons = null;
        try {
            sermons = tq.getResultList();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }

        return sermons;
    }

    // UPDATE

    public static Sermon addYouTubeInfo(Sermon dbSermon) {
        EntityManager em = JPAUtility.getEntityManager();
        EntityTransaction et = null;
        Sermon sermon = null;
        try {
            et = em.getTransaction();
            et.begin();
            sermon = em.find(Sermon.class, dbSermon.getId());
            SermonFinder.addYouTubeInfo(sermon);
            em.persist(sermon);
            et.commit();
        } catch (Exception ex) {
            if(et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        }
        return sermon;
    }

    public static Sermon updateYouTubeInfo(int id, String query) {
        EntityManager em = JPAUtility.getEntityManager();
        EntityTransaction et = null;
        Sermon sermon = null;
        YouTubeInfo youTubeInfo = null;
        try {
            et = em.getTransaction();
            et.begin();
            sermon = em.find(Sermon.class, id);
            if ( sermon.getYouTubeInfo() == null )
            {
                youTubeInfo = new YouTubeInfo();
                sermon.setYouTubeInfo(youTubeInfo);
            }
            else
                youTubeInfo = em.find(YouTubeInfo.class, sermon.getYouTubeInfo().getId());
            SearchResult response = SermonFinder.searchYouTube(String.format("\"%s\" %s", sermon.getTitle(), query));

            youTubeInfo.updateValues(
                    "https://www.youtube.com/watch?v=" + response.getId().getVideoId(),
                    response.getId().getVideoId(),
                    response.getSnippet().getChannelTitle(),
                    response.getSnippet().getDescription(),
                    response.getSnippet().getTitle()
            );
            em.persist(youTubeInfo);
            em.persist(sermon);
            et.commit();
        } catch (Exception ex) {
            if(et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        }
        return sermon;
    }

}
