package com.aliyun.aliyundemo.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * 该类过滤哪些类在项目启动时,不进容器
 * @author 余新生
 */
@Component
@Slf4j
public class MyTypeFilter implements TypeFilter {

    private final String packName = "com.aliyun.aliyundemo.rabbitmq.chuxue";

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        String className = classMetadata.getClassName();
        if (className.contains(packName)) {
            log.info("不扫描的类名:{}", className);
            return true;
        }
        return false;
    }
}
