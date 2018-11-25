package com.hibernate.mappings.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hibernate.mappings.jpa.EntityA;
import com.hibernate.mappings.jpa.exception.ResourceNotFoundException;
import com.hibernate.mappings.jpa.repository.EntityARepository;

@RestController
public class EntityAController {

    @Autowired
    private EntityARepository entityARepository;

    @GetMapping("/asss/{entityAId}/bss")
    public EntityA getAllBssByEntityAId(@PathVariable (value = "entityA") Integer entityAId,
                                                Pageable pageable) {
        return entityARepository.findById(entityAId).
        		orElseThrow(() -> new ResourceNotFoundException("EntityA " + entityAId + " not found"));
    }

    @GetMapping("/asss")
    public List<EntityA> getAllAss() {
        return entityARepository.findAll();
    }
    
//    @PostMapping("/posts/{postId}/comments")
//    public Comment createComment(@PathVariable (value = "postId") Long postId,
//                                 @Valid @RequestBody Comment comment) {
//        return postRepository.findById(postId).map(post -> {
//            comment.setPost(post);
//            return entityARepository.save(comment);
//        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
//    }

//    @PutMapping("/posts/{postId}/comments/{commentId}")
//    public Comment updateComment(@PathVariable (value = "postId") Long postId,
//                                 @PathVariable (value = "commentId") Long commentId,
//                                 @Valid @RequestBody Comment commentRequest) {
//        if(!postRepository.existsById(postId)) {
//            throw new ResourceNotFoundException("PostId " + postId + " not found");
//        }
//
//        return entityARepository.findById(commentId).map(comment -> {
//            comment.setText(commentRequest.getText());
//            return entityARepository.save(comment);
//        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
//    }

//    @DeleteMapping("/posts/{postId}/comments/{commentId}")
//    public ResponseEntity<?> deleteComment(@PathVariable (value = "postId") Long postId,
//                              @PathVariable (value = "commentId") Long commentId) {
//        if(!postRepository.existsById(postId)) {
//            throw new ResourceNotFoundException("PostId " + postId + " not found");
//        }
//
//        return entityARepository.findById(commentId).map(comment -> {
//             entityARepository.delete(comment);
//             return ResponseEntity.ok().build();
//        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + " not found"));
//    }
}
