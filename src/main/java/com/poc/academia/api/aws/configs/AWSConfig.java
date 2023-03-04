package com.poc.academia.api.aws.configs;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

    @Value("${aws.access_key_id}")
    private String accesskey;

    @Value("${aws.secret_access_key}")
    private String secretkey;

    public AWSCredentials credentials() {
        return new BasicAWSCredentials(
                accesskey,
                secretkey
        );
    }

    @Bean
    public AmazonS3 amazonS3() {
        String url = "http://localhost:9444/s3";

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials()))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(url,"auto"))
                .build();


//        return AmazonS3ClientBuilder
//                .standard()
//                .withCredentials(new AWSStaticCredentialsProvider(credentials()))
//                .withRegion(Regions.US_EAST_1)
//                .build();

    }
}