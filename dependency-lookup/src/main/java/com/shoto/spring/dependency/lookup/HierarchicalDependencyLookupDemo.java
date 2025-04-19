package com.shoto.spring.dependency.lookup;

import com.shoto.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Bean 的层次性查找
 */
public class HierarchicalDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ObjectProviderDemo.class);

        // 设置 ParentBeanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);

        // 依赖查询 Bean
        displayContainerLocalBean(beanFactory, "user");
        displayContainerLocalBean(parentBeanFactory, "user");

        displayContainerBean(beanFactory, "user");
        displayContainerBean(parentBeanFactory, "user");

        Map<String, User> users = BeanFactoryUtils.beansOfTypeIncludingAncestors(beanFactory, User.class);
        System.out.println(users);

        context.refresh();
        context.close();
    }

    private static void displayContainerBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 BeanName[%s]，结果:[%s]\n", beanFactory, beanName, containsBean(beanFactory, beanName));
    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        // 如果是层次性 Bean，则先递归让 parentBeanFactory 查询
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            if (containsBean((ConfigurableListableBeanFactory) parentBeanFactory, beanName)) {
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }

    private static void displayContainerLocalBean(BeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 BeanName[%s]，结果:[%s]\n", beanFactory, beanName, beanFactory.containsBean(beanName));
        if (beanFactory instanceof HierarchicalBeanFactory sonBeanFactory) {
            System.out.printf("当前 BeanFactory[%s] 是否包含 Local BeanName[%s]，结果:[%s]\n", beanFactory, beanName, sonBeanFactory.containsLocalBean(beanName));
        }
    }

    private static HierarchicalBeanFactory createParentBeanFactory() {
        String location = "META-INF/dependency-lookup-context.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(location);
        return context.getBeanFactory();
    }

}
