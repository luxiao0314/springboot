package com.springboot.controller;

import com.springboot.pojo.Tom;
import com.springboot.pojo.TomDao;
import com.springboot.pojo.TomProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 02/03/2018 11:02 AM
 * @Version
 */
@RestController
class TestController {

    @Value("${easemob.org.name}")
    private String name;
    @Value("${age}")
    private Integer age;

    @Autowired
    private TomProperties tomProperties;

    @Autowired
    private TomDao tomDao;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return name + ": " + age;
    }

    @RequestMapping(value = "query", method = RequestMethod.GET)
    public List<Tom> query() {
        return tomProperties.findAll();
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Tom save(@RequestParam("age") Integer age,
                    @RequestParam("name") String name) {
        Tom tom = new Tom();
        tom.setAge(age);
        tom.setName(name);
        return tomProperties.save(tom);
    }

    /**
     * 传json
     *
     * @param tom
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Tom save(@RequestBody Tom tom) {
        return tomProperties.save(tom);
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public void delete(@RequestParam("id") Integer id) {
        tomProperties.deleteById(id);
    }

    @RequestMapping(value = "query/id", method = RequestMethod.GET)
    public Optional<Tom> findById(@RequestParam("id") Integer id) {
        return tomProperties.findById(id);
    }

    @RequestMapping(value = "query/age", method = RequestMethod.GET)
    public List<Tom> findByAge(@RequestParam("age") Integer age) {
        return tomProperties.findByAge(age);
    }

    /**
     * 查询大于30岁的人
     */
    @RequestMapping(value = "query/other", method = RequestMethod.GET)
    public List<Tom> findOther() {
//        return tomProperties.findOther();
        return tomDao.findOther();
    }
}
