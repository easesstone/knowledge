Fusion Insight 安装
## 1,环境准备。
### 1.1 三台Linux 机器,不一定需要带大小网（双平面和单平面的区别），密码相同
### 1.2 挂盘，挂到opt目录下
### 1.3 挂载镜像
例如：mount rhel-server-6.4-x86_64-dvd.iso /media/ -o loop  
注意区分系统信息：cat /etc/issues*  
如出现如下信息，请忽略：  
```
mount: block device /opt/SLES-11-SP4-DVD-x86_64-GM-DVD1.iso is write-protected, mounting read-only
```
### 1.4 关闭Swap
执行如下命令： sysctl vm.swappiness=0  
把vm.swappiness = 0 写到/etc/sysctl.conf  文件末尾  
### 1.5 设置时区与同步时间
ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime  
hwclock -w  
date检查时区是否是CST  
并且同步集群时间，可以用NTP服务进行同步   
### 1.6 设置/etc/hosts 映射
类似如下：有时候会从windows 机器出现ping 不通小网的情况，把etc host 改成大网。
```
100.109.242.218 LFG1000227120
100.109.242.218 LFG1000227120
100.109.242.218 SUSE11SP40601
#
# hosts         This file describes a number of hostname-to-address
#               mappings for the TCP/IP subsystem.  It is mostly
#               used at boot time, when no name servers are running.
#               On small systems, this file can be used instead of a
#               "named" name server.
# Syntax:
#    
# IP-Address  Full-Qualified-Hostname  Short-Hostname
#

127.0.0.1       localhost

# special IPv6 addresses
::1             localhost ipv6-localhost ipv6-loopback

fe00::0         ipv6-localnet

ff00::0         ipv6-mcastprefix
ff02::1         ipv6-allnodes
ff02::2         ipv6-allrouters
ff02::3         ipv6-allhosts
10.74.51.199 linux-kj9e
10.140.8.53 code.huawei.com

192.168.187.95  SZV1000244850
192.168.122.235  SZV1000244851
192.168.125.52  SZV1000244849
```

## 2，获取相应的FI安装包进行安装OMM
### 2.1 修改FI包解压后的FusionInsight/software/preinstall/preinstall.ini 
```
# Examples:
#   g_hosts="10.18.40.[1-5]"
#   g_hosts="10.18.40.[1-5,15,17],host.[rm,zk,nn].cluster"
#   g_hosts="10.18.[10-12].[1-200],192.188.45.[1-5,40],host[1-100]"
#
g_hosts="100.109.210.185,100.109.242.218,100.109.163.166"


# Examples:
#
#   g_pkgs_dir="redhat-6.4:/media/redhat/;suse-11.1:/media/suse11-1/"
#
#   g_pkgs_dir="redhat-6.4:/media/"
#
#   g_pkgs_dir="suse-11.1:/media/"
#
#   g_pkgs_dir="centos-6.4:/media/"
#
# Remarks: Mount iso file command:
#          mount rhel-server-6.4-x86_64-dvd.iso /media/ -o loop
#
g_pkgs_dir="suse-11.4:/media/"
```
### 2.1 执行如下脚本进行安装：./FusionInsight/software/preinstall/preinstall.sh
请忽略如下报错：
```
[ERROR] localhost: fail to parse configuaration file, check the file name or the content of config file.
[ERROR] localhost: quit the programme.
```

### 2.2 配置install.ini,single 和两个ip
```
[HA]
    ha_mode=single
    local_ip1=100.109.210.185
    local_ip2=
    local_ip3=
    local_ip4=
    peer_ip1=
    peer_ip2=
    peer_ip3=
    peer_ip4=
    ws_float_ip=
    ws_float_ip_interface=
    ws_float_ip_netmask=
    ws_gateway=
    om_float_ip=
    om_float_ip_interface=
    om_float_ip_netmask=
    om_gateway=
    ntp_server_ip=
    om_mediator_ip=100.109.210.185
    sso_ip=
    sso_port=
    bigdata_home=
    bigdata_data_home=
[/HA]
```

### 2.3 执行如下命令进行安装 ./install.sh -m single -f install.ini
若成功，则提示如下：  
```
=================================== Welcome ===================================
=== STEP 1 Checking the parameters.
=== STEP 2 Preparing for installation components.                         [done]
=== STEP 3 Installing the package.                                        [done]
=== STEP 4 Waiting for ntp to startup.                                    [done]
============================= Install Successfully ============================
Please visit http://100.109.210.185:8080/web/ to continue cluster installation.
Installation is successful. 
```
如下失败是因为：install.ini 中没有改默认的那个single。
```
=================================== Welcome ===================================
=== STEP 1 Checking the parameters.
ERROR:At least one float ip should be input in double mode.
ERROR:Failed to set the default value of input parameters.
ERROR:Installation failed. For details about the error, see the log file /tmp/scriptlog/install.log.
```

## 3.安装集群。
### 3.1根据提示进入安装集群界面，一般是主节点ip:8080/web,
### 3.2修改密码：初始密码Admin@123
### 3.3填写集群名字，添加集群描述。
### 3.4列出集群ip。
### 3.5勾选要安装的服务，一般勾选Spark 和Hbase 即可。
### 3.6某些选项只允许选取两个节点，即对应主备两台服务器。其余的可全选。一般选取以当前登陆页面的Ip对应的机器作客户端（client），其余机器作为服务端（server）, 服务端即为主备两节点。
原则上说，主备节点选在主节点外的两个节点，至少3个的3个都选，hive 的都选。  
### 3.7 重要，点击next，修改集群配置：
```
浮动Ip：应选取本网段内当前机器ping不通的ip；
仲裁Ip：应选取本网段内可ping通的Ip。
对于含有大小网的机器，这个地方选择的是小网ip
其中浮动ip:192.168.100.241,242,仲裁ip:192.168.0.1
注意：
1、LdapServer 和 DBService 均需作上述操作，两者的浮动Ip应不同，仲裁Ip可相同。
2、在空间不足的情况下，在第六步中，把dfs.datanode.du.reserved 这个参数改成0 
3、调整yarn.nodemanager.resource.memory-mb、yarn.nodemanager.resource.cpu-vcores参数
和yarn.nodemanager.local-dirs、yarn.nodemanager.log-dirs、dfs.datanode.data.dir、dfs.namenode.edits.dir， 
其他的可按照需求更新。上层目录需要有omm用户权限，FI才会自动创建对应文件夹(chown omm: wheel 文件名)
4、步需要建立上层目录   在每个节点建立 
/opt/huawei #  mkdir cluster-data    
/opt/huawei #  chown omm:wheel cluster-data –R    重点重点重点
5、注意这个地方总共需要修改的地方7个。修改Hdfs  dir 中的namenode 和datanode的dir :
/opt/huawei/cluster-data/hadoop/data/dn
/opt/huawei/cluster-data/namenode
6、注意把HDFS的dfs.datanode.data.dir和YARN服务的yarn.nodemanager.local-dirs配置项配置到有较大空间的目录下：
yarn.nodemanager.local-dirs参考5 来建立，建立的时候，尽可能地放在/opt/huawei 下。
```

### 3.8 点击next 直至完成
### 3.9 若出错，请参考集群日记进行修改配置。
## 安装客户端
1,在服务管理里面下载客户端，选择下载到主节点上。  
2，建立客户端目录： 例如mkdir /opt/FI-Client  
3，加压下载的文件，需要进行两次解压。
4，在解压之后的目录中执行如下命令：./install.sh /opt/FI-Client  
