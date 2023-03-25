package com.king.mystory.controller;

import com.king.mystory.pojo.District;
import com.king.mystory.service.DistrictService;
import com.king.mystory.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/districts")
@RestController
public class DistrictController extends BaseController {

    @Autowired
    private DistrictService districtService;

    // 注意@RequestMapping是一个数组类型
    @RequestMapping({"/",""})
    public JsonResult<List<District>> getByParent(String parent) {
        List<District> data = districtService.getByParen(parent);
        return new JsonResult<>(OK, data);
    }

}
