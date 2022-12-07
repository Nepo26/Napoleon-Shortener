package com.nepo.napoleon.shortlink.adapter.output.persistence.shortlink;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;

import java.util.Objects;

@DynamoDBTable(tableName = "ShortLink")
@NoArgsConstructor
@Getter
@Setter
public class ShortLinkEntity {
    @DynamoDBHashKey(attributeName = "randomId")
    private String randomId;

    @DynamoDBAttribute(attributeName = "link")
    private String link;


    public ShortLinkEntity(final String randomId, final String link){
        Objects.requireNonNull(randomId);
        Objects.requireNonNull(link);
        this.randomId = randomId;
        this.link = link;
    }
}
