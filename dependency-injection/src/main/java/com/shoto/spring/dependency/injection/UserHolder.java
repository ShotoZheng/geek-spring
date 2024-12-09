package com.shoto.spring.dependency.injection;

import com.shoto.spring.ioc.overview.domain.User;

/**
 * {@link com.shoto.spring.ioc.overview.domain.User} 实现 Holder 上下文
 */
public class UserHolder {

    private User user;

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
