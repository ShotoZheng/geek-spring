package com.shoto.spring.ioc.overview.domain;

import com.shoto.spring.ioc.overview.annotation.Super;

@Super
public class SuperUser extends User {
    private String address;

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
