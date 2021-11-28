package com.j2kb5th.chippo.interview.controller;

import com.j2kb5th.chippo.global.controller.dto.UserResponse;
import com.j2kb5th.chippo.interview.controller.dto.request.SaveInterviewRequest;
import com.j2kb5th.chippo.interview.controller.dto.request.SaveInterviewTagDetailRequest;
import com.j2kb5th.chippo.interview.controller.dto.request.UpdateInterviewRequest;
import com.j2kb5th.chippo.interview.controller.dto.request.UpdateInterviewTagDetailRequest;
import com.j2kb5th.chippo.interview.controller.dto.response.*;
import com.j2kb5th.chippo.interview.service.InterviewService;
import com.j2kb5th.chippo.tag.domain.InterviewTag;
import com.j2kb5th.chippo.tag.domain.TagType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/api/interviews")
@RestController
public class InterviewController {
    private final InterviewService interviewService;

    @GetMapping("/{interviewId}")
    public ResponseEntity<InterviewDetailResponse> findInterview(
            @PathVariable(name = "interviewId") Long interviewId
    ){
        List<InterviewCommentResponse> testComments = new ArrayList<>();
        testComments.add(new InterviewCommentResponse(1L, null, new UserResponse(123L, "카카오꿈나무"),
                "헉 정말 좋은 답변이네요!", LocalDateTime.now()));
        testComments.add(new InterviewCommentResponse(2L, null, new UserResponse(123L, "배민에서 탈주한 사람"),
                "여기저기거기에서 본 면접 질문과 비슷하네요.", LocalDateTime.now()));

        List<InterviewTagDetailResponse> testTags = new ArrayList<>();
        testTags.add(new InterviewTagDetailResponse(1L, TagType.COMPANY, "카카오"));
        testTags.add(new InterviewTagDetailResponse(2L, TagType.TECHSTACK, "프론트엔드"));

        return ResponseEntity.ok(new InterviewDetailResponse(
                interviewId,
                new UserResponse(101L, "면접본사람"),
                "카카오 1024번 공채 면접 질문 1번: 프로그래밍을 왜 시작했나요?",
                "어려서부터 컴퓨터 게임을 좋아했고 어쩌구저쩌구 중학교 때 C언어의 매력에 어쩌구",
                "면접 분위기가 굉장히 유한 편이었고 면접관분들 모두 친절하셨어요",
                true,
                testTags,
                testComments,
                3L,
                LocalDateTime.now()
        ));
    }


    @GetMapping
    public ResponseEntity<InterviewsResponse> findInterviewsByTag(
            @RequestParam(name = "tag_name") String tagName,
            @RequestParam(name = "tag_type") String tagType,
            @RequestParam(name = "size") Long size
    ){
        List<InterviewTagDetailResponse> testTags = new ArrayList<>();
        for (Long i=1L; i<size; i++){
            testTags.add(new InterviewTagDetailResponse(i, TagType.COMPANY, tagName));
        }

        InterviewResponse testInterview = new InterviewResponse(
                12L,
                new UserResponse(101L, "태그맨날검색하는사람"),
                "JVM은 무엇이고 왜 사용하나요?",
                testTags,
                3L,
                LocalDateTime.now()
        );

        InterviewResponse testInterview2 = new InterviewResponse(
                13L,
                new UserResponse(101L, "테스트짓기어렵다"),
                "Java의 가장 큰 특징은 무엇이라 생각하나요?",
                testTags,
                3L,
                LocalDateTime.now()
        );

        InterviewResponse testInterview3 = new InterviewResponse(
                14L,
                new UserResponse(102L, "이제뭘써야하지"),
                "ORM이란 무엇인가요?",
                testTags,
                3L,
                LocalDateTime.now()
        );

        List<InterviewResponse> testInterviews = new ArrayList<>(Arrays.asList(testInterview, testInterview2, testInterview3));
        return ResponseEntity.ok(new InterviewsResponse(testInterviews));
    }


    @PostMapping
    public ResponseEntity<InterviewDetailResponse> saveInterview(
            UriComponentsBuilder uriBuilder,
            @RequestBody SaveInterviewRequest interviewRequest
    ){
        List<InterviewTagDetailResponse> testTags = new ArrayList<>();
        List<SaveInterviewTagDetailRequest> tags = interviewRequest.getInterviewTags();
        for (int i=0; i<interviewRequest.getInterviewTags().size(); i++){
            testTags.add(new InterviewTagDetailResponse(1L, tags.get(i).getType(), tags.get(i).getName()));
        }

        Long testInterviewId = 100L;
        InterviewDetailResponse testResponse = new InterviewDetailResponse(
                testInterviewId,
                new UserResponse(101L, "면접본사람"),
                interviewRequest.getQuestion(),
                interviewRequest.getAnswer(),
                interviewRequest.getExtraInfo(),
                interviewRequest.isVisible(),
                testTags,
                null,
                0L,
                LocalDateTime.now()
        );

        URI uri = uriBuilder.path("/api/interviews/{interviewId}").buildAndExpand(testInterviewId).toUri();
        return ResponseEntity.created(uri).body(testResponse);
    }


    @PutMapping
    public ResponseEntity<InterviewDetailResponse> updateInterview(
            @PathVariable(name = "interviewId") Long interviewId,
            @RequestBody UpdateInterviewRequest interviewRequest
    ){
        List<InterviewCommentResponse> testComments = new ArrayList<>();
        testComments.add(new InterviewCommentResponse(1L, null, new UserResponse(123L, "카카오꿈나무"),
                "헉 정말 좋은 답변이네요!", LocalDateTime.now()));
        testComments.add(new InterviewCommentResponse(2L, null, new UserResponse(123L, "배민에서 탈주한 사람"),
                "여기저기거기에서 본 면접 질문과 비슷하네요.", LocalDateTime.now()));

        List<InterviewTagDetailResponse> testTags = new ArrayList<>();
        List<UpdateInterviewTagDetailRequest> tags = interviewRequest.getInterviewTags();
        for (int i=0; i<interviewRequest.getInterviewTags().size(); i++){
            testTags.add(new InterviewTagDetailResponse(1L, tags.get(i).getType(), tags.get(i).getName()));
        }

        Long testInterviewId = 100L;

        InterviewDetailResponse testResponse = new InterviewDetailResponse(
                testInterviewId,
                new UserResponse(101L, "면접본사람"),
                interviewRequest.getQuestion(),
                interviewRequest.getAnswer(),
                interviewRequest.getExtraInfo(),
                interviewRequest.isVisible(),
                testTags,
                testComments,
                3L,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(testResponse);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteInterview(
            @PathVariable(name = "interviewId") Long interviewId
    ){
        return ResponseEntity.noContent().build();
    }
}