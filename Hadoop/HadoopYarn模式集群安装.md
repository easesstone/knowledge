## Hadoop集群安装步骤

### 1.   Hadoop集群安装

#### 1.1             环境说明
```
集群：三台Linux机器（SUSE）；
JDK1.8（提前下载好对应的tar.gz）
Hadoop2.7.2（提前下载好对应的tar.gz）
以下所有配置需要在每个主机上进行，但按照本文配置，可配置一个以后复制过去，完全相同，不用修改。
```
#### 1.2             同步时间
```
集群上的机器需要进行时间同步，不然运行MR任务时会报错。一般集群机器不能联网，手动修改每台机器时间。
查看本机时间和时区：date
设置时区：
  执行tzselect命令查找适合于本地的时区
  执行cp /usr/share/zoneinfo/Aisa/Shanghai /etc/localtime
修改日期：date –s 15/07/2015
修改时间：date –s 16:18:52
写入硬盘时间（hwclock -w）
查看时区
cat /etc/sysconfig/clock
一些参考：
1.以root身份登录，通过执行`date`来检查您的机器当前正在使用的时区。你会看到像“Mon 17 Jan 2005 12:15:08 PM PST -0.461203秒”的东西，在这种情况下，PST是当前的时区。
2.将目录切换到/ usr / share / zoneinfo，在这里您可以找到时区区域的列表。选择最适当的地区，如果你住在加拿大或美国这个目录是“美洲”目录。
3.如果需要，请将以前的时区配置复制到其他位置。如`mv / etc / localtime / etc / localtime-old`。
4.创建从适当时区到/ etc / localtime的符号链接。示例：`ln -s / usr / share / zoneinfo / Europe / Amsterdam / etc / localtime`。
5.如果有实用程序rdate，请通过执行`/ usr / bin / rdate -s time.nist.gov`来更新当前系统时间。 （这一步可以跳过！）
6.在文件/ etc / sysconfig / clock文件中设置ZONE条目（例如“America / Los_Angeles”）
7.通过执行以下命令来设置硬件时钟：`/ sbin / hwclock --systohc`
```
#### 1.3             关闭防火墙
```
如果机器上正在运行防火墙，需要把它关上。
停止防火墙：service iptables stop
启动防火墙：service iptables start
但以上命令只会当次机器运行有效，机器重启又会无效，如需要，可以如下：
chkconfig iptables on
chkconfig iptables off

执行如下命令： sysctl vm.swappiness=0
把vm.swappiness = 0 写到/etc/sysctl.conf 文件末尾
```
#### 1.4             配置Hosts文件
```
首先，你要先给你的所有机器分配好IP和hostname，hadoop会根据主机名去/etc/hosts文件中查找对应的ip。
查看/修改当前机器的主机名
cat/vim /etc/HOSTNAME
如果修改了，通过如下命令使其立即生效
/etc/rc.d/boot.localnet start
在每台机器的/etc/hosts文件末尾加上下面三行(替换相应的ip和hostname，此处假设hostname分别为m1，m2，m3)：
{ip1} m1
{ip2} m2
{ip3} m3
```
#### 1.5             配置SSH互信
```
为了使集群之间无密码访问（为了以后集群通信时不用每次都输入密码），需要在机器之间配置互信（只要确保能从master无密码访问slave就好了）。
配置互信前请确保已经安装并启动了ssh服务。
生成密钥并配置ssh无密码登录主机(master主机)
ssh-keygen -t dsa -P '' -f ~/.ssh/id_dsa
cat ~/.ssh/id_dsa.pub >> ~/.ssh/authorized_keys
将authorized.keys文件拷贝到其他两台slave主机
scp authorized_keys m2:~/.ssh
scp authorized_keys m3:~/.ssh
验证是否可以从master无密码登录slave主机
ssh m2（在master主机输入）登录成功则配置成功，exit退出登录返回Master
```
#### 1.6             安装JDK和Hadoop
```
Hadoop是用java开发的，Hadoop的编译和MR的运行都需要使用JDK，所以JDK是必须安装的。
在安装目录下（如/usr/java）解压JDK（解压后可删除tar.gz以节省空间）
tar -zxvf java.tar.gz
在安装目录下(如/usr/hadoop)解压Hadoop文件（解压后可删除tar.gz以节省空间）
tar -zxvf hadoop.tar.gz
配置环境变量（末尾添加）
vim /etc/profile
export JAVA_HOME=/usr/java/jdk1.8.0_19
export CALSSPATH=.:$JAVA_HOME/lib/tools.jar
export HADOOP_HOME=/usr/hadoop
export PATH=$PATH:$JAVA_HOME/bin:$HADOOP_HOME/bin
使其立即生效
source /etc/profile
验证JDK是否成功
java -version
验证HADOOP是否成功
hadoop version
 ```
