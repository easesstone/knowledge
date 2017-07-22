```
ERROR: org.apache.hadoop.hbase.PleaseHoldException: Master is initializing
	at org.apache.hadoop.hbase.master.HMaster.checkInitialized(HMaster.java:2379)
	at org.apache.hadoop.hbase.master.MasterRpcServices.getTableNames(MasterRpcServices.java:900)
	at org.apache.hadoop.hbase.protobuf.generated.MasterProtos$MasterService$2.callBlockingMethod(MasterProtos.java:55650)
	at org.apache.hadoop.hbase.ipc.RpcServer.call(RpcServer.java:2196)
	at org.apache.hadoop.hbase.ipc.CallRunner.run(CallRunner.java:112)
	at org.apache.hadoop.hbase.ipc.RpcExecutor.consumerLoop(RpcExecutor.java:133)
	at org.apache.hadoop.hbase.ipc.RpcExecutor$1.run(RpcExecutor.java:108)
	at java.lang.Thread.run(Thread.java:748)

Here is some help for this command:
```
