# Hbase Shell
## 启动Hbase Shell
```
## 到hbase 根目录
./bin/hbase shell
root@master:/home/DataSight/hbase/bin# ./hbase shell 
2017-06-20 23:00:32,124 WARN  [main] util.NativeCodeLoader: Unable to 
load native-hadoop library for your platform... using builtin-java classes where applicable
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/home/DataSight/hbase/lib/slf4j-log4j12-1.7.5.jar
!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/home/DataSight/hadoop/share/hadoop/common/lib/slf4j-log4j12-1.7.10.jar!
/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [org.slf4j.impl.Log4jLoggerFactory]
HBase Shell; enter 'help<RETURN>' for list of supported commands.
Type "exit<RETURN>" to leave the HBase Shell
Version 1.2.6, rUnknown, Mon May 29 02:25:32 CDT 2017

hbase(main):001:0>
```

## 列出有哪些表格
```
hbase(main):010:0> list
TABLE
test
testtable 
2 row(s) in 0.0310 seconds
=> ["test", "testtable"]
```

## Create创建表格
```
hbase(main):003:0> create 'test', 'cf'
0 row(s) in 1.2200 seconds
hbase(main):003:0> list 'table'
test
1 row(s) in 0.0550 seconds
```

## 描述表
```
hbase(main):016:0> describe 'testtable'
Table testtable is ENABLED                                                                                                      
testtable                                                                                                                       
COLUMN FAMILIES DESCRIPTION                                                                                                     
{NAME => 'colfam1', BLOOMFILTER => 'ROW', VERSIONS => '1', IN_MEMORY => 'false', KEEP_DELETED_CELLS => 'FALSE', DATA_BLOCK_ENCOD
ING => 'NONE', TTL => 'FOREVER', COMPRESSION => 'NONE', MIN_VERSIONS => '0', BLOCKCACHE => 'true', BLOCKSIZE => '65536', REPLICA
TION_SCOPE => '0'}                                                                                                              
1 row(s) in 0.1030 seconds

```

## Put插入数据
```
hbase(main):004:0> put 'test', 'row1', 'cf:a', 'value1'
0 row(s) in 0.0560 seconds
hbase(main):005:0> put 'test', 'row2', 'cf:b', 'value2'
0 row(s) in 0.0370 seconds
hbase(main):006:0> put 'test', 'row3', 'cf:c', 'value3'
0 row(s) in 0.0450 seconds
```

## Scan 查看所有数据
```
hbase(main):007:0> scan 'test'
ROW        COLUMN+CELL
row1       column=cf:a, timestamp=1288380727188, value=value1
row2       column=cf:b, timestamp=1288380738440, value=value2
row3       column=cf:c, timestamp=1288380747365, value=value3
3 row(s) in 0.0590 seconds
```

## Get 获取某一行数据
```
hbase(main):014:0> get 'testtable' , 'row1'
COLUMN                            CELL
 colfam1:qual1                    timestamp=1498023802449, value=val1 
 colfam1:qual2                    timestamp=1498023802449, value=val2
2 row(s) in 0.1520 seconds
```

## 禁用表格。
```
hbase(main):012:0> disable 'test'
0 row(s) in 1.0930 seconds
```

## 删除表格
```
hbase(main):013:0> drop 'test'
0 row(s) in 0.0770 seconds 
```

## 退出shell，退出客户端。
```
exit
```
## 开启Debug 模式
```
想要在shell中看到 DEBUG 级别的 logging ，可以在启动的时候加上 -d 参数.
$ ./bin/hbase shell -d
```
