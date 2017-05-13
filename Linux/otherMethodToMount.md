```
Linux的硬盘识别:
一般使用”fdisk -l”命令可以列出系统中当前连接的硬盘
设备和分区信息.新硬盘没有分区信息,则只显示硬盘大小信息.

1.关闭服务器加上新硬盘

2.启动服务器，以root用户登录

3.查看硬盘信息
#fdisk -l
[cpp] view plain copy 在CODE上查看代码片派生到我的代码片
Disk /dev/sda: 42.9 GB, 42949672960 bytes  
255 heads, 63 sectors/track, 5221 cylinders  
Units = cylinders of 16065 * 512 = 8225280 bytes  
Sector size (logical/physical): 512 bytes / 512 bytes  
I/O size (minimum/optimal): 512 bytes / 512 bytes  
Disk identifier: 0x0004406e  
   Device Boot      Start         End      Blocks   Id  System  
/dev/sda1   *           1          39      307200   83  Linux  
Partition 1 does not end on cylinder boundary.  
/dev/sda2              39        2589    20480000   83  Linux  
/dev/sda3            2589        2850     2097152   82  Linux swap / Solaris  
/dev/sda4            2850        5222    19057664    5  Extended  
/dev/sda5            2850        5222    19056640   83  Linux  
   
Disk /dev/sdb: 10.7 GB, 10737418240 bytes  
255 heads, 63 sectors/track, 1305 cylinders  
Units = cylinders of 16065 * 512 = 8225280 bytes  
Sector size (logical/physical): 512 bytes / 512 bytes  
I/O size (minimum/optimal): 512 bytes / 512 bytes  
Disk identifier: 0x14b52796  
   Device Boot      Start         End      Blocks   Id  System  

4.创建新硬盘分区命令参数：
fdisk可以用m命令来看fdisk命令的内部命令；
a：命令指定启动分区；
d：命令删除一个存在的分区；
l：命令显示分区ID号的列表；
m：查看fdisk命令帮助；
n：命令创建一个新分区；
p：命令显示分区列表；
t：命令修改分区的类型ID号；
w：命令是将对分区表的修改存盘让它发生作用。
 

5.进入磁盘，对磁盘进行分区，注意红色部分。
#fdisk /dev/sdb
[cpp] view plain copy 在CODE上查看代码片派生到我的代码片
Command (m for help):n  
Command action  
　　   e    extended                  //输入e为创建扩展分区  
　　   p    primary partition (1-4)      //输入p为创建逻辑分区  
p  
Partion number(1-4)：1      //在这里输入l，就进入划分逻辑分区阶段了；  
First cylinder (51-125, default 51):   //注：这个就是分区的Start 值；这里最好直接按回车，如果您输入了一个非默认的数字，会造成空间浪费；  
Using default value 51  
Last cylinder or +size or +sizeM or +sizeK (51-125, default 125): +200M 注：这个是定义分区大小的，+200M 就是大小为200M ；当然您也可以根据p提示的单位cylinder的大小来算，然后来指定 End的数值。回头看看是怎么算的；还是用+200M这个办法来添加，这样能直观一点。如果您想添加一个10G左右大小的分区，请输入 +10000M ；  
  
Command (m for help): w                     //最后输入w回车保存。  
查看一下： 
#fdisk -l
可以看到/dev/sdb1分区,我就省略截图咯。
 
6.格式化分区：
#mkfs.ext3 /dev/sdb1           //注：将/dev/sdb1格式化为ext3类型
[cpp] view plain copy 在CODE上查看代码片派生到我的代码片
mke2fs 1.41.12 (17-May-2010)  
文件系统标签=  
操作系统:Linux  
块大小=4096 (log=2)  
分块大小=4096 (log=2)  
Stride=0 blocks, Stripe width=0 blocks  
640848 inodes, 2562359 blocks  
128117 blocks (5.00%) reserved for the super user  
第一个数据块=0  
Maximum filesystem blocks=2625634304  
79 block groups  
32768 blocks per group, 32768 fragments per group  
8112 inodes per group  
Superblock backups stored on blocks:  
        32768, 98304, 163840, 229376, 294912, 819200, 884736, 1605632  
   
正在写入inode表: 完成  
Creating journal (32768 blocks): 完成  
Writing superblocks and filesystem accounting information: 完成  
   
This filesystem will be automatically checked every 35 mounts or  
180 days, whichever comes first.  Use tune2fs -c or -i to override.  
这样就格式化好了，我们就可以用mount 加载这个分区，然后使用这个文件系统；

7.创建/data1目录：
#mkdir /data1

8.开始挂载分区：
#mount /dev/sdb1 /data1

9.查看硬盘大小以及挂载分区：
#df -h

10.配置开机自动挂载
因为mount挂载在重启服务器后会失效，所以需要将分区信息写到/etc/fstab文件中让它永久挂载：
#vim /etc/fstab
加入：
/dev/sdb1(磁盘分区)  /data1（挂载目录） ext3（文件格式）defaults  0  0

11.重启系统

```
