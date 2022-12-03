package br.com.nepo.napoleonshortener.dataprovider.config.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import java.util.Objects;

@RequiredArgsConstructor
@Configuration
public class DynamoDBConfiguration {

    private final Environment environment;

    private AmazonDynamoDB buildAmazonDynamoDB() {
        final String region = Objects.requireNonNull(this.environment.getProperty("thirdparty.aws.region"));
        final AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder
                .standard();

        if (this.environment.acceptsProfiles(Profiles.of("local", "test", "localstack"))) {
            final String endpoint = Objects.requireNonNull(this.environment.getProperty("thirdparty.aws.endpoint"));

            builder.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region));

            final String accessKey = Objects.requireNonNull(this.environment.getProperty("thirdparty.aws.accesskeyId"));
            final String secretKey = Objects.requireNonNull(this.environment.getProperty("thirdparty.aws.secretkey"));

            builder.withCredentials(
                    new AWSStaticCredentialsProvider(
                            new BasicAWSCredentials(
                                    accessKey,
                                    secretKey
                            )
                    )
            );
        } else {
            builder.setRegion(region);
        }

        return builder.build();
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(buildAmazonDynamoDB());
    }
}
