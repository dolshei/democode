package com.example.democode.domain.sample.simple.regex;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLSplit {

    /**
     * Split the URL into components
     * @param url to be split
     */
    public static void splitUsingRegex(String url) {
        System.out.println("Split URL example using regex");
        System.out.println();
        Pattern pattern = Pattern.compile("(https?://)([^:^/]*)(:\\d*)?(.*)?");
        Matcher matcher = pattern.matcher(url);
        matcher.find();

        String protocol = matcher.group(1);
        String domain = matcher.group(2);
        String port = matcher.group(3);
        String uri = matcher.group(4);
        System.out.println(url);
        System.out.println("protocol: " + (protocol != null ? protocol : ""));
        System.out.println("domain: " + (domain != null ? domain : ""));
        System.out.println("port: " + (port != null ? port : ""));
        System.out.println("uri: " + (uri != null ? uri : ""));
        System.out.println();
    }

    /**
     * Split the URL into components
     *
     * @param path to be split
     */
    public static void splitUsingURL(String path) throws Exception {
        System.out.println("Split url example using URL class");
        System.out.println();
        URL url = new URL(path);
        System.out.println("protocol: " + url.getProtocol());
        System.out.println("domain: " + url.getHost());
        System.out.println("port: " + url.getPort());
        System.out.println("uri: " + url.getPath());
    }

    public static void main(String[] args) throws Exception {
        String url1 = "/how-to-format-xmlgregoriancalendar/";
        String url2 = "http://docs.oracle.com/javase/7/docs/api/java/util/Date.html";
        String url3 = "https://example.com:8080/test1/index.html";

        splitUsingRegex(url1);
        splitUsingRegex(url2);
        splitUsingRegex(url3);

        splitUsingURL(url1);
        splitUsingURL(url2);
        splitUsingURL(url3);

    }
}
