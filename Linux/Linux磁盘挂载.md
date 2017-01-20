## Linux磁盘挂载
#### 1、fdisk -l查看磁盘名称

```
Disk /dev/xvda: 42.9 GB, 42949672960 bytes
255 heads, 63 sectors/track, 5221 cylinders, total 83886080 sectors
Units = sectors of 1 * 512 = 512 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes
Disk identifier: 0x000d7b57

    Device Boot      Start         End      Blocks   Id  System
/dev/xvda1            2048     8386559     4192256   82  Linux swap / Solaris
/dev/xvda2   *     8386560    83886079    37749760   83  Linux

Disk /dev/xvde: 96.6 GB, 96636764160 bytes
255 heads, 63 sectors/track, 11748 cylinders, total 188743680 sectors
Units = sectors of 1 * 512 = 512 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes
Disk identifier: 0x00000000

Disk /dev/xvde doesn't contain a valid partition table
```


#### 2、pvcreate /dev/xvde 创建PV

```
root@ldl:/ # pvcreate /dev/xvde
  Physical volume "/dev/xvde" successfully created
```

#### 3、vgcreate vg1 /dev/xvde 创建VG

```
root@ldl:/ # vgcreate vg1 /dev/xvde
  Volume group "vg1" successfully created
```


#### 4、vgdisplay查看VG大小：

```
 --- Volume group ---
  VG Name               vg1
  System ID             
  Format                lvm2
  Metadata Areas        1
  Metadata Sequence No  1
  VG Access             read/write
  VG Status             resizable
  MAX LV                0
  Cur LV                0
  Open LV               0
  Max PV                0
  Cur PV                1
  Act PV                1
  VG Size               90.00 GiB
  PE Size               4.00 MiB
  Total PE              23039
  Alloc PE / Size       0 / 0   
  Free  PE / Size       23039 / 90.00 GiB
  VG UUID               55dqvu-cwwL-Jzvx-xncE-LKTz-fT5P-Fbc9RM
```


#### 5、lvcreate -l ***** -n lv1 vg1 （ * 为上一步骤vgdipaly查询到的VG容量,Free PE 23039）创建LV

```
Logical volume "lv1" created
```

#### 6、mkfs.ext3 -j /dev/vg1/lv1      (Ubuntu使用ext4，suse使用ext3)  tune2fs -c 0 -i 0 /dev/vg1/lv1 


```
格式化磁盘：
ROOT@LDL:/ # mkfs.ext3 -j /dev/vg1/lv1
mke2fs 1.41.9 (22-Aug-2009)
Filesystem label=
OS type: Linux
Block size=4096 (log=2)
Fragment size=4096 (log=2)
5898240 inodes, 23591936 blocks
1179596 blocks (5.00%) reserved for the super user
First data block=0
Maximum filesystem blocks=4294967296
720 block groups
32768 blocks per group, 32768 fragments per group
8192 inodes per group
Superblock backups stored on blocks: 
	32768, 98304, 163840, 229376, 294912, 819200, 884736, 1605632, 2654208, 
	4096000, 7962624, 11239424, 20480000

Writing inode tables: done                            
Creating journal (32768 blocks): done
Writing superblocks and filesystem accounting information: done

This filesystem will be automatically checked every 21 mounts or
180 days, whichever comes first.  Use tune2fs -c or -i to override.
ROOT@LDL:/ # tune2fs -c 0 -i 0 /dev/vg1/lv1 
tune2fs 1.41.9 (22-Aug-2009)
Setting maximal mount count to -1
Setting interval between checks to 0 seconds
```

#### 7、fdisk -l查询磁盘状态，生成 /dev/dm-0： (Suse 系统下执行这一步，ubuntu 转至11)

#### 8、mount /dev/dm-0 /usr1 挂载磁盘（/usr1为挂载目录，如无此目录，需先创建，另此目录可根据业务需要进行修改）：

#### 9、 修改启动项，将磁盘信息记录到文件系统中，保证重启后，磁盘仍按照指定路径挂载.

```
编辑：vi /etc/fstab/
在最后一行写入：/dev/dm-0 /usr1 ext3 defaults 0 0   使用系统重启时实现自动挂载 (ubuntu使用ext4,suse和redhat使用ext3)
```


#### 10、df -h 查看磁盘挂载状态：

#### 11、mount /dev/vg1/lv1 /usr1 挂载磁盘（/usr1为挂载目录，如无此目录，需先创建，另此目录可根据业务需要进行修改）：

#### 12、echo “/dev/mapper/vg1-lv1 /usr1 ext4 defaults 0 0” >>/etc/fstab 修改启动项，使用系统重启时实现自动挂载（unbuntu使用ext4）
       more /etc/fstab 查看是否修改成功，/etc/fstab文件显示存在下图红框部分说明修改成功。
