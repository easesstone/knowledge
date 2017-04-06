```
Note: We ignore environment variables, when use of profile is detected in conjunction with environment variable.
[info] Set current project to spark-parent (in build file:/usr1/ci-home/workspace/V100R002C30_Spark_UT_Test_2.x_34/)
#
# A fatal error has been detected by the Java Runtime Environment:
#
#  SIGSEGV (0xb) at pc=0x0000000800000008, pid=116389, tid=140451506267904
#
# JRE version: Java(TM) SE Runtime Environment (7.0_60-b19) (build 1.7.0_60-b19)
# Java VM: Java HotSpot(TM) 64-Bit Server VM (24.60-b09 mixed mode linux-amd64 compressed oops)
# Problematic frame:
# C  0x0000000800000008
#
# Core dump written. Default location: /usr1/ci-home/workspace/V100R002C30_Spark_UT_Test_2.x_34/core or core.116389
#
# An error report file with more information is saved as:
# /usr1/ci-home/workspace/V100R002C30_Spark_UT_Test_2.x_34/hs_err_pid116389.log
Compiled method (c2)   43742 5383   !         java.util.concurrent.ThreadPoolExecutor::getTask (186 bytes)
 total in heap  [0x00007fbd74227dd0,0x00007fbd74229df8] = 8232
 relocation     [0x00007fbd74227ef0,0x00007fbd74228048] = 344
 main code      [0x00007fbd74228060,0x00007fbd74228c60] = 3072
 stub code      [0x00007fbd74228c60,0x00007fbd74228d38] = 216
 oops           [0x00007fbd74228d38,0x00007fbd74228e08] = 208
 scopes data    [0x00007fbd74228e08,0x00007fbd742298e0] = 2776
 scopes pcs     [0x00007fbd742298e0,0x00007fbd74229c10] = 816
 dependencies   [0x00007fbd74229c10,0x00007fbd74229c20] = 16
 handler table  [0x00007fbd74229c20,0x00007fbd74229d88] = 360
 nul chk table  [0x00007fbd74229d88,0x00007fbd74229df8] = 112
Compiled method (c2)   43742 5383   !         java.util.concurrent.ThreadPoolExecutor::getTask (186 bytes)
 total in heap  [0x00007fbd74227dd0,0x00007fbd74229df8] = 8232
 relocation     [0x00007fbd74227ef0,0x00007fbd74228048] = 344
 main code      [0x00007fbd74228060,0x00007fbd74228c60] = 3072
 stub code      [0x00007fbd74228c60,0x00007fbd74228d38] = 216
 oops           [0x00007fbd74228d38,0x00007fbd74228e08] = 208
 scopes data    [0x00007fbd74228e08,0x00007fbd742298e0] = 2776
 scopes pcs     [0x00007fbd742298e0,0x00007fbd74229c10] = 816
 dependencies   [0x00007fbd74229c10,0x00007fbd74229c20] = 16
 handler table  [0x00007fbd74229c20,0x00007fbd74229d88] = 360
 nul chk table  [0x00007fbd74229d88,0x00007fbd74229df8] = 112
Compiled method (c2)   43742 5383   !         java.util.concurrent.ThreadPoolExecutor::getTask (186 bytes)
 total in heap  [0x00007fbd74227dd0,0x00007fbd74229df8] = 8232
 relocation     [0x00007fbd74227ef0,0x00007fbd74228048] = 344
 main code      [0x00007fbd74228060,0x00007fbd74228c60] = 3072
 stub code      [0x00007fbd74228c60,0x00007fbd74228d38] = 216
 oops           [0x00007fbd74228d38,0x00007fbd74228e08] = 208
 scopes data    [0x00007fbd74228e08,0x00007fbd742298e0] = 2776
 scopes pcs     [0x00007fbd742298e0,0x00007fbd74229c10] = 816
 dependencies   [0x00007fbd74229c10,0x00007fbd74229c20] = 16
 handler table  [0x00007fbd74229c20,0x00007fbd74229d88] = 360
 nul chk table  [0x00007fbd74229d88,0x00007fbd74229df8] = 112
#
# If you would like to submit a bug report, please visit:
#   http://bugreport.sun.com/bugreport/crash.jsp
#
[error] Got a return code of 134 on line 164 of the run-tests script.
Process leaked file descriptors. See http://wiki.jenkins-ci.org/display/JENKINS/Spawning+processes+from+build for more information
Build step 'Execute shell' marked build as failure
job completed...

```
