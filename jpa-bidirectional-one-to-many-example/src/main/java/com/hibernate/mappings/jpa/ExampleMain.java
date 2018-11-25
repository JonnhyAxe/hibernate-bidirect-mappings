package com.hibernate.mappings.jpa;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ExampleMain {

        public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test1");
        try {
            EntityManager em = emf.createEntityManager();
            nativeQuery(em, "SHOW TABLES");
            nativeQuery(em, "SHOW COLUMNS from EntityA");
            nativeQuery(em, "SHOW COLUMNS from EntityB");
            emf.close();
        } finally {
            emf.close();
        }
    }

    public static void nativeQuery(EntityManager em, String s) {
        System.out.printf("-----------------------------%n'%s'%n",  s);
        Query query = em.createNativeQuery(s);
        List list = query.getResultList();
        for (Object o : list) {
            if(o instanceof Object[]) {
                System.out.println(Arrays.toString((Object[]) o));
            }else{
                System.out.println(o);
            }
        }
    }
}