 ```
 nohup  hive --service metastore 2>&1  >> metastore.log  &
 nohup  hiveserver2 start 2>&1 >> hiveserver2.log & 
 ```
