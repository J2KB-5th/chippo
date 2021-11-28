package com.j2kb5th.chippo.interview.controller.dto.response;

import com.j2kb5th.chippo.tag.domain.InterviewTag;
import com.j2kb5th.chippo.tag.domain.TagType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor // 임시 어노테이션
public class InterviewTagDetailResponse {
    private final Long id;
    private final TagType type;
    private final String name;

    public InterviewTagDetailResponse(InterviewTag interviewTag){
        // 예외처리 필요
        this.id = interviewTag.getTag().getId();
        this.type = interviewTag.getTag().getType();
        this.name = interviewTag.getTag().getName();
    }
}