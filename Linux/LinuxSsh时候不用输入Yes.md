```
方法一：连接时加入StrictHostKeyChecking=no
ssh -o StrictHostKeyChecking=no root@192.168.1.100

方法二：修改/etc/ssh/ssh_config配置文件，添加：
StrictHostKeyChecking no

此外，为防止出现这类警告：POSSIBLE BREAK-IN ATTEMPT!
Address 192.168.0.101 maps to localhost, but this does not map back to the address - POSSIBLE BREAK-IN ATTEMPT!
可以将/etc/ssh/ssh_config配置文件中的：
GSSAPIAuthentication yes
改为：
GSSAPIAuthentication no
可以使用sed流编辑器来完成修改：
sed -i 's/#   StrictHostKeyChecking ask/StrictHostKeyChecking no/' /etc/ssh/ssh_config
sed -i 's/GSSAPIAuthentication yes/GSSAPIAuthentication no/' /etc/ssh/ssh_config
service sshd restart

```
