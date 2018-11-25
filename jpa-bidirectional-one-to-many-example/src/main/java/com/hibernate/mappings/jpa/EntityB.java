package com.hibernate.mappings.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EntityB {
    @Id
    @GeneratedValue
    private int myIdB;
    private String strB;
    @ManyToOne
    @JsonIgnore
    private EntityA refEntityA;

    public String getStrB() {
        return strB;
    }

    public void setStrB(String strB) {
        this.strB = strB;
    }

    public EntityA getRefEntityA() {
        return refEntityA;
    }

    public void setRefEntityA(EntityA refEntityA) {
        this.refEntityA = refEntityA;
    }

    @Override
    public String toString() {
        return "EntityB{" +
                "myIdB=" + myIdB +
                ", strB='" + strB + '\'' +
                ", refEntityA='" + refEntityA + '\'' +
                '}';
    }
}