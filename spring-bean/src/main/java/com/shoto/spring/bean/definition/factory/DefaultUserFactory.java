package com.shoto.spring.bean.definition.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct DefaultUserFactory 初始化中");
    }

    public void initUserFactory() {
        System.out.println("initUserFactory DefaultUserFactory 初始化中");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("InitializingBean#afterPropertiesSet DefaultUserFactory 初始化中");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy DefaultUserFactory 销毁中");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy DefaultUserFactory 销毁中");
    }

    public void destroyUserFactory() {
        System.out.println("destroyUserFactory DefaultUserFactory 销毁中");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("触发垃圾回收...");
    }
}
