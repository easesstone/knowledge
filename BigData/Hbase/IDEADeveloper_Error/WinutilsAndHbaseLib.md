```
六月 21, 2017 3:46:42 下午 org.apache.hadoop.util.Shell getWinUtilsPath
严重: Failed to locate the winutils binary in the hadoop binary path
java.io.IOException: Could not locate executable null\bin\winutils.exe in the Hadoop binaries.
	at org.apache.hadoop.util.Shell.getQualifiedBinPath(Shell.java:356)
	at org.apache.hadoop.util.Shell.getWinUtilsPath(Shell.java:371)
	at org.apache.hadoop.util.Shell.<clinit>(Shell.java:364)
	at org.apache.hadoop.util.StringUtils.<clinit>(StringUtils.java:80)
	at org.apache.hadoop.conf.Configuration.getBoolean(Configuration.java:1437)
	at org.apache.hadoop.hbase.HBaseConfiguration.checkDefaultsVersion(HBaseConfiguration.java:67)
	at org.apache.hadoop.hbase.HBaseConfiguration.addHbaseResources(HBaseConfiguration.java:81)
	at org.apache.hadoop.hbase.HBaseConfiguration.create(HBaseConfiguration.java:96)
	at client.PutExample.main(PutExample.java:22)
  
  
  警告: Failed to identify the fs of dir hdfs://master:9000/hbase/lib, ignored
java.io.IOException: No FileSystem for scheme: hdfs
	at org.apache.hadoop.fs.FileSystem.getFileSystemClass(FileSystem.java:2644)
	at org.apache.hadoop.fs.FileSystem.createFileSystem(FileSystem.java:2651)
	at org.apache.hadoop.fs.FileSystem.access$200(FileSystem.java:92)
	at org.apache.hadoop.fs.FileSystem$Cache.getInternal(FileSystem.java:2687)
	at org.apache.hadoop.fs.FileSystem$Cache.get(FileSystem.java:2669)
	at org.apache.hadoop.fs.FileSystem.get(FileSystem.java:371)
	at org.apache.hadoop.fs.Path.getFileSystem(Path.java:295)
	at org.apache.hadoop.hbase.util.DynamicClassLoader.initTempDir(DynamicClassLoader.java:120)
	at org.apache.hadoop.hbase.util.DynamicClassLoader.<init>(DynamicClassLoader.java:98)
	at org.apache.hadoop.hbase.protobuf.ProtobufUtil.<clinit>(ProtobufUtil.java:246)
	at org.apache.hadoop.hbase.ClusterId.parseFrom(ClusterId.java:64)
	at org.apache.hadoop.hbase.zookeeper.ZKClusterId.readClusterIdZNode(ZKClusterId.java:75)
	at org.apache.hadoop.hbase.client.ZooKeeperRegistry.getClusterId(ZooKeeperRegistry.java:105)
	at org.apache.hadoop.hbase.client.ConnectionManager$HConnectionImplementation.retrieveClusterId(ConnectionManager.java:907)
	at org.apache.hadoop.hbase.client.ConnectionManager$HConnectionImplementation.<init>(ConnectionManager.java:691)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at org.apache.hadoop.hbase.client.ConnectionFactory.createConnection(ConnectionFactory.java:238)
	at org.apache.hadoop.hbase.client.ConnectionFactory.createConnection(ConnectionFactory.java:218)
	at org.apache.hadoop.hbase.client.ConnectionFactory.createConnection(ConnectionFactory.java:119)
	at util.HBaseHelper.<init>(HBaseHelper.java:37)
	at util.HBaseHelper.getHelper(HBaseHelper.java:42)
	at client.PutExample.main(PutExample.java:27)


```
