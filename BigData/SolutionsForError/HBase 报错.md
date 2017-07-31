解决：conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem")
```
java.io.IOException: No FileSystem for scheme: hdfs
	at org.apache.hadoop.fs.FileSystem.getFileSystemClass(FileSystem.java:2644)
	at org.apache.hadoop.fs.FileSystem.createFileSystem(FileSystem.java:2651)
	at org.apache.hadoop.fs.FileSystem.access$200(FileSystem.java:92)
	at org.apache.hadoop.fs.FileSystem$Cache.getInternal(FileSystem.java:2687)
	at org.apache.hadoop.fs.FileSystem$Cache.get(FileSystem.java:2669)
	at org.apache.hadoop.fs.FileSystem.get(FileSystem.java:371)
	at org.apache.hadoop.fs.Path.getFileSystem(Path.java:295)
	at org.apache.hadoop.hbase.util.DynamicClassLoader.<init>(DynamicClassLoader.java:104)
	at org.apache.hadoop.hbase.protobuf.ProtobufUtil.<clinit>(ProtobufUtil.java:232)
	at org.apache.hadoop.hbase.ClusterId.parseFrom(ClusterId.java:64)
	at org.apache.hadoop.hbase.zookeeper.ZKClusterId.readClusterIdZNode(ZKClusterId.java:75)
	at org.apache.hadoop.hbase.client.ZooKeeperRegistry.getClusterId(ZooKeeperRegistry.java:86)
	at org.apache.hadoop.hbase.client.ConnectionManager$HConnecti
  onImplementation.retrieveClusterId(ConnectionManager.java:833)
	at org.apache.hadoop.hbase.client.ConnectionManager$HConnectionImplemen
  tation.<init>(ConnectionManager.java:623)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl
  .newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.
  newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at org.apache.hadoop.hbase.client.ConnectionFactory.createConnection(ConnectionFactory.java:238)
	at org.apache.hadoop.hbase.client.ConnectionFactory.createConnection(ConnectionFactory.java:218)
	at org.apache.hadoop.hbase.client.ConnectionFactory.createConnection(ConnectionFactory.java:119)
	at com.hzgc.hbase.util.HBaseHelper.initHBaseConnection(HBaseHelper.java:58)
	at com.hzgc.hbase.util.HBaseHelper.getHBaseConnection(HBaseHelper.java:72)
	at com.hzgc.hbase.util.HBaseHelper.getTable(HBaseHelper.java:176)
	at com.hzgc.hbase.staticrepo.ObjectInfoHandlerImpl
  .addObjectInfo(ObjectInfoHandlerImpl.java:39)
	at com.hzgc.hbase.staticreposuite.StaticRepoUtilSuite.
  testAddObjectInfo(StaticRepoUtilSuite.java:44)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
	at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.star
  tRunnerWithArgs(IdeaTestRunner.java:51)
	at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndS
  tart(JUnitStarter.java:242)
	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)
  ```
  
  解决：
  在工程run ===> edict Configuration 的那里：
  配置一个Hadoop windows 的home
  
  ```
  2017-07-31 14:17:03,211 : ERROR :
  Shell : Failed to locate the winutils binary in the hadoop binary path
java.io.IOException: Could not locate executable
null\bin\winutils.exe in the Hadoop binaries.
	at org.apache.hadoop.util.Shell.getQualifiedBinPath(Shell.java:356)
	at org.apache.hadoop.util.Shell.getWinUtilsPath(Shell.java:371)
	at org.apache.hadoop.util.Shell.<clinit>(Shell.java:364)
	at org.apache.hadoop.util.StringUtils.<clinit>(StringUtils.java:80)
	at org.apache.hadoop.conf.Configuration.getBoolean(Configuration.java:1437)
	at org.apache.hadoop.hbase.HBaseConfiguration.checkDefaultsVersion(HBaseConfiguration.java:67)
	at org.apache.hadoop.hbase.HBaseConfiguration.addHbaseResources(HBaseConfiguration.java:81)
	at org.apache.hadoop.hbase.HBaseConfiguration.create(HBaseConfiguration.java:96)
	at com.hzgc.hbase.util.HBaseHelper.initHBaseConfiguration(HBaseHelper.java:33)
	at com.hzgc.hbase.util.HBaseHelper.getHBaseConfiguration(HBaseHelper.java:48)
	at com.hzgc.hbase.util.HBaseHelper.initHBaseConnection(HBaseHelper.java:58)
	at com.hzgc.hbase.util.HBaseHelper.getHBaseConnection(HBaseHelper.java:72)
	at com.hzgc.hbase.util.HBaseHelper.getTable(HBaseHelper.java:176)
	at com.hzgc.hbase.staticrepo.
  ObjectInfoHandlerImpl.addObjectInfo(ObjectInfoHandlerImpl.java:39)
	at com.hzgc.hbase.staticreposuite.
  StaticRepoUtilSuite.testAddObjectInfo(StaticRepoUtilSuite.java:44)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
	at com.intellij.rt.execution.junit.IdeaTestRunne
  r$Repeater.startRunnerWithArgs(IdeaTestRunner.java:51)
	at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)

  ```
  
