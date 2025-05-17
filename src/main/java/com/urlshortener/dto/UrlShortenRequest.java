package com.urlshortener.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UrlShortenRequest {
    private String url;
    private int plusDays; // 만료일자 지정 (days)
}
