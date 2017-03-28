### 命令update-alternatives
```
作用：当一个系统中有多个版本的的同一个软件，比如系统安装了多个jdk ，则，可以用update-alternative 进行解决。
[root@SHA1000031310 ~]# update-alternatives  --help
alternatives version 1.3.49.3 - Copyright (C) 2001 Red Hat, Inc.
This may be freely redistributed under the terms of the GNU Public License.

usage: alternatives --install <link> <name> <path> <priority>
                    [--initscript <service>]
                    [--slave <link> <name> <path>]*
       alternatives --remove <name> <path>
       alternatives --auto <name>
       alternatives --config <name>
       alternatives --display <name>
       alternatives --set <name> <path>

common options: --verbose --test --help --usage --version
                --altdir <directory> --admindir <directory>
例如：
[root@SHA1000031310 ~]# update-alternatives --install /usr/bin/java  java   /opt/odbc/jdk1.7.0_60/bin/java
[root@SHA1000031310 ~]# update-alternatives --config java

There are 3 programs which provide 'java'.

  Selection    Command
-----------------------------------------------
*  1           /usr/lib/jvm/jre-1.5.0-gcj/bin/java
   2           /opt/odbc/jdk1.7.0_05/bin/java
 + 3           /opt/odbc/jdk1.7.0_60/bin/java

Enter to keep the current selection[+], or type selection number: 3

```
