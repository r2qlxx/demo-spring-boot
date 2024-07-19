package com.example.demo.domain.common;

import com.example.demo.config.WebProps;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class HttpClient_ {
    private final RestTemplate rest;
    private final WebProps props;

    // GET request sample.
    public String fetchPost() {
        String url = String.format("%s://%s:%s%s", props.getProtocol(), props.getHost(), props.getPort(), props.getPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<String> res = rest.exchange(url, HttpMethod.GET, entity, String.class);

        if (res.getStatusCode() != HttpStatus.OK) {
            // snip
        }

        return res.getBody();
    }

    // Post request sample.
    public String createPost() {
        String url = String.format("%s://%s:%s%s", props.getProtocol(), props.getHost(), props.getPort(), "/posts");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String reqBody = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";
        HttpEntity<String> entity = new HttpEntity<>(reqBody, headers);
        ResponseEntity<String> res = rest.exchange(url, HttpMethod.POST, entity, String.class);

        if (res.getStatusCode() != HttpStatus.CREATED) {
            // snip
        }

        return res.getBody();
    }

}
