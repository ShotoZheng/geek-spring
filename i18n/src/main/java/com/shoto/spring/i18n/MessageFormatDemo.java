package com.shoto.spring.i18n;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @see java.text.MessageFormat
 */
public class MessageFormatDemo {
    public static void main(String[] args) {
        // 实例1
        int planet = 7;
        String event = "a disturbance in the Force";
        String pattern = "At {1,time} on {1,date}, there was {2} on planet {0,number,integer}.";
        String result = MessageFormat.format(pattern, planet, new Date(), event);
        System.out.println(result);

        // 示例2
        MessageFormat messageFormat = new MessageFormat(pattern);
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        // pattern 重置
        pattern = "This is message: {0}";
        messageFormat.applyPattern(pattern);
        result = messageFormat.format(new Object[]{"Hello World!"});
        System.out.println(result);

        // Locale 重置
        pattern = "At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.";
        messageFormat.setLocale(Locale.JAPANESE); // setLocale 要在 applyPattern 方法之前执行
        messageFormat.applyPattern(pattern);
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        // 重置 format
        messageFormat.setFormat(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);
    }
}
