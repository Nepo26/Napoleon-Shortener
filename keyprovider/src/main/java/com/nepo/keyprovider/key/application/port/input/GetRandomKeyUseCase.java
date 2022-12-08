package com.nepo.keyprovider.key.application.port.input;

import com.nepo.keyprovider.key.domain.Key;

public interface GetRandomKeyUseCase {
    Key getRandomKey();
}
