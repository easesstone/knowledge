HBase 在加载协处理器的时候加载不到类

```
org.apache.hadoop.hbase.DoNotRetryIOException: org.apache.hadoop.hbase.DoNotRetryIOException:
Class com.hzgc.hbase2es.coproccessor.PersonRepo2EsObserver cannot be loaded Set hbase.table.sanity.checks 
to false at conf or table descriptor if you want to bypass sanity checks
	at org.apache.hadoop.hbase.master.HMaster.warnOrThrowExceptionForFailure(HMaster.java:1754)
	at org.apache.hadoop.hbase.master.HMaster.sanityCheckTableDescriptor(HMaster.java:1615)
	at org.apache.hadoop.hbase.master.HMaster.modifyTable(HMaster.java:2142)
	at org.apache.hadoop.hbase.master.MasterRpcServices.modifyTable(MasterRpcServices.java:1171)
	at org.apache.hadoop.hbase.protobuf.generated.MasterProtos$MasterService$2.callBlockingMethod(MasterProtos.java:55680)
	at org.apache.hadoop.hbase.ipc.RpcServer.call(RpcServer.java:2196)
	at org.apache.hadoop.hbase.ipc.CallRunner.run(CallRunner.java:112)
	at org.apache.hadoop.hbase.ipc.RpcExecutor.consumerLoop(RpcExecutor.java:133)
	at org.apache.hadoop.hbase.ipc.RpcExecutor$1.run(RpcExecutor.java:108)
	at java.lang.Thread.run(Thread.java:748)

	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at org.apache.hadoop.ipc.RemoteException.instantiateException(RemoteException.java:106)
	at org.apache.hadoop.ipc.RemoteException.unwrapRemoteException(RemoteException.java:95)
	at org.apache.hadoop.hbase.client.RpcRetryingCaller.translateException(RpcRetryingCaller.java:226)
	at org.apache.hadoop.hbase.client.RpcRetryingCaller.translateException(RpcRetryingCaller.java:240)
	at org.apache.hadoop.hbase.client.RpcRetryingCaller.callWithRetries(RpcRetryingCaller.java:140)
	at org.apache.hadoop.hbase.client.HBaseAdmin.executeCallable(HBaseAdmin.java:3601)
	at org.apache.hadoop.hbase.client.HBaseAdmin.modifyTable(HBaseAdmin.java:2172)
	at com.hzgc.hbase2es.util.HBaseHelper.createTableWithCoprocessor(HBaseHelper.java:177)
	at com.hzgc.hbase2es.table.CreatePersonRepoTable.main(CreatePersonRepoTable.java:91)
Caused by: org.apache.hadoop.hbase.ipc.RemoteWithExtrasException(org.apache.hadoop.hbase.DoNotRetryIOException):
org.apache.hadoop.hbase.DoNotRetryIOException: Class com.hzgc.hbase2es.coproccessor.PersonRepo2EsObserver cannot be loaded 
Set hbase.table.sanity.checks to false at conf or table descriptor if you want to bypass sanity checks
	at org.apache.hadoop.hbase.master.HMaster.warnOrThrowExceptionForFailure(HMaster.java:1754)
	at org.apache.hadoop.hbase.master.HMaster.sanityCheckTableDescriptor(HMaster.java:1615)
	at org.apache.hadoop.hbase.master.HMaster.modifyTable(HMaster.java:2142)
	at org.apache.hadoop.hbase.master.MasterRpcServices.modifyTable(MasterRpcServices.java:1171)
	at org.apache.hadoop.hbase.protobuf.generated.MasterProtos$MasterService$2.callBlockingMethod(MasterProtos.java:55680)
	at org.apache.hadoop.hbase.ipc.RpcServer.call(RpcServer.java:2196)
	at org.apache.hadoop.hbase.ipc.CallRunner.run(CallRunner.java:112)
	at org.apache.hadoop.hbase.ipc.RpcExecutor.consumerLoop(RpcExecutor.java:133)
	at org.apache.hadoop.hbase.ipc.RpcExecutor$1.run(RpcExecutor.java:108)
	at java.lang.Thread.run(Thread.java:748)

	at org.apache.hadoop.hbase.ipc.RpcClientImpl.call(RpcClientImpl.java:1254)
	at org.apache.hadoop.hbase.ipc.AbstractRpcClient.callBlockingMethod(AbstractRpcClient.java:216)
	at org.apache.hadoop.hbase.ipc.AbstractRpcClient$BlockingRpcChannelImplementation.callBlockingMethod(AbstractRpcClient.java:300)
	at org.apache.hadoop.hbase.protobuf.generated.MasterProtos$MasterService$BlockingStub.modifyTable(MasterProtos.java:44776)
	at org.apache.hadoop.hbase.client.ConnectionManager$HConnectionImplementation$4.modifyTable(ConnectionManager.java:1803)
	at org.apache.hadoop.hbase.client.HBaseAdmin$24.call(HBaseAdmin.java:2176)
	at org.apache.hadoop.hbase.client.HBaseAdmin$24.call(HBaseAdmin.java:2172)
	at org.apache.hadoop.hbase.client.RpcRetryingCaller.callWithRetries(RpcRetryingCaller.java:126)
	... 4 more

```
