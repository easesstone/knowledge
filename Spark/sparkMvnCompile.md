```
1,c10,mvn -Pbigtop-dist  -Phive-thriftserver -Phive -Pyarn  -Psparkr -Pdatasight-hadoop-2.7 -Dhadoop.version=2.7.2 -Dhbase.version=1.0.2 -Dzookeeper.version=3.5.1 -DskipTests -Dmanifest.version=1.5.1-$version clean install 
2,c20,mvn -Pbigtop-dist  -Phive-thriftserver -Phive -Pyarn  -Psparkr -Pdatasight-hadoop-2.7 -Dhadoop.version=2.7.2 -Dhbase.version=1.0.2 -Dzookeeper.version=3.5.1 -DskipTests -Dmanifest.version=1.5.1-$version clean install 
3,c30,1.5-old,mvn -Pbigtop-dist  -Phive-thriftserver -Phive -Pyarn  -Psparkr -Pdatasight-hadoop-2.7 -Phbase -Dhadoop.version=2.7.2 -Dhbase.version=1.0.2 -Dzookeeper.version=3.5.1 -DskipTests -Dmanifest.version=1.5.1-$version clean install
4,c30,1.5-new, mvn -Pbigtop-dist  -Phive-thriftserver -Phive -Pyarn  -Psparkr -Pdatasight-hadoop-2.7 -Phbase -Dhadoop.version=2.7.2 -Dhbase.version=1.0.2 -Dzookeeper.version=3.5.1 -DskipTests -Dmanifest.version=1.5.1-$version clean install
5,c30,2.x, mvn  -Pbigtop-dist  -Phive-thriftserver -Phive -Pyarn  -Psparkr -Pdatasight-hadoop-2.7  -Dhadoop.version=2.7.2 -Phbase -Dhbase.version=1.0.2  -Dzookeeper.versiovn=3.5.1 -DskipTests -T 1C clean  install
```
