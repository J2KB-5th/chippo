package com.j2kb5th.chippo.interview.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j2kb5th.chippo.comment.domain.Comment;
import com.j2kb5th.chippo.global.controller.dto.UserResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Getter
@RequiredArgsConstructor // dummy data용 임시 어노테이션 (구현 후 제거)
public class InterviewCommentResponse {

    private final Long id;
    private final Long parentId;
    private final UserResponse user;
    private final String content;

    @JsonFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime updatedAt;

    public InterviewCommentResponse(Comment comment) {
        this.id = comment.getId();
        this.user = new UserResponse(comment.getUser());
        this.parentId = comment.getParent().getId(); // 수정 필요
        this.content = comment.getContent();
        this.updatedAt = comment.getUpdatedAt();
    }
}