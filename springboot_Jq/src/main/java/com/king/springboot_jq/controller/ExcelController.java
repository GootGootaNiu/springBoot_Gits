package com.king.springboot_jq.controller;

import com.king.springboot_jq.pojo.ExcelVo;
import com.king.springboot_jq.service.ExcelvoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/mains")
public class ExcelController {

    @Autowired
    private ExcelvoService excelvoService;

    /**
     * 注意用户传过来的是一个文件 这里就是接收用户的文件
     *
     * @param file
     * @return
     */
    @RequestMapping("/exe")
    public String importData(@RequestParam("file") MultipartFile file) {
        try {
            List<ExcelVo> lists = excelvoService.list(file.getInputStream());
            for (ExcelVo exe : lists
            ) {
                System.out.println(exe);
            }
        } catch (IOException e) {

            return "fail";
//            e.printStackTrace();

        }
        // 遍历数据
        return "success";
    }

}
