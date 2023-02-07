package com.joaomeleski.reactivereviews.client;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.joaomeleski.reactivereviews.client.dto.S3UploadResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Data
@Component
public class AmazonS3Client {

    private static final String BUCKET_REGION = "sa-east-1";
    private AmazonS3 amazonS3;
    private String uri;
    private String bucketName;
    private String accessKey;
    private String secretKey;

    public AmazonS3Client(@Value("${amazon.s3.endpoint}") String uri,
                          @Value("${amazon.s3.bucket-name}") String bucketName,
                          @Value("${amazon.s3.access-key}") String accessKey,
                          @Value("${amazon.s3.secret-key}") String secretKey) {
        this.uri = uri;
        this.bucketName = bucketName;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.amazonS3 = buildClient();
    }

    public AmazonS3 buildClient() {
        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(uri, BUCKET_REGION))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();
    }

    public S3UploadResponse uploadFile(String key, InputStream stream) {
        PutObjectResult upload = amazonS3.putObject(bucketName, key, stream, new ObjectMetadata());

        return S3UploadResponse.builder()
                .bucketName(bucketName)
                .region(BUCKET_REGION)
                .key(key)
                .build();
    }
}
