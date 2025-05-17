package com.urlshortener.service;

import com.urlshortener.model.ShortenedUrl;
import com.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@Service
public class UrlShortenerService {
    private static final int KEY_LENGTH = 6;
    private final UrlRepository urlRepository;
    private final SecureRandom random = new SecureRandom();

    public UrlShortenerService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    /**
     * shortenUrl : url을 짧은 키값으로 변환하고 저장
     *
     * @param originalUrl
     * @param plusDays    : 만료일자 지정 (days)
     * @return ShortenedUrl : shortened url info model
     */
    public ShortenedUrl shortenUrl(String originalUrl, int plusDays) {
        String shortKey = generateUniqueShortKey();
        ShortenedUrl shortenedUrl = new ShortenedUrl(originalUrl, shortKey, plusDays);
        return urlRepository.save(shortenedUrl);
    }

    /**
     * getOriginalUrl : url을 조회하고, 만료일자가 지났으면 새로운 url을 생성하여 반환
     *
     * @param shortKey
     * @return Optional<ShortenedUrl> : original url info model
     */
    public Optional<ShortenedUrl> getOriginalUrl(String shortKey) {
        return urlRepository.findByShortKey(shortKey)
                .filter(url -> !url.isExpired())
                .map(url -> {
                    url.incrementVisits();
                    return urlRepository.save(url);
                });
    }

    /**
     * generateUniqueShortKey : 유니크한 키값 생성
     *
     * @return String : unique key
     */
    private String generateUniqueShortKey() {
        String shortKey;
        do {
            shortKey = generateRandomKey();
        } while (urlRepository.existsByShortKey(shortKey));
        return shortKey;
    }

    /**
     * generateRandomKey : 랜덤 키값 생성
     *
     * @return String : random key
     */
    private String generateRandomKey() {
        byte[] randomBytes = new byte[KEY_LENGTH];
        random.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes).substring(0, KEY_LENGTH);
    }
}
