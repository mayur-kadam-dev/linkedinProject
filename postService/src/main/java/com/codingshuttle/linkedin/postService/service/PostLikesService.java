package com.codingshuttle.linkedin.postService.service;

import com.codingshuttle.linkedin.postService.entity.PostLikes;
import com.codingshuttle.linkedin.postService.exception.BadRequestException;
import com.codingshuttle.linkedin.postService.exception.ResourceNotFoundException;
import com.codingshuttle.linkedin.postService.repository.PostLikesRepository;
import com.codingshuttle.linkedin.postService.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;

@Service
@RequiredArgsConstructor
public class PostLikesService {

    private final PostLikesRepository postLikesRepository;
    private final PostRepository postRepository;

    @Transactional
    public void likePost(Long postId) {

        Long userId = 1L;
        postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post not found with ID: "+postId));

        boolean hasLiked = postLikesRepository.existsByUserIdAndPostId(userId,postId);

        if(hasLiked){
            throw new BadRequestException("User has already liked this post");
        }
        PostLikes postLikes = new PostLikes();
        postLikes.setPostId(postId);
        postLikes.setUserId(userId);
        postLikesRepository.save(postLikes);
    }


    @Transactional
    public void unlikePost(Long postId) {
        Long userId = 1L;
        postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post not found with ID: "+postId));

        boolean hasLiked = postLikesRepository.existsByUserIdAndPostId(userId,postId);

        if(!hasLiked){
            throw new BadRequestException("User has already unliked this post");
        }

        postLikesRepository.deleteByUserIdAndPostId(userId,postId);
    }
}
