### 1，安装mysql，可以用navicat 辅助，然后创建一个表格。并且往表格中插入三条数据。
```
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
INSERT INTO `user` VALUES ('1', 'xiaohong', '123456');
INSERT INTO `user` VALUES ('2', 'xiaohua', '123456');
INSERT INTO `user` VALUES ('3', 'xiaoniao', '123456');
```


### 2，建立一个maven 工程，pom文件大致如下：
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.sydney.dream</groupId>
  <artifactId>FirstApp</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>jar</packaging>

  <name>FirstApp</name>
  <url>http://maven.apache.org</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <mysql-connector-java.version>5.1.25</mysql-connector-java.version>
    <mybatis.version>3.2.7</mybatis.version>
    <junit.version>4.12</junit.version>
  </properties>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>${junit.version}</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>${mybatis.version}</version>
    </dependency>
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>${mysql-connector-java.version}</version>
    </dependency>
  </dependencies>
  <build>
    <resources>
      <resource>
        <directory>src/main/java</directory>
        <includes>
          <include>**/*.xml</include>
        </includes>
        <filtering>true</filtering>
      </resource>
      <resource>
        <directory>src/main/resources</directory>
        <includes>
          <include>*.xml</include>
          <include>*.properties</include>
        </includes>
      </resource>
    </resources>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>2.18.1</version>
        <configuration>
          <includes>
            <include>**/*Suite.java</include>
          </includes>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
```

### mybatis-config.xml 如下：
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC
        "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!-- 配置环境变量,数据库信息-->
    <properties resource="db.properties" />
    <typeAliases>
        <typeAlias type="com.sydney.dream.model.User" alias="User"/>
    </typeAliases>
    <environments  default="development">
        <environment id="development">
            <transactionManager type="JDBC" />
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.driverClassName}" />
                <property name="url" value="${jdbc.url}" />
                <property name="username" value="${jdbc.username}" />
                <property name="password" value="${jdbc.password}" />
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="com/sydney/dream/model/mapper/UserMapper.xml"/>
        <mapper resource="com/sydney/dream/model/mapper/IUserOperationMapper.xml"/>
        <mapper class="com.sydney.dream.Interface.IUserOperation01" />
    </mappers>
</configuration>
```


###  db.properties 如下。
```
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://100.106.43.30:3306/test?characterEncoding=UTF-8
jdbc.username=lidiliang
jdbc.password=lidiliang
```



###  添加用户类。User.java
```
package com.sydney.dream.model;

/**
 * Created by Sydney on 2016/11/19.
 */
public class User {
    private int id;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {}

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (((User) obj).getId() == this.getId() && ((User) obj).getPassword().equals(this.getPassword())
                && ((User) obj).getUsername().equals(this.getUsername()))
            return true;
        return false;
    }
}

```

### 添加Mapper.xml 
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.sydney.dream.User">
    <!-- 在select标签中编写查询的SQL语句， 设置select标签的id属性为getUser，id属性值必须是唯一的，不能够重复
    使用parameterType属性指明查询时使用的参数类型，resultType属性指明查询返回的结果集类型
    resultType="me.gacl.domain.User"就表示将查询结果封装成一个User类的对象返回
    User类就是users表所对应的实体类
    -->
    <resultMap id="resultUserArticleList" type="com.sydney.dream.model.Article">
        <id property="id" column="id"/>
        <result property="titel" column="titel" />
        <result property="content" column="content" />
        <association property="user" javaType="com.sydney.dream.model.User" >
            <id property="id" column="id" />
            <result property="name" column="name"/>
            <result property="age" column="age" />
        </association>
    </resultMap>
    <!--
    根据id查询得到一个user对象
    -->
    <select id="getUser" parameterType="int"
        resultType="com.sydney.dream.model.User">
        select * from user where id=#{id}
    </select>
</mapper>
```


### 最后添加测试类，测试部分代码大致如下。
```
public static User getUserById(int id){
        User user = new User();
        String statement = "com.sydney.dream.User.getUser";
        SqlSession session = null;
        try{
            session = sqlSessionFactory.openSession();
            user = session.selectOne(statement,1);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return user;
    }
```
