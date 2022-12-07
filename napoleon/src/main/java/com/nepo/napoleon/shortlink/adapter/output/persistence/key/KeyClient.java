package com.nepo.napoleon.shortlink.adapter.output.persistence.key;

import com.nepo.napoleon.config.FeignClientConfig;
import com.nepo.napoleon.shortlink.domain.Key;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "${feign.url}", name = "${feign.name}", configuration = FeignClientConfig.class, fallback = KeyClientFallback.class)
public interface KeyClient {

    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Key> getRandomKey();
}
