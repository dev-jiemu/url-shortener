package com.urlshortener.repository;

import com.urlshortener.model.ShortenedUrl;

import java.util.Optional;

public interface UrlRepository {
    ShortenedUrl save(ShortenedUrl shortenedUrl);
    Optional<ShortenedUrl> findByShortKey(String shortKey);
    boolean existsByShortKey(String shortKey);
}
