package controller;

import model.User;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */
@Controller
@SessionAttributes(types = {User.class },value = {"user"})
@RequestMapping("/hello")
public class TextController {
    private Logger LOGGER = LoggerFactory.getLogger(TextController.class);

    @RequestMapping(value = "/hi")
    @ResponseBody
    public ResponseEntity<byte[]> GetRequestController(@RequestParam("fileId") MultipartFile multipartFiles) throws IOException {
        //设置格式
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.IMAGE_JPEG);
        String filePath = "D://aaa.jpg";
        File file = new File(filePath);
//        for (MultipartFile multipartFile : multipartFiles) {
//
//            File file = new File(filePath+multipartFile.getOriginalFilename());
//            multipartFile.transferTo(file);
//        }
        multipartFiles.transferTo(file);

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),header, HttpStatus.OK);
    }
}
