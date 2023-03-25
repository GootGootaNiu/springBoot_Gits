package com.king.mystory.service;

import com.king.mystory.pojo.District;

import java.util.List;

/**
 * 省市联动
 */
public interface DistrictService {
    /**
     * 根据父代号来查询区域信息（省市区）
     * @param parent 父代码
     * @return 多个区域信息
     */
   List<District> getByParen(String parent);

    /**
     * 根据 code 查询信息
     * @param code
     * @return
     */
    String findByCode(String code);
}
