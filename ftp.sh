```
#!/bin/bash
ftp_pid=$(jps | grep KafkaOverFtpServer | awk '{print $1}')
echo "ftp's pid is: ${ftp_pid}"
if [ -n ${ftp_pid} ];then
    echo "ftp process is exit, first to kill it.then to start ftp."
    kill -9 ${ftp_pid}
    sh /opt/RealTimeFace/bin/start-ftpserver.sh
else 
    echo "ftp process is not exit, just to start ftp."
    sh /opt/RealTimeFace/bin/start-ftpserver.sh
fi
ftp_pid_restart=$(jps | grep KafkaOverFtpServer | awk '{print $1}')
sleep 10s
echo "starting, please wait........"
if [ -z ${ftp_pid_restart} ];then
    echo "start ftp failed....."
    sh  /opt/RealTimeFace/bin/start-ftpserver.sh
    ftp_pid_retry=$(jps | grep KafkaOverFtpServer | awk '{print $1}')
    if [ -z  ${ftp_pid_retry} ];then
        echo "retry start ftp failed, please check the config......"
    else
        echo "secondary try start ftp sucess."
    fi
else 
    echo "start ftp sucess."
fi
```
