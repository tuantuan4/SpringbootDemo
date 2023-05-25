package com.rest.api.repository;

import com.rest.api.entity.Comment;
import com.rest.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * FROM comments WHERE post_id = :idPost", nativeQuery = true)
    List<Comment> getCommentsPost(@Param("idPost") long idPost);
}
