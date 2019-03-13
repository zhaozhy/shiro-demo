package com.example.shiro.shiro.config;

import com.example.shiro.shiro.Shiro.MyShiroRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn(value = "lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true); // it's false by default
        return creator;
    }

    @Bean
    public MyShiroRealm  myShiroRealm()
    {
        MyShiroRealm  myShiroRealm=new MyShiroRealm();
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager()
    {
        DefaultWebSecurityManager defaultWebSecurityManager =new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myShiroRealm());
        return  defaultWebSecurityManager;
    }



    @Bean
    public   AuthorizationAttributeSourceAdvisor  authorizationAttributeSourceAdvisor(SecurityManager securityManager)
    {
       AuthorizationAttributeSourceAdvisor  authorizationAttributeSourceAdvisor=new AuthorizationAttributeSourceAdvisor();
       authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
       return  authorizationAttributeSourceAdvisor;
    }
    @Bean
    public ShiroFilterFactoryBean  shirFilter(SecurityManager securityManager)
    {
        ShiroFilterFactoryBean  shiroFilterFactoryBean =new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        Map<String ,String > filterMap=new HashMap<String ,String>();
        filterMap.put("/*","anon");
        filterMap.put("/authc/index","authc");
        filterMap.put("/authc/admin","roles[admin]");
        filterMap.put("/authc/cau","perms[Create,Update]");
        filterMap.put("/authc/removable","perms[Delete]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return  shiroFilterFactoryBean;

    }




}
