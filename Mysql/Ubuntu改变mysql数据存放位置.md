```  
1，修改 /etc/mysql/mysql.conf.d/mysqld.cnf  
把datadir 改变成自己存放数据的目录：
[mysqld]
pid-file	= /var/run/mysqld/mysqld.pid
socket		= /var/run/mysqld/mysqld.sock
#datadir		= /var/lib/mysql
datadir     = /home/data/mysql
log-error	= /var/log/mysql/error.log


2，创建/home/data/mysql，把数据拷贝过来，
cp -R /var/lib/mysql/* /home/data/mysql
chown -R mysql:mysql /home/data/mysql


3,修改如下文件：

vim /etc/apparmor.d/usr.sbin.mysqld
#把
/var/lib/mysql r,
/var/lib/mysql/** rwk, 
 
#改成
/data/mysql r,
/data/mysql/** rwk

4，重启服务,
/etc/init.d/mysql restart

```
