package com.shoto.spring.bean.definition.factory;

import com.shoto.spring.ioc.overview.domain.User;

public interface UserFactory {

    default User createUser() {
        return User.createUser();
    }

    default void initUserFactory() {

    }

    default void destroyUserFactory() {}

}
