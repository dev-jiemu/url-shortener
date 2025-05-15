package com.urlshortener.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ShortenedUrl {
    private String id;
    private String originalUrl;
    private String shortKey;
    private final LocalDateTime createdAt = LocalDateTime.now();
    // TODO : expiredAt 필요함
    private int visits; // 방문횟수

    public ShortenedUrl(String originalUrl, String shortKey) {
        this.originalUrl = originalUrl;
        this.shortKey = shortKey;
        this.visits = 0;
    }

    public void incrementVisits() {
        this.visits++;
    }
}