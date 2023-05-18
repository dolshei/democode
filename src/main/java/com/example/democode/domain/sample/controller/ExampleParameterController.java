package com.example.democode.domain.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/example/parameter")
public class ExampleParameterController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/example1")
    public String example1(@RequestParam String id,
                                 @RequestParam String code) {

        return null;

    }
}
