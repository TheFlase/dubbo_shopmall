package start;


import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @Author wgc
 * @Description
 * @Date 3/30/2020
 **/
@SpringBootApplication
@EnableDubbo
@MapperScan("com.wgc.shopmall.inventory.dao")
@DubboComponentScan("com.wgc.shopmall.inventory.service")
public class MallShopInventoryStart {
    public static void main(String[] args) {
        SpringApplication.run(MallShopInventoryStart.class, args);
    }

}
