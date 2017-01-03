###umount device busy  
```
Example:
root # umount /mnt/suse
umount: /mnt: device is busy.
        (In some cases useful info about processes that use
         the device is found by lsof(8) or fuser(1))
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
