package com.j2kb5th.chippo.interview.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j2kb5th.chippo.global.controller.dto.UserResponse;
import com.j2kb5th.chippo.interview.domain.Interview;
import com.j2kb5th.chippo.preanswer.domain.PreAnswer;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class InterviewDetailResponse {

    private final Long id;
    private final UserResponse user;
    private final String question;
    private final String answer;
    private final String extraInfo;

    private final InterviewPreAnswerResponse preAnswer; // 작성한 사전 답안이 있다면 함께 리턴
    private final InterviewThumbResponse thumb;
    private final List<InterviewTagDetailResponse> interviewTags;
    private final List<InterviewCommentResponse> comments;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime updatedAt;

    public InterviewDetailResponse(Interview interview, PreAnswer preAnswer, boolean thumbClicked) {
        this.id = interview.getId();
        this.user = new UserResponse(interview.getUser());
        this.question = interview.getQuestion();
        this.answer = interview.getAnswer();
        this.extraInfo = interview.getExtraInfo();
        this.preAnswer = (preAnswer == null)? null: new InterviewPreAnswerResponse(preAnswer);
        this.thumb = new InterviewThumbResponse(thumbClicked, interview.getThumbs().stream().count());
        this.interviewTags = interview.getInterviewTags().stream()
                .map((interviewTag) -> new InterviewTagDetailResponse(interviewTag.getTag()))
                .collect(Collectors.toList());
        this.comments = interview.getComments().stream()
                .map(InterviewCommentResponse::new).collect(Collectors.toList());
        this.updatedAt = interview.getUpdatedAt();
    }
}
