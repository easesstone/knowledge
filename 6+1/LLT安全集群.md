 ``` 
 集群时间，不同步的时候，可能会出现kerbros 认证不了的现象。
 [foreach] 
  [foreach]      [exec] 17/04/11 07:33:07 INFO retry.RetryInvocationHandler: Exception while invoking getFileInfo of clas
  s ClientNamenodeProtocolTranslatorPB over /10.162.174.169:65110 after 1 fail over attempts. Trying to fail over immediately.
  [foreach] 
  [foreach]      [exec] java.io.IOException: Failed on local exception: java.io.IOException: javax.security.sasl.SaslException: 
  GSS initiate failed [Caused by GSSException: No valid credentials provided (Mechanism level: Failed to find any Kerberos tgt)]; 
  Host Details : local host is: "SZV1000176644/10.162.174.174"; destination host is: "SZV1000176595":65110; 
  [foreach] 
  [foreach]      [exec] 	at org.apache.hadoop.net.NetUtils.wrapException(NetUtils.java:796)
  [foreach] 
  [foreach]      [exec] 	at org.apache.hadoop.ipc.Client.call(Client.java:1515)
  [foreach] 
  [foreach]      [exec] 	at org.apache.hadoop.ipc.Client.call(Client.java:1447)
  [foreach]      [exec] 	at org.apache.hadoop.ipc.ProtobufRpcEngine$Invoker.invoke(ProtobufRpcEngine.java:230)
  [foreach]      [exec] 	at com.sun.proxy.$Proxy9.getFileInfo(Unknown Source)
  [foreach]      [exec] 	at org.apache.hadoop.hdfs.protocolPB.ClientNamenodeProtocolTranslatorPB.getFileInfo
  (ClientNamenodeProtocolTranslatorPB.java:802)
  [foreach]      [exec] 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
  [foreach]      [exec] 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
  [foreach]      [exec] 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
  [foreach]      [exec] 	at java.lang.reflect.Method.invoke(Method.java:497)
  [foreach]      [exec] 	at org.apache.hadoop.io.retry.RetryInvocationHandler.invokeMethod(RetryInvocationHandler.java:201)
  [foreach]      [exec] 	at org.apache.hadoop.io.retry.RetryInvocationHandler.invoke(RetryInvocationHandler.java:103)
  [foreach]      [exec] 	at com.sun.proxy.$Proxy10.getFileInfo(Unknown Source)
  [foreach]      [exec] 	at org.apache.hadoop.hdfs.DFSClient.getFileInfo(DFSClient.java:1874)
  [foreach]      [exec] 	at org.apache.hadoop.hdfs.DistributedFileSystem$27.doCall(DistributedFileSystem.java:1500)
  [foreach]      [exec] 	at org.apache.hadoop.hdfs.DistributedFileSystem$27.doCall(DistributedFileSystem.java:1496)
  [foreach]      [exec] 	at org.apache.hadoop.fs.FileSystemLinkResolver.resolve(FileSystemLinkResolver.java:81)
  [foreach]      [exec] 	at org.apache.hadoop.hdfs.DistributedFileSystem.getFileStatus(DistributedFileSystem.java:1496)
  [foreach]      [exec] 	at org.apache.hadoop.fs.Globber.getFileStatus(Globber.java:59)
  [foreach]      [exec] 	at org.apache.hadoop.fs.Globber.glob(Globber.java:204)
  [foreach]      [exec] 	at org.apache.hadoop.fs.Globber.glob(Globber.java:164)
  [foreach]      [exec] 	at org.apache.hadoop.fs.FileSystem.globStatus(FileSystem.java:1684)
  [foreach]      [exec] 	at org.apache.hadoop.fs.shell.PathData.expandAsGlob(PathData.java:330)
  [foreach]      [exec] 	at org.apache.hadoop.fs.shell.Command.expandArgument(Command.java:235)
  [foreach]      [exec] 	at org.apache.hadoop.fs.shell.Command.expandArguments(Command.java:218)
  [foreach]      [exec] 	at org.apache.hadoop.fs.shell.Command.processRawArguments(Command.java:201)
  [foreach]      [exec] 	at org.apache.hadoop.fs.shell.Command.run(Command.java:165)
  [foreach]      [exec] 	at org.apache.hadoop.fs.FsShell.run(FsShell.java:296)
  [foreach]      [exec] 	at org.apache.hadoop.util.ToolRunner.run(ToolRunner.java:70)
  [foreach]      [exec] 	at org.apache.hadoop.util.ToolRunner.run(ToolRunner.java:84)
  [foreach]      [exec] 	at org.apache.hadoop.fs.FsShell.main(FsShell.java:352)
  [foreach]      [exec] Caused by: java.io.IOException: javax.security.sasl.SaslException: GSS initiate failed 
  [Caused by GSSException: No valid credentials provided (Mechanism level: Failed to find any Kerberos tgt)]
  [foreach]      [exec] 	at org.apache.hadoop.ipc.Client$Connection$1.run(Client.java:722)
  [foreach]      [exec] 	at java.security.AccessController.doPrivileged(Native Method)
  [foreach]      [exec] 	at javax.security.auth.Subject.doAs(Subject.java:422)
  [foreach]      [exec] 	at org.apache.hadoop.security.UserGroupInformation.doAs(UserGroupInformation.java:1738)
  [foreach]      [exec] 	at org.apache.hadoop.ipc.Client$Connection.handleSaslConnectionFailure(Client.java:685)
  [foreach]      [exec] 	at org.apache.hadoop.ipc.Client$Connection.setupIOstreams(Client.java:772)
  [foreach]      [exec] 	at org.apache.hadoop.ipc.Client$Connection.access$2900(Client.java:394)
  [foreach]      [exec] 	at org.apache.hadoop.ipc.Client.getConnection(Client.java:1564)
  [foreach]      [exec] 	at org.apache.hadoop.ipc.Client.call(Client.java:1486)
  [foreach]      [exec] 	... 29 more
  [foreach]      [exec] Caused by: javax.security.sasl.SaslException: GSS initiate failed [Caused by GSSException: 
  No valid credentials provided (Mechanism level: Failed to find any Kerberos tgt)]
  [foreach]      [exec] 	at com.sun.security.sasl.gsskerb.GssKrb5Client.evaluateChallenge(GssKrb5Client.java:211)
  [foreach]      [exec] 	at org.apache.hadoop.security.SaslRpcClient.saslConnect(SaslRpcClient.java:421)
  [foreach]      [exec] 	at org.apache.hadoop.ipc.Client$Connection.setupSaslConnection(Client.java:581)
  [foreach]      [exec] 	at org.apache.hadoop.ipc.Client$Connection.access$1900(Client.java:394)
  [foreach]      [exec] 	at org.apache.hadoop.ipc.Client$Connection$2.run(Client.java:764)
  [foreach]      [exec] 	at org.apache.hadoop.ipc.Client$Connection$2.run(Client.java:760)
  [foreach]      [exec] 	at java.security.AccessController.doPrivileged(Native Method)
  [foreach]      [exec] 	at javax.security.auth.Subject.doAs(Subject.java:422)
  [foreach]      [exec] 	at org.apache.hadoop.security.UserGroupInformation.doAs(UserGroupInformation.java:1738)
  [foreach]      [exec] 	at org.apache.hadoop.ipc.Client$Connection.setupIOstreams(Client.java:759)
  [foreach]      [exec] 	... 32 more
  [foreach]      [exec] Caused by: GSSException: No valid credentials provided (Mechanism level: Failed to find any Kerberos tgt)
  [foreach]      [exec] 	at sun.security.jgss.krb5.Krb5InitCredential.getInstance(Krb5InitCredential.java:147)
  [foreach]      [exec] 	at sun.security.jgss.krb5.Krb5MechFactory.getCredentialElement(Krb5MechFactory.java:122)
  [foreach]      [exec] 	at sun.security.jgss.krb5.Krb5MechFactory.getMechanismContext(Krb5MechFactory.java:187)
  [foreach]      [exec] 	at sun.security.jgss.GSSManagerImpl.getMechanismContext(GSSManagerImpl.java:224)
  [foreach]      [exec] 	at sun.security.jgss.GSSContextImpl.initSecContext(GSSContextImpl.java:212)
  [foreach]      [exec] 	at sun.security.jgss.GSSContextImpl.initSecContext(GSSContextImpl.java:179)
  [foreach]      [exec] 	at com.sun.security.sasl.gsskerb.GssKrb5Client.evaluateChallenge(GssKrb5Client.java:192)
  [foreach]      [exec] 	... 41 more
  [foreach]      [exec] 17/04/11 07:33:07 WARN ipc.Client: Exception encountered while connecting to the server :
  javax.security.sasl.SaslException: GSS initiate failed [Caused by GSSException: No valid credentials provided (
  Mechanism level: Failed to find 
