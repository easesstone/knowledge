删除当前目录的所有目录：
ls -F | grep  '/$' |xargs rm -rf

删除文件
ls | grep -v hbase2es-1.0.jar  | grep -v photo.tar.gz  |xargs rm -rf
