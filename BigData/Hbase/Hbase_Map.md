## 官方文档链接：
http://abloz.com/hbase/book.html 

## 传统的关系型数据库。
```
1,无垂直增长的用户，以及垂直增加的数据。I/O 开销，网络原因，限制了客户端的访问速度。
2，而Hbase 很好的解决了这些问题。
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
