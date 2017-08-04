```
 1 java.lang.UnsupportedClassVersionError: org/apache/lucene/store/Directory :
 Unsupported major.minor version 51.0
 2  at java.lang.ClassLoader.defineClass1(Native Method)
 3  at java.lang.ClassLoader.defineClassCond(ClassLoader.java:631)
 4  at java.lang.ClassLoader.defineClass(ClassLoader.java:615)
 5  at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:141)
 6  at java.net.URLClassLoader.defineClass(URLClassLoader.java:283)
 7  at java.net.URLClassLoader.access$000(URLClassLoader.java:58)
 8  at java.net.URLClassLoader$1.run(URLClassLoader.java:197)
 9  at java.security.AccessController.doPrivileged(Native Method)
10  at java.net.URLClassLoader.findClass(URLClassLoader.java:190)
11  at java.lang.ClassLoader.loadClass(ClassLoader.java:306)
12  at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:301)
13  at java.lang.ClassLoader.loadClass(ClassLoader.java:247)
14 Exception in thread "main" 
复制代码
　　看报错信息，是因为使用的jar包版本不对。这个51.0是什么呢？
　　JDK不同的版本，编译出的class文件是不同的。通过查看分析class文件前几个字节，
  可以找到对应的关系，详细的参考官方，细微的版本之间可能有差异：

复制代码
1 J2SE 7 = 51 (0x33 hex),
2 J2SE 6.0 = 50 (0x32 hex),
3 J2SE 5.0 = 49 (0x31 hex),
4 JDK 1.4 = 48 (0x30 hex),
5 JDK 1.3 = 47 (0x2F hex),
6 JDK 1.2 = 46 (0x2E hex),
7 JDK 1.1 = 45 (0x2D hex).

```
