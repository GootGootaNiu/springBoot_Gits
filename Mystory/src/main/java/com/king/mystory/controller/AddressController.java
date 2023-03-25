package com.king.mystory.controller;

import com.king.mystory.mapper.AddressMapper;
import com.king.mystory.pojo.Address;
import com.king.mystory.pojo.BaseEntity;
import com.king.mystory.service.AddressService;
import com.king.mystory.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * 添加用户信息的
 *  用户地址信息
 */
@RequestMapping("/addresses")
@RestController
public class AddressController extends BaseController {

    @Autowired
    private AddressService addressService;


    @RequestMapping("/add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session){
        // 第一步:把数据传入到session中去
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);

        // 这里调用这里调用数据

        addressService.addNewAddress(uid,username,address);
        return new JsonResult<>(OK);
    }

    /**
     * 根据用户udi查询用户信息
     * @param session 从session 中获取uid
     * @return
     */
    @RequestMapping({"/",""})
    public JsonResult<List<Address>> getByUid(HttpSession session){
        Integer uid = getuidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return new JsonResult<>(OK,data);
    }
}
