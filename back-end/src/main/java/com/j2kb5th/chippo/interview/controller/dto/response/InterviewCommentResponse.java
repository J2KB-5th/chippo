package com.j2kb5th.chippo.interview.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j2kb5th.chippo.comment.domain.Comment;
import com.j2kb5th.chippo.global.controller.dto.UserResponse;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class InterviewCommentResponse {

    private final Long id;
    private final UserResponse user;
    private final String content;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime updatedAt;

    public InterviewCommentResponse(Comment comment) {
        // 예외처리
        this.id = comment.getId();
        this.user = new UserResponse(comment.getUser());
        this.content = comment.getContent();
        this.updatedAt = comment.getUpdatedAt();
    }
}
