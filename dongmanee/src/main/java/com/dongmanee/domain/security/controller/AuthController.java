package com.dongmanee.domain.security.controller;

import com.dongmanee.domain.security.dto.request.SignInRequest;
import com.dongmanee.domain.security.service.AuthService;
import com.dongmanee.global.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public ApiResponse<?> userLogin(@Valid @RequestBody SignInRequest request) {
        return ApiResponse.success(authService.login(request.getLoginId(), request.getPassword()), "로그인 성공");
    }

}
