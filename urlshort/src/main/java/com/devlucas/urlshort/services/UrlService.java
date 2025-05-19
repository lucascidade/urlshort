package com.devlucas.urlshort.services;
import com.devlucas.urlshort.controller.dtos.UrlRequest;
import com.devlucas.urlshort.controller.dtos.UrlResponse;
import com.devlucas.urlshort.entity.Url;
import com.devlucas.urlshort.repository.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.time.LocalDateTime;
@Service
public class UrlService {
    private UrlRepository urlRepository;
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }
    public UrlResponse generateShortUrl(UrlRequest url, HttpServletRequest request) {
        String id;
        do {
            id = RandomStringUtils.randomAlphanumeric(5,10);
        } while (urlRepository.existsById(id));
        urlRepository.save(new Url(id, url.url(), LocalDateTime.now().plusMinutes(1)));
        var redirectUrl = request.getRequestURL().toString().replace("shorten-url", id);
        return new UrlResponse(redirectUrl);
    }

    public ResponseEntity<Object> redirectToUrl(String id) {
        return urlRepository.findById(id)
                .map(url -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setLocation(URI.create(url.getUrlFull()));
                    return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

