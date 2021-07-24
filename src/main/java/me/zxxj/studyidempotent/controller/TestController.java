package me.zxxj.studyidempotent.controller;

import me.zxxj.studyidempotent.annotations.ApiIdem;
import me.zxxj.studyidempotent.annotations.ApiIdempotent;
import me.zxxj.studyidempotent.basic.R;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * controller for test api idempotent
 *
 * @author whale
 */
@RestController
public class TestController {

    @ApiIdempotent
    @RequestMapping("/test/{name}")
    public R test(@PathVariable(name = "name") String name) {
        return R.success(name);
    }

    @ApiIdem
    @RequestMapping("/test2/{name}")
    public R test2(@PathVariable(name = "name") String name) {
        return R.success(name);
    }
}
