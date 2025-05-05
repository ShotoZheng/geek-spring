package com.shoto.spring.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * java 消息生产/消费模型示例
 */
public class ObserverDemo {

    public static void main(String[] args) {
        // 消息生产者
        EventObservable observable = new EventObservable();
        // 添加消息消费者
        observable.addObserver(new EventObserver());
        // 发送消息
        observable.notifyObservers("Hello World");
    }

    static class EventObserver implements Observer, EventListener {

        @Override
        public void update(Observable o, Object event) {
            EventObject eventObject = (EventObject) event;
            System.out.println("接收到信息：" + eventObject);
        }
    }

    static class EventObservable extends Observable {
        @Override
        public void notifyObservers(Object arg) {
            try {
                setChanged();
                super.notifyObservers(new EventObject(arg));
            } finally {
                clearChanged();
            }
        }
    }
}
