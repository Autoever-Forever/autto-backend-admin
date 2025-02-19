package com.example.auttobackendadmin.service;

import com.example.auttobackendadmin.exception.ProductRegisteration.FileUploadException;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.NetworkException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {
    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucketName;

//    @Value("${minio.fileUrl}")  // DNS 이름을 주입
    private String minioFileUrl = "http://auttoforever.com";

    public String uploadFile(MultipartFile file, String directory) {
        validateAndCreateBucket();
        String fileName = createFileName(directory, file);
        uploadToMinio(file, fileName);
        return generateFileUrl(fileName);
    }

    private void validateAndCreateBucket() {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());

            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                log.info("버킷 생성 완료: {}", bucketName);
            }
        } catch (Exception e) {
            log.error("MinIO 버킷 처리 중 오류 발생: ", e);
            throw new FileUploadException();
        }
    }

    private String createFileName(String directory, MultipartFile file) {
        return directory + "/" + UUID.randomUUID() + getFileExtension(Objects.requireNonNull(file.getOriginalFilename()));
    }

    private void uploadToMinio(MultipartFile file, String fileName) {
        try {
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build()
            );
        } catch (Exception e) {
            throw new FileUploadException();
        }

    }

    private String generateFileUrl(String fileName) {
        return minioFileUrl + "/" + bucketName + "/" + fileName;
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}