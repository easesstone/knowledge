```shell
[WS-CLEANUP] Deleting project workspace...
[WS-CLEANUP] Done
Cloning the remote Git repository
Cloning repository git@code.huawei.com:l00251599/Hive_1.2.x_V1R2C30SPC100.git
 > git init /usr1/ci-home/workspace/V100R002C30_Hive_MR_Build # timeout=10
Fetching upstream changes from git@code.huawei.com:l00251599/Hive_1.2.x_V1R2C30SPC100.git
 > git --version # timeout=10
 > git -c core.askpass=true fetch --tags --progress git@code.huawei.com:l00251599/Hive_1.2.x_V1R2C30SPC100.git +refs/heads/*:refs/remotes/l00251599/Hive_1.2.x_V1R2C30SPC100/*
 > git config remote.l00251599/Hive_1.2.x_V1R2C30SPC100.url git@code.huawei.com:l00251599/Hive_1.2.x_V1R2C30SPC100.git # timeout=10
 > git config --add remote.l00251599/Hive_1.2.x_V1R2C30SPC100.fetch +refs/heads/*:refs/remotes/l00251599/Hive_1.2.x_V1R2C30SPC100/* # timeout=10
 > git config remote.l00251599/Hive_1.2.x_V1R2C30SPC100.url git@code.huawei.com:l00251599/Hive_1.2.x_V1R2C30SPC100.git # timeout=10
Fetching upstream changes from git@code.huawei.com:l00251599/Hive_1.2.x_V1R2C30SPC100.git
 > git -c core.askpass=true fetch --tags --progress git@code.huawei.com:l00251599/Hive_1.2.x_V1R2C30SPC100.git +refs/heads/*:refs/remotes/l00251599/Hive_1.2.x_V1R2C30SPC100/*
 > git config remote.origin.url git@code.huawei.com:datasight/Hive_1.2.x_V1R2C30SPC100.git # timeout=10
Fetching upstream changes from git@code.huawei.com:datasight/Hive_1.2.x_V1R2C30SPC100.git
 > git -c core.askpass=true fetch --tags --progress git@code.huawei.com:datasight/Hive_1.2.x_V1R2C30SPC100.git +refs/heads/*:refs/remotes/origin/*
 > git rev-parse l00251599/Hive_1.2.x_V1R2C30SPC100/tmpTblBug^{commit} # timeout=10
 > git rev-parse refs/remotes/${gitlabSourceRepoName}/l00251599/Hive_1.2.x_V1R2C30SPC100/tmpTblBug^{commit} # timeout=10
 > git rev-parse l00251599/Hive_1.2.x_V1R2C30SPC100/tmpTblBug^{commit} # timeout=10
 > git rev-parse refs/remotes/origin/l00251599/Hive_1.2.x_V1R2C30SPC100/tmpTblBug^{commit} # timeout=10
Merging Revision 6fe113eb7d31f5b422b6183e81a144b65a14be08 (l00251599/Hive_1.2.x_V1R2C30SPC100/tmpTblBug) to origin/master, UserMergeOptions{mergeRemote='origin', mergeTarget='${gitlabTargetBranch}', mergeStrategy='default', fastForwardMode='--ff'}
 > git rev-parse origin/master^{commit} # timeout=10
 > git config core.sparsecheckout # timeout=10
 > git checkout -f origin/master
 > git merge --ff 6fe113eb7d31f5b422b6183e81a144b65a14be08 # timeout=10
 > git config core.sparsecheckout # timeout=10
 > git checkout -f 6fe113eb7d31f5b422b6183e81a144b65a14be08
ERROR: Branch not suitable for integration as it does not merge cleanly: Could not merge AnyObjectId[6fe113eb7d31f5b422b6183e81a144b65a14be08]
job completed...
UploadBuildProperty,buildTypeChoose=null
Finished: FAILURE
解决办法：
在本地手动先执行一遍Merge，
之后就OK 了。
echo $gitlabSourceRepoURL $gitlabTargetBranch
export repoName=`echo $gitlabSourceRepoName | awk -F '/' '{print $1}' `
git fetch $gitlabSourceRepoURL  $gitlabSourceBranch
git checkout -b $gitlabSourceRepoName  FETCH_HEAD
git checkout origin/$gitlabTargetBranch
git merge --no-ff $gitlabSourceRepoName
```
