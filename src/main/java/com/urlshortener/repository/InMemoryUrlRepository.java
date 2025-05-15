package com.urlshortener.repository;

import com.urlshortener.model.ShortenedUrl;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUrlRepository implements UrlRepository {

    // 데모니까 일단 인메모리 기반
    private final Map<String, ShortenedUrl> storage = new ConcurrentHashMap<>();

    @Override
    public ShortenedUrl save(ShortenedUrl shortenedUrl) {
        storage.put(shortenedUrl.getShortKey(), shortenedUrl);
        return shortenedUrl;
    }

    @Override
    public Optional<ShortenedUrl> findByShortKey(String shortKey) {
        return Optional.ofNullable(storage.get(shortKey));
    }

    @Override
    public boolean existsByShortKey(String shortKey) {
        return storage.containsKey(shortKey);
    }
}
