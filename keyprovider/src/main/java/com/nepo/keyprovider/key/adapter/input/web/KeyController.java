package com.nepo.keyprovider.key.adapter.input.web;


import com.nepo.keyprovider.key.application.port.KeyService;
import com.nepo.keyprovider.key.domain.Key;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
@Slf4j
public class KeyController {

    private final KeyService keyService;

    /**
     * Get random key. Be it from a cache of pre generated ones or from a function that creates it.
     * @return Key
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Key> getKey(){
        final Key randomKey = keyService.getRandomKey();
        return ResponseEntity.ok().body(randomKey);
    }
}
