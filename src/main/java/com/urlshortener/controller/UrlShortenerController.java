package com.urlshortener.controller;

import com.urlshortener.dto.UrlShortenRequest;
import com.urlshortener.dto.UrlShortenResponse;
import com.urlshortener.model.ShortenedUrl;
import com.urlshortener.service.UrlShortenerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
public class UrlShortenerController {
    private final UrlShortenerService urlShortenerService;
    private final String BASE_URL = "http://localhost:8080"; // TODO : update domain url

    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/api/shorten")
    public ResponseEntity<UrlShortenResponse> shortenUrl(@RequestBody UrlShortenRequest request) {
        if (request.getUrl() == null || request.getUrl().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        ShortenedUrl shortenedUrl = urlShortenerService.shortenUrl(request.getUrl(), request.getPlusDays());
        String shortUrl = BASE_URL + "/" + shortenedUrl.getShortKey();

        UrlShortenResponse response = new UrlShortenResponse(shortenedUrl.getOriginalUrl(), shortUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{shortKey}")
    public RedirectView redirectToOriginalUrl(@PathVariable String shortKey) {
        Optional<ShortenedUrl> shortenedUrl = urlShortenerService.getOriginalUrl(shortKey);

        RedirectView redirectView = new RedirectView();
        if (shortenedUrl.isPresent()) {
            redirectView.setUrl(shortenedUrl.get().getOriginalUrl());
        } else {
            redirectView.setUrl("/not-found");
        }

        return redirectView;
    }

    @GetMapping("/not-found")
    public ResponseEntity<String> notFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("URL is not found");
    }
}
