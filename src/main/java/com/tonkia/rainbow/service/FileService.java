package com.tonkia.rainbow.service;

import java.io.InputStream;
import java.io.OutputStream;

public interface FileService {
    public boolean getImg(OutputStream outputStream, String fileName);

    public String uploadImg(String fileName, InputStream inputStream);
}
