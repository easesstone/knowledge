参考单机模式的安装。
[单机模式下的zk 安装](https://github.com/easesstone/knowledge/blob/master/BigData/Zookeeper/ZK%20%E5%8D%95%E6%9C%BA%E6%A8%A1%E5%BC%8F.md)
```
1，在配置上面，
在zoo.cfg 中，加入如下配置。
server.1=master:2888:3888
server.2=slave1:2888:3888
server.3=slave2:2888:3888

2，把文件拷贝到各个机器上面

3，在各个机器上，创建数据存放目录：
并且在各个数据存放目录下，创建一个myid 文件，里面的内容分别对应1,2,3


4，在各台机器上启动zkServer

```
