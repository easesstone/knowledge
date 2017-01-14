## 第三部分 核心技术
* IoC 容器
* 资源
* 验证器，数据绑定，类型转换
* SpEL
* Spring 面向切面编程
* AOP 的APIS

### IoC 容器。
#### 对IoC和bean 的理解
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
  
#### Container
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
    <!-- 注意id 值唯一，class 的值是指全类名 -->
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
      <!--通过ref 这种关系，来反应对象之间的联系和引用 -- >
      <!-- additional collaborators and configuration for this bean go here -->
    </bean>
    <!-- more bean definitions for services go here -->
  </beans>
  ```
* 如下则是daos.xml也作为一个参考。

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
* 通常的做法，可以通过如下的方式，把多个xml 文件合并成为一个。从而加载的时候通过加载一个组合的xml来达到加载所有xml文件的
  形式。
  
  ```xml
  <beans>
    <import resource="services.xml"/>
    <import resource="resources/messageSource.xml"/>
    <import resource="/resources/themeSource.xml"/>
    <!-- 注意几个impore 的路径。 /resource/themeSource.xml 中的第一个斜杠会被忽略掉。不推荐使用相对路径，
        存在重大的安全隐患-->
    <bean id="bean1" class="..."/>
    <bean id="bean2" class="..."/>
  </beans>
  ```
* Container 的使用

  ```java
  // create and configure beans
  ApplicationContext context =
      new ClassPathXmlApplicationContext(new String[] {"services.xml", "daos.xml"});
  // retrieve configured instance
  PetStoreService service = context.getBean("petStore", PetStoreService.class);
  // use configured instance
  List<String> userList = service.getUsernameList();
  ```

#### Bean 概览
* bean 的配置，通常包含以下几点。
 
 ```
  1，专有名词，包限定类名，一个合法的类名：通常可以理解为全类名，即定义的bean 的具体实现类。
  2，行为配置，包含生命周期，有效范围。
  3，Bean 为了完成自身的功能对其他Bean 的依赖关系，被依赖的bean 成为协作这或者依赖关系。
  4，Bean 属性的设置，默认属性的设置。
  ```
  
  其常见的属性如下：

  | Property |Explained in… |
  | ------  |  ------ |
  | id     | the identifiers |
  | class | the section called “Instantiating beans” |
  | name | the section called “Naming beans” |
  | scope | Section 7.5, “Bean scopes” |
  | constructor | arguments the section called “Dependency Injection” |
  | properties | the section called “Dependency Injection” |
  | autowiring  mode | the section called “Autowiring collaborators” |
  | lazy-initialization mode | the section called “Lazy-initialized beans” |
  | initialization method  | the section called “Initialization callbacks” |
  | destruction method | the section called “Destruction callbacks” |
  
* 别名的应用
  例如，在多个子系统中，主系统与各个子系统使用不同的数据库时，可以使用别名进行配置。
  配置如下，虽然他们是同一个bean，但是引用的不同，他们的数据就不一样。
  注意名字的命名最好遵守java 的命名规则，即小写字母开头，之后是骆驼峰的形式。

  ```xml
    <alias name="subsystemA-dataSource" alias="subsystemB-dataSource"/>
    <alias name="subsystemA-dataSource" alias="myApp-dataSource" />
  ```


* 通过构造方法初始化

  ```xml
  <bean id="exampleBean" class="examples.ExampleBean"/>
  <bean name="anotherExample" class="examples.ExampleBeanTwo"/>
  ```

* 通过静态工厂方法

  ```xml
  <bean id="clientService"
    class="examples.ClientService"
    factory-method="createInstance"/>
  ```

  ```java
  public class ClientService {
    private static ClientService clientService = new ClientService();
    private ClientService() {}
    public static ClientService createInstance() {
      return clientService;
    }
  }
  ```
  
* 通过实例工厂方法实例化Bean
  与静态工厂方法的区别是，可以通过依赖注入，把实例工厂类，注入到IoC容器进行管理。


  
  ```xml
  <!-- the factory bean, which contains a method called createInstance() -->
  <bean id="serviceLocator" class="examples.DefaultServiceLocator">
	<!-- inject any dependencies required by this locator bean -->
  </bean>
  <!-- the bean to be created via the factory bean -->
  <bean id="clientService"
	factory-bean="serviceLocator"
	factory-method="createClientServiceInstance"/>
  ```

  ```java
  public class DefaultServiceLocator {
	  private static ClientService clientService = new ClientServiceImpl();
	  private DefaultServiceLocator() {}
	  public ClientService createClientServiceInstance() {
		return clientService;
    }
  }
  ```
  
  注：一个实例工厂方类，可以有多个方法来实例化对象:
  类似如下：


  ```xml
  <bean id="serviceLocator" class="examples.DefaultServiceLocator">
		<!-- inject any dependencies required by this locator bean -->
  </bean>
  <bean id="clientService"
	factory-bean="serviceLocator"
	factory-method="createClientServiceInstance"/>
  <bean id="accountService"
	factory-bean="serviceLocator"
	factory-method="createAccountServiceInstance"/>
  ```
  

  ```java
  public class DefaultServiceLocator {
	private static ClientService clientService = new ClientServiceImpl();
	private static AccountService accountService = new AccountServiceImpl();
	private DefaultServiceLocator() {}
	public ClientService createClientServiceInstance() {
		return clientService;
	}
	public AccountService createAccountServiceInstance() {
		return accountService;
	}
  }
  ```
