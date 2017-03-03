```
echo $gitlabSourceRepoURL $gitlabTargetBranch
export repoName=`echo $gitlabSourceRepoName | awk -F '/' '{print $1}' `
git fetch $gitlabSourceRepoURL  $gitlabSourceBranch
git checkout -b $gitlabSourceRepoName  FETCH_HEAD
git checkout origin/$gitlabTargetBranch
git merge --no-ff $gitlabSourceRepoName

```
