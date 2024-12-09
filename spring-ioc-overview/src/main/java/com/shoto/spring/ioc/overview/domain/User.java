package com.shoto.spring.ioc.overview.domain;

import com.shoto.spring.ioc.overview.enums.City;
import org.springframework.core.io.Resource;

import java.util.Arrays;
import java.util.List;

/**
 * @author admin
 */
public class User {
    private String name;
    private Integer age;

    private City city;

    private List<City> workCities;

    private City[] lifeCities;

    private Resource resourceFileLocation;

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<City> getWorkCities() {
        return workCities;
    }

    public void setWorkCities(List<City> workCities) {
        this.workCities = workCities;
    }

    public City[] getLifeCities() {
        return lifeCities;
    }

    public void setLifeCities(City[] lifeCities) {
        this.lifeCities = lifeCities;
    }

    public Resource getResourceFileLocation() {
        return resourceFileLocation;
    }

    public void setResourceFileLocation(Resource resourceFileLocation) {
        this.resourceFileLocation = resourceFileLocation;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", city=" + city +
                ", workCities=" + workCities +
                ", lifeCities=" + Arrays.toString(lifeCities) +
                ", resourceFileLocation=" + resourceFileLocation +
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
