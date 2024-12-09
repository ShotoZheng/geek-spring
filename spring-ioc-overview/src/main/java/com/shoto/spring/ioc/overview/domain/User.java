package com.shoto.spring.ioc.overview.domain;

/**
 * @author admin
 */
public class User {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    /**
     * 静态方法创建 Bean
     */
    public static User createUser() {
       User user = new User();
       user.setName("shoto");
       user.setAge(25);
       return user;
    }
}
