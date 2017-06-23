## 环境准备
```
Linux 机器一台，
JDK 
ZK tar 包
```

## 配置
```
jdk 安装，
配置解压后ZK 目录下的内容，如下：
conf/zoo.cfg  (ZK 启动的时候，会默认去找这个文件)
tickTime=2000
initLimit=10
syncLimit=5
dataDir=/home/service/zookeeper/data
clientPort=2181
```

## 配置详解
```

initLimit：这个配置项是用来配置 Zookeeper 接受客户端（这里所说的客户端不是用户连接
Zookeeper 服务器的客户端，而是 Zookeeper 服务器集群中连接到 Leader 的 Follower 服务器）
初始化连接时最长能忍受多少个心跳时间间隔数。当已经超过 10 个心跳的时间（也就是 tickTime）
长度后 Zookeeper 服务器还没有收到客户端的返回信息，那么表明这个客户端连接失败。总的时间长度就是 5*2000=10 秒
syncLimit：这个配置项标识 Leader 与 Follower 之间发送消息，请求和应答时间长度，最长不能超过多少个
tickTime 的时间长度，总的时间长度就是 2*2000=4 秒
server.A=B：C：D：其中 A 是一个数字，表示这个是第几号服务器；B 是这个服务器的 ip 地址；
C 表示的是这个服务器与集群中的 Leader 服务器交换信息的端口；
D 表示的是万一集群中的 Leader 服务器挂了，需要一个端口来重新进行选举，
选出一个新的 Leader，而这个端口就是用来执行选举时服务器相互通信的端口。
如果是伪集群的配置方式，由于 B 都是一样，所以不同的 Zookeeper 实例通信端口号不能一样，所以要给它们分配不同的端口号。
tickTime：这个时间是作为 Zookeeper 服务器之间或客户端与服务器之间维持心跳的时间间隔，
也就是每个 tickTime 时间就会发送一个心跳。
dataDir：顾名思义就是 Zookeeper 保存数据的目录，默认情况下，Zookeeper 将写数据的日志文件也保存在这个目录里。
clientPort：这个端口就是客户端连接 Zookeeper 服务器的端口，Zookeeper 会监听这个端口，接受客户端的访问请求。
```

## 启动ZK 
```
./bin//zkServer.sh start  服务端进行启动
./bin/zkCli.sh   启动客户端
```
