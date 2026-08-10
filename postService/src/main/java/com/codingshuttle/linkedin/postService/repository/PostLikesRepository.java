package com.codingshuttle.linkedin.postService.repository;

import com.codingshuttle.linkedin.postService.entity.PostLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikesRepository extends JpaRepository<PostLikes, Long> {
    boolean existsByUserIdAndPostId(Long userId, Long postId);

    void deleteByUserIdAndPostId(Long userId, Long postId);
}
