package com.example.auttobackendadmin.service;

import com.example.auttobackendadmin.exception.ProductRegisteration.FileUploadException;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucketName;

    @Value("${minio.endpoint}")
    private String minioEndpoint;

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
            }
        } catch (Exception e) {
            throw new FileUploadException();
        }
    }

    private String createFileName(String directory, MultipartFile file) {
        return directory + "/" + UUID.randomUUID() + getFileExtension(file.getOriginalFilename());
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
        return minioEndpoint + "/" + bucketName + "/" + fileName;
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}