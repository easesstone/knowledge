## Linux 磁盘扩容
#### 使用fdisk -l命令查看新增磁盘的设备名,如/dev/xdvf
#### 创建PV, pvcreate /dev/xdvf
#### 加入原有VG中：vgextend bg1 /dev/xdvf 
#### 扩展lv 大小： lvextend -l +100%FREE/dev/mapper/vg1-lv1
#### 调整文件系统大小： resize2fs /dev/mapper/vg1-lv1

```
如执行resize2fs /dev/vg1/lv1时报错：
resize2fs 1.38 (30-Jun-2005)
/dev/vg1/lv1 is mounted; can't resize a mounted filesystem!
则需要执行
1、重启主机
reboot
2、查看磁盘挂载情况
df -h
3、如/dev/vg1/lv1 已挂载，则进行解挂，否则跳过此步
umount /dev/vg1/lv1
4、调整文件系统大小
resize2fs /dev/vg1/lv1
如提示需检查磁盘情况，则按提示操作进行检查。
5、重新挂载磁盘
mount /dev/vg1/lv1 /usr1
```
