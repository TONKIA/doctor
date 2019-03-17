package com.tonkia.rainbow.service.impl;

import com.tonkia.rainbow.service.FileService;
import com.tonkia.rainbow.utils.FtpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Value("${ftpclient.host}")
    private String host;
    @Value("${ftpclient.port}")
    private int port;
    @Value("${ftpclient.username}")
    private String username;
    @Value("${ftpclient.password}")
    private String password;
    @Value("${ftpclient.basePath}")
    private String basePath;

    @Value("${ftpclient.filePath.imgs}")
    private String filePathImg;

    @Value("${imgRequestUrl}")
    private String imgRequestUrl;

    @Override
    public String uploadImg(String fileName, InputStream inputStream) {
        fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf('.'));
        boolean res = FtpUtil.uploadFile(host, port, username, password, basePath, filePathImg, fileName, inputStream);
        if (res)
            return imgRequestUrl + fileName;
        else
            return null;
    }

    @Override
    public boolean getImg(OutputStream outputStream, String fileName) {
        return FtpUtil.downloadFile(host, port, username, password, basePath + filePathImg, fileName, outputStream);
    }

}
