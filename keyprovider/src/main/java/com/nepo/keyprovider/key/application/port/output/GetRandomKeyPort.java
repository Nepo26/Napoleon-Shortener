package com.nepo.keyprovider.key.application.port.output;

import com.nepo.keyprovider.key.domain.Key;

public interface GetRandomKeyPort {
    Key getRandomKey();
}
