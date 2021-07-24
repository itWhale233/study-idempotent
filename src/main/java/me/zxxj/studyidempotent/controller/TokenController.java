package me.zxxj.studyidempotent.controller;

import me.zxxj.studyidempotent.basic.R;
import me.zxxj.studyidempotent.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Token Controller
 *
 * @author whale
 */
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping("/token")
    public R createToken() {
        return tokenService.createToken();
    }
}
