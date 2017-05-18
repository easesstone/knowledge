显示文件目录
```
root:/opt/ldl # hadoop fs -lsr /user/hadoop 
lsr: DEPRECATED: Please use 'ls -R' instead.
17/05/18 15:35:34 WARN util.NativeCodeLoader: Unable to load native-hadoop library for your platform...
using builtin-java classes where applicable
drwxr-xr-x   - root supergroup          0 2017-05-18 15:35 /user/hadoop/dir1
-rw-r--r--   3 root supergroup         13 2017-05-18 15:35 /user/hadoop/dir1/ldl.txt
drwxr-xr-x   - root supergroup          0 2017-05-17 14:27 /user/hadoop/dir2
-rw-r--r--   3 root supergroup         13 2017-05-17 16:04 /user/hadoop/ldl.txt
root:/opt/ldl # hadoop fs -ls /user/hadoop 
17/05/18 15:38:43 WARN util.NativeCodeLoader: Unable to load native-hadoop library for your platform... 
using builtin-java classes where applicable
Found 3 items
drwxr-xr-x   - root supergroup          0 2017-05-18 15:35 /user/hadoop/dir1
drwxr-xr-x   - root supergroup          0 2017-05-17 14:27 /user/hadoop/dir2
-rw-r--r--   3 root supergroup         13 2017-05-17 16:04 /user/hadoop/ldl.txt
root:/opt/ldl # hadoop fs -ls -R  /user/hadoop 
17/05/18 15:38:54 WARN util.NativeCodeLoader: Unable to load native-hadoop library for your platform... 
using builtin-java classes where applicable
drwxr-xr-x   - root supergroup          0 2017-05-18 15:35 /user/hadoop/dir1
-rw-r--r--   3 root supergroup         13 2017-05-18 15:35 /user/hadoop/dir1/ldl.txt
drwxr-xr-x   - root supergroup          0 2017-05-17 14:27 /user/hadoop/dir2
-rw-r--r--   3 root supergroup         13 2017-05-17 16:04 /user/hadoop/ldl.txt
```
创建目录
```

```
