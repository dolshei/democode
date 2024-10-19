package com.example.democode.domain.sample.controller;

import com.example.democode.global.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example/parameter")
public class ExampleParameterController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/check")
    public String check() {
        return "check";
    }

    @GetMapping("/paramcheck")
    public String example1(@RequestParam String id,
                                 @RequestParam String code) {
        boolean result = ObjectUtils.isAnyNullOrEmpty(id, code);
        String message;

        if (result) {
            message = "success";
        } else {
            message = "fail";
        }

        return message;

    }
}
