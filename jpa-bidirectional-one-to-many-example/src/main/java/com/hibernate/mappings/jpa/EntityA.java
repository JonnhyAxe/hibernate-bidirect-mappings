package com.hibernate.mappings.jpa;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class EntityA {
    @Id
    @GeneratedValue
    private int myIdA;
    
    private String strA;
    @OneToMany(mappedBy = "refEntityA")
    private List<EntityB> entityBList;

    public String getStrA() {
        return strA;
    }

    public void setStrA(String strA) {
        this.strA = strA;
    }

    public List<EntityB> getEntityBList() {
        return entityBList;
    }

    public void setEntityBList(List<EntityB> entityBList) {
        this.entityBList = entityBList;
    }

    @Override
    public String toString() {
        return "EntityA{" +
                "myIdA=" + myIdA +
                ", strA='" + strA + '\'' +
                '}';
    }
}