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
    private LocalDateTime expiredAt = LocalDateTime.now();
    private int visits; // 방문횟수

    public ShortenedUrl(String originalUrl, String shortKey, int plusDays) {
        this.originalUrl = originalUrl;
        this.shortKey = shortKey;
        this.visits = 0;
        this.expiredAt = LocalDateTime.now().plusDays(plusDays);
    }

    public void incrementVisits() {
        this.visits++;
    }

    /**
     * isExpired : 만료일자가 지났는지 확인
     * @return boolean
     */
    public boolean isExpired() {
        LocalDateTime now = LocalDateTime.now();

        return this.expiredAt.isBefore(now);
    }
}