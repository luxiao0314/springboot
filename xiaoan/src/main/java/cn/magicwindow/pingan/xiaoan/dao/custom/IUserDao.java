package cn.magicwindow.pingan.xiaoan.dao.custom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.magicwindow.pingan.model.custom.User;

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 05/03/2018 2:27 PM
 * @Version
 */
public interface IUserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    /**
     * 通过userId获取User信息
     *
     * @param userId
     * @return
     */
    public User findByUserId(String userId);
}
