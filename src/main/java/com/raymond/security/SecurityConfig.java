package com.raymond.security;

import com.raymond.constants.HttpStatus;
import com.raymond.filter.*;
import com.raymond.utils.AjaxResult;
import com.raymond.utils.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CorsFilter corsFilter;
    @Autowired
    private CachingRequestBodyFilter cachingRequestBodyFilter;


    private JWTTokenUtil jwtTokenUtil;

    public SecurityConfig(JWTTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        自定义表单登录
//        http.formLogin(form ->{
//            form.usernameParameter("username")
//                    .passwordParameter("password")
//                    .loginProcessingUrl("/api/login/pwd")
//                    .successHandler((request, response, authentication) -> {
//                        AjaxResult ajaxResult = new AjaxResult(HttpStatus.SUCCESS,"Authentication success",null);
//                        response.setContentType("application/json");
//                        response.getWriter().write(ajaxResult.toString());
//                    })
//                    .failureHandler((request, response, exception) -> {
//                        AjaxResult ajaxResult = new AjaxResult(HttpStatus.LOGIN_FAILED,"Authentication fail",null);
//                        response.setContentType("application/json");
//                        response.getWriter().write(ajaxResult.toString());
//                    });
//        });
        http.logout(logout ->{
            logout.logoutUrl("/api/admin/logout")
                    .logoutSuccessHandler((request, response, authentication) -> {
                        AjaxResult ajaxResult = new AjaxResult(HttpStatus.SUCCESS,"Logout success",null);
                        response.setContentType("application/json");
                        response.getWriter().write(ajaxResult.toString());
                    })
                    .clearAuthentication(true)
                    .invalidateHttpSession(true);
        });
        // 权限拦截配置
        http.authorizeRequests(authorize ->{
            // TODO 除了登录请求其他请求全部拦截
            authorize.antMatchers("/api/login/pwd").permitAll()
                    .anyRequest().authenticated();
        });
        // 异常处理
        http.exceptionHandling(exception ->{
            exception.accessDeniedHandler(((request, response, accessDeniedException) -> {
                AjaxResult ajaxResult = new AjaxResult(HttpStatus.UNAUTHORIZED,"Access denied",null);
                response.setContentType("application/json");
                response.getWriter().write(ajaxResult.toString());
                    }))
                    .authenticationEntryPoint(((request, response, authException) -> {
                        AjaxResult ajaxResult = new AjaxResult(HttpStatus.UNAUTHORIZED,"Access denied",null);
                        response.setContentType("application/json");
                        response.getWriter().write(ajaxResult.toString());
                    }));
        });

        // 添加 JWT 过滤器到 Spring Security 过滤器链中
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenUtil, authenticationManager()),
                UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new ContentTypeFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(corsFilter, SessionManagementFilter.class); // cors过滤器
        http.addFilterBefore(cachingRequestBodyFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new XSSFilter(), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
