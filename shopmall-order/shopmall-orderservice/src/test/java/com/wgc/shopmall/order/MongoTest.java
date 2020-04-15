package com.wgc.shopmall.order;

import com.alibaba.fastjson.JSON;
//import org.junit.jupiter.api.Test;
import com.wgc.shopmall.order.start.ShopMallOrderStart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author wgc
 * @Description
 * @Date 4/14/2020
 **/
@SpringBootTest(classes = ShopMallOrderStart.class)
@RunWith(SpringRunner.class)
public class MongoTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void add(){
        User user = new User();
        user.setId(1);
        user.setName("zhuyu");
        user.setAge(26);
        mongoTemplate.insert(user , "User");//save方法,如果存在则update
    }
    @Test
    public void edit(){
        Query query = new Query(Criteria.where("name").is("zhuyu"));
        Update update = new Update();
        update.set("name","zy");
        update.set("age",25);
        mongoTemplate.updateFirst(query , update , "User");
    }
    @Test
    public void del(){
        Query query = new Query(Criteria.where("name").is("zy"));
        mongoTemplate.remove(query , "User");
    }
    @Test
    public void getById(){
        Query query = new Query(Criteria.where("name").is("zy"));
        User user = mongoTemplate.findOne(query,User.class,"User");
        System.out.println(JSON.toJSONString(user));
    }

    private class User{
        private Integer id;
        private Integer age;
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

