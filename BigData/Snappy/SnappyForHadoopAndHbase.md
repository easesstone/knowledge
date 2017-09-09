```
因为业务需要所以对hbase的数据做整体的压缩处理。
 
首先需要在hadoop集群里安装snappy成功。以下介绍如何在hadoop中部署snappy
第一步骤：
下载snappy
网页地址：http://code.google.com/p/snappy/downloads/detail?name=snappy-1.1.1.tar.gz&can=2&q=
在linux下面可以直接执行
wget http://snappy.googlecode.com/files/snappy-1.1.1.tar.gz
即可。
然后解压后，执行三步骤：
./configure
make
make install
我采用的是默认的安装，路径：/usr/local/lib下面
 
第二步骤：
下载hadoop-snappy源码。
打开网址：http://code.google.com/p/hadoop-snappy/
运行命令
svn checkout http://hadoop-snappy.googlecode.com/svn/trunk/ hadoop-snappy-read-only
首先保证有svn的命令
下载源码后，
cd hadoop-snappy-read-only
mvn package
我的运气很好，一次运行就成功了。
在网上有很多人说编译源码无法成功，可以参考其他人的文档，看看相关的其他命令是否安装，如下：
截取来自官方文档：
Requirements: gcc c++, autoconf, automake, libtool, Java 6, JAVA_HOME set, Maven 3
我在安装的过程中，并没有出现网上其他人所说的gcc版本要用4.4
我的机器版本如下：
[hadoop@master hadoop-snappy-read-only]$ gcc --version
gcc (GCC) 4.1.2 20080704 (Red Hat 4.1.2-54)
Copyright (C) 2006 Free Software Foundation, Inc.
This is free software; see the source for copying conditions.  There is NO
warranty; not even for MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
[hadoop@master hadoop-snappy-read-only]$
是4.1.2的版本，比4.4低。也许是高版本的不支持吧。
 
编译成功后，在target目录下，就会有相关的文件如下：
[hadoop@master hadoop-snappy-read-only]$ cd target/
[hadoop@master target]$ ls -lh
total 154M
drwxr-xr-x 2 root root 4.0K Nov  5 18:30 antrun
drwxr-xr-x 2 root root 4.0K Nov  5 18:31 archive-tmp
drwxr-xr-x 3 root root 4.0K Nov  5 18:30 classes
-rw-r--r-- 1 root root  194 Nov  5 18:31 copynativelibs.sh
drwxr-xr-x 4 root root 4.0K Nov  5 18:31 generated-sources
-rw-r--r-- 1 root root  12K Nov  5 18:31 hadoop-snappy-0.0.1-SNAPSHOT.jar
-rw-r--r-- 1 root root 118M Nov  5 18:32 hadoop-snappy-0.0.1-SNAPSHOT-Linux-amd64-64.tar
drwxr-xr-x 3 root root 4.0K Nov  5 18:31 hadoop-snappy-0.0.1-SNAPSHOT-tar
-rw-r--r-- 1 root root  36M Nov  5 18:32 hadoop-snappy-0.0.1-SNAPSHOT.tar.gz
drwxr-xr-x 2 root root 4.0K Nov  5 18:31 maven-archiver
drwxr-xr-x 3 root root 4.0K Nov  5 18:31 native-build
drwxr-xr-x 8 root root 4.0K Nov  5 18:31 native-src
drwxr-xr-x 2 root root 4.0K Nov  5 18:31 surefire-reports
drwxr-xr-x 3 root root 4.0K Nov  5 18:31 test-classes
-rw-r--r-- 1 root root 245K Nov  5 18:31 test.txt.snappy
[hadoop@master target]$
 
 第三步骤：
就是如何在hadooo中部署snappy
解压hadoop-snappy-0.0.1-SNAPSHOT.tar.gz文件，会生成hadoop-snappy-0.0.1-SNAPSHOT目录，
拷贝这个目录下相关文件到$HADOOP_HOME/lib/native/Linux-amd64-64
cp -r /hadoop-snappy-0.0.1-SNAPSHOT/lib/native/Linux-amd64-64/* $HADOOP_HOME/lib/native/Linux-amd64-64
将target目录下的hadoop-snappy-0.0.1-SNAPSHOT.jar拷贝到$HADOOP_HOME/lib/目录下。
修改三个文件：
hadoop-env.sh，增加内容如下：
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:$HADOOP_HOME/lib/native/Linux-amd64-64/:/usr/local/lib/
修改core-site.xml文件，增加红色字体部分
<property>
<name>io./ompression.codecs</name>  
<value>org.apache.hadoop.io.compress.DefaultCodec,org.apache.hadoop.io.compress.GzipCodec
,org.apache.hadoop.io.compress.BZip2Codec,org.apache.hadoop.io.compress.SnappyCodec</value>
</property>
修改mapred-site.xml文件
<property>
<name>mapred.output.compress</name>
 <value>true</value>
</property>
<property>
<name>mapred.output.compression.codec</name>
<value>org.apache.hadoop.io.compress.SnappyCodec</value>
</property>
<property>
<name>mapred.compress.map.output</name>
<value>true</value>
 </property>
<property>
 <name>mapred.map.output.compression.codec</name>
 <value>org.apache.hadoop.io.compress.SnappyCodec</value>
</property>
调整好后，重启hadoop集群即可，以上就是在hadoop集群里部署完成。
 
目前压缩效果，我会在hive中进行相关的测试。
参考文档《hive各种文件格式与压缩方式的结合测试》
然后再往HBase中使用压缩方式
当hadoop的snappy配置成功后，配置hbase就很简单了，两个步骤：
第一步骤复制相关jar包
cp -r $HADOOP_HOME/lib/native/Linux-amd64-64/* $HBASE_HOME/lib/native/Linux-amd64-64/*
这里需要注意下，有些版本在安装过程中，没有这个Linux-amd64-64这个目录，需要手工创建下。
第二步骤配置hbase-env.sh环境变量
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:$HADOOP_HOME/lib/native/Linux-amd64-64/:/usr/local/lib/
export HBASE_LIBRARY_PATH=$HBASE_LIBRARY_PATH:$HBASE_HOME/lib/native/Linux-amd64-64/:/usr/local/lib/
 
两个步骤处理完成后，在hbase部署完成snappy后的测试结果：
[hadoop@master bin]$ hbase org.apache.hadoop.hbase.util.CompressionTest 
hdfs://master:8020/benchmarks/TestDFSIO/io_data/test_io_0 snappy    
13/11/11 11:57:18 INFO util.ChecksumType: org.apache.hadoop.util.PureJavaCrc32 not available.
13/11/11 11:57:18 INFO util.ChecksumType: Checksum can use java.util.zip.CRC32
13/11/11 11:57:18 INFO util.ChecksumType: org.apache.hadoop.util.PureJavaCrc32C not available. 
13/11/11 11:57:18 DEBUG util.FSUtils: Creating file:hdfs:
//master:8020/benchmarks/TestDFSIO/io_data/test_io_0with permission:rwxrwxrwx
13/11/11 11:57:19 WARN snappy.LoadSnappy: Snappy native library is available
13/11/11 11:57:19 INFO util.NativeCodeLoader: Loaded the native-hadoop library
13/11/11 11:57:19 INFO snappy.LoadSnappy: Snappy native library loaded
13/11/11 11:57:19 INFO compress.CodecPool: Got brand-new compressor
13/11/11 11:57:19 DEBUG hfile.HFileWriterV2: Initialized with CacheConfig:disabled
13/11/11 11:57:19 INFO compress.CodecPool: Got brand-new decompressor
SUCCESS
只要出现以上结果，就说明hbase可以使用snappy压缩模式了。
 
 ```
