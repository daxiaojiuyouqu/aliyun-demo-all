package com.aliyun.aliyundemo.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class GuavaCacheConfig {
    /**
     * 说明:
     * GuavaCache适用场景
     * 1.某些接口或者键值会被查询多次以上；
     * 2.愿意使用或牺牲一些内存空间来提升访问或者计算速度；
     * 3.缓存内容或者结果值较小，不会超过内存总容量
     * 注解说明:
     *
     * @Cacheable 触发缓存逻辑: 标注的方法前先查看缓存中是否有数据，如果有数据，则直接返回缓存数据；若没有数据，执行该方法并将方法返回值放进缓存。
     * @CacheEvict 触发缓存逐出逻辑:方法执行成功后会从缓存中移除相应数据。
     * @CachePut 不干涉方法执行地更新缓存:@Cacheable 类似，但会把方法的返回值放入缓存中, 主要用于数据新增和修改方法。
     * 待研究:https://blog.csdn.net/yueaini10000/article/details/78918871
     */


    private static final int DEFAULT_MAXSIZE = 1000;

    private static final int DEFAULT_TTL = 3600;

    /**
     * 声明cacheManager
     *
     * @return
     */
    @Bean
    public CacheManager cacheManager() {
        GuavaCacheManager cacheManager = new GuavaCacheManager();
        cacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterWrite(DEFAULT_TTL, TimeUnit.SECONDS).maximumSize(DEFAULT_MAXSIZE));
        return cacheManager;
    }


}
