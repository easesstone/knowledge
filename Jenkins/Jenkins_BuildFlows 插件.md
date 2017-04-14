```shell
Jenkins 使用 Build Flow 插件配置工作流任务依赖

Jenkins 多任务依赖方式的配置方法目前可以通过MultiJob Project 或者Build Flow 或者Pipleline,或者
多个自由风格的job通过tigger触发的方式进行依赖关联。

新建 Build Flow project 工作流，Build Flow的方式配置任务依赖：具体配置需要使用DSL编辑器定义工作流。
Build Flow 可以通过简单的脚本（Groovy DSL）定义工作流。具体的教程可以参考Build Flow 插件的官方说明文档：
https://wiki.jenkins-ci.org/display/JENKINS/Build+Flow+Plugin
下面是一些常用的配置方法：

##顺序执行3个任务：job1，job2，job3
build( "job1" )
build( "job2" )
build( "job3" )
##参数构建
b = build( "job1", param1: "foo", param2: "bar" )
build( "job2", param1: b.build.number )

##获取环境变量
def revision = b.environment.get( "GIT_REVISION" )

##guard + rescue类似于 java的try +finally；可以在finally做一些清理工作。
guard {
    build( "this_job_may_fail" )
} rescue {
    build( "cleanup" )
}

##忽略某种状态：
##这里忽略操作可选的状态有：UNSTABLE < FAILURE < ABORTED
ignore(FAILURE) {
    build( "send_twitter_notification" )
}
## 任务失败重试：相当于retry-failed-job plugin，
## 重试3次当任务失败的时候。
retry ( 3 ) {
    build( "this_job_may_fail" )
}

###并发的执行任务：job1，job2,job3 会被并发的执行，job4会在job1,2,3都完成后才执行。
parallel (
    // job 1, 2 and 3 will be scheduled in parallel.
    { build("job1") },
    { build("job2") },
    { build("job3") }
)
// job4 will be triggered after jobs 1, 2 and 3 complete
build("job4")

###与并行插件相比，并行可以用于更复杂的工作流，其中并行分支可以顺序链接多个作业。
###并行的执行两组任务，这两组任务内又是顺序执行的。 
###job1A,job1B,job1C 是顺序执行的；job2A,job2B,job2C也是顺序执行的。

parallel (
    {
        build("job1A")
        build("job1B")
        build("job1C")
    },
    {
        build("job2A")
        build("job2B")
        build("job2C")
    }
)

###给并行任务定义一个名字，利用名字引用执行的状态。
join = parallel ([
        first:  { build("job1") },
        second: { build("job2") },
        third:  { build("job3") }
])

// now, use results from parallel execution
build("job4",
       param1: join.first.result.name,
       param2: join.second.lastBuild.parent.name)

###可以结合其他业务流程关键字：
parallel (
    {
        guard {
            build("job1A")
            build("job1B")
        } rescue {
            build("job1C")
        }
    },
    {
        retry 3, {
            build("job2")
        }
    }
)
##并发里面使用retry。
parallel (
    // job 1, 2 and 3 will be scheduled in parallel.
   {retry(3){
        build("MultiJobTest-step1-1")
    }},
    { build("MultiJobTest-step1-2") }
)
##对一个并发操作进行重试。
retry ( 3 ) {
  parallel (
      // job 1, 2 and 3 will be scheduled in parallel.
     {retry(3){
          build("MultiJobTest-step1-1")
      }},
      { build("MultiJobTest-step1-2") }
  )
}
 
缺点：该项目前2017年虽然还在维护和更新，但jenkins官网提示该插件是已经是过时的，建议用户迁移 到Pipeline Plugin插件。
可以使用 Build Graph View Plugin插件展示工作流视图。下载地址：https://wiki.jenkins-ci.org/display/JENKINS/Build+Graph+View+Plugin (
有时：需要重启Jenkins和tomcat服务，才能正常展示视图)

```
