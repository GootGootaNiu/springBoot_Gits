package com.king.springboot_jq.controller;

import com.king.springboot_jq.pojo.User;
import com.king.springboot_jq.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.swing.plaf.ListUI;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


//    @RequestMapping("/user/login")
//    public String getlogin(Integer age, String username, Model model) {
//        User user = userService.getSelsectUname(age, username);
//        if (user != null) {
//            model.addAttribute("msg", "登录成功");
//            return "redirect:/users/selectAll";
//        } else {
//            model.addAttribute("datas", "请输入数据");
//            return "index";
//        }
//    }

//    @ResponseBody
//    @RequestMapping("/users/selectAll")
//    public String getAll(User user, Model model) {
//        List<User> list = userService.getAll();
//        model.addAttribute("list", list);
//        return "main";
//    }


    @RequestMapping("/users/selectAll")
//    @CorssOrigin
    public List<User> getAll(User user, Model model) {
        List<User> list = userService.getAll();
        model.addAttribute("list", list);
        return list;
    }


    @RequestMapping("/users/delete")
    public Integer delete(Integer id) {
        int count = userService.deleteById(id);
        if(count!=1){
            System.out.println("删除失败");
        }else {
            System.out.println("删除用户成功");
        }
        return count;
    }


//    @RequestMapping("/user/insert")
//    public String insert() {
//        return "insert";
//    }
//
//    @RequestMapping("/user/insertAll")
//    public String isnertAll(User user) {
//        int i = userService.insertAll(user);
//        if (i != 1) {
//            System.out.println("添加数据时失败");
//        }
//        return "redirect:/users/selectAll";
//    }
//
//    @RequestMapping("/user/delete")
//    public String delete(Integer id) {
//        int i = userService.deleteById(id);
//        System.out.println("删除用户成功");
//        return "redirect:/users/selectAll";
//    }
//
//    @RequestMapping("/user/update")
//    public String update(Integer id , Model model){
//        User user = userService.selectById(id);
//        model.addAttribute("user",user);
//        return "update";
//    }
//
//    @RequestMapping("/user/updateById")
//    public String updates(User user){
//        int i = userService.updateById(user);
//        System.out.println(i+"数据修改成功");
//        return "redirect:/users/selectAll";
//    }

}
