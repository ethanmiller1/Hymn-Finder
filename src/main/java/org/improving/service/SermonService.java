package org.improving.service;

import org.improving.database.JPAUtility;
import org.improving.entity.Sermon;

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
        String query = "select s from Sermon as s where s.youTubeInfo is not null";
        TypedQuery<Sermon> tq = em.createQuery(query, Sermon.class);
        List<Sermon> sermons = null;
        try {
            sermons = tq.getResultList();
            sermons.forEach(sermon -> System.out.println(sermon.toString()));
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }

        return sermons;
    }

}
