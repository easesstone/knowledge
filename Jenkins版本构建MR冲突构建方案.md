##  解决方案
### 方案1，暴力手段，让其处于排队状态
```
实施，让jenkins 的slave 节点，在同一个时间段内，只可以跑一个工程。
```
### 方案2，在版本构建的时间段内，让提的MR 不生效。
 ```shell
 I，实施讲解
 jenkins 上的MR 工程，是通过gitlab.pbi 这个插件进行触发的。
 jenkins 相当于一个服务器。codeclube 相当于一个客户端。
 CodeClube  提交一个MergeRequest 的时候，会把一些相关的信息，绑定到URL ，传到jenkins 服务器，服务器进行相应，触发相应的工程，然后，
 把相应的工程执行的结果，返回给codeclube 这个客户端。
 
 这个方案，相当于为MR 工程设置一个开关，
 在版本构建的时间段内，MR 工程处于一个关闭的状态。
 其他的时间段内，MR 工程处于一个打开的状态。
 
 
 II，
 实施方案，
 第一步，修改工程配置中的config.xml 文件。
 可以在工程配置中存放两个文件，
 一个，config.xml.trigger,另一个config.xml.notrigger
 
 第二步，建立一个工程，
 版本构建前20分钟前，定时执行这个工程。
 工程脚本里，进行执行如下命令。
 类似。
 cp config.xml.notrigger  config.xml 
 java -jar jenkins-cli.jar -s http://100.106.75.10:8080/jenkins/ -noKeyAuth 
 reload-job V100R002C30_Astro_MR  --username  ******** --password ********
 
 
 第三步，建立另一个工程，
 版本构建后，执行这个工程。
 
 工程脚本里。
 类似如下。
 cp config.xml.trigger config.xml,然后，执行如下命令。
 java -jar jenkins-cli.jar -s http://100.106.75.10:8080/jenkins/ -noKeyAuth reload-job V100R002C30_Astro_MR 
 --username  ******** --password ******* 
 ```
