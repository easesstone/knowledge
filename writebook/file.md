```
/usr/bin/ld: cannot find -ljvm
collect2: ld returned 1 exit status
make[3]: *** [libodbchive.la] Error 1
make[3]: Leaving directory `/usr1/code/DataSight_Spark/hiveodbc/unixODBC-2.3.2/Drivers/hive'
make[2]: *** [all-recursive] Error 1
make[2]: Leaving directory `/usr1/code/DataSight_Spark/hiveodbc/unixODBC-2.3.2/Drivers'
make[1]: *** [all-recursive] Error 1
make[1]: Leaving directory `/usr1/code/DataSight_Spark/hiveodbc/unixODBC-2.3.2'
make: *** [all] Error 2
Compile unixODBC failed.
Build step 'Execute shell' marked build as failure





cp ${JAVA_HOME}/include/jni.h /usr/include/
cp ${JAVA_HOME}/include/linux/jni_md.h /usr/include/
ln -s ${JAVA_HOME}/jre/lib/amd64/server/libjvm.so /usr/local/lib/libjvm.so
ln -s ${JAVA_HOME}/jre/lib/amd64/server/libjvm.so /usr/lib/libjvm.so





+ g++ -o odbc_test.o -c odbc_test.cpp -I../cpp
odbc_test.cpp:26:25: error: gtest/gtest.h: No such file or directory
odbc_test.cpp:82: error: expected constructor, destructor, or type conversion before ‘(’ token
Build step 'Execute shell' marked build as failure


gtest 没有安装。
```
