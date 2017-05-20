# 概念  
HDFS集群主要由一个NameNode来管理文件系统元数据和存储实际数据的DataNodes。    
另外其他的概念还有Secondary NameNode,Fsimage Block等。  
## 特性  
1，高容错，可扩展，易扩展。   
2，基于java，可移植。    
3，硬件要求小，可以部署到廉价的Linux 机器上。  
4，有交互式的shell。  
5，易于监控集群状态。  
## Namenode和Datanode
namenode相当于一个管理者，或者说是服务器，负责对文件系统[namespace]metada 的管理以及响应客户端的请求，  
响应对文件系统中的文件的操作，如删除，保存，打开，关闭，重命名文件或者文件夹等，以及管理block 和对应的  
datanode 之间的映射关系。(虽然namenode 响应客户端对文件操作，但是数据并不会经过namenode ，客户端从namenode  
中获取响应文件的block 信息和datanode 的对应关系后，直接从datanode 中操作数据)  
  
datanode,用来存储数据，通常一个文件会被默认的以64M 每个Blocks 的形式存在集群中的datanode 所在的节点上  
datanode 通常一个机器只有一个datanode.一个文件会被切分成N个Block， datanode 周期性的向namenode 发送  
心跳信号和快状态信号（blockreport）.心跳状态表示datenode 正常工作，块状态信号包含block 的列表。  
  
## 副本存放机制
HDFS采用一种称为机架感知(rack-aware)的策略来改进数据的可靠性、可用性和网络带宽的利用率。  
集群通常有多个机架构成，可以理解为多个小的集群，3个节点为一个小集群，一个小集群为一个机架。 <br />
同机架中的机器宽带比较高，不同机架中的机器，通常需要通过交换机。   
机架感知可以通过datanode 来获取机架id  
则<br />
1，第一种简单的副本存放规则<br />
通常一块block 备份3分，存放不同的机架上。，这种情况下，对写数据有一定的影响。<br/>
2，另一种存放策略<br/>
本地机器上存放一块blocks，同一个机架上的另一台机器存放第二块block, 第三块存放到其他机器上。<br />
（另外hdfs 的读取，是先读取最近的block 为原则，例如：如果在读取程序的同一个机架上有一个副本，那么就读取该副本。）    

## Namenode 文件结构
```
hdfs-site.xml中定义的dfs.name 文件的存放位置
${dfs.name}/current/VERSION
                   /edits
                   /fsimage
                   
VERSION 的值：
#Wed May 17 13:40:02 CST 2017
namespaceID=272346466  文件系统的唯一标识，是在文件系统初次格式化的时候生成的
clusterID=CID-c8f6a199-79da-4a51-a4bb-833a3d735d7c
cTime=0
storageType=NAME_NODE  表示此文件夹保存的是Namenode 的数据。
blockpoolID=BP-1612505008-100.109.241.112-1494999602925
layoutVersion=-63
```


