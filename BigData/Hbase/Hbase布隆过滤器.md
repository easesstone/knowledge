```
（1）Bloomfilter在hbase中的作用
Hbase利用bloomfilter来提高随机读（get）的性能，对于顺序读（scan）而言，
设置Bloomfilter是没有作用的（0.92版本以后，如果设置了bloomfilter为rowcol，
对于执行了qualifier的scan有一定的优化）
（2）Bloomfilter在hbase中的开销
Bloomfilter是一个列族(cf)级别的配置属性，如果在表中设置了bloomfilter，
那么hbase会在生成storefile时包含一份bloomfilter结构的数据，称其为MetaBlock与DataBlock（真实的KeyValu数据）
一起由LRUBlockCache维护。所以开启bloomfilter会有一定的存储及内存cache开销。
（3）Hbase中的bloomfilter的类型及使用
a)  ROW，根据KeyValue中的row来过滤storefile。举例（该情况可以针对列族和列都相同,
只有rowkey不同的情况下，可以使用ROW来过滤。）
　　　　　　　　如：假设有2个storfile文件sf1和sf2，
　　　　　　　　sf1包含kv1（r1  cf:q1  v） 、kv2（r2  cf:q1  v）
　　　　　　　　sf2包含kv3（r3  cf:q2  v） 、kv4（r4  cf:q2  v）
　　　　　　　　如果设置了cf属性的bloomfilter为ROW，那么get（r1）时会过滤sf2，get（r3）时会过滤sf1.
b)  ROWCOL，根据KeyValue中的row+qualifier来过滤storefile。举例：
（该情况是针对列族相同，列和rowkey不同的情况，可以用ROWCOL来过滤。）
　　　　　　　　如：假设有2个storefile文件sf1和sf2，
　　　　　　　　Sf1包含kv1（r1  cf:q1  v）、kv2（r2  cf:q1  v）
　　　　　　　　Sf2包含kv3（r1  cf:q2  v）、kv4（r2  cf:q2  v）
　　　　　　　　如果设置了cf属性中的bloomfilter为ROW，无论get（r1，q1）还是get（r1，q2）都会读取sf1+sf2；
        而如果设置了cf属性中的bloomfilter为ROWCOL，那么get（r1，q1）就会过滤
        sf2，get（r1，q2）就会过滤sf1。
（4）ROWCOL与ROW对比
           i. ROWCOL只对指定列（Qualifier）的随机读取Get有效，如果应用中的随机读取Get只含有row，
           而且没有指定读取哪个qualifier，那么设置ROWCOL是没有效果的，这种场景就应该使用ROW。
           ii.   如果随机读中指定的列（Qualifier）的数目大于等于2，在0.9版本中ROWCOL是无效的，0.9版本以后是有效的。
           iii.  如果同一个row多个列的数据在应用上是同一时间put的，那么ROW与ROWCOL的效果近似相同，
           而ROWCOL只对指定了列的随机读才会有效，所以设置为ROW更佳。
           iv.  ROWCOL与ROW只在名称上有联系，ROWCOL并不是ROW的扩展，不能取代ROW。
 ```
