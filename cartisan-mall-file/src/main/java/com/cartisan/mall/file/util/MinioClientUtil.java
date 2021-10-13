package com.cartisan.mall.file.util;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;

/**
 * @author colin
 */
public class MinioClientUtil {
    public void uploadFile(MinioClient minioClient, String bucketName, String objectName, String filePath) {
        final boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());

    }
}
