package com.shoto.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;

public class CustomizedSpringEventDemo {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.addApplicationListener(new MySpringEventListener());

        context.refresh();
        context.publishEvent(new MySpringEvent("Hello World"));

        context.close();
    }

    static class MySpringEventListener implements ApplicationListener<MySpringEvent> {

        @Override
        public void onApplicationEvent(MySpringEvent event) {
            println(event);
        }
    }

    public static void println(Object arg) {
        System.out.printf("[线程 %s]: %s\n", Thread.currentThread().getName(), arg);
    }

    static class MySpringEvent extends ApplicationEvent {

        /**
         * Create a new {@code ApplicationEvent}.
         *
         * @param source the object on which the event initially occurred or with
         *               which the event is associated (never {@code null})
         */
        public MySpringEvent(Object source) {
            super(source);
        }
    }
}
