package com.aliyun.aliyundemo.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.assertj.core.util.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidConfig {
    /**
     * @return
     * @ConfigurationProperties 该注解表示读取配置文件前缀为prefix的配置，将外部的配置文件与这里绑定
     * @Bean 表示容器开启与关闭
     */
    @ConfigurationProperties(prefix = "spring.druid")
    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setProxyFilters(Lists.newArrayList(statFilter()));
        return dataSource;
    }

    /**
     * 利用filter将慢sql的日志打印出来
     *
     * @return
     */
    @Bean
    public Filter statFilter() {
        StatFilter statFilter = new StatFilter();
        //多长时间定义为慢sql，这里定义为5s
        statFilter.setSlowSqlMillis(5000);
        //是否打印出慢日志
        statFilter.setLogSlowSql(true);
        //是否将日志合并起来
        statFilter.setMergeSql(true);
        return statFilter;
    }

    /**
     * 配置druid监控
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    }
}
