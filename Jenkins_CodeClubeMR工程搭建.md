## 前提条件：
```
1，需要git client plugin ,git plugin, gitlab plugin.
关于MergeRequest 工程。有两种形式的MegeRequest
```

### 第一种：单个工程形式的。配置的方法如下：
```
I，git 配置中配置主库的地址。且高级设置中Name填origin, 私库中，Repository URL	 填的是：${gitlabSourceRepoURL},高级设置中，Name填的是：${gitlabSourceRepoName}，而branches to build 填的是：${gitlabSourceRepoName}/${gitlabSourceBranch}，
II，勾选Build when a change is pushed to GitLab. GitLab CI Service URL: http://10.18.219.241:8080/jenkins/project/DataSight-V1R2C10-Spark-streaming-MR，除了Build on Push Events 这个选项，其他都可以勾选。 
III，在shell 脚本中合并主库和私库的代码：
echo $gitlabSourceRepoURL $gitlabTargetBranch
export repoName=`echo $gitlabSourceRepoName | awk -F '/' '{print $1}' `
git fetch $gitlabSourceRepoURL  $gitlabSourceBranch
git checkout -b $gitlabSourceRepoName  FETCH_HEAD
git checkout origin/$gitlabTargetBranch
git merge --no-ff $gitlabSourceRepoName
如果不这样做，可以通过另外一种方法，在第二步的后面增加Merge Before build 的选项，其中，Name  of repository 填的是origin  ，branch to merge to 填的是：${gitlabTargetBranch}  Merge Strategy 填的是：default , 其他的可能还有，Fast-forward mode  ： 这个选 -ff 就好。
```
### 第二种，多个工程的形式。
```
首先，配置一个总工程的入口，如MergeRequest 入口：
里面的配置如,可以加入参数化构建过程：这个可以加，也可以不加。
加的话主要有以下几个String parameter 类型的参数，
gitlabSourceRepoName : lwx355499/example ,
gitlabSourceRepoURL : gitlabSourceRepoURL,
gitlabSourceBranch : TestContextSuite,
gitlabTargetBranch : */master
gitlabActionType : Merge
然后， 配置git 仓库，具体参见第一种的第一步I,
最后，勾选Build when a change is pushed to GitLab. GitLab CI Service URL: http://10.18.219.241:8080/jenkins/project/DataSight-V1R2C10-Spark-streaming-MR，除了Build on Push Events 这个选项，其他都可以勾选。
对于子工程的配置，可以配置为并行的形式， 也可以配置为串行的形式。串行的形式，有两种形式，一个是利用MutiJob 的插件，另一种，则是利用，Trigger plugin，把这个工程完成后，在工程构建完成后操作中选：选择Trigger paremeterized build on other job.
子工程的配置，参见第一步。


(非MR 的git 工程，高级的name 不用设置。)
```
