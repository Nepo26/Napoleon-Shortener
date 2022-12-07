package com.nepo.napoleon.shortlink.adapter.output.persistence.shortlink;

import com.nepo.napoleon.shortlink.domain.ShortLink;

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
