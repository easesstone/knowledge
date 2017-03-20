```java

root@master:/home/java/FirstApp# cat FirstApp.java 
public class FirstApp{
    public static  void main(String []args){
        System.out.println("hello.world!");
    }
}
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
可以手动的进行编辑，当然，也可以用下面的maven 命名快速生成一个标准的java 简单项目。
mvn archetype:generate -DgroupId=com.sydney.dream -DartifactId=FirstApp -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false  -Dversion=1.0  -DarchetypeCatalog=internal

```
