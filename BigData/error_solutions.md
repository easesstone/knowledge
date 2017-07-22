HBase 集群中：
本地时间和服务器时间，以及服务器防火墙开着的原因。
需要关闭防火墙，
需要更改时间。
删除日记，重启集群环境。
```
2017-07-21 20:11:32,357 [main-SendThread(ldl:2181)] WARN  [org.apache.zookeeper.ClientCnxn] 
- Session 0x0 for server null, unexpected error, closing socket connection and attempting reconnect
java.net.ConnectException: Connection timed out: no further information
	at sun.nio.ch.SocketChannelImpl.checkConnect(Native Method)
	at sun.nio.ch.SocketChannelImpl.finishConnect(SocketChannelImpl.java:717)
	at org.apache.zookeeper.ClientCnxnSocketNIO.doTransport(ClientCnxnSocketNIO.java:361)
	at org.apache.zookeeper.ClientCnxn$SendThread.run(ClientCnxn.java:1081)
2017-07-21 20:11:33,461 [main-SendThread(ldl:2181)] INFO  [org.apache.zookeeper.ClientCnxn] 
- Opening socket connection to server ldl/172.18.18.22:2181. Will not attempt to authenticate using SASL (unknown error)
2017-07-21 20:11:54,463 [main-SendThread(ldl:2181)] WARN  [org.apache.zookeeper.ClientCnxn]
- Session 0x0 for server null, unexpected error, closing socket connection and attempting reconnect
java.net.ConnectException: Connection timed out: no further information
	at sun.nio.ch.SocketChannelImpl.checkConnect(Native Method)
	at sun.nio.ch.SocketChannelImpl.finishConnect(SocketChannelImpl.java:717)
	at org.apache.zookeeper.ClientCnxnSocketNIO.doTransport(ClientCnxnSocketNIO.java:361)
	at org.apache.zookeeper.ClientCnxn$SendThread.run(ClientCnxn.java:1081)
2017-07-21 20:11:55,563 [main-SendThread(ldl:2181)] INFO  [org.apache.zookeeper.ClientCnxn] 
- Opening socket connection to server ldl/172.18.18.22:2181. Will not attempt to authenticate using SASL (unknown error)
2017-07-21 20:12:16,571 [main-SendThread(ldl:2181)] WARN  [org.apache.zookeeper.ClientCnxn] 
- Session 0x0 for server null, unexpected error, closing socket connection and attempting reconnect
java.net.ConnectException: Connection timed out: no further information
	at sun.nio.ch.SocketChannelImpl.checkConnect(Native Method)
	at sun.nio.ch.SocketChannelImpl.finishConnect(SocketChannelImpl.java:717)
	at org.apache.zookeeper.ClientCnxnSocketNIO.doTransport(ClientCnxnSocketNIO.java:361)
	at org.apache.zookeeper.ClientCnxn$SendThread.run(ClientCnxn.java:1081)
2017-07-21 20:12:17,671 [main-SendThread(ldl:2181)] INFO  [org.apache.zookeeper.ClientCnxn] 
- Opening socket connection to server ldl/172.18.18.22:2181. Will not attempt to authenticate using SASL (unknown error)
2017-07-21 20:12:38,667 [main-SendThread(ldl:2181)] WARN  [org.apache.zookeeper.ClientCnxn] 
- Session 0x0 for server null, unexpected error, closing socket connection and attempting reconnect
java.net.ConnectException: Connection timed out: no further information
	at sun.nio.ch.SocketChannelImpl.checkConnect(Native Method)
	at sun.nio.ch.SocketChannelImpl.finishConnect(SocketChannelImpl.java:717)
	at org.apache.zookeeper.ClientCnxnSocketNIO.doTransport(ClientCnxnSocketNIO.java:361)
	at org.apache.zookeeper.ClientCnxn$SendThread.run(ClientCnxn.java:1081)
2017-07-21 20:12:39,767 [main-SendThread(ldl:2181)] INFO  [org.apache.zookeeper.ClientCnxn] 
- Opening socket connection to server ldl/172.18.18.22:2181. Will not attempt to authenticate using SASL (unknown error)
2017-07-21 20:13:00,769 [main-SendThread(ldl:2181)] WARN  [org.apache.zookeeper.ClientCnxn] 
- Session 0x0 for server null, unexpected error, closing socket connection and attempting reconnect
java.net.ConnectException: Connection timed out: no further information
	at sun.nio.ch.SocketChannelImpl.checkConnect(Native Method)
	at sun.nio.ch.SocketChannelImpl.finishConnect(SocketChannelImpl.java:717)
	at org.apache.zookeeper.ClientCnxnSocketNIO.doTransport(ClientCnxnSocketNIO.java:361)
	at org.apache.zookeeper.ClientCnxn$SendThread.run(ClientCnxn.java:1081)
  
  ```
