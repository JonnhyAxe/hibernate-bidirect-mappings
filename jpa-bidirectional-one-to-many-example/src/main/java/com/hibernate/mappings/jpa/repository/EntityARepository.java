package com.hibernate.mappings.jpa.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibernate.mappings.jpa.EntityA;
import com.hibernate.mappings.jpa.EntityB;

@Repository
public interface EntityARepository extends JpaRepository<EntityA, Integer> {
	
//    Page<EntityB> findBymyIdA(Integer entityBId, Pageable pageable);
}
