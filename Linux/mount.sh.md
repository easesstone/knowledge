```shell
export diskname=$1
function checkResult()
{
    if [ $? -eq 0 ];then
        echo "do $1 success."
        echo ""
        echo ""
    else
        echo "do $1 false."
	echo ""
        echo ""
        exit 1
    fi
}


function mountTheDisk()
{
    echo "show message,the name of disk is : ---- ${diskname}."
#    diskname=$1
    pvcreate ${diskname}
    checkResult "pvcreate "
   
    vgcreate vg1  ${diskname}
    checkResult "vgcreate "
    
    vgdisplay
    SizeOfDisk=`vgdisplay  | grep "Total PE" | gawk -F " "  '{print $NF}'`
    checkResult "get the size of disk failed."    
    echo "the Size of disk is ${SizeOfDisk}"

    lvcreate -l ${SizeOfDisk} -n lv1 vg1
    checkResult  "lvcreate -l  sizeofdisk -n lv1 vg1"

    mkfs.ext3 -j /dev/vg1/lv1
    checkResult  "mkfs.ext3 -j /dev/vg1/lv1"

    tune2fs -c 0 -i 0 /dev/vg1/lv1
    checkResult  "tune2fs -c 0 -i 0 /dev/vg1/lv1"

    fdisk -l 
    echo "/dev/mapper/vg1-lv1  /usr1                ext3       defaults              0 0 " >>/etc/fstab
}

mountTheDisk

echo "vi /etc/fstab/,add something like that /dev/dm-0 /usr1 ext3 defaults 0 0  ,and mount the disk to where you want to mount it"
```
