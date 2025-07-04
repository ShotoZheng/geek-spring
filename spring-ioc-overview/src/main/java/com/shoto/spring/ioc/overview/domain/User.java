package com.shoto.spring.ioc.overview.domain;

import com.shoto.spring.ioc.overview.enums.City;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author admin
 */
public class User implements BeanNameAware {
    private String name;
    private Integer age;

    private City city;

    private List<City> workCities;

    private City[] lifeCities;

    private Resource resourceFileLocation;

    private Company company;

    private transient String beanName;

    private Properties context;

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

    public Properties getContext() {
        return context;
    }

    public void setContext(Properties context) {
        this.context = context;
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
                ", company=" + company +
                ", beanName='" + beanName + '\'' +
                ", context=" + context +
                '}';
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @PostConstruct
    public void init() {
        System.out.println("user " + beanName + " init...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("user " + beanName + " destroy...");
    }
}
