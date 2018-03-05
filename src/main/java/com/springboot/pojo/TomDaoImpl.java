package com.springboot.pojo;

import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 02/03/2018 4:21 PM
 * @Version
 */
@Repository
public class TomDaoImpl implements TomDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Tom> findOther() {
        Query query = entityManager.createQuery("select t from Tom t where t.age between 30 and 40");
        return query.getResultList();
    }
}
