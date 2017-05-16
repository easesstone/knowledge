# 环境准备
Linux 系统。  
软件： ssh ，java  
hadoop 发布包，  
配置代理后可以直接用wget 下载.  
# Standalone 模式
修改 etc/hadoop-env.sh 中的java安装目录JAVA_HOME  
然后bin/hadoop version 可以看到相应的信息  
```
创建目录 
 $ mkdir input
 $ cp etc/hadoop/*.xml input
 $ bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.3.jar grep input output 'dfs[a-z.]+'
 $ cat output/*
 ```
 # Pseudo-Distributed Operation
 
 ********** 详情请参见官网  
