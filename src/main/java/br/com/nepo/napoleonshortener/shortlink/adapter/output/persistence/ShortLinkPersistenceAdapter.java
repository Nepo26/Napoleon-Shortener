package br.com.nepo.napoleonshortener.shortlink.adapter.output.persistence;

import br.com.nepo.napoleonshortener.shortlink.application.port.output.CreateShortLinkPort;
import br.com.nepo.napoleonshortener.shortlink.application.port.output.ListShortLinkPort;
import br.com.nepo.napoleonshortener.shortlink.domain.ShortLink;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@RequiredArgsConstructor
@Component
public class ShortLinkPersistenceAdapter implements CreateShortLinkPort, ListShortLinkPort {
    
    private final ShortLinkRepository shortLinkRepository;

    @Override
    public void create(ShortLink shortLink) {
        final ShortLinkEntity shortLinkEntity = new ShortLinkEntity(shortLink.getRandomId(), shortLink.getLink());
            shortLinkRepository.save(shortLinkEntity);
    }

    @Override
    public Collection<ShortLink> list() {
        return shortLinkRepository.list();
    }
}
