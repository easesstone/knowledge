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
  
* 依赖注入的两种表现形式<br />
  1，基于构造函数的依赖注入<br />
  2，基于setter 设置属性值的方式进行依赖注入<br />
  
  
* 基于构造函数的依赖注入。
  形如下面的Class，只可以用构造函数的依赖注入。

  
  ```java
  public class SimpleMovieLister {
    // the SimpleMovieLister has a dependency on a MovieFinder
    private MovieFinder movieFinder;
    // a constructor so that the Spring container can inject a MovieFinder
    public SimpleMovieLister(MovieFinder movieFinder) {
      this.movieFinder = movieFinder;
    }
    // business logic that actually uses the injected MovieFinder is omitted...
  }
  ```

* 对于构造函数参数的指定，一般情况下必须指定其类型，或者在构造参数中的位置。<br />
  如下是不用指定特定的的类型与参数的位置：<br />

  ```java 
  package x.y;
    public class Foo {
    public Foo(Bar bar, Baz baz) {
      // ...
    }
  }
  ```

  ```xml
  <beans>
    <bean id="foo" class="x.y.Foo">
      <constructor-arg ref="bar"/>
      <constructor-arg ref="baz"/>
    </bean>
     <bean id="bar" class="x.y.Bar"/>
    <bean id="baz" class="x.y.Baz"/>
  </beans>
  ```
  
* 必须指定参数的情况，一种，用类型指定，一种用其在构造参数中的位置来指定，以及参数的名字来指定。<br/>
  形如如下：你可以同时指定 type 与index 或者name ,注意index 是从0开始的。<br/>
	请注意，在setter方法上使用@Required注释可以使属性成为必需的依赖关系。<br/>

  ```java
  package examples;
    public class ExampleBean {
    // Number of years to calculate the Ultimate Answer
    private int years;
    // The Answer to Life, the Universe, and Everything
    private String ultimateAnswer;
    public ExampleBean(int years, String ultimateAnswer) {
      this.years = years;
      this.ultimateAnswer = ultimateAnswer;
    }
  }
  ```

  ```xml
  <!-- 如下按照参数类型来指定。-->
	<bean id="exampleBean" class="examples.ExampleBean">
    <constructor-arg type="int" value="7500000"/>
     <constructor-arg type="java.lang.String" value="42"/>
  </bean>
  <!--或者形如如下，按照参数的位置来指定 -->
  <bean id="exampleBean" class="examples.ExampleBean">
    <constructor-arg index="0" value="7500000"/>
    <constructor-arg index="1" value="42"/>
  </bean>
	<!--如下，按照名字来指定 -->
  <bean id="exampleBean" class="examples.ExampleBean">
    <constructor-arg name="years" value="7500000"/>
    <constructor-arg name="ultimateAnswer" value="42"/>
  </bean>
  ```
	
* 注意形如如下的 @ConstructorProperties 的使用，

	```java
	//An annotation on a constructor that shows how the parameters 
	//of that constructor correspond to the constructed object's getter methods. For example:
	//The annotation shows that the first parameter of the constructor can be retrieved with 
	//the getX() method and the second with the getY() method. Since parameter names are not 
	//in general available at runtime, without the annotation there would be no way to know 
	//whether the parameters correspond to getX() and getY() or the other way around.
  public class Point {
     @ConstructorProperties({"x", "y"})
     public Point(int x, int y) {
         this.x = x;
         this.y = y;
     }
     public int getX() {
         return x;
     }
     public int getY() {
         return y;
     }
     private final int x, y;
 }
 ```

* Bean 的解析的过程，<br/>
  1，ApplicationContext根据Configuration metadata 配置元数据（可以来自xml ，anotation，或者java 代码），<br/>
  2，当一个Bean 被使用时，它的参数和属性等依赖关系，通过属性设置，构造参数设置，静态工厂方法设置，<br/>
  3，注意不要使用循环应用的Bean 形式，即A依赖B的实例，B依赖A的实例， 虽然这种情况下可以用setter 形式的依赖注入解决。<br/>
  
