package com.nepo.napoleon.shortlink.application.port.output;

import com.nepo.napoleon.shortlink.domain.ShortLink;


public interface RetrieveShortLinkPort {
    ShortLink retrieve(String id);
}
