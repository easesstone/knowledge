
### 例子：
注意java 中默认引入java.lang 下面所有的类。
root@master:/home/java/FirstApp# cat FirstApp.java 
```java
public class FirstApp{
    public static  void main(String []args){
        System.out.println("hello.world!");
    }
}
```
```
root@master:/home/java/FirstApp# javac FirstApp.java 
root@master:/home/java/FirstApp# ll
total 16
drwxr-xr-x 2 root root 4096 Mar 20 08:23 ./
drwxr-xr-x 3 root root 4096 Mar 20 08:17 ../
-rw-r--r-- 1 root root  422 Mar 20 08:23 FirstApp.class
-rw-r--r-- 1 root root  120 Mar 20 08:23 FirstApp.java
root@master:/home/java/FirstApp# java FirstApp 
hello.world!
root@master:/home/java/FirstApp# 
```


### 扩展：


```
可以手动的进行编辑，当然，也可以用下面的maven 命名快速生成一个标准的java 简单项目。
mvn archetype:generate -DgroupId=com.sydney.dream -DartifactId=FirstApp -DarchetypeArtifactId=
maven-archetype-quickstart -DinteractiveMode=false  -Dversion=1.0  -DarchetypeCatalog=internal

pom.xml 稍作修改如下：
```
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.sydney.dream</groupId>
  <artifactId>FirstApp</artifactId>
  <packaging>jar</packaging>
  <version>1.0</version>
  <name>FirstApp</name>
  <url>http://maven.apache.org</url>
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
  <build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-jar-plugin</artifactId>
            <configuration>
                <archive>
                    <manifest>
                        <addClasspath>true</addClasspath>
                        <mainClass>com.sydney.dream.App</mainClass>
                    </manifest>
                </archive>
            </configuration>
        </plugin>
    </plugins>
  </build>
</project>
```

```
SHA1000030905:/home/ldl/FirstApp # ll src/main/java/com/sydney/dream/App.java 
-rw-r--r-- 1 root root 179 Mar 21 17:36 src/main/java/com/sydney/dream/App.java
SHA1000030905:/home/ldl/FirstApp # ll src/
main/ test/ 
SHA1000030905:/home/ldl/FirstApp # ll src/
main/ test/ 
SHA1000030905:/home/ldl/FirstApp # ll src/test/java/com/sydney/dream/AppTest.java 
-rw-r--r-- 1 root root 644 Mar 21 17:36 src/test/java/com/sydney/dream/AppTest.java
SHA1000030905:/home/ldl/FirstApp # cat src/
main/ test/ 
SHA1000030905:/home/ldl/FirstApp # cat src/
main/ test/ 
SHA1000030905:/home/ldl/FirstApp # cat src/main/java/com/sydney/dream/App.java 
```

```java
package com.sydney.dream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}

```


SHA1000030905:/home/ldl/FirstApp # cat src/test/java/com/sydney/dream/AppTest.java 
```java
package com.sydney.dream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
```

```
SHA1000030905:/home/ldl/FirstApp # cd target/
SHA1000030905:/home/ldl/FirstApp/target # ls
FirstApp-1.0.jar  classes  maven-archiver  maven-status  surefire-reports  test  test-classes
SHA1000030905:/home/ldl/FirstApp/target # java -jar FirstApp-1.0.jar 
Hello World!
SHA1000030905:/home/ldl/FirstApp/target # 
```
