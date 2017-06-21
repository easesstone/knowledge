# Hbase 集群搭建，（基于HDFS，内置ZOOKEEPER）
## 搭建Hadoop 集群。
### 环境准备
```
1，Linux 虚拟机三台。
2，集群时间同步，关闭swap，关闭防火墙。
3，配置互信。
4，ip映射。（注意Ubuntu 环境下，把127.0.1.1 ubuntu  改成127.0.0.1 ubuntu）
5，安装jdk （每台）
6，在其中一台机器上下载hadoop tar 包
7，解压，配置etc/conf 下的五个文件。
core-site.xml
hdfs-site.xml
yarn-site.xml
mapred-site.xml
slaves
hadoop-env.sh
8，把配置好的文件的整个hadoop 目录，拷贝到其他两个机器上。
9，到hadoop 的sbin 目录下，格式化namenode（系统格式化hadoop namenode -format），
启动HDFS。 （./start-dfs.sh）
```
### core-site.xml
```xml
<configuration>
    <property>
        <name>hadoop.tmp.dir</name>
        <value>file:/home/service/hadoop/tmp</value>
        <description>Abase for other temporary directories.</description>
    </property>
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://master:9000</value>
    </property>
</configuration>
```
### hdfs-site.xml 
```xml
<configuration>
    <property>
        <name>dfs.namenode.secondary.http-address</name>
        <value>slave1:50090</value>
    </property>
    <property>
        <name>dfs.replication</name>
        <value>3</value>
    </property>
    <property>
        <name>dfs.namenode.name.dir</name>
        <value>file:/home/service/hadoop/tmp/dfs/name</value>
    </property>
    <property>
        <name>dfs.datanode.data.dir</name>
        <value>/home/service/hadoop/tmp/dfs/data-slave1,/home/service/hadoop/tmp/dfs/data-slave2,
        /home/service/hadoop/tmp/dfs/data-slave3</value>
    </property>
</configuration>
```
### mapred-site.xml
```xml
<configuration>
    <property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>
</configuration>
```

### yarn-site.xml
```xml
<configuration>
    <property>
        <name>yarn.resourcemanager.hostname</name>
        <value>master</value>
    </property>
    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>
</configuration>
```
### slaves
```
root@master:/home/DataSight/hadoop/etc/hadoop# cat slaves 
slave1
slave2
```

### hadoop-env.sh
```
把其中的JAVE_HOME 写成你机器对应的jdk具体的绝对路径。
```

## 配置启动Hbase 集群
```
1，下载Hbase tar 包。
2，解压，配置里面的 hbase-site.xml，regionservers 
3，到hbase的bin 目录下，启动hbase 集群。（./start-hbase.sh） 
```

### hbase-site.xml 
```xml 
<configuration>
  <property>
    <name>hbase.rootdir</name>
    <value>hdfs://master:9000/hbase</value> <!--此处与hdfs 中core 中设置的地址相类似 -->
  </property>
  <property>
    <name>hbase.cluster.distributed</name>
    <value>true</value>
  </property>
  <property>
    <name>hbase.zookeeper.property.clientPort</name>
    <value>2181</value>
  </property>
  <property>
    <name>hbase.zookeeper.quorum</name>
    <value>master,slave1,slave2</value>
  </property>
  <property>
    <name>hbase.zookeeper.property.dataDir</name>
    <value>/home/service/hbase/zk/data</value>
  </property>
  <property>
    <name>dfs.replication</name>
    <value>3</value>
  </property>
  <!-- 新增的配置 -->
  <property>
    <name>hbase.master.info.port</name>
    <value>60010</value>
  </property>
  <!-- 新增的配置 -->  
</configuration>
```
### regionservers
```
root@master:/home/DataSight/hadoop/etc/hadoop# cat slaves 
slave1
slave2
```

## 集群健康状态检查
```
root@master:/home/DataSight/hbase/conf# checkJps 
---------------master-----------------------
     1	15363 HMaster
     2	15300 HQuorumPeer
     3	7894 ResourceManager
     4	7625 NameNode
--------------slave1-------------------------
     1	1029 SecondaryNameNode
     2	1175 NodeManager
     3	4617 HQuorumPeer
     4	954 DataNode
     5	4700 HRegionServer
---------------slave2-----------------------
     1	3973 HQuorumPeer
     2	1063 NodeManager
     3	4056 HRegionServer
     4	922 DataNode
```
