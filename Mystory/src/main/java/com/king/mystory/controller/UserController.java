package com.king.mystory.controller;

import com.king.mystory.controller.ex.*;
import com.king.mystory.pojo.User;
import com.king.mystory.service.IUserService;
import com.king.mystory.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired // 自动装配
    private IUserService iUserService;

    // 定义一个常量 用来接收用户的文件
    /*设置文件上传的最大值*/
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;

    /**
     * 限制文件上传的类型
     */
    public static final List<String> AVATAR_TYPE =
            new ArrayList<>();

    // 定义一个静态的代码块
    static {
        // 设置上传文件的类型
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    /**
     * MultipartFile 接口是springmvc提供的一个接口 这个接口为我们包装了
     * 获取文件类型的数据(用来接收任何类型的文件 file)
     * 然后springboot 又整合了springmvc
     * 只需要在处理请求的方法参数列表上声明一个参数类型为MultipartFil
     * 的参数 然后springboot 会自传递文件中的参数
     *
     * @param session
     * @param file
     * @return
     * @RequestParam 表示将请求中的参数 将请求中的参数注入请求处理方法的某个
     * 前后端传入的数据不一致
     */
    @RequestMapping("/change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session,
                                           @RequestParam("file") MultipartFile file) {
        // 第一步：文件上传 定义一个常量 用来接收用户上传文件的大小
        // 第二步: 判断用户上传的文件 是不是空
        if (file.isEmpty()) {
            // 如果为空就报异常
            throw new FileEmptyException("文件为空");
        }
        // 第三步: 判断文件是否超出文件限制
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException("文件超出限制");
        }
        // 第四步：判断文件的类型是否是我们规定的后缀类型
        String contentType = file.getContentType();
        // 如果集合包含某个元素则返回值为true
        if (!AVATAR_TYPE.contains(contentType)) {
            throw new FileTypeException("文件类型不支持");
        }
        // 上传文件 ../upload/文件.png
        //拿到session 中的数据
        String parent = session.getServletContext()
                .getRealPath("upload");

        // File 对象指向这个路径 File 是否存在
        File dir = new File(parent);
        if (!dir.exists()) {// 检测目录是否存在
            dir.mkdirs();// 创建当前的目录
        }

        // 目录创建完成之后 把文件上传的名称随机一下
        // 获取到这个文件的文件名称,UUID工具类将生成新的字符串文件名
        // 列如avatar01.png
        String originalFilename = file.getOriginalFilename();
        System.out.println("originalFilename" + originalFilename);
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String filename = UUID.randomUUID().toString().toUpperCase()
                + suffix;

        // 文件名随机值生成之后 把这个文件通过流的方式搞到内存中去
        // 定位文件
        File dest = new File(dir, filename); //是一个空文件
        // 参数file 中的数据 写入到空文件中
        try {
            // transferTo();将file 文件中的数据写入到dest 文件中去
            file.transferTo(dest);
        } catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        } catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        }

        // 获取session中的uid  和 username
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        //  返回头像的路径/upload/test.png
        String avatar = "/upload/" + filename;
        iUserService.changeAvatar(uid, avatar, username);
        // 返回用户头像的路径给前端页面， 将来用于头像展示使用
        return new JsonResult<>(OK, avatar);

    }


    /**
     * 根据用户的uid 查询用户的信息 返回用户的数据
     *
     * @param session
     * @return
     */
    @RequestMapping("/by_uid")
    public JsonResult<User> selectAll(HttpSession session) {
        User data = iUserService.getById(getuidFromSession(session));
        // 注意：这里返回用户的数据 和状态码
        return new JsonResult<>(OK, data);
    }

    /**
     * 用户修改数据
     *
     * @param user    返回的对象信息
     * @param session session 中的数据
     * @return 返回的值
     */
    @RequestMapping("/get_up_dateALl")
    public JsonResult<User> updateAll(User user, HttpSession session) {
        // 第一步：需要去拿到用户的uid 信息 和 username 用户名
        //uid 数据需要再次封装到user对象中
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        // 从域中拿到的数据
        iUserService.changeInfo(uid, username, user);
        return new JsonResult<>(OK);

    }

    /**
     * 用户密码的修改功能
     *
     * @param oldPassword 用户输入的原密码
     * @param newPassword 用户输入的新密码
     * @param session     从session 中取出数据
     * @return 返回
     */
    @RequestMapping("/upPassword")
    public JsonResult<Void> updatePassword(String oldPassword,
                                           String newPassword,
                                           HttpSession session) {
        // 这里通过session 拿到uid
        // 用户登录成功之后会吧数据放到session中去 然后这里就是拿到用户的放到session中的数据
        Integer uid = getuidFromSession(session);
        //这个也是 用户登录成功之后用户名和id 会放到session中去 然后这里是为了拿到数据
        String username = getUsernameFromSession(session);

        // 这里调用iUserService 把数据放入进去
        iUserService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }


    @RequestMapping("/login")
    public JsonResult<User> loginAll(String username, String password, HttpSession session) {
        // 用户输入的用户名和密码 然后去数据库中查询
        User data = iUserService.selectAllService(username, password);

        // 把数据传入到session中去
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
        // session 获取session 中绑定的数据
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));

        // 返回前端状态码 和 用户中的数据
        return new JsonResult<User>(OK, data);
    }


    /**
     * 用户注册的功能实现
     *
     * @param user
     * @return
     */
    @RequestMapping("/reg")
//    @ResponseBody // 表示此方法响应结果以及json格式进行数据的响应给前端
    public JsonResult<Void> reg(User user) {
        iUserService.reg(user);
        return new JsonResult<>(OK);
    }


//    @RequestMapping("/reg")
//    @ResponseBody // 表示此方法响应结果以及json格式进行数据的响应给前端
//    public JsonResult<Void> reg(User user){
//        /**
//         * 分析 用户输入数据之后 我们要怎么做
//         */
//        // 创建响应结果的对象
//        JsonResult<Void> result = new JsonResult<>();
//        try {
//            //拿到用户输入的数据
//            iUserService.reg(user);
//            // 没有遇到异常的话返回成功
//            result.setState(200);
//            result.setMessage("OK");
//
//            // 拿到用户的数据之后 有可能出现了异常
//            // 用户名已经存在
//            // 未知的异常
//        } catch (UsernameDuplicatedException e) {
//            result.setState(4000);
//            result.setMessage("用户名已存在!!!");
//        }catch (InsertException e){
//            result.setState(4001);
//            result.setMessage("未知异常请重新操作");
//        }
//        return result;
//    }
}






















































