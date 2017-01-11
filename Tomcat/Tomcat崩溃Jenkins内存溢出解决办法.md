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
      
    3 ) 以下，解析各个参数的含义，
      各个参数的配置，在不同的环境中可以进行适当的调整。
      1，-server  一定要作为第一个参数，在多个cpu 的时候性能佳。
      因为tomcat默认是以一种叫java –client的模式来运行的，server即意味着你的tomcat是以真实的production的模式在运行的，
      这也就意味着你的tomcat以 server模式运行时将拥有：更大、更高的并发处理能力，更快更强捷的JVM垃圾回收机制，
      可以获得更多的负载与吞吐量。。
      2，-Xms -Xms
      把Xms与Xmx两个值设成一样是最优的做法,
      用java -Xms1500m -version 测试最大可用内存。
      3，–Xmn
      设置年轻代大小为512m。整个堆大小=年轻代大小 + 年老代大小 + 持久代大小。持久代一般固定大小为64m，所以增大年轻代后，将会减小年老代大小。
      此值对系统性能影响较大，Sun官方推荐配置为整个堆的3/8。
      4，-Xss
      是指设定每个线程的堆栈大小。这个就要依据你的程序，看一个线程 大约需要占用多少内存，可能会有多少线程同时运行等。一般不易设置超过1M，
      要不然容易出现out ofmemory
      5，-XX:+UseBiasedLocking
      启用一个优化了的线程锁，我们知道在我们的appserver，每个http请求就是一个线程，有的请求短有的请求长，就会有请求排队的现象，
      甚至还会出现线程阻塞，这个优化了的线程锁使得你的appserver内对线程处理自动进行最优调配。
```
