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

