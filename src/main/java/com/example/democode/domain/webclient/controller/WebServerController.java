package com.example.democode.domain.webclient.controller;

import io.netty.handler.codec.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
public class WebServerController {

    private Logger logger = LoggerFactory.getLogger(WebServerController.class);

    @GetMapping("/webclient/{param}")
    public String testWebClient(@PathVariable String param,
                                @RequestHeader HttpHeaders headers,
                                @CookieValue(name = "httpclient-type", required = false, defaultValue = "undefined") String httpClientType) {
        logger.info(">>>>> Cookie 'httpclient-type={}'", httpClientType);

        headers.forEach((value) -> {

        });

        logger.info("### Received: /webclient/" + param);
        String msg = param + " => Working successfully !!!";
        logger.info("### Sent: " + msg);

        return msg;
        }

    @GetMapping("/webclient2/{param}")
    public String onlyParamWebClient(@PathVariable String param) {

        logger.info("### Received: /webclient/" + param);
        String msg = param + " => Working successfully !!!";
        logger.info("### Sent: " + msg);

        return msg;
    }

}