* Bean 基于Setter 方式的xml注例子：

  ```xml
  <bean id="exampleBean" class="examples.ExampleBean">
    <!-- setter injection using the nested ref element -->
    <property name="beanOne">
      <ref bean="anotherExampleBean"/>
    </property>
    <!-- setter injection using the neater ref attribute -->
    <property name="beanTwo" ref="yetAnotherBean"/>
    <property name="integerProperty" value="1"/>
  </bean>
  <bean id="anotherExampleBean" class="examples.AnotherBean"/>
  <bean id="yetAnotherBean" class="examples.YetAnotherBean"/>
  ```
  
  ```java
  public class ExampleBean {
    private AnotherBean beanOne;
    private YetAnotherBean beanTwo;
    private int i;
    public void setBeanOne(AnotherBean beanOne) {
      this.beanOne = beanOne;
    }
    public void setBeanTwo(YetAnotherBean beanTwo) {
      this.beanTwo = beanTwo;
    }
    public void setIntegerProperty(int i) {
      this.i = i;
    }
  }
  ```
  


*  Bean 基于构造函数依赖注入的例子。

  ```xml
  <bean id="exampleBean" class="examples.ExampleBean">
    <!-- constructor injection using the nested ref element -->
    <constructor-arg>
		<ref bean="anotherExampleBean"/>
		</constructor-arg>
		<!-- constructor injection using the neater ref attribute -->
		<constructor-arg ref="yetAnotherBean"/>
		<constructor-arg type="int" value="1"/>
  </bean>
  <bean id="anotherExampleBean" class="examples.AnotherBean"/>
  <bean id="yetAnotherBean" class="examples.YetAnotherBean"/>
  ```


  ```java
  public class ExampleBean {
	private AnotherBean beanOne;
	private YetAnotherBean beanTwo;
	private int i;
	public ExampleBean(AnotherBean anotherBean, YetAnotherBean yetAnotherBean, int i) {
		this.beanOne = anotherBean;
		this.beanTwo = yetAnotherBean;
		this.i = i;
	}
  }
  ```
  

* 使用静态工厂方法实例化一个bean

  ```xml
  <bean id="exampleBean" class="examples.ExampleBean" factory-method="createInstance">
    <constructor-arg ref="anotherExampleBean"/>
	<constructor-arg ref="yetAnotherBean"/>
	<constructor-arg value="1"/>
  </bean>
  <bean id="anotherExampleBean" class="examples.AnotherBean"/>
  <bean id="yetAnotherBean" class="examples.YetAnotherBean"/>
  ```



  ```java
  public class ExampleBean {
	// a private constructor
	private ExampleBean(...) {
		...
	}
	// a static factory method; the arguments to this method can be
	// considered the dependencies of the bean that is returned,
	// regardless of how those arguments are actually used.
	public static ExampleBean createInstance (
	AnotherBean anotherBean, YetAnotherBean yetAnotherBean, int i) {
		ExampleBean eb = new ExampleBean (...);
		// some other operations...
		return eb;
	}
  }
  ```


