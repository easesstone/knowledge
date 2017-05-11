```
Linux机器重启后首先会去读取/etc/init.d目录下的脚本，所以如果想要某个程序在开机时启动则可以在/etc/ininit.d目录下写个脚本，并且设置一下开机启动服务的顺序就可以了，下面以设置tomcat程序开机自启动为例讲解一下设置过程

1.进入/etc/init.d目录下，执行vi tomcat命令，编写tomcat的启动脚本

tomcat的脚本内容如下：

#!/bin/bash
export JENKINS_HOME=/usr1/jenkins
export PATH=$PATH:$JENKINS_HOME
export CATALINA_BASE=/usr1/tools/tomcat
export CATALINA_HOME=/usr1/tools/tomcat
export CATALINA_TMPDIR=/usr1/tools/tomcat/temp
export JRE_HOME=/usr1/tools/jdk1.7.0_60
sh /usr1/tools/tomcat/bin/catalina.sh start


注意：  
   (1).需要在脚本中设置JENKINS_HOME的环境变量，如果不设置的话，启动tomcat时JENKINS_HOME会为默认值，从而导致Jenkins上的工程和节点信息丢失
   (2).需要设置Java和tomcat的安装路径，因为在执行该脚本时，机器上的profile脚本中设置的Java、tomcat和JENKINS_HOME的环境变量此时还未读取到，所以需        要在该脚本中重新设置
   (3).如果需要非root用户启动tomcat，需要在执行catalina.sha脚本时切换用户，脚本如下：
     su -c 'sh /usr1/tools/tomcat/bin/catalina.sh start' username
2.给tomcat脚本加上执行权限
  chmod +x tomcat
3.将tomcat脚本设置成系统启动时自动执行
 chkconfig --add tomcat
 执行chkconfig --list tomcat命令查看tomcat服务的启动级别
 
上图表示tomcat服务在启动级别为3、5的情况下会自动启动，一般情况下直接执行chkconfig --add命令时默认级别都为3和5
下面介绍一下Linux系统的运行级别：
等级0表示：表示关机
等级1表示：单用户模式
等级2表示：无网络连接的多用户命令行模式
等级3表示：有网络连接的多用户命令行模式
等级4表示：不可用
等级5表示：带图形界面的多用户模式
等级6表示：重新启动
chkconfig的命令如下：

chkconfig --list //命令查看已设置的开启自启动列表。
xxxd 0:off 1:off 2:on ... 6:off //list的结果，表示在xxxd服务在启动级别为2 3 4 5 的情况下会自动启动。 
chkconfig --add xxxd//向任务列表中添加一个xxxd服务
chkconfig [--level 1/2/../6] xxxd on/off//设置xxxd用服务在n状态为开/关，[]内省略则在2345级别开启
chkconfig --del xxxd //将任务列表中的xxxd服务删除

```
