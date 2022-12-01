package br.com.nepo.napoleonshortener.shortlink.adapter.output.persistence;

import br.com.nepo.napoleonshortener.shortlink.domain.ShortLink;

public class ShortLinkMapper {
    private ShortLinkMapper(){
        throw new IllegalStateException("Mapper Class");
    }

    public static ShortLink mapToDomain(final ShortLinkEntity shortLinkEntity){
        return new ShortLink(
                shortLinkEntity.getRandomId(),
                shortLinkEntity.getLink()
        );
    }
}
