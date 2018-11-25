package com.hibernate.mappings.jpa;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ExampleMain2 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test1");
        try {
            persistEntity(emf);
            nativeQueries(emf);
            loadEntityA(emf);
            loadEntityB(emf);
        } finally {
            emf.close();
        }
    }

    private static void nativeQueries(EntityManagerFactory emf) {
        System.out.println("-- native queries --");
        EntityManager em = emf.createEntityManager();
        ExampleMain.nativeQuery(em, "Select * from EntityA");
        ExampleMain.nativeQuery(em, "Select * from EntityB");
    }

    private static void persistEntity(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        EntityB entityB1 = new EntityB();
        entityB1.setStrB("testStringB");

        EntityB entityB2 = new EntityB();
        entityB2.setStrB("testStringB2");

        EntityA entityA = new EntityA();
        entityA.setStrA("testStringA");
        entityA.setEntityBList(Arrays.asList(entityB1, entityB2));

        entityB1.setRefEntityA(entityA);
        entityB2.setRefEntityA(entityA);

        System.out.println("-- persisting entities --");
        System.out.printf(" %s%n entityA#entityBList: %s%n", entityA, entityA.getEntityBList());
        System.out.printf(" %s%n entityB1#refEntityA: %s%n", entityB1, entityB1.getRefEntityA());
        System.out.printf(" %s%n entityB2#refEntityA: %s%n", entityB2, entityB2.getRefEntityA());

        em.getTransaction().begin();
        em.persist(entityA);
        em.persist(entityB1);
        em.persist(entityB2);
        em.getTransaction().commit();

        em.close();
    }

    private static void loadEntityA(EntityManagerFactory emf) {
        System.out.println("-- loading EntityA --");
        EntityManager em = emf.createEntityManager();
        List<EntityA> entityAList = em.createQuery("Select t from EntityA t").getResultList();
        entityAList.forEach(e -> System.out.printf(" %s%n entityA#entityBList: %s%n", e, e.getEntityBList()));
        em.close();
    }

    private static void loadEntityB(EntityManagerFactory emf) {
        System.out.println("-- Loading EntityB --");
        EntityManager em = emf.createEntityManager();
        List<EntityB> entityBList = em.createQuery("Select t from EntityB t").getResultList();
        entityBList.forEach(e -> System.out.printf(" %s%n entityB#refEntityA: %s%n", e, e.getRefEntityA()));
        em.close();
    }
}