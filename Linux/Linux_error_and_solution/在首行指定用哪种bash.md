#!/bin/sh
a=( 1 2 3)
for number in ${a[@]}
do
echo $number
done
执行该脚本，在有的机器上会报错Syntax error: "(" unexpected

这与你实际使用的shell版本有关。你可以用 ls -l /bin/*sh 打印出来，例如：
-rwxr-xr-x 1 root root 959120 Mar 29  2013 /bin/bash
lrwxrwxrwx 1 root root     21 Nov 22  2013 /bin/csh -> /etc/alternatives/csh
-rwxr-xr-x 1 root root 109768 Mar 30  2012 /bin/dash
lrwxrwxrwx 1 root root      4 Mar 29  2013 /bin/rbash -> bash
lrwxrwxrwx 1 root root      4 Mar 30  2012 /bin/sh -> dash
lrwxrwxrwx 1 root root      7 Nov 17  2012 /bin/static-sh -> busybox
lrwxrwxrwx 1 root root     13 Oct 15  2011 /bin/tcsh -> /usr/bin/tcsh
在这里，sh被重定向到dash，因此，如果执行./example.sh，则使用的是dash
避免报错可有多种方法，例如执行 bash example.sh，或者，将脚本第一行改为
#!/bin/bash，执行./example.sh也可以。
