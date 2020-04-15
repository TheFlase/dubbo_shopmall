package com.wgc.shopmall.order;

import com.wgc.shopmall.order.start.ShopMallOrderStart;
import com.wgc.shopmall.order.util.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author wgc
 * @Description redis测试类
 * @Date 4/15/2020
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShopMallOrderStart.class)
public class RedisConfigTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private ValueOperations<String,Object> valueOperations;

    @Resource
    private HashOperations<String, String, Object> hashOperations;

    @Resource
    private ListOperations<String, Object> listOperations;

    @Resource
    private SetOperations<String, Object> setOperations;

    @Resource
    private ZSetOperations<String, Object> zSetOperations;

    @Resource
    private RedisService redisService;

    private final String TEST_PRE = "temp:user:";

    @Test
    public void testObj() throws Exception{
        UserVo userVo = new UserVo("wgc","广东深圳",25);
        ValueOperations<String,Object> operations = redisTemplate.opsForValue();
        operations.set(TEST_PRE,userVo);
        redisService.expireKey(TEST_PRE,20, TimeUnit.SECONDS);
        UserVo vo = (UserVo) operations.get(TEST_PRE);
        System.out.println("测试==========="+vo);
    }

    @Test
    public void testValueOption( )throws  Exception{
        UserVo userVo = new UserVo("不择细流","shenzhen",33);
        valueOperations.set("test",userVo);
        System.out.println(valueOperations.get("test"));
    }

    @Test
    public void testSetOperation() throws Exception{
        UserVo userVo = new UserVo("不择细流","广州",27);
        UserVo auserVo = new UserVo("张三","上海",29);
        setOperations.add("user:test",userVo,auserVo);
        Set<Object> result = setOperations.members("user:test");
        System.out.println(result);
    }

    @Test
    public void HashOperations() throws Exception{
        UserVo userVo = new UserVo("小明","大连",22);
        hashOperations.put("hash:user",userVo.hashCode()+"",userVo);
        System.out.println(hashOperations.get("hash:user",userVo.hashCode()+""));
    }

    @Test
    public void  ListOperations() throws Exception{
        UserVo userVo = new UserVo("李四","重庆",26);
//        listOperations.leftPush("list:user",userVo);
//        System.out.println(listOperations.leftPop("list:user"));
        // pop之后 值会消失
        System.out.println(listOperations.leftPop("list:user"));
    }

    private static class UserVo{
        public  static final String Table = "t_user";

        private String name;
        private String address;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public UserVo(String name, String address, Integer age) {
            this.name = name;
            this.address = address;
            this.age = age;
        }

        public UserVo() {
        }

        @Override
        public String toString() {
            return "UserVo{" +
                    "name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
