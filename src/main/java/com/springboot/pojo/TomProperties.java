package com.springboot.pojo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 02/03/2018 11:19 AM
 * @Version
 */
public interface TomProperties extends JpaRepository<Tom, Integer> {

    List<Tom> findByAge(Integer age);

    /**
     * 查询30到40岁的人
     * @return
     */
    @Query("select t from Tom t where t.age between 30 and 40")
    List<Tom> findOther();
}
