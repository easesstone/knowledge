## 环境准备
```
Linux 机器一台，
JDK 
ZK tar 包
```

## 配置
```
jdk 安装，
配置解压后ZK 目录下的内容，如下：
conf/zoo.cfg  (ZK 启动的时候，会默认去找这个文件)
tickTime=2000
initLimit=10
syncLimit=5
dataDir=/home/service/zookeeper/data
clientPort=2181
```

## 启动ZK 
```
./bin//zkServer.sh start  服务端进行启动
./bin/zkCli.sh   启动客户端
```
