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

    public String uploadFile(File file) {
        PutObjectRequest request = new PutObjectRequest(bucketName, "blog-images/" + file.getName(), file);
        PutObjectResult res = client.putObject(request);

        if(res == null) {
            return "";
        }
        System.out.println("Uploaded this file: " + file.getName());
        return "/blog-images/" + file.getName();
    }

}
