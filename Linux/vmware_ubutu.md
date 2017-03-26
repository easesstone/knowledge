###  允许远程登录 
1，sudo apt-get install openssh-server   
2，sudo apt-get install vim  
3，vi　/etc/ssh/sshd_config   
4，修改里面的PermitRootLogin 的值为yes  
5，service ssh restart 
