package com.shoto.spring.environment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestPropertySourceTests.class) // 测试注解驱动
@TestPropertySource(locations = "classpath:/META-INF/default.properties")
//@TestPropertySource(properties = "user.name = tawson")
public class TestPropertySourceTests {

    @Value("${user.name}")
    private String userName;

    @Test
    public void test() {
        assertEquals("tawson", userName);

    }
}
