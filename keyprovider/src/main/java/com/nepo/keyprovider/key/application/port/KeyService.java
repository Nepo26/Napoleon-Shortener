package com.nepo.keyprovider.key.application.port;

import com.nepo.keyprovider.key.application.port.input.GetRandomKeyUseCase;
import com.nepo.keyprovider.key.application.port.output.GetRandomKeyPort;
import com.nepo.keyprovider.key.domain.Key;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.nepo.keyprovider.key.utils.RandomAlphanumericGenerator.randomAlphanumeric;

@RequiredArgsConstructor
@Service
@Slf4j
public class KeyService implements GetRandomKeyUseCase {
//    private final GetRandomKeyPort getRandomKeyPort;
    @Override
    public Key getRandomKey() {
        return new Key(randomAlphanumeric(5));
    }
}
