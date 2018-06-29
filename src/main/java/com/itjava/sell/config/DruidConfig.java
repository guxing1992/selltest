package com.itjava.sell.config;

import javax.sql.DataSource;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

import java.util.HashMap;
import java.util.Map;

@Configuration  //标识该类被纳入spring容器中实例化并管理
//@ServletComponentScan //用于扫描所有的Servlet、filter、listener
public class DruidConfig {
    @Bean
    @ConfigurationProperties(prefix="spring.datasource.druid") //加载时读取指定的配置信息,前缀为spring.datasource.druid
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 注册一个StatViewServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean DruidStatViewServlet(){
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> initParams=new HashMap<String,String>();
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
        initParams.put("allow","");
//        initParams.put("deny",)
        servletRegistrationBean.setInitParameters(initParams);
        return servletRegistrationBean;
    }

    /**
     * 注册一个：filterRegistrationBean
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter(){

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());

        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");

        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}