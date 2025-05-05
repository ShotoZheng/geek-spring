package com.shoto.spring.event;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class ApplicationListenerDemo implements ApplicationEventPublisherAware {

    public static void main(String[] args) {
        // 基于接口形式 Spring 事件监听
//        GenericApplicationContext context = new GenericApplicationContext();
//        context.addApplicationListener(event -> System.out.println("ApplicationListener-接收事件：" + event));

        // 基于注解方式 Spring 事件监听
        // 通过 Context API 进行注册
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ApplicationListenerDemo.class);

        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                println("ApplicationListener - 接收到 Spring 事件：" + event);
            }
        });

        // 作为 Spring Bean 进行注册
        context.register(MyApplicationListener.class);

        context.refresh();
        context.start();

        context.close();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new ApplicationEvent("ApplicationEventPublisherAware-setApplicationEventPublisher1") {
        });
        applicationEventPublisher.publishEvent("ApplicationEventPublisherAware-setApplicationEventPublisher2");
        applicationEventPublisher.publishEvent(new MyPayloadApplicationEvent(this, "MyPayloadApplicationEvent"));
    }

    static class MyPayloadApplicationEvent extends PayloadApplicationEvent<String> {

        /**
         * Create a new PayloadApplicationEvent.
         *
         * @param source  the object on which the event initially occurred (never {@code null})
         * @param payload the payload object (never {@code null})
         */
        public MyPayloadApplicationEvent(Object source, String payload) {
            super(source, payload);
        }
    }

    @EventListener
    public void onPayloadApplicationEvent(PayloadApplicationEvent<String> event) {
        println("onPayloadApplicationEvent - 接收到 Spring PayloadApplicationEvent：" + event);
    }

    static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            println("MyApplicationListener onApplicationEvent: " + event);
        }
    }

//    @Async
    @EventListener
    public void onApplicationEvent(ApplicationEvent event) {
        println("@EventListener-接收事件：" + event);
    }

    @Order(2)
//    @Async
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        println("@EventListener-ContextRefreshedEvent-接收事件：" + event);
    }

    @Order(1)
//    @Async
    @EventListener
    public void onApplicationEvent(ContextStartedEvent event) {
        println("@EventListener-ContextStartedEvent-接收事件：" + event);
    }

    public static void println(Object arg) {
        System.out.printf("[线程 %s]: %s\n", Thread.currentThread().getName(), arg);
    }
}
