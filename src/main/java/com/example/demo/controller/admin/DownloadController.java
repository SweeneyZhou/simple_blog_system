package com.example.demo.controller.admin;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/admin")
public class DownloadController {

    @GetMapping(value = "/img/{filename}", produces = MediaType.IMAGE_JPEG_VALUE)
    public BufferedImage getImage(@PathVariable("filename") String fileName, HttpServletRequest request) throws IOException {
        String path = request.getServletContext().getRealPath("") + "uploaded" + File.separator;
        return ImageIO.read(new FileInputStream(new File(path + fileName)));
    }
}
