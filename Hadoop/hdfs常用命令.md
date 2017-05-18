## 显示文件目录
hadoop fs -ls -R  /user/hadoop  
hadoop fs -lsr /user/hadoop  
hadoop fs -ls /user/hadoop  
## 创建目录
hadoop fs -mkdir -p  /user/nima/nima  
hadoop fs -mkdir /user/nima/nima  
## 从本地把文件放到hdfs 文件系统
hadoop fs -put hello.txt  /user/test  
## 从hdfs 中，把文件取出放到本地
hadoop fs -get  /user/test/hello.txt  ./  
## 从文件系统中读取内容 
hadoop fs -cat  /user/test/hello.txt  |tail -10
