# Hbase 集群搭建，（基于HDFS，内置ZOOKEEPER）
## 搭建Hadoop 集群。
### 环境准备
```
1，Linux 虚拟机三台。
2，集群时间同步，关闭swap，关闭防火墙。
3，配置互信。
4，ip映射。
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
