#### Jenkins内存溢出Tomcat崩溃解决办法
```
    参考：http://www.cnblogs.com/larryzhang/archive/2012/07/03/2574781.html
    1 ) 在tomact的bin目录下在catalina.sh文件
      export CATALINA_OPTS="-server -Xms4G -Xmx8G -XX:NewSize=256M -XX:MaxNewSize=512M -Xss1024K -XX:PermSize=1G -XX:MaxPermSize=1G"
      或者
      export JAVA_OPTS="-server -Xms4G -Xmx8G -XX:NewSize=256M -XX:MaxNewSize=512M -Xss1024K -XX:PermSize=1G -XX:MaxPermSize=1G"
      或者
      export JAVA_OPTS="-server -Xms1400M -Xmx1400M -Xss512k -XX:+AggressiveOpts -XX:+UseBiasedLocking -XX:PermSize=128M 
      -XX:MaxPermSize=256M  -XX:+DisableExplicitGC -XX:MaxTenuringThreshold=31 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC  
      -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m  
      -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -Djava.awt.headless=true "
    2 ) 在windows 下的catalina.bat 
      set JAVA_OPTS="-server -Xms256m -Xmx512m -XX:NewSize=256M -XX:MaxNewSize=512M -Xss1024K -XX:PermSize=1G -XX:MaxPermSize=1G"
      
    3 ) 一下，解析各个参数的含义，
      各个参数的配置，在不同的环境中可以进行适当的调整。
      1，-server  一定要作为第一个参数，在多个cpu 的时候性能佳。
      因为tomcat默认是以一种叫java –client的模式来运行的，server即意味着你的tomcat是以真实的production的模式在运行的，
      这也就意味着你的tomcat以 server模式运行时将拥有：更大、更高的并发处理能力，更快更强捷的JVM垃圾回收机制，
      可以获得更多的负载与吞吐量。。
      2，-Xms -Xms
      把Xms与Xmx两个值设成一样是最优的做法,
```
