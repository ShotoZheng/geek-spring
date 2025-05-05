package com.shoto.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.EventListener;

public class CustomizedAnnotationSpringEventDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(CustomizedAnnotationSpringEventDemo.class, MySpringEventListener.class);

        context.refresh();

        context.publishEvent(new MySpringEvent("Hello World"));

        context.close();
    }

    static class MySpringEventListener {
        @EventListener
        public void listener(MySpringEvent event) {
            println("listener - 接收到 Spring MySpringEvent：" + event);
        }
    }

    static class MySpringEvent extends ApplicationEvent {

        public MySpringEvent(Object source) {
            super(source);
        }
    }

    public static void println(Object arg) {
        System.out.printf("[线程 %s]: %s\n", Thread.currentThread().getName(), arg);
    }
}
