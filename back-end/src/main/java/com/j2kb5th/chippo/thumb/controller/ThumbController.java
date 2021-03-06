package com.j2kb5th.chippo.thumb.controller;

import com.j2kb5th.chippo.config.auth.LoginUser;
import com.j2kb5th.chippo.config.auth.dto.SessionUser;
import com.j2kb5th.chippo.global.exception.ErrorMessage;
import com.j2kb5th.chippo.global.exception.GlobalException;
import com.j2kb5th.chippo.thumb.controller.dto.response.CheckThumbResponse;
import com.j2kb5th.chippo.thumb.controller.dto.response.ThumbResponse;
import com.j2kb5th.chippo.thumb.domain.Thumb;
import com.j2kb5th.chippo.thumb.service.ThumbService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Tag(name = "따봉(좋아요)", description = "따봉 API")
@RequiredArgsConstructor
@RequestMapping("/api/interviews/{interviewId}")
@RestController
public class ThumbController {

    private final ThumbService thumbService;

    private void validateUserAuthentication(SessionUser user) {
        if (user == null) {
            throw new GlobalException(HttpStatus.UNAUTHORIZED, ErrorMessage.GL003);
        }
    }

    @Operation(summary = "따봉 저장", description = "해당 id의 기술면접에 따봉(좋아요)을 등록합니다.")
    @PostMapping("/thumbs")
    public ResponseEntity<ThumbResponse> saveThumb(
            UriComponentsBuilder uriBuilder,
            @Parameter(description = "기술면접 ID") @PathVariable(name = "interviewId") Long interviewId,
            @LoginUser SessionUser user
    ){
        // validate user
        validateUserAuthentication(user);

        // save thumb && create thumbResponse Object
        Thumb thumb = thumbService.saveThumb(interviewId, user.getUserId());
        ThumbResponse response = new ThumbResponse(thumb);

        // Content-Location
        URI uri = uriBuilder.path("/api/interviews/{interviewId}").build().toUri();

        // 201 created
        return ResponseEntity.created(uri).body(response);
    }

    @Operation(summary = "따봉 취소", description = "해당 id의 기술면접에서, 해당 id의 따봉(좋아요)을 취소합니다.")
    @DeleteMapping("/users/{userId}/thumb")
    public ResponseEntity<Void> deleteThumb(
            @Parameter(description = "기술면접 ID") @PathVariable(name = "interviewId") Long interviewId,
            @Parameter(description = "사용자 ID") @PathVariable(name = "userId") Long userId,
            @LoginUser SessionUser user
    ){
        // validate user
        validateUserAuthentication(user);

        // cancel(delete) thumb
        thumbService.cancelThumb(interviewId, userId, user.getUserId());

        // 204 no content
        return ResponseEntity.noContent().build();
    }

    // 특정 인터뷰에 대해 유저의 따봉 여부 판단
    // interview 조회 시 줄 예정이지만, 혹시 몰라 추가함
    @Operation(summary = "따봉 여부 판단(임시용)",
            description = "현재는 기술면조 단건 조회 시 따봉 여부가 함께 전달됩니다. 혹시 몰라 추가해둔 API입니다.")
    @GetMapping("/users/{userId}/thumbs")
    public ResponseEntity<CheckThumbResponse> checkThumb (
        @Parameter(description = "기술면접 ID") @PathVariable(name = "interviewId") Long interviewId,
        @Parameter(description = "유저 ID") @PathVariable(name = "userId") Long userId,
        @LoginUser SessionUser user
    ){
        // check whether it's thumbs up or not
        boolean clicked = thumbService.checkThumb(interviewId, userId);

        // create checkThumbResponse object
        CheckThumbResponse response = new CheckThumbResponse(clicked);

        // 200 ok
        return ResponseEntity.ok(response);
    }
}
