### 如下：
在/etc/security/limits.conf 中添加两行。
```
#<domain>      <type>  <item>         <value>
#
#*               soft    core            0
#*               hard    rss             10000
#@student        hard    nproc           20
#@faculty        soft    nproc           20
#@faculty        hard    nproc           50
#ftp             hard    nproc           0
#@student        -       maxlogins       4
* soft nofile 65535
* hard nofile 65535  (添加的两行)
# End of file

```

```
* soft nofile 65535 <br/>
* hard nofile 65535 <br/>
```
> 执行以下两个命令， <br/>
> ulimit -Hn 65535 <br/>
> ulimit -Sn 65535 <br/>
> 之后重启系统，reboot <br/>
