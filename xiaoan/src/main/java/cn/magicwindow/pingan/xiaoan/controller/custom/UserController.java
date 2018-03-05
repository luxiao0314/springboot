package cn.magicwindow.pingan.xiaoan.controller.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.magicwindow.pingan.common.dto.Msg;
import cn.magicwindow.pingan.model.custom.User;
import cn.magicwindow.pingan.xiaoan.service.IUserService;

/**
 * @Description 用户信息相关controller
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 05/03/2018 2:24 PM
 * @Version
 */
@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 登录接口
     *
     * @param telephone
     * @param isVerified
     * @return
     */
    @GetMapping(value = "login")
    public Msg login(@RequestParam("telephone") String telephone, @RequestParam("isVerified") String isVerified) {
        if ("Y".equals(isVerified)) {
            User user = iUserService.login(telephone, isVerified);
            return Msg.success().add("user", user);
        }
        return Msg.error("验证码输入错误");
    }

    /**
     * 保存用户信息
     *
     * @return
     */
    @GetMapping(value = "save")
    public Msg save(@RequestParam("userId") String userId,
                     @RequestParam("userName") String userName,
                     @RequestParam("birthday") String birthday,
                     @RequestParam("sex") String sex,
                     @RequestParam("maritalStatus") String maritalStatus,
                     @RequestParam("photo") String photo) {
        User user = iUserService.save(userId, userName, birthday, sex, maritalStatus, photo);
        if (user == null) {
            return Msg.error();
        }
        return Msg.success().add("user", user);
    }
}
