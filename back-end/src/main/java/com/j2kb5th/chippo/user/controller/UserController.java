package com.j2kb5th.chippo.user.controller;

import com.j2kb5th.chippo.config.auth.LoginUser;
import com.j2kb5th.chippo.config.auth.dto.SessionUser;
import com.j2kb5th.chippo.user.controller.dto.request.UpdateUserRequest;
import com.j2kb5th.chippo.user.controller.dto.response.UserDetailResponse;
import com.j2kb5th.chippo.user.controller.dto.response.UserResponse;
import com.j2kb5th.chippo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/user-info")
    public ResponseEntity<UserDetailResponse> getUserDetail(@LoginUser SessionUser user) {

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(userService.getUserDetail(user), HttpStatus.OK);
    }

    @PatchMapping("/users/{userId}")
    public ResponseEntity<UserResponse> updateUserInfo(@PathVariable Long userId, @LoginUser SessionUser user, @RequestBody UpdateUserRequest request) {

        if (user == null || user.getUserId() != userId) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(userService.update(user, request), HttpStatus.OK);
    }
}
