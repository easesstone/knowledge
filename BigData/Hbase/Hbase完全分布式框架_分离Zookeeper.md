参考1,Hbae:   
[Hbase集群搭建_自带Zookeeper](https://github.com/easesstone/knowledge/blob/master/BigData/Hbase/Hbase%E9%9B%86%E7%BE%A4%E6%90%AD%E5%BB%BA_%E8%87%AA%E5%B8%A6Zookeeper.md)  
备注：注意在上面一步Hbase集群搭建_自带Zookeeper 这个完成后，把hbase-env.sh 其中一行的配置改为如下：  
export HBASE_MANAGES_ZK=false
参考2,Zookeeper：  
[Zookeeper集群搭建](https://github.com/easesstone/knowledge/blob/master/BigData/Zookeeper/ZK%20%E7%9A%84%E5%AE%8C%E5%85%A8%E5%88%86%E5%B8%83%E5%BC%8F%E6%A8%A1%E5%BC%8F.md)  
集群启动的时候：
先启动HDFS,然后启动ZK， 最后Hbase。  
至于Yarn 是否要启动，看你自己需求。  
