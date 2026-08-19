package com.codingshuttle.linkedin.postService.service;

import com.codingshuttle.linkedin.postService.auth.AuthContextHolder;
import com.codingshuttle.linkedin.postService.client.ConnectionsServiceClient;
import com.codingshuttle.linkedin.postService.dto.PersonDto;
import com.codingshuttle.linkedin.postService.dto.PostCreateRequestDto;
import com.codingshuttle.linkedin.postService.dto.PostDto;
import com.codingshuttle.linkedin.postService.entity.Post;
import com.codingshuttle.linkedin.postService.exception.ResourceNotFoundException;
import com.codingshuttle.linkedin.postService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final ConnectionsServiceClient connectionsServiceClient;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId) {
        Post post = modelMapper.map(postCreateRequestDto, Post.class);
        post.setUserId(userId);
        post = postRepository.save(post);
        return modelMapper.map(post,PostDto.class);
    }

    public PostDto getPostById(Long postId) {
        Long userId = AuthContextHolder.getCurrentUserId();

//        TODO: Remove in future
//        Call the Connections Service from the Posts Service and pass the userId inside the headers

        List<PersonDto> personDtoList = connectionsServiceClient.getFirstDegreeConnections(userId);
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post not found with ID: "+postId));
        return modelMapper.map(post,PostDto.class);
    }

    public List<PostDto> getAllPostsOfUser(Long userId) {
        List<Post> posts = postRepository.findAllByUserId(userId);
        return  posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }
}
