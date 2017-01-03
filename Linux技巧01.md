### 1) grep string_search  path  -R -A  line_num
    -R  ：  循环遍历，
    -A  ：  后面N行，
    -B  ：  前面N行，
    eg: 遍历当前目录下所有文件中含有“namenode”字符创该行的后面2行  grep namenode ./ -R -A 

### 2） jar -tf ***jar |grep -i text
    eg: 在jar中查找text文本内容

### 3） find -name hadoop-common*jar -exec ls -l {} \;
    查出所有的文件并展示详细列表，-exec 参数为任何执行命令，"{}"表示前面过滤的数据，以“；”结尾，但是需要转移，则为"\;"

### 4） nm -D libCavium4J.so |grep generateKey
    Linux如何查看so中是否包含某个函数 

### 5） nm -A /usr/lib/* 2>/dev/null | grep "T memset"
    在 /usr/lib/ 目录下找出哪个库文件定义了memset函数

### 6）hdfs执行命令开启debug，在hadoop-env.sh中加上以下参数
    export HADOOP_ROOT_LOGGER=DEBUG,RFA

### 7 ) 在控制台打印hadoop日志
    export HADOOP_CLIENT_OPTS=-Dhadoop.root.logger=DEBUG,console

### 8）重定向日志
    hdfs dfs -ls / >/dev/null 2> test.log&

### 9） 集群间时间同步
    ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
    hwclock -w
    
### 10) 释放内存
    sync
    echo 3 > /proc/sys/vm/drop_caches
    
### 11) 删除a.txt中含"abc"的行，将操作之后的结果保存到a.log
    sed -e '/abc/d'  a.txt  > a.log   

### 12) 检查什么时候重启系统
    方法一（用于reboot的场景）： who -b
    方法二（用于shutdown的场景）： last -x|grep shutdown
    
### 13） 检查目录下文件个数
    find /export2/BigData/datanode/dn1 -type f | wc -l
    
### 14) 解压jar 包
    例如：unzip zk-helper-1.0.jar  -d hello
