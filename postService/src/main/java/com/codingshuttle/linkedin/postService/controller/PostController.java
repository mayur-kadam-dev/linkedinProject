package com.codingshuttle.linkedin.postService.controller;

import com.codingshuttle.linkedin.postService.dto.PostCreateRequestDto;
import com.codingshuttle.linkedin.postService.dto.PostDto;
import com.codingshuttle.linkedin.postService.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/core")
public class PostController {

    private final PostService postService;


    @PostMapping
    public ResponseEntity<PostDto> createdPost(@RequestBody PostCreateRequestDto postCreateRequestDto,
                                               HttpServletRequest httpServletRequest) {
        PostDto postDto = postService.createPost(postCreateRequestDto,1L);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId) {
        PostDto postDto = postService.getPostById(postId);
        return ResponseEntity.ok(postDto);
    }

    @GetMapping("/users/{userId}/allPosts")
    public ResponseEntity<List<PostDto>> getAllPostofUsers(@PathVariable Long userId){
        List<PostDto> listofPosts = postService.getAllPostsOfUser(1L);
        return ResponseEntity.ok(listofPosts);
    }
}
