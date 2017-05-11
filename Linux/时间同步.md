```

一、搭建时间同步服务器
1、编译安装ntp server
rpm -qa | grep ntp
若没有找到，则说明没有安装ntp包，从光盘上找到ntp包，使用
rpm -Uvh ntp***.rpm
进行安装
2、修改ntp.conf配置文件
vi /etc/ntp.conf
①、第一种配置：允许任何IP的客户机都可以进行时间同步
靠近server 附近
将"restrict default nomodify notrap noquery"这行修改成：
restrict default nomodify notrap
配置文件示例：/etc/ntp.conf
②、第二种配置：只允许192.168.211.***网段的客户机进行时间同步
在restrict default nomodify notrap noquery（表示默认拒绝所有IP的时间同步）之后增加一行：
restrict 192.168.211.0 mask 255.255.255.0 nomodify notrap
3、启动ntp服务
rcntp start
或/etc/init.d/ntp start

开机启动服务
chkconfig ntp on
4、ntpd启动后，客户机要等几分钟再与其进行时间同步，否则会提示"no server suitable for synchronization found"错误。

二、配置时间同步客户机
1、安装和配置NTP
2、手工执行 sntp -P no -r <ntp server> 来同步(注意有时需要用如下命令sntp -S <ntp server>, NTP 时间同步软件版本间不同导致。)
或者利用crontab来执行
crontab -e
0 21 * * * ntpdate 192.168.211.22 >> /root/ntpdate.log 2>&1
每天晚上9点进行同步
(或者)echo '0 21 * * * ntpdate 192.168.211.22 >> /root/ntpdate.log 2>&1' >>/var/spool/cron/tabs/root 

3.重启服务：service cron restart
4.将cron服务加入开机自启动中
 chkconfig cron on



````
