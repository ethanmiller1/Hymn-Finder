package org.improving.service;

import org.improving.database.JPAUtility;
import org.improving.entity.Sermon;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class SermonService {

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

}
