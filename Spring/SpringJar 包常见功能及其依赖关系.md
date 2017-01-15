```
1，spring-core.jar（必须）：这个jar 文件包含Spring 框架基本的核心工具类。Spring 其它组件要都要使用到这个包里的类，
是其它组件的基本核心，当然你也可以在自己的应用系统中使用这些工具类。 外部依赖Commons Logging， (Log4J)。,
2，spring-beans.jar（必须）：这 个jar 文件是所有应用都要用到的，它包含访问配置文件、创建和管理bean 以及进行Inversion
of Control / Dependency Injection（IoC/DI）操作相关的所有类。如果应用只需基本的IoC/DI 支持，引入spring-core.jar
及spring-beans.jar 文件就可以了。 
外部依赖spring-core，(CGLIB)。 
3，spring-aop.jar（必须）：这个jar 文件包含在应用中使用Spring 的AOP 特性时所需的类和源码级元数据支持。使用基于AOP
的Spring特性，如声明型事务管理（Declarative Transaction Management），也要在应用里包含这个jar包。 
外部依赖spring-core， (spring-beans，AOP Alliance， CGLIB，Commons Attributes)。 
4，spring-context.jar（必须）：这个jar 文件在基础IOC功能上为Spring 核心提供了大量扩展服务，此外还提供许多企业级服务
的支持，有邮件服务、任务调度、JNDI定位，EJB集成、远程访问、缓存以及多种视图层框架的支持。可以找到使用Spring 
ApplicationContext特性时所需的全部类，JDNI 所需的全部类，instrumentation组件以及校验Validation 方面的相关类。 
外部依赖spring-beans, (spring-aop)。
5，spring-jdbc.jar（必须） ：这个jar 文件包含对Spring 对JDBC 数据访问进行封装的所有类。 
外部依赖spring-beans，spring-dao。 ,
6，spring-web.jar（必须） ：这个jar 文件包含Web 应用开发时，用到Spring 框架时所需的核心类，包括自动载入Web Application
Context 特性的类、Struts 与JSF 集成类、文件上传的支持类、Filter 类和大量工具辅助类。 
外部依赖spring-context, Servlet API, (JSP API, JSTL, Commons FileUpload, COS)。 
7，spring-webmvc.jar ：这个jar 文件包含Spring MVC 框架相关的所有类。包含国际化、标签、Theme、视图展现的FreeMarker、
JasperReports、Tiles、Velocity、XSLT相关类。包括框架的Servlets，Web MVC框架，控制器和视图支持。当然，如果你的应用使用
了独立的MVC 框架，则无需这个JAR 文件里的任何类。 
外部依赖spring-web, (spring-support，Tiles，iText，POI)。 ,
8，spring-aspects.jar ：提供对AspectJ的支持，以便可以方便的将面向方面的功能集成进IDE中，比如Eclipse AJDT。 
9，spring-context-support.jar：Spring context的扩展支持，用于MVC方面。,
10，spring-expression.jar：Spring表达式语言。
11，spring-instrument.jar：Spring对服务器的代理接口,
12，spring-instrument-tomcat.jar：Spring对tomcat连接池的集成
13，spring-jms.jar：为简化jms api的使用而做的简单封装。 
外部依赖spring-beans，spring-dao，JMS API。 ,
14，spring-orm.jar：整合第三方的orm实现，如hibernate，ibatis，jdo以及spring 的jpa实现
15，spring-oxm.jar：Spring对于object/xml映射的支持，可以让JAVA与XML之间来回切换,
16，spring-messaging.jar：
17，spring-test.jar：对JUNIT等测试框架的简单封装,
18，spring-tx.jar：为JDBC、Hibernate、JDO、JPA等提供的一致的声明式和编程式事务管理。
19，spring-webmvc-portlet.jar：Spring MVC的增强,
20，spring-websocket.jar：

```
