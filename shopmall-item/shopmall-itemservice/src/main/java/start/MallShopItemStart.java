package start;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author wgc
 * @Description
 * @Date 3/30/2020
 **/
@SpringBootApplication
@EnableDubbo
@MapperScan("com.wgc.shopmall.item.dao")
@DubboComponentScan("com.wgc.shopmall.item.service")
@ComponentScan("com.wgc.shopmall.item.controller")
public class MallShopItemStart {
    public static void main(String[] args) {
        SpringApplication.run(MallShopItemStart.class, args);
    }
}
