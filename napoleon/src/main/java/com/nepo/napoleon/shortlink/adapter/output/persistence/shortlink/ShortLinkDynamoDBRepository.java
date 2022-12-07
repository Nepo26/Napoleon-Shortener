package com.nepo.napoleon.shortlink.adapter.output.persistence.shortlink;

import com.nepo.napoleon.shortlink.domain.ShortLink;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Repository
public class ShortLinkDynamoDBRepository implements ShortLinkRepository {

    private final DynamoDBMapper mapper;

    @Override
    public void save(final ShortLinkEntity shortLinkEntity) {
        Objects.requireNonNull(shortLinkEntity, "Entity must not be null!");

        mapper.save(shortLinkEntity);

        // Get the Entity to verify if it was indeed saved
        final ShortLinkEntity savedShortLinkEntity = mapper.load(ShortLinkEntity.class, shortLinkEntity.getRandomId());

        Objects.requireNonNull(savedShortLinkEntity, "ShortLink '" + shortLinkEntity.getLink() + "' not saved on DynamoDB");
    }

    @Override
    public Collection<ShortLink> list() {
        final PaginatedScanList<ShortLinkEntity> shortLinkEntities = mapper.scan(ShortLinkEntity.class, new DynamoDBScanExpression());
        if (shortLinkEntities.isEmpty()) {
            return List.of();
        }

        return shortLinkEntities.stream()
                .map(ShortLinkMapper::mapToDomain)
                .toList();
    }

    @Override
    public ShortLink retrieve(final String id) {
        final ShortLinkEntity shortLinkEntity = mapper.load(ShortLinkEntity.class, id);

        Objects.requireNonNull(shortLinkEntity);

        return ShortLinkMapper.mapToDomain(shortLinkEntity);
    }
}
