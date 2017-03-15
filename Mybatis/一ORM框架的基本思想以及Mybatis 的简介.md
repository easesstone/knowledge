```
一，
众多的orm 框架中，比如hibernate,ibatis,mybatis,jpa等。
整个处理流程大致如下：
1，从xml 读取配置，获取sessionfactory，
2，从sessionfactory 中生成个session。
3，基于session 对数据库的数据进行增删改查，进行事务管理。
4，进行session 的关闭。
(另外，一些框架，还支持注解方式的配置，当然也支持纯java 代码的配置)
二，
mybatis 简介
mybatis 支持普通sql 查询，支持存储过程，支持高级映射，是一个优秀的持久化框架。
优点：
1，分离sql 语句和java 代码，分离sql 的传值设置。
2，灵活性强。
3，轻量级，容易上手。
4，编写定制化sql 比较容易。
缺点：
1，依赖数据库，
2，编写sql 语句比较麻烦，尤其当查询的数据段比较多的时候
```
