Hbase  API
```java
//根据配置获取Configuration 对象，
//然后建立连接
//获得表格
//创建put 对象
//往表格中添加对象。
//关闭表格
//关闭连接
//在根目录下方一个hbase-site.xml 
//hbase-site.xml 包含的是zk服务器，以及zk 的端口号。
Configuration cnof = Configuration.create()
Connection connection = ConnectionFactary.createConnection(conf);
Table table = connection.getTabel(TabelName.valueOf("test"));
Put put = new Put(Bytes.toBytes("colum1"));
put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("name"),Bytes.toBytes("xiaoli"));
table.put(put);
table.close();
connection.close();
```
## Hbase shell 补充
```
help  获取帮助
help 'generate' 获取详细帮助
help 'ddl'
help '命令'



```
