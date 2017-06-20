# 基本概念
## 官方文档链接：
http://abloz.com/hbase/book.html 

## Hbase 架构的硬件条件和软件条件
```
硬件主要考虑如下两个方面的内容，
一，服务器。（Linux 机器，考虑CPU，磁盘，内存，机架，）
二，网络。
软件：
一，操作系统。
二，软件。
（JDK,HADOOP，SSH,域名服务，文件句柄与进程限制（io异常，too many open file），datanode 处理的文件上限数。）
too many open file：/etc/security/limits.conf ，/etc/security/limits.conf ，等文件系统异常的配置。
尽量减少使用swap 分区。
一般都会配套hdfs 文件系统进行使用Hbase，因为hdfs 提供了传统的或者其他的文件系统或者说
数据库所不具有的强大使用的功能。
文件系统：
1，本地文件系统，
2，HDFS（可扩展，防丢失，容错，可靠，自动冗余）

```

## 传统的关系型数据库。
```
1,垂直增长的用户，以及垂直增加的数据。I/O 开销，网络原因，限制了客户端的访问速度。
2，而Hbase 很好的解决了这些问题。
```

## Hbase 大致架构
```
Server，Client， RegionServer
（主Server 经由Zookeeper（可靠，高可用，持久化的分布式协调系统），协调RegionServer，把
故障的RegionServer 一道另一个地方去。）
```
## Hbase 基本概念
```
1，基本单位是列，一列或者多列组成一行，存在着唯一的行健（row keys），
2, 所有的行按照字典序排序。
3，一行有若干列组成，其中的某些列则会组成一个列族（Column family），列族在创建表的时候需要先行创建好，
不可以包含太多内容，也不可以修改太过频繁。
4，列族中的内容保存到一个文件中。文件叫做HFile。
5，HFile 末尾中存在一个索引文件，加载到内存中的时候有限读取。
6，HFile 文件存储在HDFS 系统中，解决数据存储瓶颈问题。
```

## Hbase 数据模型
```sql
（Table，RowKey,Family,Column,Timestamp）-> Value
或者：SortedMap<RowKey,List<SortedMap<Cloumn,List<Value,Timestamp>>>>
SortedMap 表示的是这张表，里面包含一个列族List ，列族里面包另一个存储列和相应的值。
```

## Hbase 特点
```
提供唯一的索引，rowkeys, 方便检索，字典排序。
可以过滤掉无用的行和信息，给网络传输减压。
```

## Hbase 和其他组件的简单架构
```
一般情况下，hbase 的表存在hdfs 中，同时一般上设计map和reduce 的操作。
所以，其基本的架构一般如下,(分两种类型的节点）
1，master 机器上（HDFS 的NameNode,MapReduce的JobTracker，和Hbase 的master--Hmaster）
2，slave 节点的  (HDFS 的DataNode，MapReduce的Task Tracker， 和Hbase 的RegionServer)
```

## Hbase 运行模式
```
1，单机模式。
2，分布式模式。
   伪分布模式。
   完全分布式模式。
```

## Hbase 集群的简单启动和停止
```
bin/hbase-start.sh
bin/hbase-stop.sh
```
# 对外API
