Fusion Insight 安装
## 1,环境准备。
### 1.1 三台Linux 机器，带大小网，密码相同
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

## 2，获取相应的FI安装包进行安装
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
FusionInsight/software/precheck/checkNodes.Config: No such file or directory 
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

### 执行如下命令进行安装 ./install.sh -m single -f install.ini
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
