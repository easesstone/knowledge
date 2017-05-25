```shell
#!/bin/bash
################################################################################
## Copyright:   HUAWEI Tech. Co., Ltd.
## Filename:    declareGlobalVariableAndFunction.sh
## Description: Spark1.5 SparkUT
## Version:     DataSight V100R002C30SPC100 
## Author:      lwx355499
## Created:     2017-05-25
################################################################################


#---------------------------------------------------------------------#
#                              定义变量                               #
#---------------------------------------------------------------------#
export SCRIPT_HOME="$(cd "`dirname $0`"/..; pwd)"  ## 脚本目录
export BUILD_HOME=$1    ## Spark 代码所处路径
export CODE_PACAKGE_HOME=$2  ## 代码分发过来的路径
## sbt 编译所需的参数,参数以及请参考maven 官网
export SBT_MAVEN_PROFILES_ARGS=${SBT_MAVEN_PROFILES_ARGS:-"-Pyarn -Pdatasight-hadoop-2.7  -Dhadoop.version=2.7.2 \ 
-Dzookeeper.version=3.5.1 -Dhbase.version=1.0.2 -Phive -Phive-thriftserver -Phbase"} 
export _SUITE_TESTS_ONLY=true  ## sbt 编译所需参数，表示按照Suite 来跑用例。

#####################################################################
# 模块名: N/A
# 描述: 介绍脚本的用法
# 参数: $# 传入的参数的个数，需要传入两个参数
# 返回值: N/A
# 其他: N/A                     
##################################################################### 
{
    if [ $# -ne 2 ];then
		echo "用法:  source  install_tools.sh   args[1]  args[2]"
		echo "参数 args[1]，Spark 代码所在目录"
		echo "参数 args[2]，分发过来的代码的package tar包的路径。"
        exit 1;
    fi
}


#####################################################################
# 函数名: handle_error
# 描述: 针对脚本错误的处理
# 参数: $1 错误所处的那一行。$? 命令行返回码
# 返回值: N/A
# 其他: N/A                     
##################################################################### 
function handle_error() 
{
  echo "[error] Got a return code of $? on line $1 of the run-tests script."
  exit $CURRENT_BLOCK
}

##################################################################################
# 描述：  
#     对于管道命令，返回从左到右的第一个错误的返回值                      
#     ls hello.txt | echo "hi"  此命令中，不管第一个ls 是否执行正确       
#     返回值都是0， 但是如果在这个命令之前加上set -o pipefail 则返回值是1 
##################################################################################
set -o pipefail

#####################################################################
# 描述：
#     如果脚本出错，则调用handle_error 函数进行处理                      
#####################################################################
trap 'handle_error $LINENO' ERR

#####################################################################
# 函数名: clearLocalTmpJars
# 描述: 清除本地的遗留的第三方的jar 包。
# 参数: N/A
# 返回值: N/A
# 其他: N/A                     
##################################################################### 
function clearLocalTmpJars()
{
	echo ""
	echo "========================================================================="
	echo "clear local tmp thirdPartDepends Jars"
	echo "========================================================================="
	find /home/jenkins/.m2/repository/org/apache/hadoop/ -name *2.7.2*jar | xargs rm -f 
	find /home/jenkins/.ivy2/cache/org.apache.hadoop -name *2.7.2*jar | xargs  rm -f
	find /home/jenkins/.ivy2/cache/org.apache.zookeeper -name *3.5.1*jar | xargs  rm -f
	find /home/jenkins/.ivy2/cache/org.apache.hbase -name *1.0.2*jar | xargs  rm -f
	find /home/jenkins/.ivy2/cache/org.spark-project.hive -name *1.2.1*jar | xargs  rm -f
	find /home/jenkins/.ivy2/cache/org.spark-project.hive.shims -name  *1.2.1*jar | xargs  rm -f
	find /tmp -user jenkins 2>/dev/null  | xargs rm -rf 
	rm -rf /home/jenkins/.ivy2/cache/org.spark-project.hive/*
}

#####################################################################
# 函数名: clearCaches
# 描述: 释放内存空间   
# 参数: N/A
# 返回值: N/A
# 其他: N/A                     
##################################################################### 
function clearCaches()
{
	echo ""
	echo "========================================================================="
	echo "clear caches"
	echo "========================================================================="
    echo "huawei" | su  root -C "sync; echo 3 > /proc/sys/vm/drop_caches" 
	ulimit -c unlimited
}


#####################################################################
# 函数名: prepareCode
# 描述: 清理上一次遗留的代码，准备最新的Spark 的代码，   
# 参数: N/A
# 返回值: N/A
# 其他: N/A                     
#####################################################################    
function prepareCode()
{
	echo ""
	echo "========================================================================="
	echo "prepare code for building"
	echo "========================================================================="
	if [ ! -e  ${BUILD_HOME} ];then 
		mkdir	${BUILD_HOME}
	fi	
	cd  ${BUILD_HOME}
	rm -rf *
	cp ${CODE_PACAKGE_HOME}  ./
	tar zxvf pacakge.tar.gz > /dev/null 
	rm -rf pacakge.tar.gz
}

#####################################################################
# 函数名: prepareForSbtBuild
# 描述: 清理本地环境，准备一些脚本的返回值   
# 参数: N/A
# 返回值: N/A
# 其他: N/A                     
##################################################################### 
function prepareForSbtBuild()
{
	echo ""
	echo "========================================================================="
	echo "init sbt building"
	echo "========================================================================="
	cd ${BUILD_HOME}
	rm -rf ./work
	rm -rf ~/.ivy2/local/org.apache.spark
	rm -rf ~/.ivy2/cache/org.apache.spark\
	source ${BUILD_HOME}/dev/run-tests-codes.sh
}

#####################################################################
# 函数名: prepareForSbtBuild
# 描述: 检查jdk 版本，有些用例依赖高版本jdk  
# 参数: N/A
# 返回值: N/A
# 其他: N/A                     
##################################################################### 
function checkJavaVersion()
{
	echo ""
	echo "========================================================================="
	echo "check jdk version"
	echo "========================================================================="
	if test -x "$JAVA_HOME/bin/java"; then
		declare java_cmd="$JAVA_HOME/bin/java"
	else
		declare java_cmd=java
	fi
  
	JAVA_VERSION=$(
		$java_cmd -version 2>&1 \
		| grep -e "^java version" --max-count=1 \
		| sed "s/java version \"\(.*\)\.\(.*\)\.\(.*\)\"/\1\2/"
	)

	if [ "$JAVA_VERSION" -lt 18 ]; then
		echo "[warn] Java 8 tests will not run because JDK version is < 1.8."
	fi
}

#####################################################################
# 函数名: buildSparkCodeWithNoTestCode
# 描述: 编译Spark 代码，注意，此编译不会编译Spark 的test 的代码
# 参数: N/A
# 返回值: N/A
# 其他: N/A                     
##################################################################### 
function buildSparkCodeWithNoTestCode_SPC100_1_5()
{
	cd ${BUILD_HOME}
	echo ""
	echo "========================================================================="
	echo "Building Spark code, but did not include the testCode"
	echo "========================================================================="
	HIVE_BUILD_ARGS="$SBT_MAVEN_PROFILES_ARGS -Phive -Phive-thriftserver -Phbase"
	echo "[info] Compile with Hive 0.13.1"
	[ -d "lib_managed" ] && rm -rf lib_managed
	echo "[info] Building Spark with these arguments: $HIVE_BUILD_ARGS"
	echo -e "q\n" \
	| build/sbt $HIVE_BUILD_ARGS clean package assembly/assembly streaming-kafka-assembly/assembly \
	| grep -v -e "info.*Resolving" -e "warn.*Merging" -e "info.*Including"
}

#####################################################################
# 函数名: buildSparkCodeWithNoTestCode
# 描述: 编译Spark 代码，注意，只编译Spark 的单元测试UT 的代码
# 参数: N/A
# 返回值: N/A
# 其他: N/A                     
##################################################################### 
function buildSparkCodeWithTestCode_SPC100_1_5()
{
	cd ${BUILD_HOME}
	echo ""
	echo "========================================================================="
	echo "Building Spark code, only include the testCode"
	echo "========================================================================="
	HIVE_BUILD_ARGS="$SBT_MAVEN_PROFILES_ARGS -Phive -Phive-thriftserver -Phbase"
	echo "[info] Compile with Hive 0.13.1"
	echo "[info] Building Spark with these arguments: $HIVE_BUILD_ARGS"
	echo -e "q\n" \
	| build/sbt $HIVE_BUILD_ARGS test:compile \
	| grep -v -e "info.*Resolving" -e "warn.*Merging" -e "info.*Including"
}

```
