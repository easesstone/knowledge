```
SHA1000046793:/usr1/ci-home/workspace/V100R002C30SPC100_Spark_UT_Test_08.hello # su jenkins 
jenkins@SHA1000046793:/usr1/ci-home/workspace/V100R002C30SPC100_Spark_UT_Test_08.hello> jps 
20255 Jps
20228 sbt-launch-0.13.7.jar
jenkins@SHA1000046793:/usr1/ci-home/workspace/V100R002C30SPC100_Spark_UT_Test_08.hello> ls
target
jenkins@SHA1000046793:/usr1/ci-home/workspace/V100R002C30SPC100_Spark_UT_Test_08.hello> ll
total 4
drwxr-xr-x 3 jenkins users 4096 Apr 14 10:11 target
jenkins@SHA1000046793:/usr1/ci-home/workspace/V100R002C30SPC100_Spark_UT_Test_08.hello> rm -rf target/
rm: cannot remove `target/tmp/spark-0336c87b-133f-4d1b-9cb4-87b6e904ba0f/child': Permission denied
jenkins@SHA1000046793:/usr1/ci-home/workspace/V100R002C30SPC100_Spark_UT_Test_08.hello> cd target/tmp
jenkins@SHA1000046793:/usr1/ci-home/workspace/V100R002C30SPC100_Spark_UT_Test_08.hello/target/tmp> ls
spark-0336c87b-133f-4d1b-9cb4-87b6e904ba0f
jenkins@SHA1000046793:/usr1/ci-home/workspace/V100R002C30SPC100_Spark_UT_Test_08.hello/target/tmp> ll
total 4
dr-xr-xr-x 2 jenkins users 4096 Apr 14 10:06 spark-0336c87b-133f-4d1b-9cb4-87b6e904ba0f
jenkins@SHA1000046793:/usr1/ci-home/workspace/V100R002C30SPC100_Spark_UT_Test_08.hello/target/tmp> cd spark-0336c87b-133f-4d1b-9cb4-87b6e904ba0f/
jenkins@SHA1000046793:/usr1/ci-home/workspace/V100R002C30SPC100_Spark_UT_Test_08.hello/target/tmp/spark-0336c87b-133f-4d1b-9cb4-87b6e904ba0f> ls
child
jenkins@SHA1000046793:/usr1/ci-home/workspace/V100R002C30SPC100_Spark_UT_Test_08.hello/target/tmp/spark-0336c87b-133f-4d1b-9cb4-87b6e904ba0f> ll
total 0
-rw-r--r-- 1 jenkins users 0 Apr 14 10:06 child
jenkins@SHA1000046793:/usr1/ci-home/workspace/V100R002C30SPC100_Spark_UT_Test_08.hello/target/tmp/spark-0336c87b-133f-4d1b-9cb4-87b6e904ba0f> cat child 
jenkins@SHA1000046793:/usr1/ci-home/workspace/V100R002C30SPC100_Spark_UT_Test_08.hello/target/tmp/spark-0336c87b-133f-4d1b-9cb4-87b6e904ba0f> vi child 
jenkins@SHA1000046793:/usr1/ci-home/workspace/V100R002C30SPC100_Spark_UT_Test_08.hello/target/tmp/spark-0336c87b-133f-4d1b-9cb4-87b6e904ba0f> ls
child
jenkins@SHA1000046793:/usr1/ci-home/workspace/V100R002C30SPC100_Spark_UT_Test_08.hello/target/tmp/spark-0336c87b-133f-4d1b-9cb4-87b6e904ba0f> ll
total 0
-rw-r--r-- 1 jenkins users 0 Apr 14 10:06 child
jenkins@SHA1000046793:/usr1/ci-home/workspace/V100R002C30SPC100_Spark_UT_Test_08.hello/target/tmp/spark-0336c87b-133f-4d1b-9cb4-87b6e904ba0f> rm child 
rm: cannot remove `child': Permission denied
jenkins@SHA1000046793:/usr1/ci-home/workspace/V100R002C30SPC100_Spark_UT_Test_08.hello/target/tmp/spark-0336c87b-133f-4d1b-9cb4-87b6e904ba0f> 

解决办法：echo "huawei" | su root -C "rm -rf $WORKSPACE/*"
```
