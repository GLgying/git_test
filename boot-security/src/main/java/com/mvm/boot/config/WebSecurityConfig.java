package com.mvm.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author:TuoTuo
 * @createDate:2022/12/2 19:33
 * @description:
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    /**
     * 密码验证器
     */
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    /**
     * 成功处理器
     */
//    @Autowired
//    private AuthenticationSuccessHandler authenticationSuccessHandler;


    /**
     * 失败处理器
     */
//    @Autowired
//    private AuthenticationFailureHandler authenticationFailureHandler;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    AuthenticationSuccessHandler authenticationSuccessHandler(){
        return  new MyAuthenticationSuccessHandler();
    };

    @Bean
    AuthenticationFailureHandler authenticationFailureHandler(){
        return  new MyAuthenticationFailureHandler();
    };

    /**
     * 向Security注入用户信息
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }



    /**
     * 配置规则
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //开启登陆配置
        http.authorizeRequests()
                // 登录之后就能访问
                .antMatchers("/no-authorize").authenticated()
                // 登陆后 需要校长角色权限
                .antMatchers("/need-authorize").hasRole("校长")
                // 其他的路径都是登录后即可访问
                .anyRequest().authenticated()
                .and().formLogin()
                // 定义登录页面，未登录时，访问一个需要登录之后才能访问的接口，会自动跳转到该页面
                .loginPage("/login_page")
                //登录成功的处理器
                .successHandler(authenticationSuccessHandler())
                //登录失败的处理器  authenticationFailureHandler
                .failureHandler(authenticationFailureHandler())
                // 登录处理接口
                .loginProcessingUrl("/login")
                // 定义登录时，用户名的 key，默认为 username
                .usernameParameter("username")
                //定义登录时，用户密码的 key，默认为 password
                .passwordParameter("password").permitAll()
                .and().logout()
                ////和表单登录相关的接口统统都直接通过
                .permitAll()
                .and().csrf().disable().exceptionHandling().accessDeniedHandler(getAccessDeniedHandler());
    }

    /**
     * 对于/static/  下的路径都不用认证
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/no-login");
    }

    /**
     * 用户未认证异常拦截
     */
    @Bean
    AccessDeniedHandler getAccessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }


    /**
     * 1、配置的是认证信息, AuthenticationManagerBuilder 这个类,就是AuthenticationManager的建造者, 我们只需要向这个类中, 配置用户信息,
     *    就能生成对应的AuthenticationManager, 这个类也提过,是用户身份的管理者, 是认证的入口, 因此,我们需要通过这个配置,想security提供真实的用户身份。
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    }
    /**
     * 2、配置Security的认证策略, 每个模块配置使用and结尾。这个也是最复杂的
     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//    }
    /**
     * 3、这个配置方法用于配置静态资源的处理方式，可使用 Ant 匹配规则。就是可以不用认证就可以直接访问的接口
     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//    }

}
