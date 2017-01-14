# Spring Framework Reference Document
## Park III, Core technologies
* Chapter 7, The IoC Container
* Chapter 8, Resource
* Chapter 9, Validation, Data Binding, and Type Conversion
* Chapter 10, Spring Expression Language (SpEL)
* Chapter 11, Aspect Oriented Programming with Spring
* Chapter 12, Spring AOP APIs

### 7, The IoC Container
#### 7.1, Introduction to  the Spring Ioc Container  and Beans 
> 本章介绍 Spring Framework (Ioc) 实现反转控制的原理， IoC 也被称为依赖注入 （DI），它是一个过程，即对象自定义它们自己的依赖关系，
> 也就是说，它们使用的其他对象，是通过构造函数构造和工厂方法返回的对象，这些对象的参数的设置，一个是，通过构造函数的参数进行设置，另
> 一个是，通过工厂方法的参数进行设置，或者，通过通过对象实例的属性进行设置。而当对象创建的时候，有一个容器管理这些对象的依赖关系。这
> 样一个过程从根本上说是相反的，因此被命名为反转控制。bean 本身通过直接的类的构造函数或者某种形如服务定位模式的机制，来控制他们的实
> 例化或者依赖的位置。
