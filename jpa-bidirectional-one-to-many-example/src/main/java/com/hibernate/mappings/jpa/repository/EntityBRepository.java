package com.hibernate.mappings.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibernate.mappings.jpa.EntityB;

@Repository
public interface EntityBRepository extends JpaRepository<EntityB, Integer> {

}
