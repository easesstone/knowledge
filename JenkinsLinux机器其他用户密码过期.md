## 解决办法
### 详见如下方法。
```
云机器或者说Linux机器，其新建用户密码的有效期默认是180天，可以在/etc/login.defs中查看PASS_MAX_DAYS变量，默认是180天，并且执行chage -M days user生效。
LLT安全集群安装过程中，使用ant进行SSH执行命令，不光使用了securedn 用户，还有hdfs用户，都需要修改。
sed -i "s/PASS_MAX_DAYS 180/PASS_MAX_DAYS 99999/g" /etc/login.defs;
chage -M 99999 securedn
chage -M 99999 hdfs

(参考：)
for i in `cat /etc/passwd |cut -f 1 -d :`;do echo $i;chage -l $i; done 
```
