package com.qyhl.guns.config.uniquery;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * uniquery通用查询配置类
 */
@Configuration
@ConfigurationProperties(prefix = "uniquery")
public class UniQueryConfig {
    private List<Select> selectList;
    private List<ParentTable> parentTableList;

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("uniquery.yml"));//File引入
        configurer.setProperties(yaml.getObject());
        return configurer;
    }


    public List<Select> getSelectList() {
        return selectList;
    }

    public void setSelectList(List<Select> selectList) {
        this.selectList = selectList;
    }

    public List<ParentTable> getParentTableList() {
        return parentTableList;
    }

    public void setParentTableList(List<ParentTable> parentTableList) {
        this.parentTableList = parentTableList;
    }
}
