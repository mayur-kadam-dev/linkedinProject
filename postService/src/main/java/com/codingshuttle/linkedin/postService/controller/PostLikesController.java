package com.codingshuttle.linkedin.postService.controller;

import com.codingshuttle.linkedin.postService.service.PostLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class PostLikesController {

    private final PostLikesService postLikesService;

    @PostMapping("/{postId}")
    public ResponseEntity<Void> likePost(@PathVariable Long postId) {
        postLikesService.likePost(postId);
        return ResponseEntity.noContent().build();

    }

    @PostMapping("/unlike/{postId}")
    public ResponseEntity<Void> unlikePost(@PathVariable Long postId) {
        postLikesService.unlikePost(postId);
        return ResponseEntity.noContent().build();
    }
}
