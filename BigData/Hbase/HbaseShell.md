# Hbase Shell
## 基本命令
```
./bin/hbase shell  启动shell
./bin/hbase shell -d  启用debug 模式
echo "describe 'test'"  | ./hbase shell -n  非交互式模式 
./hbase shell ./sample_commands.txt  批处理模式
HBASE_SHELL_OPTS="-verbose:gc -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCDateStamps \
  -XX:+PrintGCDetails -Xloggc:$HBASE_HOME/logs/gc-hbase.log" ./bin/hbase shell  设置JVM 参数
  
help  帮助 
help 'list'  查看list 具体用法
list  列出所有表
create 'test', 'cf'  建立表格
describe 'testtable'  查看表格定义
put 'test', 'row1', 'cf:a', 'value1'  插入数据
scan 'test'  查看所有表格中的数据
get 'testtable' , 'row1'  获取某一行的值
disable 'test'  禁用表格
drop 'test' 删除表格
exit 退出
```

## Hbase Namespace
```
1、介绍
在HBase中，namespace命名空间指对一组表的逻辑分组，类似RDBMS中的database，
方便对表在业务上划分。Apache HBase从0.98.0, 0.95.2两个版本开始支持namespace级别的授权操作，
HBase全局管理员可以创建、修改和回收namespace的授权。

2、namespace
HBase系统默认定义了两个缺省的namespace
hbase：系统内建表，包括namespace和meta表
default：用户建表时未指定namespace的表都创建在此
```

## Create 建表语句详解
```


create 'NewsClickFeedback',{NAME=>'Toutiao',VERSIONS=>1,BLOCKCACHE=>true,BLOOMFILTER=>'ROW',
COMPRESSION=>'SNAPPY',TTL => ' 259200 '},
{SPLITS => ['1','2','3','4','5','6','7','8','9','a','b','c','d','e','f']}
上述建表语句表示创建一个表名为“NewsClickFeedback”的表，该表只包含一个列簇“Toutiao”。
接下来重点讲解其他字段的含义以及如何正确设置。Note：因为篇幅有限本文并不讲解具体的工作原理，
后续会有相关专题对其进行分析。

VERSIONS  

数据版本数，HBase数据模型允许一个cell的数据为带有不同时间戳的多版本数据集，
VERSIONS参数指定了最多保存几个版本数据，默认为1。假如某个用户想保存两个历史版本数据，
可以将VERSIONS参数设置为2，再使用如下Scan命令就可以获取到所有历史数据：

scan 'NewsClickFeedback',{VERSIONS => 2}
BLOOMFILTER

布隆过滤器，优化HBase的随即读取性能，可选值NONE|ROW|ROWCOL，默认为NONE
，该参数可以单独对某个列簇启用。启用过滤器，对于get操作以及部分scan操作
可以剔除掉不会用到的存储文件，减少实际IO次数，提高随机读性能。
Row类型适用于只根据Row进行查找，而RowCol类型适用于根据Row+Col联合查找，如下：

Row类型适用于：get ‘NewsClickFeedback’,’row1′

RowCol类型适用于：get ‘NewsClickFeedback’,’row1′,{COLUMN => ‘Toutiao’}

对于有随机读的业务，建议开启Row类型的过滤器，使用空间换时间，提高随机读性能。

COMPRESSION

数据压缩方式，HBase支持多种形式的数据压缩，一方面减少数据存储空间，
一方面降低数据网络传输量进而提升读取效率。目前HBase支持的压缩算法主要包括三种：
GZip | LZO | Snappy，下面表格分别从压缩率，编解码速率三个方面对其进行对比：

Snappy的压缩率最低，但是编解码速率最高，对CPU的消耗也最小，目前一般建议使用Snappy

TTL

数据过期时间，单位为秒，默认为永久保存。对于很多业务来说，有时候并不需要永久保存某些数据
，永久保存会导致数据量越来越大，消耗存储空间是其一，另一方面还会导致查询效率降低。
如果设置了过期时间，HBase在Compact时会通过一定机制检查数据是否过期，过期数据会被删除。
用户可以根据具体业务场景设置为一个月或者三个月。示例中TTL => ‘ 259200’设置数据过期时间为三天

IN_MEMORY

数据是否常驻内存，默认为false。HBase为频繁访问的数据提供了一个缓存区域，缓存区域一般存储数据量小、
访问频繁的数据，常见场景为元数据存储。默认情况，该缓存区域大小等于Jvm Heapsize * 0.2 * 0.25 ，
假如Jvm Heapsize = 70G，存储区域的大小约等于3.2G。需要注意的是HBase Meta元数据信息存储在这块区域，
如果业务数据设置为true而且太大会导致Meta数据被置换出去，导致整个集群性能降低，所以在设置该参数时需要格外小心。

BLOCKCACHE

是否开启block cache缓存，默认开启。

SPLITS

region预分配策略。通过region预分配，数据会被均衡到多台机器上
，这样可以一定程度上解决热点应用数据量剧增导致系统自动split引起的性能问题。
HBase数据是按照rowkey按升序排列，为避免热点数据产生，一般采用hash + partition的方式预分配region，
比如示例中rowkey首先使用md5 hash，然后再按照首字母partition为16份，就可以预分配16个region。
```
