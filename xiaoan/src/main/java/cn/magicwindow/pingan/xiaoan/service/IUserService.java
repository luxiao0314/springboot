package cn.magicwindow.pingan.xiaoan.service;

import cn.magicwindow.pingan.model.custom.User;

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 05/03/2018 4:52 PM
 * @Version
 */
public interface IUserService {

    User login(String telephone, String isVerified);

    User save(String userId, String userName, String birthday,String sex, String maritalStatus, String photo);

    User saveSignature(String userId, String signature);
}
