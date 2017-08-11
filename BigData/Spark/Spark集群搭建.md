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
