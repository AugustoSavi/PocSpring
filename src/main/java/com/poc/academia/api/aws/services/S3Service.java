package com.poc.academia.api.aws.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class S3Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(S3Service.class);

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Autowired
    private AmazonS3 amazonS3Client;

    public void createS3Bucket(String bucketName) {
        if (amazonS3Client.doesBucketExistV2(bucketName)) {
            LOGGER.info("Bucket name already exists Try another name.");
            return;
        }
        amazonS3Client.createBucket(bucketName);
    }

    public List<Bucket> listBuckets() {
        return amazonS3Client.listBuckets();
    }

    public void deleteBucket(String bucketName) {
        try {
            amazonS3Client.deleteBucket(bucketName);
        } catch (AmazonServiceException e) {
            LOGGER.error(e.getErrorMessage());
        }
    }

    public String putObject(MultipartFile multipartFile) {
        String keyName = UUID.randomUUID() + "." + Objects.requireNonNull(multipartFile.getOriginalFilename()).split("\\.")[1];
        File file = new File(keyName);

        try {
            Path copyLocation = Paths.get(file.getAbsolutePath());
            Files.copy(multipartFile.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

            createS3Bucket(bucketName);

            amazonS3Client.putObject(bucketName, keyName, new File(keyName));
            amazonS3Client.setObjectAcl(bucketName, keyName, CannedAccessControlList.PublicRead);

            LOGGER.info("upload finalizado");

            Files.delete(file.toPath());

            return amazonS3Client.getUrl(bucketName, keyName).toURI().toString();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<S3ObjectSummary> listObjects(String bucketName) {
        ObjectListing objectListing = amazonS3Client.listObjects(bucketName);
        return objectListing.getObjectSummaries();
    }

    public void deleteObject(String bucketName, String objectName) {
        amazonS3Client.deleteObject(bucketName, objectName);
    }

    public void moveObject(String bucketSourceName, String objectName, String bucketTargetName) {
        amazonS3Client.copyObject(
                bucketSourceName,
                objectName,
                bucketTargetName,
                objectName
        );
    }
}
