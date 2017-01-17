1, java -jar spilt.jar suites 要有xml 配置文件。

```xml
<JenkinsConfig>
    <JenkinsHome>/home/jenkins</JenkinsHome>
    <MrJob>DataSight-V1R2C30-Spark-MergeRequest-Builder</MrJob>
    <SplitNum>99</SplitNum>
    <MaxDurationLimit>90</MaxDurationLimit>
</JenkinsConfig>
```


2,java -jar compare.jar  OldSuites NewSuites GapSuites
