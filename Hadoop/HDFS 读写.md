参考链接：http://www.cnblogs.com/laov/p/3434917.html

## 写流程：
```
比如你有一个100M的文件。则写的流程大致如下。
a,Client 将File 分块，分别为block1,和block2 （64M，和36M）
b,Client 向NameNode 发送写数据请求。
c,NameNode 获取Client 的请求，记录Block的信息，并返回可用的DataNode，以及Block该存放在那块block 上。
例如:block1(host2,host1,host3)
     block2(host7,host8,host4)
block 存放原理：
NameNode具有RackAware机架感知功能，这个可以配置。
  1,若client为DataNode节点，那存储block时，规则为：
    副本1，同client的节点上；
    副本2，不同机架节点上；
    副本3，同第二个副本机架的另一个节点上；其他副本随   机挑选。
  2,若client不为DataNode节点，那存储block时，规则为：
    副本1，随机选择一个节点上；
    副本2，不同副本1，机架上；
    副本3，同副本2相同的另一个节点上；其他副本随   机挑选。
d,Client 获取NameNode 返回的存放block 的datanode信息，开始写数据。
写数据的过程是流式的写过程。
先写第一块block1.
1,将64M的block1按照64K 的package 划分。
2，将第一个package 发送给hosts2.
3,host2接收完第一个package,向host1发送第一个package,同时client向host2 发送第二个package.
4,host1接收完第一个package,向host3发送第一个package,同时接收host2发送过来的第二个package.
5,依此类推，直至第一个block1 写完。
6，host2,host1,host3,向namenode，host2向client 发送消息，说，消息发送完了。
7，client 确认host2发来的消息后，向namenode发送消息，所block1 写完了。至此，block1 正式结束。
接着发送第二块block2,过程与block1 相同。
至此，两块block 内容写到hdfs 系统中，则写完成。
析，通过写过程，我们可以了解到：
    ①写1T文件，我们需要3T的存储，3T的网络流量贷款。
    ②在执行读或写的过程中，NameNode和DataNode通过HeartBeat进行保存通信，
    确定DataNode活着。如果发现DataNode死掉了，就将死掉的DataNode上的数据，放到其他节点去。读取时，要读其他节点去。
    ③挂掉一个节点，没关系，还有其他节点可以备份；甚至，挂掉某一个机架，也没关系；其他机架上，也有备份。
```

## 读流程
```
a. client向namenode发送读请求。
b. namenode查看Metadata信息，返回fileA的block的位置。
    block1:host2,host1,host3
    block2:host7,host8,host4
c. block的位置是有先后顺序的，先读block1，再读block2。而且block1去host2上读取；然后block2，去host7上读取
```



