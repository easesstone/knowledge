### 1，概念
```
Hadoop是一个开源的、可运行于大规模集群上的分布式并行编程框架，它实现了 Map/Reduce计算模型。
```

### 2，核心所在。
```
1，Hadoop分布式文件系统（HDFS，Hadoop Distributed File System） -----------datanode namenode 之间的点滴

2，MapReduce（Google MapReduce的开源实现)，。MapReduce是一种并行编程模式
MapReduce框架是由一个单独运行在“主节点”上的JobTracker 和运行在每个集群“从节点”上的TaskTracker共同组成的。主节点负责调度构成一个作业的所有任务，
这些任务分布在不同的从节点上。主节点监控它们的执行情况，并且重新执行之前失败的任务；从节点仅负责由主节点指派的任务。当一个Job被提交时，
JobTracker接收到提交作业和配置信息之后，就会将配置信息等分发给从节点，同时调度任务并监控TaskTracker的执行。 
HDFS在集群上实现了分布式文件系统，MapReduce在集群上实现了分布式计算和任务处理。HDFS在MapReduce任务处理过程中提供了文件操作和存储等支持
，MapReduce在HDFS的基础上实现了任务的分发、跟踪、执行等工作，并收集结果，二者相互作用，完成了Hadoop分布式集群的主要任务。

MapReduce编程模型的原理是：利用一个输入的key/value对集合来产生一个输出的key/value对集合。
MapReduce库的用户用两个函数来表达这个计算：Map和Reduce
用户自定义的map函数接收一个输入的key/value对，然后产生一个中间key/value对的集合，
MapReduce 把所有具有相同key 值的value 集合在一起，然后传递给reduce 函数。用
户自定义的reduce 函数接收key 和相关的value 集合。reduce 函数合并这些value 值，形成
一个较小的value 集合。一般来说，每次reduce 函数调用只产生0 或1 个输出的value 值。
通常我们通过一个迭代器把中间的value 值提供给reduce 函数，这样就可以处理无法全部放
入内存中的大量的value 值集合了。

```
