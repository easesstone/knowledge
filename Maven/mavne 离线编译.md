```
find . -name *lastUpdated -exec rm {} \;
find . -name _remote.repositories  -exec rm {} \;
然后在maven 编译时，加上 -o
```