#### Bean依赖和详细配置
* 1，p-namespace p命名空间的使用 <br/>
  2，valus 标签的使用，一个很好的功能，<br/>
  3，idref 标签 <br/>
  4，ref标签 <br/>
  5，parent 标签 <br />
  6，内部bean,处于properties标签或者constructor-arg中，<br/>
  7，集合标签，包含list, set, map, and props <br/>
     对应java 的List, Set, Map, and Propertie<br />
  8，父与子Bean 集合或者属性的覆盖
  

  ```xml
  <!--不用命名空间的情况下-->
  <bean id="myDataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
    <!-- results in a setDriverClassName(String) call -->
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
	<property name="url" value="jdbc:mysql://localhost:3306/mydb"/>
	<property name="username" value="root"/>
	<property name="password" value="masterkaoli"/>
  </bean>
  
  <!-- 1，用命名空间 -->
  <beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:p="http://www.springframework.org/schema/p"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd">
	<bean id="myDataSource" class="org.apache.commons.dbcp.BasicDataSource"
		destroy-method="close"
		p:driverClassName="com.mysql.jdbc.Driver"
		p:url="jdbc:mysql://localhost:3306/mydb"
		p:username="root"
		p:password="masterkaoli"/>
  </beans>
  
  <!--,2，使用value 的例子 -->
  <bean id="mappings"
	class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
	<!-- typed as a java.util.Properties -->
	<property name="properties">
		<value>
			jdbc.driver.className=com.mysql.jdbc.Driver
			jdbc.url=jdbc:mysql://localhost:3306/mydb
		</value>
	</property>
  </bean>
  
  <!-- 3，idref 标签 推荐使用，可以有效的检测致命错误 -->
  <bean id="theTargetBean" class="..."/>
  <bean id="theClientBean" class="...">
	<property name="targetName">
		<idref bean="theTargetBean" />
	</property>
  </bean>
  <!-- 与如下等同 -->
  <bean id="theTargetBean" class="..." />
  <bean id="client" class="...">
	<property name="targetName" value="theTargetBean" />
  </bean>
  
  <!-- 4,ref 标签 bean 的值是Container 中bean 的id 或者name -->
  <ref bean="someBean"/>
  
  <!-- 5,parent 标签-->
  <!-- in the parent context -->
  <bean id="accountService" class="com.foo.SimpleAccountService">
	<!-- insert dependencies as required as here -->
  </bean>
  <!-- in the child (descendant) context -->
  <bean id="accountService" 
    class="org.springframework.aop.framework.ProxyFactoryBean"><!-- bean name is the same as
      the parent bean -->
    <property name="target">
      <ref parent="accountService"/> <!-- notice how we refer to the parent bean -->
    </property>
  <!-- insert other configuration and dependencies as required here -->
  </bean>
  
  <!-- 6,内部bean -->
  <bean id="outer" class="...">
    <!-- instead of using a reference to a target bean, simply define the target bean inline -->
	<property name="target">
		<bean class="com.example.Person"> <!-- this is the inner bean -->
			<property name="name" value="Fiona Apple"/>
			<property name="age" value="25"/>
		</bean>
	</property>
  </bean>
  
  <!-- 7,集合标签，
	The value of a map key or value, or a set value, can also again be
	any of the following elements:
	bean | ref | idref | list | set | map | props | value | null -->
  <bean id="moreComplexObject" class="example.ComplexObject">
	<!-- results in a setAdminEmails(java.util.Properties) call -->
	<property name="adminEmails">
		<props>
			<prop key="administrator">administrator@example.org</prop>
			<prop key="support">support@example.org</prop>
			<prop key="development">development@example.org</prop>
		</props>
	</property>
	<!-- results in a setSomeList(java.util.List) call -->
	<property name="someList">
		<list>
			<value>a list element followed by a reference</value>
			<ref bean="myDataSource" />
		</list>
	</property>
	<!-- results in a setSomeMap(java.util.Map) call -->
	<property name="someMap">
		<map>
			<entry key="an entry" value="just some string"/>
			<entry key ="a ref" value-ref="myDataSource"/>
		</map>
	</property>
	<!-- results in a setSomeSet(java.util.Set) call -->
	<property name="someSet">
		<set>
			<value>just some string</value>
			<ref bean="myDataSource" />
		</set>
	</property>
  </bean>
  
  <!-- 8，父与子bean 中的集合覆盖如下 -->
  <beans>
	<bean id="parent" abstract="true" class="example.ComplexObject">
		<property name="adminEmails">
			<props>
				<prop key="administrator">administrator@example.com</prop>
				<prop key="support">support@example.com</prop>
			</props>
		</property>
	</bean>
	<bean id="child" parent="parent">
		<property name="adminEmails">
			<!-- the merge is specified on the child collection definition -->
			<props merge="true">
				<prop key="sales">sales@example.com</prop>
				<prop key="support">support@example.co.uk</prop>
			</props>
		</property>
	</bean>
  <beans>
  ```
  
* 强类型转换
  

  ```java
  public class Foo {
    private Map<String, Float> accounts;
	public void setAccounts(Map<String, Float> accounts) {
		this.accounts = accounts;
	}
  }
  ```

  ```xml
  <beans>
    <bean id="foo" class="x.y.Foo">
		<property name="accounts">
			<map>
				<entry key="one" value="9.99"/>
				<entry key="two" value="2.75"/>
				<entry key="six" value="3.99"/>
			</map>
		</property>
	</bean>
  </beans>
  ```
  
