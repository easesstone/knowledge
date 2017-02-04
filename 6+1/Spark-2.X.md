```shell
  # If the Spark SQL tests are enabled, run the tests with the Hive profiles enabled.
  # This must be a single argument, as it is.
  if [ -n "$_RUN_SQL_TESTS" ]; then
    SBT_MAVEN_PROFILES_ARGS="$SBT_MAVEN_PROFILES_ARGS -Phive -Phive-thriftserver"
  fi

  if [ -n "$_SQL_TESTS_ONLY" ]; then
    # This must be an array of individual arguments. Otherwise, having one long string
    # will be interpreted as a single test, which doesn't work.
    SBT_MAVEN_TEST_ARGS=("catalyst/test" "hive/test" "hive-thriftserver/test")
     
  else
    SBT_MAVEN_TEST_ARGS=("test")
  fi

  if [ -n "$_CORE_TESTS_ONLY" ];then
    SBT_MAVEN_TEST_ARGS=("core/test" "unsafe/test"  "graphx/test" "repl/test")
  fi

  if [ -n "$_STREAMING_TESTS_ONLY" ];then
     SBT_MAVEN_TEST_ARGS=("streaming/test"  "streaming-flume-sink/test" "sql-kafka-0-10/test"
			  "streaming-kafka-0-10/test" "streaming-kafka-0-8/test" "streaming-flume/test"  
			  "streaming-kinesis-asl/test" "mllib-local/test" "network-common/test" "network-shuffle/test" 
			  "yarn/test" "launcher/test" "mllib/test" "sql/test" "sketch/test")
  fi
```
