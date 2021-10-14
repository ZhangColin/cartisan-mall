package com.cartisan.mall.file.controller;

import com.cartisan.util.SnowflakeIdWorker;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static com.cartisan.response.ResponseUtil.success;
import static com.cartisan.response.ResponseUtil.successWithMessage;

/**
 * @author colin
 */
@RestController
public class FileController {
    private final SnowflakeIdWorker idWorker;

    public FileController(SnowflakeIdWorker idWorker) {
        this.idWorker = idWorker;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> upload(@RequestParam(value = "file") MultipartFile file)
            throws ServerException, InsufficientDataException,
            ErrorResponseException, IOException, NoSuchAlgorithmException,
            InvalidKeyException, InvalidResponseException, XmlParserException,
            InternalException {

        final MinioClient minioClient = MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("zhang_colin", "cartisan")
                .build();

//        final boolean exists = minioClient.bucketExists(
//                BucketExistsArgs.builder().bucket("cartisan-mall").build());
//
//        if (!exists) {
//            minioClient.makeBucket(MakeBucketArgs.builder().bucket("cartisan-mall").build());
//        }

        final String filename = file.getOriginalFilename();

        final String objectName = String.format("%s/%s", idWorker.nextId(), filename);

        minioClient.putObject(PutObjectArgs.builder()
                .bucket("cartisan-mall")
                .contentType(file.getContentType())
                .object(objectName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .build());

        final HashMap<String, String> map = new HashMap<String, String>();
        map.put("filename", filename);
        map.put("url", "http://localhost:9000/cartisan-mall/" + objectName);

        return success(map);

    }


    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam(value = "objectName") String objectName)
            throws ServerException, InsufficientDataException,
            ErrorResponseException, IOException, NoSuchAlgorithmException,
            InvalidKeyException, InvalidResponseException, XmlParserException,
            InternalException {

        final MinioClient minioClient = MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("zhang_colin", "cartisan")
                .build();

        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket("cartisan-mall")
                .object(objectName)
                .build());

        return successWithMessage("删除成功。");

    }


}
