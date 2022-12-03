package com.mvm.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author:TuoTuo
 * @createDate:2022/11/25 22:06
 * @description:
 */
@Slf4j
@SpringBootApplication
public class Application {
    public static void main(String[] args) throws UnknownHostException {
        log.info("====启动开始====");
        ConfigurableApplicationContext application = SpringApplication.run(Application.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        if(!StringUtils.hasLength(path)){
            path = "";
        }
        log.info("====启动完成====");
        log.info("\n---------------------------------------------------------------------------------------------------\n\t" +
                "Application chat-service is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "\n\t" +
//                "Swagger文档: \thttp://" + ip + ":" + port + path + "doc.html\n" +
                "----------------------------------------------------------------------------------------------------");
    }
}
