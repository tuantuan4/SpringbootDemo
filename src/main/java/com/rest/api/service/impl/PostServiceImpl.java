package com.rest.api.service.impl;

import com.rest.api.utils.request.dto.PostDTO;
import com.rest.api.entity.Comment;
import com.rest.api.entity.Post;
import com.rest.api.repository.PostRepository;
import com.rest.api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Comment save(PostDTO dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setDescription(dto.getDescription());
        return null;
    }

    @Override
    public Comment update(PostDTO dto, Long id) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return null;
    }


}
