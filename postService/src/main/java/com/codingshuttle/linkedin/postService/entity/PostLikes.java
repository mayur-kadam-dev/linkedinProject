package com.codingshuttle.linkedin.postService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
public class PostLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long postId;

    @CreationTimestamp
    private String createdAt;
}
