## 安装
```
下载相应rpm 包，
rpm -ivh impala-*.rpm  --force --nodeps
log 日记目录
/var/log/impala
mysql 链接驱动
./var/lib/impala
配置文件路径：
./etc/default/
hbase hadoop hive conf 配置文件位置
./etc/impala/
重要的安装目录：
 /usr/lib/impala
```

## 启动
```
service impala-catalog start
service impala-catalog status
service impala-state-store start
service impala-state-store start
service impala-server start
service impala-server status
```
