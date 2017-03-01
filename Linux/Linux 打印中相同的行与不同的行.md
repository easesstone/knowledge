```shell
cat suite_[1-9].txt suite_[1-9][0-9].txt  | sort | uniq -d  #(不同的行)
cat suite_[1-9].txt suite_[1-9][0-9].txt  | sort | uniq -u  #（相同的行）
#借此可以找出不同文件中相同的行。
```
