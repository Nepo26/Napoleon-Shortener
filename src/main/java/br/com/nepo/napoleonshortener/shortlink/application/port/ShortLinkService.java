package br.com.nepo.napoleonshortener.shortlink.application.port;

import br.com.nepo.napoleonshortener.shortlink.application.port.input.CreateShortLinkCommand;
import br.com.nepo.napoleonshortener.shortlink.application.port.input.CreateShortLinkUseCase;
import br.com.nepo.napoleonshortener.shortlink.application.port.input.ListShortLinkUseCase;
import br.com.nepo.napoleonshortener.shortlink.application.port.input.RetrieveShortLinkUseCase;
import br.com.nepo.napoleonshortener.shortlink.application.port.output.CreateShortLinkPort;
import br.com.nepo.napoleonshortener.shortlink.application.port.output.ListShortLinkPort;
import br.com.nepo.napoleonshortener.shortlink.application.port.output.RetrieveShortLinkPort;
import br.com.nepo.napoleonshortener.shortlink.domain.ShortLink;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static br.com.nepo.napoleonshortener.shortlink.utils.RandomAlphanumericGenerator.randomAlphanumeric;


@RequiredArgsConstructor
@Component
@Slf4j
//@Transactional
public class ShortLinkService implements CreateShortLinkUseCase, ListShortLinkUseCase, RetrieveShortLinkUseCase {

    private final CreateShortLinkPort createShortLinkPort;
    private final ListShortLinkPort listShortLinkPort;
    private final RetrieveShortLinkPort retrieveShortLinkPort;

    @Override
    public ShortLink createShortLink(final CreateShortLinkCommand createShortLinkCommand) {
        final String randomId = randomAlphanumeric(8);
        final ShortLink shortLink = new ShortLink(randomId, createShortLinkCommand.getLink());

        createShortLinkPort.create(shortLink);


        return shortLink;
    }

    @Override
    public Collection<ShortLink> listShortLinks(final CreateShortLinkCommand createShortLinkCommand) {
        return listShortLinkPort.list();
    }

    @Override
    public ShortLink retrieveShortLinkById(final String id) {
        return retrieveShortLinkPort.retrieve(id);
    }
}
