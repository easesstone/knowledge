### Spring 优点
* 轻量级的，非侵入式的，应用很少需要重构spring api。  
* IOC 和DI ，反转控制，依赖注入，更好地实现对象的管理。  
* AOP，面向切面编程，更好地实现事物，日记等的管理。  
* 功能强大，提供一站式的解决方案，完全用spring 就可以架构你所需要的应用。  
* 易于扩展，可以很好的和其他框架对接和整合。  
* 易于构建restful 风格的应用，即，类似于MVC 模型的应用。  
* 安全，提供非常强大的安全控制功能。  
### Spring Bean 配置。
* Spring 命名空间的使用  

http://www.springframework.org/schema/beans  
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
                http://www.springframework.org/schema/beans
                http://www.springframework.org/schema/beans/spring-beans-4.3.xsd">
</beans>
```


* IOC 和 DI
很好地体现了把对象作为一个服务提供者这一个思想。    
所有的对象都归一个容器管理，即ApplicationContext 和 BeanFactory.  
BeanFactory 面向的是Spring Spring 框架的基础设施，  
ApplicationContext 面向的是使用Spring 框架的开发者  
ApplicationContext 接口有两个中主要的实现类，ClassPathXmlApplicationContext  
和 FileSystemXmlApplicationContext, 分别从类路径进行读取配置 和从文件系统读取配置。
