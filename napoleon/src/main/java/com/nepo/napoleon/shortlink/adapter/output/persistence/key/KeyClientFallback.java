package com.nepo.napoleon.shortlink.adapter.output.persistence.key;

import com.nepo.napoleon.shortlink.domain.Key;
import org.springframework.http.ResponseEntity;

import static com.nepo.napoleon.shortlink.utils.RandomAlphanumericGenerator.randomAlphanumeric;

public class KeyClientFallback implements KeyClient{
    @Override
    public ResponseEntity<Key> getRandomKey() {
        final Key randomKey = new Key(randomAlphanumeric(5));
        return ResponseEntity.ok().body(randomKey);
    }
}
