## 主要有两种启动方式：

###  一，把其放在tomcat 环境中运行
```
需要注意的点：
1，在catalina.sh  的脚本开头，加上环境变量：
export CATALINA_OPTS="-Xms4G -Xmx8G -XX:NewSize=256M -XX:MaxNewSize=512M -XX:PermSize=1G -XX:MaxPermSize=1G"
这样配置的原因，可以防止tomcat 内容不足的情况。
2，启动命令，在tomcat 安装目录下：
./catalina.sh start 
```

### 二，利用命令的形式启动。
```
把jenkins.war 放到一个启动目录里。
java -Xmx8196m -Xms3096m -XX:MaxPermSize=2048m -jar /jenkins/jenkins.war > nohup.out  2>&1 &
两个过程中
在/etc/profile 中，可以用如下变量定义jenkins 的环境的工作目录。
export JENKINS_HOME=/usr1/jenkins/jenkins_home 
```
