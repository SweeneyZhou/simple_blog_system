package com.example.demo.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class UploadController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/upload")
    public Map<String, Object> uplaod(HttpServletRequest req, @RequestParam("file") MultipartFile file) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (file.isEmpty()) {
            result.put("success", 1);
            return result;
        }
        String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();

        String path = req.getServletContext().getRealPath("") + "uploaded" + File.separator + fileName;

        logger.info("upload file to:" + path);
        File dest = new File(path);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return result;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return result;
        }
        return result;
    }


}
