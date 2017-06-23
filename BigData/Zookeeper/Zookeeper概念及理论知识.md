# Zookeeper 知识地图
## Zookeeper 的作用 以及特点
```
1，官方介绍：保证高可靠的，高可用的协同服务。
2，使用场景：refered to https://www.ibm.com/developerworks/cn/opensource/os-cn-zookeeper/ 
   a，统一命名服务，（由目录结构这个特点，例如设置Hbase 唯一的row key)
   b, 配置管理，（统一修改保存和修改集群一些相同的配置）
   c, 集群管理，（选举出一个leader 管理集群状态，例如避免传统意义上的单master 故障，方法创建一个序列——临时节点
   且编号最小的节点作为master，如果这个挂掉，则会把这个节点删掉，而又出现一个最小编号的节点作为master）
   d, 共享锁。
   e, 队列管理。
3，分布式同步处理等。
特点：
ZK 的数据，保存在内存中，所以可以实现高吞吐量、低延迟的特点。
ZK 本身性能稳定，可以用于大集群，可以是集群不会出现单点故障这类问题。
ZK 中注意理解其数据模型和层次命名空间（类似于Linux 层次命名空间。）
```

## Zookeeper 搭建（从Hbase 内建中独立出来，因为进群中的其他组件也可能会用到zookeeper）
```
1,一些基本的概念，
A，Client，
   向Server 周期性的发送消息，表明自己还是alive 的，而Server接受到后会回应消息。
   如果Client 没有收到Server 的回应，则自动重定向到另一个Server   
B，Server
   Server 接收来自Client 的消息，如果一段时间后，没有接收到Client 的消息，则Server 认为Client 
   是down 掉的。
C，Ensemble
   ZK 是由一些列的节点组成，推荐用基数个节点，3,5等，最小为3.
D，Leader
   ZK 启动时选举出来的，在Follow 失败时，需要进行相应的处理。
E，follower
   接收Leader的指令。
```
