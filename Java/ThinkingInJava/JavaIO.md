# 流的概念
> java中程序对数据的处理，抽象成流，数据从一个地方流向另一个地方。我们可以往流中写入数据，也可以从流中获取数据。<br/>
> 总的来看，java io 的类中，主要分为两种，一种是以字节为单位的流，一种是以字符为单位的流。<br/>
> 即，Inpustream,OuputStream 以及Reader 和Writer。<br/>

 对于java io 的用途，概括来看有如下：

* 文件访问
* 网络访问
* 内存缓存访问
* 线程内部通信(管道)
* 缓冲
* 过滤
* 解析
* 读写文本 (Readers / Writers)
* 读写基本类型数据 (long, int etc.)
* 读写对象

关于字节流，主要的类如下。

| 属性 | 输入 | 输出 |
| ------ | ------ | ------ |
| Basic | InputStream | OutputStream |
| Arrays | ByteArraysInputStream | ByteArraysInputStream |
| File | FileInputStream,RandomAccessFile | FileOutputStream,RandomAccessFile |
| Pines | PinedInputStream | PinedOutputStream |
| Buffering | BufferedInputStream | BufferedOutputStream |
| Fitering | FilterInputStream | FilterOutputStream|
| Parsing | PushbackInputStream,StreamTokenizer| |
| String |||
| Data | DataInputStream | DataOutputStream |
| Data-formatted || PrintStream|
| Objects | ObjectInputStream | ObjectOutputStream |
| Utilities | SequenceInputStream | |

关于字节流，其主要的类如下：

| 属性 | 输入 | 输出 | 说明 |
| ------ | ------ | ------ | ------ |
| Basic | Reader,InputStreamReader | Writer,OutputStreamWriter | InputStreamReader与Output
| Arrays | CharArraysInputStream | CharArraysInputStream ||
| File | FileReader | FileWriter ||
| Pines | PinedReader | PinedWriter ||
| Buffering | BufferedReader | BufferedWriter ||
| Fitering | FilterReader | FilterWriter||
| Parsing | PushbackReader,LineNumberReader| ||
| String | StringReader | StringWriter ||
| Data |  |  ||
| Data-formatted || PrintWriter||
| Objects |  |  ||
| Utilities |  | ||
