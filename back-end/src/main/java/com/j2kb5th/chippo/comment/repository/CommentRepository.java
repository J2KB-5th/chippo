package com.j2kb5th.chippo.comment.repository;

import com.j2kb5th.chippo.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByInterviewId(Long interviewId);
}
