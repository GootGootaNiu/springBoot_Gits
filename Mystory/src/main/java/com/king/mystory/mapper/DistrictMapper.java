package com.king.mystory.mapper;

import com.king.mystory.pojo.Address;
import com.king.mystory.pojo.District;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DistrictMapper {

    /**
     * 根据父代号查询区域信息
     * @param parent 父代号
     * @return 某个区域下的所有列表
     */
    List<District> getByParent(String parent);

    /**
     * 根据 code 查询信息
     * @param code
     * @return
     */
    String findNameByCode(String code);
}
