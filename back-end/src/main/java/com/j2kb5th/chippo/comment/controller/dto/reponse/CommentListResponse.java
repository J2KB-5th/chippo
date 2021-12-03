package com.j2kb5th.chippo.comment.controller.dto.reponse;

import lombok.Getter;

import java.util.List;

@Getter
public class CommentListResponse {
    private final List<CommentResponse> comments;

    public CommentListResponse(List<CommentResponse> comments) {
        this.comments = comments;
    }
}
