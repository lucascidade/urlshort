package com.devlucas.urlshort.controller;

import com.devlucas.urlshort.controller.dtos.UrlRequest;
import com.devlucas.urlshort.controller.dtos.UrlResponse;
import com.devlucas.urlshort.repository.UrlRepository;
import com.devlucas.urlshort.services.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class UrlController {

    private final UrlService urlService;
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }
    @PostMapping("/shorten-url")
    public ResponseEntity<UrlResponse> shortUrl(@RequestBody UrlRequest url,
                                                HttpServletRequest response) {
       UrlResponse shortUrl = urlService.generateShortUrl(url, response);
        return ResponseEntity.ok(shortUrl);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> redirectTo(@PathVariable String id) {
       return urlService.redirectToUrl(id);

    }
}
