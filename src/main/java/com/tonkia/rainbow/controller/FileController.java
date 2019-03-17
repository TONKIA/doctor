package com.tonkia.rainbow.controller;

import com.tonkia.rainbow.pojo.ResponseMessage;
import com.tonkia.rainbow.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("file")
public class FileController {

    @Autowired
    FileService fileService;

    /**
     * 上传图片文件
     * 返回获取文件的url地址
     *
     * @param file
     * @return
     */

    @RequestMapping("uploadImg")
    @ResponseBody
    public ResponseMessage uploadImg(MultipartFile file) {
        String url = null;
        try {
            url = fileService.uploadImg(file.getOriginalFilename(), file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (url != null) {
            Map data = new HashMap();
            data.put("url", url);
            return new ResponseMessage("上传成功！", data, ResponseMessage.SUCCESS);
        } else
            return new ResponseMessage("上传失败！", null, ResponseMessage.FAILURE);
    }

    /**
     * 根据文件名获取图片文件
     *
     * @param fileName
     * @param response
     */
    @RequestMapping("getImg/{fileName:.+}")
    public void getImgFile(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        try {
            // response.setContentType("image/png");
            response.setContentType("image/jpeg");
            OutputStream outputStream = response.getOutputStream();
            boolean res = fileService.getImg(outputStream, fileName);
//            outputStream.flush();
//            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
