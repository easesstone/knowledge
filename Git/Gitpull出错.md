```shell
$ git pull ldl master:master
remote: Counting objects: 129, done.
remote: Compressing objects: 100% (64/64), done.
remote: Total 129 (delta 14), reused 129 (delta 14), pack-reused 0
Receiving objects: 100% (129/129), 27.02 KiB | 0 bytes/s, done.
Resolving deltas: 100% (14/14), done.
From github.com:easesstone/easesstone
 ! [rejected]        master     -> master  (non-fast-forward)
   ba8c882..6e6ea65  master     -> ldl/master
   
   
   
$ git pull ldl master
From github.com:easesstone/easesstone
 * branch            master     -> FETCH_HEAD
fatal: refusing to merge unrelated histories

解决办法：

E:\Project\easesstone>ls
easesstone.iml  java  pom.xml  spring  workconclusion

E:\Project\easesstone>git pull ldl master --allow-unrelated-histories
From github.com:easesstone/easesstone
 * branch            master     -> FETCH_HEAD
Merge made by the 'recursive' strategy.
 README.md | 1 +
 1 file changed, 1 insertion(+)
 create mode 100644 README.md

```