* Null和空字符串


  ```
  <bean class="ExampleBean">
	<property name="email" value=""/>
  </bean>
  
  <bean class="ExampleBean">
    <property name="email">
		<null/>
    </property>
  </bean>
  ```  

* p命名空间

  ```xml
  <beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:p="http://www.springframework.org/schema/p"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd">
	<bean name="john-classic" class="com.example.Person">
		<property name="name" value="John Doe"/>
		<property name="spouse" ref="jane"/>
	</bean>
	<bean name="john-modern"
		class="com.example.Person"
		p:name="John Doe"
		p:spouse-ref="jane"/>
	<!-- spouse 指的是属性名 -->
	<bean name="jane" class="com.example.Person">
		<property name="name" value="Jane Doe"/>
	</bean>
  </beans>
  ```
  
* C命名空间 constructor-arg


  ```xml
  <beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:c="http://www.springframework.org/schema/c"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd">
	<bean id="bar" class="x.y.Bar"/>
	<bean id="baz" class="x.y.Baz"/>
	<!-- traditional declaration -->
	<bean id="foo" class="x.y.Foo">
		<constructor-arg ref="bar"/>
		<constructor-arg ref="baz"/>
		<constructor-arg value="foo@bar.com"/>
	</bean>
	<!-- c-namespace declaration -->
	<bean id="foo" class="x.y.Foo" c:bar-ref="bar" c:baz-ref="baz" c:email="foo@bar.com"/>
	<!-- c-namespace index declaration -->
	<bean id="foo" class="x.y.Foo" c:_0-ref="bar" c:_1-ref="baz"/>
  </beans>
  ```
  
* 复合属性的使用，可能会发生空指针异常


  ```xml
  <bean id="foo" class="foo.Bar">
	<property name="fred.bob.sammy" value="123" />
  </bean>
  ```
* depend-on
  在一个bean实例化前，显示的先实例化其他的Bean,
  

  ```xml
  <bean id="beanOne" class="ExampleBean" depends-on="manager,accountDao">
	<property name="manager" ref="manager" />
  </bean>
  <bean id="manager" class="ManagerBean" />
  <bean id="accountDao" class="x.y.jdbc.JdbcAccountDao" />
  ```
  

* bean 属性里的lazy-init 延迟加载，在bean 第一次调用时初始化，<br />
  而不是容器初始化时就初始化。<br />
  

  ```xml
  <bean id="lazy" class="com.foo.ExpensiveToCreateBean" lazy-init="true"/>
  <bean name="not.lazy" class="com.foo.AnotherBean"/>
  <beans default-lazy-init="true">
    <!-- no beans will be pre-instantiated... -->
  </beans>
  ```
  
* 自动装载Autowiring


 | Mode | Explanation| 
  | ------ | ------ |
  |no    | (Default) No autowiring. Bean references must be defined via a ref element. Changing the default setting is not recommended for larger deployments,because specifying collaborators explicitly gives greater control and clarity. To some extent, it documents the structure of a system. | 
  | byName  |Autowiring by property name. Spring looks for a bean with the same name as the property that needs to be autowired. For example, if a bean definition is set to autowire by name, and it contains a master property (that is, it has a setMaster(..) method), Spring looks for a bean definition named master, and uses it to set the property. |
  | byType | Allows a property to be autowired if exactly one bean of the property type exists in the container. If more than one exists, a fatal exception is thrown, which indicates that you may not use byType autowiring for that bean. If there are no matching beans, nothing happens; the property is not set. |
  | constructor  | Analogous to byType, but applies to constructor arguments. If there is not exactly one bean of the constructor argument type in the container, a fatal error is raised.|
  
  
  关掉自动装载，在bean 的属性中autowire-candidate设置为false， 或者@Autowared=false
 
* 单例的类调用非单例的bean，在container 中的实现。
  参考参考文档，此处忽略
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
