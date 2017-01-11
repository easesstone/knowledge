## 问题
### 1）umount device busy   
```
Example:
错误提示：
root # umount /mnt/suse
umount: /mnt: device is busy.
        (In some cases useful info about processes that use
         the device is found by lsof(8) or fuser(1))
解决办法：
root # fuser  -m /mnt
/mnt:                 7806c
root # kill -9 7806
root # umount /mnt/suse
root # umount /mnt/suse
umount: /mnt: device is busy.
        (In some cases useful info about processes that use
         the device is found by lsof(8) or fuser(1))
root # umount -l /mnt/suse
以下是手册的解释：
-l Lazy unmount. 
Detach the filesystem from the filesystem hierarchy now, 
and cleanup all references to the filesystem as soon as it is not busy anymore. (Requires kernel 2.4.11 or later.)
```
### 2） How to Mount unknown filesystem type ‘LVM2_member’ 
```
错误提示：
现象，数据盘没有找到，而且执行如下操作时会出错。
mount /dev/arch1vg/lv_prmdb01 /oracle/app/oradata/ora11g
mount: you must specify the filesystem type
解决办法：
1，lvscan
2，modprobe dm-mod
3，vgchange –ay
4，lvscan
之后重新挂载即可。
```
