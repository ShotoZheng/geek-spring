package com.shoto.spring.bean.definition.factory;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
