package com.king.mystory.service.impl;

import com.king.mystory.mapper.AddressMapper;
import com.king.mystory.mapper.DistrictMapper;
import com.king.mystory.pojo.District;
import com.king.mystory.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IDistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    // 注意这一层的数据是为了服务 addressService 中的数据


    @Override
    public List<District> getByParen(String parent) {
        List<District> list = districtMapper.getByParent(parent);

        /**
         * 在进行网络数据传输时 为了尽量避免无效数据的传递 可以将无效数据设置null
         * 可以节省流量 另一方面提升了效率
         */
        for (District lists:list) {
            lists.setId(null);
            lists.setParent(null);
        }
        return list;
    }

    /**
     * 根据 code 查询城市信息
     * @param code 查询城市信息
     * @return
     */
    @Override
    public String findByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
