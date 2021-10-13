package com.cartisan.mall.file.controller;

import com.cartisan.mall.file.FastDFSFile;
import com.cartisan.mall.file.util.FastDFSClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author colin
 */
@RestController
public class FileController {
    @PostMapping("/upload")
    public String upload(@RequestParam(value = "file") MultipartFile file) {
        try {
            final FastDFSFile fastDFSFile = new FastDFSFile(file.getOriginalFilename(), file.getBytes(),
                    StringUtils.getFilenameExtension(file.getOriginalFilename()));
            final String[] upload = FastDFSClient.upload(fastDFSFile);

            return FastDFSClient.getTrackerUrl()+"/"+upload[0]+"/"+upload[1];
        } catch (IOException e) {
        }

        return null;
    }
}
