### 第三部分 核心技术
* IoC 容器
* 资源
* 验证器，数据绑定，类型转换
* SpEL
* Spring 面向切面编程
* AOP 的APIS

#### IoC 容器。
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
* Application有两个实现类，ClassPathXmlApplicationContext or FileSystemXmlApplicationContext。
