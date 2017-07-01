二次开发
http://support.huawei.com/enterprise/zh/cloud-computing/fusioninsight-pid-19919550/?category=api-documentation 

二次开发
http://support.huawei.com/huaweiconnect/enterprise/thread-311485.html

```
#!/bin/bash
if [ $# == 0 ];then 
echo "xcall  argument[]"
exit 0
fi
command=$@
echo $command
{
	echo "---------------------master-------------------"
        $command 
	echo "---------------------slave1------------------"
	ssh  slave1 "source /etc/profile;$command" 
	echo "--------------------slave2--------------------"
	ssh slave2 "source /etc/profile;$command"  
}



#!/bin/bash
if [ $# != 1 ];then
echo "cluster-start-or-stop-zk-hdfs-yarn-hbase.sh  [start|stop]"
exit 0
fi
if [ "$1" == "start" ];then
    echo "starting now, please wait.............."
    xcall zkServer.sh start
    start-dfs.sh
    start-yarn.sh
    start-hbase.sh
    cluster-status-jps
else 
    echo "stoping now, please wait.............."
    stop-hbase.sh
    stop-yarn.sh
    stop-dfs.sh 
    xcall zkServer.sh stop
fi
```

```
1，首先，简单介绍一下git （做什么的，有什么优点）
2，讲一下git 的安装和初始化 （可以简单地带过）
3，讲一下git 的团队开发模型  （讲得清晰一点）
4，讲一下 git 的常用命令   （演示）
5，演示一下git 的 冲突情况   （演示和讲解）
6，演示一下githup 上面有哪些有趣的内容  （界面介绍）
7，推荐一个可视化git客户端工具SourceTree （与git.exe 中自带的比较，还是SourceTree 比较好用） 
   和一本书（虽然比较旧），git 权威指南（里面比较详细系统的介绍了git 的一些使用，和高级用法）
```
![HBASECRUD](https://github.com/easesstone/knowledge/blob/master/HBASECRUD.gif)
