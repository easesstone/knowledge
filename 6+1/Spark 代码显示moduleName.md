### Spark 代码显示moduleName
```
进入代码目录下，执行build/sbt  moduleName

grep sbt.project.name * -R | awk '{print $2}' | awk -F ">" '{print $2}' | awk -F "<"  '{print $1}'
```
