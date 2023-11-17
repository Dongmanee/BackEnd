package com.dongmanee.global.security.login.controller;

import com.dongmanee.global.security.login.dto.request.SignInRequest;
import com.dongmanee.global.security.login.service.SignInService;
import com.dongmanee.global.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignInController {

    private final SignInService signInService;

    @PostMapping(value = "/login")
    public ApiResponse<?> userLogin(@Valid @RequestBody SignInRequest request) {
        return ApiResponse.success(signInService.login(request.getLoginId(), request.getPassword()), "로그인 성공");
    }

}
