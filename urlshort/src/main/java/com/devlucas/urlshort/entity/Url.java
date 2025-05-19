package com.devlucas.urlshort.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "urls")
public class Url {

    @Id
    private String id;

    private String urlFull;

    @Indexed(expireAfter = "0")
    private LocalDateTime expiredAt;

    public Url(String id, String urlFull, LocalDateTime expiredAt) {
        this.id = id;
        this.urlFull = urlFull;
        this.expiredAt = expiredAt;
    }
    public Url() {}

    public String getId() {
        return id;
    }

    public String getUrlFull() {
        return urlFull;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUrlFull(String urlFull) {
        this.urlFull = urlFull;
    }

    public void setExpiredAt(LocalDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }
}
