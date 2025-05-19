package com.devlucas.urlshort.repository;

import com.devlucas.urlshort.entity.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<Url, String> {
}
