
```
问题一：

[2016-11-06T16:27:21,712][WARN ][o.e.b.JNANatives ] unable to install syscall filter:

Java.lang.UnsupportedOperationException: seccomp unavailable: requires kernel 3.5+ with CONFIG_SECCOMPandCONFIG_SECCOMP_FILTERcompiledinatorg.elasticsearch.bootstrap.Seccomp.linuxImpl(Seccomp.java:349) ~[elasticsearch-5.0.0.jar:5.0.0]

at org.elasticsearch.bootstrap.Seccomp.init(Seccomp.java:630) ~[elasticsearch-5.0.0.jar:5.0.0]

原因：报了一大串错误，大家不必惊慌，其实只是一个警告，主要是因为你Linux版本过低造成的。

解决方案：
1、重新安装新版本的Linux系统
2、警告不影响使用，可以忽略

问题二：
ERROR: bootstrap checks failed

max file descriptors [4096] for elasticsearch process likely too low, increase to at least [65536]

原因：无法创建本地文件问题,用户最大可创建文件数太小

解决方案：
切换到root用户，编辑limits.conf配置文件， 添加类似如下内容：

vi /etc/security/limits.conf

添加如下内容:
*  soft nofile 65536

* hard nofile 131072

* soft nproc 2048

* hard nproc 4096
备注：* 代表Linux所有用户名称（比如 hadoop）

保存、退出、重新登录才可生效

问题三：
max number of threads [1024] for user [es] likely too low, increase to at least [2048]
原因：无法创建本地线程问题,用户最大可创建线程数太小
解决方案：切换到root用户，进入limits.d目录下，修改90-nproc.conf 配置文件。

vi /etc/security/limits.d/90-nproc.conf

找到如下内容：

* soft nproc 1024

#修改为

* soft nproc 2048

问题四：
max virtual memory areas vm.max_map_count [65530] likely too low, increase to at least [262144]
原因：最大虚拟内存太小
解决方案：切换到root用户下，修改配置文件sysctl.conf

vi /etc/sysctl.conf

添加下面配置：

vm.max_map_count=655360

并执行命令：

sysctl -p

然后重新启动elasticsearch，即可启动成功。

问题五：
ElasticSearch启动找不到主机或路由
原因：ElasticSearch 单播配置有问题
解决方案：
检查ElasticSearch中的配置文件
vi  config/elasticsearch.yml
找到如下配置：

discovery.zen.ping.unicast.hosts:["192.168.**.**:9300","192.168.**.**:9300"]
一般情况下，是这里配置有问题，注意书写格式

问题六：
org.elasticsearch.transport.RemoteTransportException: Failed to deserialize exception response from stream

原因:ElasticSearch节点之间的jdk版本不一致

解决方案：ElasticSearch集群统一jdk环境

问题七：
Unsupported major.minor version 52.0

原因：jdk版本问题太低
解决方案：更换jdk版本，ElasticSearch5.0.0支持jdk1.8.0

问题八：
bin/elasticsearch-plugin install license
ERROR: Unknown plugin license

原因：ElasticSearch5.0.0以后插件命令已经改变
解决方案：使用最新命令安装所有插件
bin/elasticsearch-plugin install x-pack


[2017-07-21T07:47:02,855][INFO ][o.e.b.BootstrapChecks    ] [node-1] bound or publishing to a non-loopback or non-link-local address, enforcing bootstrap checks
ERROR: [1] bootstrap checks failed
[1]: max size virtual memory [3313172480] for user [es] is too low, increase to [unlimited]

启动的时候：
ulimit -v unlimited

```
