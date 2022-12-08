package com.nepo.napoleon.shortlink.adapter.output.persistence.key;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nepo.napoleon.shortlink.domain.Key;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class KeyClient {
    @Value("${keyClientUrl}")
    private String keyClientUrl;

    public ResponseEntity<Key> getRandomKey()  {
        final ObjectMapper mapper= new ObjectMapper();
        final RestTemplate restTemplate = new RestTemplate();

        final String json = restTemplate.getForEntity(keyClientUrl + "/",String.class).getBody();
        try {
            final String keyValue = mapper.readTree(json)
                    .path("keyValue").asText();

            return ResponseEntity.ok().body(new Key(keyValue));

        } catch (Exception e){
            log.error("Error Processing JSON from 'keyprovider' service ", e);
            return ResponseEntity.internalServerError().body(new Key("00000"));
        }

    }
}
