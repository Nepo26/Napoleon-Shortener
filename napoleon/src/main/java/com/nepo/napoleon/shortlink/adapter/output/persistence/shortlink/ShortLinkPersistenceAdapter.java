package com.nepo.napoleon.shortlink.adapter.output.persistence.shortlink;

import com.nepo.napoleon.shortlink.application.port.output.CreateShortLinkPort;
import com.nepo.napoleon.shortlink.application.port.output.ListShortLinkPort;
import com.nepo.napoleon.shortlink.application.port.output.RetrieveShortLinkPort;
import com.nepo.napoleon.shortlink.domain.ShortLink;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@RequiredArgsConstructor
@Component
public class ShortLinkPersistenceAdapter implements CreateShortLinkPort, ListShortLinkPort, RetrieveShortLinkPort {
    
    private final ShortLinkRepository shortLinkRepository;

    @Override
    public void create(final ShortLink shortLink) {
        final ShortLinkEntity shortLinkEntity = new ShortLinkEntity(shortLink.getRandomId(), shortLink.getLink());
            shortLinkRepository.save(shortLinkEntity);
    }

    @Override
    public Collection<ShortLink> list() {
        return shortLinkRepository.list();
    }

    @Override
    public ShortLink retrieve(final String id) {
        return shortLinkRepository.retrieve(id);
    }
}
