package com.itheima;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
//@MapperScan("com.itheima.dao")
@ServletComponentScan
@SpringBootApplication
@EnableTransactionManagement //开启对事物管理的支持
public class RuijiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuijiApplication.class, args);
        log.info("项目启动成功...");
    }

}
