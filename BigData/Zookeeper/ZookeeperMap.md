# Zookeeper 只是地图
## Zookeeper 的作用
```
1，保证高可靠的，高可用的协同服务。可以理解为保证客户端和集群间的交互稳定性。
2，统一配置管理。配置进群信息，提供命名服务，
3，分布式同步处理等。
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
