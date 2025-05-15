package com.urlshortener.repository;

import com.urlshortener.model.ShortenedUrl;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryUrlRepository implements UrlShortnerRepository{

    @Override
    public ShortenedUrl save(ShortenedUrl shortenedUrl) {
        return null;
    }

    @Override
    public Optional<ShortenedUrl> findByShortKey(String shortKey) {
        return Optional.empty();
    }

    @Override
    public boolean existsByShortKey(String shortKey) {
        return false;
    }

}