#### 1.7             修改Hadoop配置文件
```
配置文件都在${HADOOP_HOME}/etc/hadoop目录下。
修改slave文件
vim slave，写入ip或hostname
m1
m2
m3
修改hadoop-env.sh
该文件中有如下配置:export JAVA_HOME=${JAVA_HOME}，有时${JAVA_HOME}并不能生效，可选择性修改为对应的目录。
修改core-site.xml
<configuration>
    <property>
        <name>fs.default.name</name>
        <value>hdfs://m1:9000</value>
    </property>
    <property>
        <name>hadoop.tmp.dir</name>
        <value>/mnt/tmp</value>
    </property>
</configuration>
注释：
1.  fs.default.name：文件系统所在主机及端口号，Deprecated，use {fs.defaultFS} instead，当前配置依然生效，向前兼容；
2.  hadoop.tmp.dir：hadoop分布式文件系统中临时文件的根目录，设置好后，其他的重要目录都是在这个目录下面，默认是/tmp/hadoop-${username}。暂不支持配置多目录，不然会出错。
a)  如果hdfs-site.xml中不配置fs.name.dir和dfs.data.dir，则namenode和datanode数据默认就放在这个路径中，namenode的名字空间存放地方就是${hadoop.tmp.dir}/dfs/name，datanode数据库的存放地方就是${hadoop.tmp.dir}/dfs/data。
b)  如果mapred-site.xml不配置mapred.local.dir。map task中间结果写本地磁盘的路径默认值为${hadoop.tmp.dir}/mapred/local。可配置多块磁盘环节写压力。当存在多个时，hadoop采用轮询的方式将不同的map task中间结果写到磁盘。
修改hdfs-site.xml
<configuration>
    <property>
        <name>dfs.name.dir</name>
        <value>/usr/local/hadoop/name</value>
    </property>
    <property>
        <name>dfs.data.dir</name>
        <value>/mnt/m1/data,/mnt/m2/data,/mnt/m3/data</value>
    </property>
    <property>
         <name>dfs.replication</name>
         <value>3</value>
    </property>
</configuration>
注释：
1.  dfs.name.dir：这是NameNode节点存储hadoop文件系统信息(fsimage)的本地路径，可以配置多个路径，但这些目录汇总的文件是一样的（防止某个磁盘挂掉，做备份）；Deprecated，use {dfs.namenode.name.dir} instead，当前配置依然生效，向前兼容；
2.  dfs.data.dir：这是DataNode节点存储blocks的本地路径，支持多目录(逗号分隔)，如果有多磁盘最好每个磁盘都配置一个路径，这样hdfs会轮询在这些路径中写入数据。所以datanode在dfs.data.dir每个目录中存的数据是不一样的，这个和namenode不同；Deprecated，use {dfs.datanode.name.dir} instead，当前配置依然生效，向前兼容；
3.  dfs.replication：设置数据块的复制次数，默认是3。如果大于节点数，则每个节点中都会存一份备份，而不会超过节点数。
修改mapred-site.xml
将mapred-site.xml.template重命名为mapred-site.xml，然后修改。
<configuration>
    <property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>
 
    <!--默认值，不用配，为了说明用途-->
    <property>
        <name>mapreduce.jobhistory.address</name>
        <value>0.0.0.0:10020</value>
    </property>
    <property>
        <name>mapreduce.jobhistory.webapp.address</name>
        <value>0.0.0.0:19888</value>
    </property>
    <property>
        <name>mapreduce.jobhistory.admin.address</name>
        <value>0.0.0.0:10033</value>
    </property>
</configuration>
注释：
1.  mapreduce.framework.name：指使用哪种框架来运行任务，三个选项：classic，yarn，local，默认为local；classic：任务提交给JobTracker，它的地址通过{mapreduce.jobtracker.address}配置；yarn：任务提交给RM中的applications manager，它的地址通过{yarn.resourcemanager.address}配置(在yarn-site.xml中)。local：任务提交给本地JobTracker，即在本地使用MR，把{mapreduce.framework.name}和{mapreduce.jobtracker.address}都配置为local即可；
2.  为了方便用户查看历史作业信息，MRAppMaster提供了一个JobHistory-Server，该服务由四个子服务组成，其中除了负责扫描删除的服务，其他三个服务都是对外的，相关配置如下：
a)  mapreduce.jobhistory.webapp.address：Web UI访问地址，默认{0.0.0.0:19888}；
b)  mapreduce.jobhistory.admin.address：对外暴露的执行管理员命令的服务接口，通过执行bin/mapred hsadmin输入的命令，都是通过该接口提交执行的，默认{0.0.0.0:10033}；
c)   mapreduce.jobhistory.address：JobHistory服务负责从HDFS上读取MR历史作业日志，然后解析成格式化信息，供UI查看，该项即该服务对UI服务进程暴露的IPC接口，默认{0.0.0.0:10020}；
修改yarn-site.xml
<configuration>
    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>
    <property>
        <name>yarn.nodemanager.aux-services.mapreduce.shuffle.class</name>
        <value>org.apache.hadoop.mapred.ShuffleHandler</value>
    </property>
    <property>
        <name>yarn.nodemanager.address</name>
        <value>${yarn.nodemanager.hostname}:8041</value>
    </property>
    <property>
        <name>yarn.resourcemanager.hostname</name>
        <value>m1</value>
    </property>
 
    <!--有了yarn.resourcemanager.hostname配置后，以下可不用配置-->
    <property>
        <name>yarn.resourcemanager.scheduler.address</name>
        <value>m1:8030</value>
    </property>
    <property>
        <name>yarn.resourcemanager.resource-tracker.address</name>
        <value>m1:8031</value>
    </property>
    <property>
        <name>yarn.resourcemanager.address</name>
        <value>m1:8032</value>
    </property>
    <property>
        <name>yarn.resourcemanager.admin.address</name>
        <value>m1:8033</value>
    </property>
    <property>
        <name>yarn.resourcemanager.webapp.address</name>
        <value>m1:8088</value>
    </property>
</configuration>
注释：
1.  yarn.nodemanager.aux-services：NM上运行的附属服务。需配置成mapreduce_shuffle才能运行MR程序；
2.  yarn.nodemanager.aux-services.mapreduce.shuffle.class：顾名思义，默认配置为{org.apache.hadoop.mapred.ShuffleHandler}，所以如果没有自定义实现，可缺省；
3.  yarn.nodemanager.address：默认值为${yarn.nodemanager.hostname}:0，因为端口0会造成启动NM失败，所以需要改一下，同时为了在复制到slave节点时不用修改，所以写成${yarn.nodemanager.hostname}而不是固定的m1，${yarn.nodemanager.hostname}默认值为0.0.0.0。如果写成当前节点的IP m1，则每个节点都要修改。
4.  yarn.resourcemanager.hostname：RM所在节点的IP，默认0.0.0.0；
5.  yarn.resourcemanager.scheduler.address：RM对AM暴露的访问地址，AM通过该地址向RM申请资源、释放资源等，默认${yarn.resourcemanager.hostname}:8030；
6.  yarn.resourcemanager.resource-tracker.address：RM对NM暴露的访问地址，NM通过该地址向RM汇报心跳、领取任务等，默认${yarn.resourcemanager.hostname}:8031；
7.  yarn.resourcemanager.address：RM对客户端暴露的地址，客户端通过该地址向RM提交应用、杀死应用等，默认${yarn.resourcemanager.hostname}:8032；
8.  yarn.resourcemanager.admin.address：RM对管理员暴露的访问地址，管理员通过该地址向RM发送管理命令等，默认${yarn.resourcemanager.hostname}:8033；
9.  yarn.resourcemanager.webapp.address：yarn对外提供的web接口，可通过该web界面观察各个task的资源调度及运行状况，默认${yarn.resourcemanager.hostname}:8088；
到这一步，才算配置完成，这时可以复制到其他两个机器上看情况修改（有关hadoop的配置文件，完全一样，不用修改）。
格式化namenode
在每个节点都配置好以上的配置后，首次运行之前，必须先格式化namenode。
hadoop namenode -format
启动Hadoop
进入到${HADDDOP_HOME}/sbin目录下，通过运行start-all.sh启动所有组件。
测试是否启动成功，通过jsp命令：
1.  在master节点上，应该可以看到：
ResourceManager
NodeManager
NameNode
SecondrayNameNode
DataNode
2.  在slave节点上，应该可以看到：
NodeManager
DataNode
或者查看相应的UI界面，RM：http://${master节点IP}:8088，NM：http://${NM节点IP}:50070
```
#### 1.8             FAQ
```
1.  通过sh start-all.sh启动hadoop后，通过jps没有发现NodeManager进程，通过web访问m1:8042也不能正常显示。
a)  问题解决：通过查看nodemanager启动日志发现有异常：cannot support recovery with an ephemeral server port. Check the setting of yarn.nodemanager.address。由于我并没有在yarn-site.xml中配置该项，所以通过查看官网提供的默认配置发现，{yarn.nodemanager.address}的默认配置是${yarn.nodemanager.hostname}:0。（网上很多资料记载的默认配置端口是8041，不知道为什么默认配置变成了0），经过配置该项为m1:8041后，再次启动，发现只有m1节点NM启动成功，其他节点依然失败，再次查看日志发现"Problem binding to m1:8041"，因为NM是运行在各个节点上的，所以该项配置应该对应各个节点各自的IP，所以应该配置成${yarn.nodemanager.hostname}:8041，问题解决。（需要注意修改所有节点的配置）。
2.  按照老版本的配置文件，在core-site.xml中配置了hadoop.tmp.dir项为/mnt/m1/tmp,/mnt/m2/tmp,/mnt/m3/tmp，本意是逗号做分隔，配置三个目录，但实际上逗号并没有起到分隔的作用，而是被作为目录的一部分，只有一个目录被创建（并没有影响正常运行）；
a)  问题解决：
1)  通过查看官网默认配置，发现该项是以file://开头的路径，所以将该项配置成file://mnt/m1/tmp,/mnt/m2/tmp,/mnt/m3/tmp后，通过start-all.sh启动，发现NM并没有被启动，通过查看日志发现，AbstractService报异常"Wrong FS file://mnt/m1/tmp,/mnt/m2/tmp,/mnt/m3/tmp/yarn-nm-recovery, expected：file///"，应该是因为第一个"/"被当做系统根目录处理了，所以不识别"file:/"；
2)  将该项配置成file:///mnt/m1/tmp,/mnt/m2/tmp,/mnt/m3/tmp后，通过start-all.sh启动，发现NM还是并没有被启动，通过查看日志发现，NativeDB报异常"IO error：/usr/hadoop/file:/mnt/m1/tmp,/mnt/m2/tmp,/mnt/m3/tmp/yarn-nm-recovery/LOCK：No such file or direcotry"，这里对比上一步可发现，NativeDB并不识别“file://”，而是将其当做普通路径来处理，而且由于没有根目录，所以当做相对路径处理，追加了${HADOOP_HOME}=/usr/hadoop作为根目录，但上一步报异常的AbstractService是识别"file://"的，所以AbstractService是按照多路径创建了三个目录，接着走到这里时，因为当做一个路径来open了，所以报异常。而且查看官网可发现该项配置并没有说明可以配置多个目录，所以应该是版本升级后不再支持多路径了。
3)  将该项配置成正常的单路径（不再以file://的形式配多路径），/mnt/tmp，问题解决（同时注意修改其他节点的配置）；
3.  在已经成功启动过的前提下，修改了配置文件，然后复制到其他节点上了，再次启动时可能DataNode会失败，查看日志发现异常"java.io.Exception：All specified directories are failed to load"；
a)  问题解决：这个是因为namenode的clusterID和datanode的clusterID不同造成的，简单的解决办法是删掉所有hdfs文件，即core-site.xml和hdfs-site.xml中配置的路径，然后重新格式化namenode，再启动就好了。
```
