package com.hibernate.mappings.jpa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hibernate.mappings.jpa.EntityB;
import com.hibernate.mappings.jpa.exception.ResourceNotFoundException;
import com.hibernate.mappings.jpa.repository.EntityBRepository;

@RestController
public class EntityBController {

    @Autowired
    EntityBRepository postRepository;

    @GetMapping("/entityBsss")
    public Page<EntityB> getAllEntitiesB(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @PostMapping("/entityB")
    public EntityB createEntityB(@Valid @RequestBody EntityB entityB) {
        return postRepository.save(entityB);
    }

    @PutMapping("/entityB/{entityBId}")
    public EntityB updateEntityB(@PathVariable Integer entityBparam, @Valid @RequestBody EntityB postRequest) {
        return postRepository.findById(entityBparam).map(entityB -> {
            entityB.setStrB(postRequest.getStrB());
            return postRepository.save(entityB);
        }).orElseThrow(() -> new ResourceNotFoundException("EntityB " + entityBparam + " not found"));
    }


    @DeleteMapping("/posts/{entityB}")
    public ResponseEntity<?> deleteEntityB(@PathVariable Integer entityB) {
        return postRepository.findById(entityB).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("EntityB " + entityB + " not found"));
    }

}
