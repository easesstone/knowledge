### 第三部分 核心技术
* IoC 容器
* 资源
* 验证器，数据绑定，类型转换
* SpEL
* Spring 面向切面编程
* AOP 的APIS
* IoC 容器。
 * IoC 反转控制，也叫作依赖注入。
   总的来说，也就是说，把一个对象对其他对象的依赖进行统一的管理。 把一个对象的new过程，实例化过程，用一个容器来统一管理。
   你需要的时候，直接从容器里面来读取，而不必再实例化一遍，而且，在我个人的理解，用容器进行管理对象的实例化过程，你需要的
   时候，容器可以帮你实例化，不需要的时候，容器可以帮你自动回收，可以一定程度的减少代码量，优化应用程序的执行。
   而传统的模式，你只可以在代码中通过new 方法实现，以及通过自己写工厂方法来实现，所以与传统的应用来说，可以说是一个相反的
   过程。
 * Ioc 容器，主要的两个包是org.springframework.beans 和 org.springframework.context。
   其中最自出的接口是BeanFactory，它提供一种先进的机制来管理任何类型的对象。它是整个Spring框架的一个基础接口。
   而ApplicationContext是继承于BeanFactory的一个子接口，它提供比BeanFactory更加丰富的特性和功能，是Spring 中一个十分
   重要的一个接口。从根本上，可以说，其可以代表Spring的IoC容器。
 * ApplicationContext有两个实现类，ClassPathXmlApplicationContext or FileSystemXmlApplicationContext。
 * ApplicationContext，也就是说，IoC 容器，通过读取Configuration Metadata的配置，来知道该如何实例化，配置，组装你的应
   用程序的Object.
 * configuration metadata是IoC 容器中一个重要的概念，翻译成中文后，指的是配置元数据。但是翻译之后比较别扭，个人认为还是
   照搬好了，从中文的角度理解，可以理解问，IoC 容器中Bean 类改如何配置。
   有如下几种方式的配置：
   1，通过xml 的形式进行配置。官方比较推荐的配置。即xml-base形式。
   2，通过Java注解的形式：有两种，
      一种是2.5 的时候引入的，即，Annotation-based configuration
      另一种是3.0的时候引入的，即， Java-based configuration
      这种方式进行配置configuration mata,也比较多的人用。对于与java代码有洁癖的人来说，是不应该存在xml 这种形式的配置方式
      的。
   3，第三种方式，通过java 代码的方式，来进行configuration metadata该如何配置。比较麻烦，感兴趣的，可以自行查找相关资料。
 * 以上集中配置形式中，xml通过<bean></bean> 的形式配置bean，而所有的bean则是放在<beans></beans>
   而java-base configuration 的形式，则是通过诸如，@Configuration, @Bean, @Import，@DependsOn 等形式。
   配置的Bean 一般是组成Your Application's Service layer，Data Access Layer, presentation layer 的Object.
 * 如下：展示了xml 形式的基础配置：
   
   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     xsi:schemaLocation="http://www.springframework.org/schema/beans
     http://www.springframework.org/schema/beans/spring-beans.xsd">
     <bean id="..." class="...">
     <!-- collaborators and configuration for this bean go here -->
     </bean>
     <bean id="..." class="...">
     <!-- collaborators and configuration for this bean go here -->
     </bean>
     <!-- more bean definitions go here -->
   </beans>
   ```
 * IoC container 的Instantiate·
  
   ```java
   ApplicationContext context =
              new ClassPathXmlApplicationContext(new String[] {"services.xml", "daos.xml"});
   ```
 * 如下展示的是service.xml，仅仅作为一个参考。
   
   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     xsi:schemaLocation="http://www.springframework.org/schema/beans
     http://www.springframework.org/schema/beans/spring-beans.xsd">
     <!-- services -->
     <bean id="petStore" class="org.springframework.samples.jpetstore.services.PetStoreServiceImpl">
       <property name="accountDao" ref="accountDao"/>
       <property name="itemDao" ref="itemDao"/>
       <!-- additional collaborators and configuration for this bean go here -->
     </bean>
     <!-- more bean definitions for services go here -->
   </beans>
   ```
 * 如下则是
 
   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     xsi:schemaLocation="http://www.springframework.org/schema/beans
     http://www.springframework.org/schema/beans/spring-beans.xsd">
     <bean id="accountDao"
       class="org.springframework.samples.jpetstore.dao.jpa.JpaAccountDao">
       <!-- additional collaborators and configuration for this bean go here -->
     </bean>
     <bean id="itemDao" class="org.springframework.samples.jpetstore.dao.jpa.JpaItemDao">
     <!-- additional collaborators and configuration for this bean go here -->
     </bean>
     <!-- more bean definitions for data access objects go here -->
   </beans>
   ```
