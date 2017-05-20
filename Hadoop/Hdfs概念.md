# hdfs概述与hdfs设计目标
```
HDFS集群主要由一个NameNode来管理文件系统元数据和存储实际数据的DataNodes。    
另外其他的概念还有Secondary NameNode,Fsimage Block等。  
## 特性  
1，高容错，可扩展，易扩展。   
2，基于java，可移植。    
3，硬件要求小，可以部署到廉价的Linux 机器上。  
4，有交互式的shell。  
5，易于监控集群状态。
```
## Namenode和Datanode简述
```
namenode相当于一个管理者，或者说是服务器，负责对文件系统[namespace]metada 的管理以及响应客户端的请求，  
响应对文件系统中的文件的操作，如删除，保存，打开，关闭，重命名文件或者文件夹等，以及管理block 和对应的  
datanode 之间的映射关系。(虽然namenode 响应客户端对文件操作，但是数据并不会经过namenode ，客户端从namenode  
中获取响应文件的block 信息和datanode 的对应关系后，直接从datanode 中操作数据)  
  
datanode,用来存储数据，通常一个文件会被默认的以64M 每个Blocks 的形式存在集群中的datanode 所在的节点上  
datanode 通常一个机器只有一个datanode.一个文件会被切分成N个Block， datanode 周期性的向namenode 发送  
心跳信号和快状态信号（blockreport）.心跳状态表示datenode 正常工作，块状态信号包含block 的列表。  
```

## 副本存放机制
```
HDFS采用一种称为机架感知(rack-aware)的策略来改进数据的可靠性、可用性和网络带宽的利用率。  
集群通常有多个机架构成，可以理解为多个小的集群，3个节点为一个小集群，一个小集群为一个机架。 
同机架中的机器宽带比较高，不同机架中的机器，通常需要通过交换机。   
机架感知可以通过datanode 来获取机架id  
则
1，第一种简单的副本存放规则
通常一块block 备份3分，存放不同的机架上。，这种情况下，对写数据有一定的影响。
2，另一种存放策略
本地机器上存放一块blocks，同一个机架上的另一台机器存放第二块block, 第三块存放到其他机器上。
（另外hdfs 的读取，是先读取最近的block 为原则，例如：如果在读取程序的同一个机架上有一个副本，那么就读取该副本。）    
```

## Namenode 文件结构
```
概括的说namenode 主要保存两种信息，文件和blocks 的对应关系，（block 和datanod的对应关系，集群启动时由datanode 对应地上传）。
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

1，namenode 把文件和文件夹的元数据保存在文件系统树中
2，信息会以问价的形式保存：命名空间镜像fsimage和修改日记fsedit
3, 保存文件包含的数据块和所在的datanode. 虽然这些信息并没有存在namenaode 的文件系统中，其数据是系统启动的时候，
由datanode主动上报得来。
```

## Secondary namenode
```
1, 不是Namenode 的备份。
2，周期的合并命名空间镜像和修改日记文件。
3，合并后的命名空间镜像在Secondary Namenode 也保存一份。防止Namenode 数据丢失时，可以恢复。
```
## 一次checkpoint 过程
```
当系统namenode 启动的时候，从磁盘中读取Editlog 和FsImage 到内存, 将所有的Editlog作用于FsImage 中，生成新的FsImage
,新的FsImage 会把东西保存到本地磁盘中，删除旧的EditLog。这个过程称为一个检查点（checkpoint）
```

## DataNode 状态报告
```
Datanode 启动的时候，产生一个本地文件所对应的所有的HDFS的数据块列表报告，上传到Nomenode.此报告是块状态报告。
```

### 单点故障
```
hdfs 的namenode 所在机器故障，称为单点故障。需要人为重启。
```

## 命名空间
```
文件系统支持传统的层次文件组织结构，namespace用于管理文件系统的资源。
```

## Namenode 元数据的存储方式
```
Namenode 作用，元数据管理和响应客户端请求。
1,内存元数据(NameSystem)
  内存元数据就是当前namenode正在使用的元数据，是存储在内存中的。
2,磁盘元数据镜像文件
  磁盘元数据镜像文件是内存元数据的镜像，保存在namenode工作目录中，它是一个准元数据，
  作用是在namenode宕机时能够快速较准确的恢复元数据。称为fsimage。
3,数据操作日志文件（可通过日志运算出元数据）
  数据操作日志文件是用来记录元数据操作的，在每次改动元数据时都会追加日志记录，
  如果有完整的日志就可以还原完整的元数据。主要作用是用来完善fsimage，
  减少fsimage和内存元数据的差距。称为editslog。
  
  
```
