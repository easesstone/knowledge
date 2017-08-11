## 一、环境准备
```
1.1, 准备3台机器
1.2, 同步时间
设置时区：
  执行tzselect命令查找适合于本地的时区
  执行cp /usr/share/zoneinfo/Aisa/Shanghai /etc/localtime
  修改日期：date –s 15/07/2015
  修改时间：date –s 16:18:52
  写入硬盘时间（hwclock -w）
1.3,关闭防火墙
  停止防火墙：service iptables stop
  启动防火墙：service iptables start
  但以上命令只会当次机器运行有效，机器重启又会无效，如需要，可以如下：
  chkconfig iptables on
  chkconfig iptables off
1.4， 配置/etc/hosts 映射：
  127.0.0.1 localhost
  #0:0:1 localhost 可以干掉
  {ip1} m1
  {ip2} m2
  {ip3} m3
1.5 配置SSH互信
  请参考网上的内容
1.6 安装jdk 
  不懂请参考网上，或者回家放牛。
```
## 二、 hadoop 集群配置。
```
2.1 下载hadoop tar 包，本文下载的是hadoop2.7.2 的版本。
2.2 解压hadoop tar 包，放到其中一个机器上，一般是master 机器。
2.3 配置如下文件：

A:
core-site.xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<!--
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License. See accompanying LICENSE file.
-->
<!-- Put site-specific property overrides in this file. -->
<configuration>
<property>
        <name>fs.defaultFS</name>
        <value>hdfs://172.18.18.106:9000</value>
    </property>
    <property>
        <name>hadoop.tmp.dir</name>
        <value>file:/home/hadoop/tmp</value>
    </property>
</configuration>


B:
hdfs-site.xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<!--
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License. See accompanying LICENSE file.
-->
<!-- Put site-specific property overrides in this file. -->
<configuration>
 <property>
        <name>dfs.namenode.name.dir</name>
        <value>file:/home/hadoop/hdfs/name</value>
    </property>
    <property>
        <name>dfs.datanode.data.dir</name>
        <value>file:/home/hadoop/hdfs/data</value>
    </property>
    <property>
        <name>dfs.replication</name>
        <value>2</value>
    </property>
    <property>
        <name>dfs.namenode.secondary.http-address</name>
        <value>172.18.18.106:9001</value>
    </property>
    <property>
    <name>dfs.webhdfs.enabled</name>
    <value>true</value>
    </property>
</configuration>

 
 C:
 mapred-site.xml
 <?xml version="1.0"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<!--
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License. See accompanying LICENSE file.
-->
<!-- Put site-specific property overrides in this file. -->
<configuration>
     <property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>
</configuration>


D:
yarn-site.xml
<?xml version="1.0"?>
<!--
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License. See accompanying LICENSE file.
-->
<configuration>
   <property>
        <name>yarn.resourcemanager.hostname</name>
        <value>172.18.18.106</value>
    </property>
    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>
    <property>  
        <name>yarn.nodemanager.pmem-check-enabled</name>  
        <value>false</value>  
    </property>  
  
    <property>  
        <name>yarn.nodemanager.vmem-check-enabled</name>  
        <value>false</value>  
    </property>  
</configuration>

E:
slaves
172.18.18.110
172.18.18.111

F:
hadoop-env.sh 中JAVA_HOME 中设置成具体的本地的jdk 安装局对路径：


2.4, 把配置好的hadoop 目录的所有内容，分发到另外另个节点同样的目录：

2.5 启动HDFS 和Yarn
在Hadoop 的bin 目录下：hadoop namenode -format
在Hadoop 的sbin 目录下：start-dfs.sh  && start-yarn.sh 
```

## 三，搭建Spark 集群：
```
1，下载Spark tar 包，
例如spark-bin-hadoop2-7.tar.gz 
注意和Hadoop 的版本对应：


2，配置文件：
如下：spark-env.sh 

export JAVA_HOME=/opt/tool/jdk
export SCALA_HOME=/opt/tool/scala
export HADOOP_HOME=/home/hadoop/hadoop-2.7.2
export HADOOP_CONF_DIR=/home/hadoop/hadoop-2.7.2/etc/hadoop
export YARN_CONF_DIR=$HADOOP_HOME/etc/Hadoop
export SPARK_MASTER_IP=172.18.18.106
export SPARK_MASTER_HOST=s106
export SPARK_LOCAL_IP=172.18.18.106
export SPARK_WORKER_MEMORY=5g
export SPARK_WORKER_CORES=2
export SPARK_HOME=/home/spark/spark
export SPARK_DIST_CLASSPATH=$(/home/hadoop/hadoop-2.7.2/bin/hadoop classpath)

slaves:
s110
s111

3,分发spark 目录：


4，到Spark 的sbin 下启动spark集群：
start-all.sh 

5, 可以看到进程类似如下：
jps
---------------------master-------------------
16866 Worker
5090 QuorumPeerMain
15363 ResourceManager
14964 NameNode
16650 Master
15179 SecondaryNameNode
22235 Jps
---------------------slave1------------------
18135 Jps
13257 DataNode
3818 QuorumPeerMain
13371 NodeManager
14638 Worker
--------------------slave2--------------------
13793 DataNode
13907 NodeManager
18855 Jps
15178 Worker
4059 QuorumPeerMain

```

四，参考说明
```
为了方便，一般每台机器上面都配置HADOOP 和Spark 环境变量，参考如下：

alias ...="cd ../.."
alias ..="cd ../"
export JAVA_HOME=/opt/tool/jdk
export ZOOKEEPER_HOME=/usr/local/zookeeper/zookeeper-3.5.1-alpha
export HADOOP_HOME=/home/hadoop/hadoop-2.7.2
export SPARK_HOME=/home/spark/spark
export HBASE_HOME=/home/hbase/hbase
export PATH=$HBASE_HOME/bin:$SPARK_HOME/bin:$JAVA_HOME/bin:$ZOOKEEPER_HOME/bin
:$HADOOP_HOME/bin:$HADOOP_HOME/sbin:$PATH:/home/ldl/util/
export HADOOP_CONF_DIR=/home/hadoop/hadoop-2.7.2/etc/hadoop
export YARN_CONF_DIR=$HADOOP_HOME/etc/Hadoop


一般的，当spark 版本没有相应的hadoop 的版本tar 包时，可以自己进行编译。
编译参考官网（下载相应分值） 然后替换相应的jar 包。比如：spark-1.5.1-bin-hadoop2.6.tgz 里面的jar 包
http://spark.apache.org/docs/latest/building-spark.html
Specifying the Hadoop Version and Enabling YARN
You can specify the exact version of Hadoop to compile against 
through the hadoop.version property. If unset, Spark will build against Hadoop 2.6.X by default.

You can enable the yarn profile and optionally set the yarn.version property 
if it is different from hadoop.version.

Examples:

# Apache Hadoop 2.6.X
./build/mvn -Pyarn -DskipTests clean package

# Apache Hadoop 2.7.X and later
./build/mvn -Pyarn -Phadoop-2.7 -Dhadoop.version=2.7.3 -DskipTests clean package






```
