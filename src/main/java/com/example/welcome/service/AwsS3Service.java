package com.example.welcome.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class AwsS3Service {

    private final String bucketName = "blog-point-assets";
    @Autowired
    private AmazonS3 client;

    public boolean uploadFile() {
        File file2 = new File("C://Users/sparsh/Pictures/0a3b6099-c254-4bc3-8360-53a9f558a0c4___Com.G_TgS_FL 8259.JPG");
        PutObjectRequest request = new PutObjectRequest(bucketName, file2.getName(),file2);
        PutObjectResult res = client.putObject(request);

        if(res == null) {
            return false;
        }

        return true;
    }

}
