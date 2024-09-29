package com.example.democode.domain.sample.controller;

import com.example.democode.domain.sample.service.LogTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LogTestController {
    @Autowired
    private LogTestService logTestService;

    @GetMapping("/logTest")
    public void test() {
        logTestService.test();
        log.trace("TRACE!!");
        log.debug("DEBUG!!");
        log.info("INFO!!");
        log.warn("WARN!!");
        log.error("ERROR!!");

        log.info(">>> LogTestController.test : {}", System.currentTimeMillis());

    }
}
