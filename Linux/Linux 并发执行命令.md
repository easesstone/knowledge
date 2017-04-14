```shell
for host in $(cat ip.log);do { ssh $host "ls /usr1/ci-home/workspace/V100R002C30_*/  -d | xargs  rm -rf"; }& done ;wait 
# 注意{ 方括号之间的空格。

```
