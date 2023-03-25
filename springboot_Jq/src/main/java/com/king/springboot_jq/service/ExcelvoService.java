package com.king.springboot_jq.service;


import com.king.springboot_jq.pojo.ExcelVo;

import java.io.InputStream;
import java.util.List;

public interface ExcelvoService {
    // 在接口中定义一个方法

    /**
     * 定义一个方法 返回list集合
     * 在集合中定义数据
     * 在入参中定义一个流 用来解析数据
     * @param inputStream
     * @return
     */
    List<ExcelVo> list(InputStream inputStream);
}
