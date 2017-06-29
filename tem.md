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
