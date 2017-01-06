```
java.net.UnknownHostException: null
        at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:178)
        at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)
        at java.net.Socket.connect(Socket.java:579)
        at java.net.Socket.connect(Socket.java:528)
        at java.net.Socket.<init>(Socket.java:425)
        at java.net.Socket.<init>(Socket.java:280)
        at org.apache.commons.httpclient.protocol.DefaultProtocolSocketFactory.createSocket(DefaultProtocolSocketFactory.java:80)
        at org.apache.commons.httpclient.protocol.DefaultProtocolSocketFactory.createSocket(DefaultProtocolSocketFactory.java:122)
        at org.apache.commons.httpclient.HttpConnection.open(HttpConnection.java:707)
        at org.apache.commons.httpclient.HttpMethodDirector.executeWithRetry(HttpMethodDirector.java:387)
        at org.apache.commons.httpclient.HttpMethodDirector.executeMethod(HttpMethodDirector.java:171)
        at org.apache.commons.httpclient.HttpClient.executeMethod(HttpClient.java:397)
        at org.apache.commons.httpclient.HttpClient.executeMethod(HttpClient.java:323)
        at com.huawei.icp.resourceManager.util.HttpRequest.sayHelloJenkins(HttpRequest.java:60)
        at com.huawei.icp.resourceManager.factory.JenkinsNodesInfoCollectTask.run(JenkinsNodesInfoCollectTask.java:41)

```
