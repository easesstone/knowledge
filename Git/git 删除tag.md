
```
github中删除release/tag只能在命令执行，不能界面点击操作

git tag -d [tag];
git push origin :[tag]
假若需要删除一个 v1.1.1 的release版本

git tag -d v1.1.1;
git push origin :v1.1.1
```
