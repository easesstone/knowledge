### 1,集群环境准备
```
准备jdk 环境，本文用的版本是，java version "1.8.0_102"
为机器准备ssh 客户端和服务端，ubuntu 环境下默认安装了客户端，那么，请安装服务端，
apt-get install openssh-server
可以的话尽量不要用root 用户操作，可以用hadoop 这个用户：sudo useradd -m hadoop -s /bin/bash
```
### 2，下载hadoop 官网的tar包。
```
本文用的是，hadoop-2.7.2，因为笔者所在的公司的Spark 依赖的也是这个版本。
下载可以从官网下载，也可以从以下网址下载。
http://mirror.bit.edu.cn/apache/hadoop/common/hadoop-2.7.2/
```
### 3，解压hadoop 的tar 包到一个指定的目录。
```
笔者解压后的位置是，/home/datasight/hadoop-2.7.2，
解压之后，可以用如下的命令简单查看hadoop 的版本。
./bin/hadoop version
```

### 4,单机模式下的例子
```
在hadoop 的跟目录下面，创建input 目录，mkdir input
之后，
cp ./etc/hadoop/*.xml ./input
./bin/hadoop jar ./share/hadoop/mapreduce/hadoop-mapreduce-examples-*.jar grep ./input ./output 'dfs[a-z.]+'
cat ./output/* 
通过命令 cat ./output/* 查看结果，符合正则的单词 dfsadmin 出现了1次
若要再次运行，需要先做如下操作
rm -r ./output
```

### 5，hadoop 伪分布式的配置。
```
Hadoop 可以在单节点上以伪分布式的方式运行，Hadoop 进程以分离的 Java 进程来运行，节点既作为 NameNode 也作为 DataNode，同时，读取的是 HDFS 中的文件。
设置hadoop 的环境变量。
# Hadoop Environment Variables
export HADOOP_HOME=/home/datasight/hadoop-2.7.2
export HADOOP_INSTALL=$HADOOP_HOME
export HADOOP_MAPRED_HOME=$HADOOP_HOME
export HADOOP_COMMON_HOME=$HADOOP_HOME
export HADOOP_HDFS_HOME=$HADOOP_HOME
export YARN_HOME=$HADOOP_HOME
export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_HOME/lib/native
export PATH=$PATH:$HADOOP_HOME/sbin:$HADOOP_HOME/bin

Hadoop 的配置文件位于 /usr/local/hadoop/etc/hadoop/ 中，伪分布式需要修改2个配置文件 core-site.xml 和 hdfs-site.xml 。
Hadoop的配置文件是 xml 格式，每个配置以声明 property 的 name 和 value 的方式来实现。

修改配置文件 core-site.xml 如下：
<configuration>
    <property>
        <name>hadoop.tmp.dir</name>
        <value>file:/home/datasight/hadoop-2.7.2/tmp</value>
        <description>Abase for other temporary directories.</description>
    </property>
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://localhost:9000</value>
    </property>
</configuration>

修改配置文件 hdfs-site.xml
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>1</value>
    </property>
    <property>
        <name>dfs.namenode.name.dir</name>
        <value>file:/usr/local/hadoop/tmp/dfs/name</value>
    </property>
    <property>
        <name>dfs.datanode.data.dir</name>
        <value>file:/usr/local/hadoop/tmp/dfs/data</value>
    </property>
</configuration>

配置完成后，执行 NameNode 的格式化:
成功的话，会看到 "successfully formatted" 和 "Exitting with status 0" 的提示，若为 "Exitting with status 1" 则是出错
./bin/hdfs namenode -format
重跑这个命令时会提示输入Y
Re-format filesystem in Storage Directory /home/datasight/hadoop-2.7.2/tmp/dfs/name ? (Y or N) Y
16/12/29 07:38:38 INFO namenode.FSImage: Allocated new BlockPoolId: BP-1748782028-192.168.74.131-1483025918286
16/12/29 07:38:38 INFO common.Storage: Storage directory /home/datasight/hadoop-2.7.2/tmp/dfs/name has been successfully formatted.
16/12/29 07:38:39 INFO namenode.NNStorageRetentionManager: Going to retain 1 images with txid >= 0
16/12/29 07:38:39 INFO util.ExitUtil: Exiting with status 0
16/12/29 07:38:39 INFO namenode.NameNode: SHUTDOWN_MSG: 
/************************************************************
SHUTDOWN_MSG: Shutting down NameNode at master/192.168.74.131
************************************************************/



接着开启 NaneNode 和 DataNode 守护进程：
./sbin/start-dfs.sh
若提示找不到java 安装路径，则要配置./etc/hadoop/hadoop-env.sh中的JAVA_HOME 为如下。
export JAVA_HOME=/home/tool/jdk1.8.0_102


通过查看启动日志分析启动失败原因
有时 Hadoop 无法正确启动，如 NameNode 进程没有顺利启动，这时可以查看启动日志来排查原因，注意几点：

启动时会提示形如 "dblab: starting namenode, logging to /usr/local/hadoop/logs/hadoop-hadoop-namenode-dblab.out"，其中 dblab 对应你的主机名，但启动的日志信息是记录在 /usr/local/hadoop/logs/hadoop-hadoop-namenode-dblab.log 中，所以应该查看这个后缀为 .log 的文件；
每一次的启动日志都是追加在日志文件之后，所以得拉到最后面看，看下记录的时间就知道了。
一般出错的提示在最后面，也就是写着 Fatal、Error 或者 Java Exception 的地方。
可以在网上搜索一下出错信息，看能否找到一些相关的解决方法。

可以通过sbin/stop-dfs.sh  停止进程。
```
#### YARN
```
有的读者可能会疑惑，怎么启动 Hadoop 后，见不到书上所说的 JobTracker 和 TaskTracker，这是因为新版的 Hadoop 使用了新的 MapReduce 框架（MapReduce V2，也称为 YARN，Yet Another Resource Negotiator）。
YARN 是从 MapReduce 中分离出来的，负责资源管理与任务调度。YARN 运行于 MapReduce 之上，提供了高可用性、高扩展性，YARN 的更多介绍在此不展开，有兴趣的可查阅相关资料。
上述通过 ./sbin/start-dfs.sh 启动 Hadoop，仅仅是启动了 MapReduce 环境，我们可以启动 YARN ，让 YARN 来负责资源管理与任务调度。

首先修改配置文件 mapred-site.xml，这边需要先进行重命名
mv ./etc/hadoop/mapred-site.xml.template ./etc/hadoop/mapred-site.xml
然后再进行编辑，同样使用 gedit 编辑会比较方便些 gedit ./etc/hadoop/mapred-site.xml

<configuration>
    <property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>
</configuration>

接着修改配置文件 yarn-site.xml
<configuration>
    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
        </property>
</configuration>

./sbin/start-yarn.sh      # 启动YARN
./sbin/mr-jobhistory-daemon.sh start historyserver  # 开启历史服务器，才能在Web中查看任务运行情况
开启后通过 jps 查看，可以看到多了 NodeManager 和 ResourceManager 两个后台进程，如下图所示
```
