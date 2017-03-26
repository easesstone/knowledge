```shell
#!/bin/bash

#set -ex

#################################################
####### some tip for srcipt        ##############
#################################################
function  show_some_tips()
{
	echo "1,remenber to add #!/bin/bash befor the script."
	echo "2,变量多的时候，函数内的变量应该声明为local，否则它是全局的变量。"
	echo "3,在脚本开头，加上set -ex"
}


#########################################
######   Linux 系统信息提取工具  ########
#########################################
function linux_version_details()
{
	echo "##########################################################################################"
	echo "#######################   Linux 系统详细信息      ########################################"
	echo "##########################################################################################"
	echo 
	#lsb_release -a
	#umame -a  （结果类似如下）
	#Linux master 4.4.0-21-generic #37-Ubuntu SMP Mon Apr 18 18:33:37 UTC 2016 x86_64 x86_64 x86_64 GNU/Linux
	#cat /etc/issue
	cat /etc/*release*
	local HOSTNAME=`hostname`
	local IP=$(ifconfig  | grep inet | grep -v inet6 | head -1 | awk '{print $2}' | awk -F ':' '{print $2}')
	local JAVA_HOME_TEMP=$JAVA_HOME
	echo  "hostname=${HOSTNAME}"
	echo  "ip=${IP}"
}


function main()
{ 
	linux_version_details
}														

main

```
