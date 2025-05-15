package com.urlshortener.service;

import com.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UrlShortenerService {
    private static final int KEY_LENGTH = 6;
    private final UrlRepository urlRepository;
    private final SecureRandom random = new SecureRandom();

    public UrlShortenerService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }


    /**
     * generateRandomKey : 랜덤 키값 생성
     * @return String : random key
     */
    private String generateRandomKey() {
        byte[] randomBytes = new byte[KEY_LENGTH];
        random.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes).substring(0, KEY_LENGTH);
    }
}
