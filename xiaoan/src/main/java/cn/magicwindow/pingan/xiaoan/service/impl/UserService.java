package cn.magicwindow.pingan.xiaoan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

import cn.magicwindow.pingan.model.custom.User;
import cn.magicwindow.pingan.util.DateUtil;
import cn.magicwindow.pingan.xiaoan.dao.custom.IUserDao;
import cn.magicwindow.pingan.xiaoan.service.IUserService;

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 05/03/2018 4:53 PM
 * @Version
 */
@Service
class UserService implements IUserService {

    @Autowired
    private IUserDao iUserDao;

    @Override
    public User login(String telephone, String isVerified) {
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setTelphone(telephone);
        iUserDao.save(user);
        return user;
    }

    @Override
    public User save(String userId, String userName, String birthday, String sex, String maritalStatus, String photo) {
        User user = iUserDao.findByUserId(userId);
        if (user == null) return null;
        user.setPhoto(photo);
        Date date = DateUtil.parseDateTime(birthday, DateUtil.DATETIME_PATTERN_3_3);
        user.setBirthday(date);
        user.setSex(sex);
        user.setUserName(userName);
        user.setMaritalStatus(maritalStatus);
        iUserDao.save(user);
        return user;
    }

    @Override
    public User saveSignature(String userId, String signature) {
        User user = iUserDao.findByUserId(userId);
        if (user == null) return null;
        user.setSignature(signature);
        iUserDao.save(user);
        return user;
    }
}
