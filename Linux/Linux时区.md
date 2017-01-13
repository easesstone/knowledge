### 时区理解
*  其实初中地理课已经涉及，很多人都多少了解一些，可能只是细节搞不太清楚。为什么会将地球分为不同时区呢？
因为地球总是自西向东自转，东边总比西边先看到太阳，东边的时间也总比西边的早。东边时刻与西边时刻的差值不仅要以时计，
而且还要以分和秒来计算。整个地球分为二十四时区，每个时区都有自己的本地时间。在国际无线电通信场合，为了统一起见，
使用一个统一的时间，称为通用协调时(UTC, Universal Time Coordinated)。(GMT, Greenwich Mean Time)一样，都与英国伦敦的本地时相同。

### 查看时区
* Date -R  或者， more  /etc/sysconfig/clock
```
root@hello:/home # date -R 
Fri, 13 Jan 2017 15:11:12 +0800

root@hello:/home # more  /etc/sysconfig/clock
## Path:		System/Environment/Clock
## Description:		Information about your timezone and time
## Type:		string(-u,--utc,--localtime)
## ServiceRestart:	boot.clock
## Command:		/sbin/refresh_initrd
#
# Set to "-u" if your system clock is set to UTC, and to "--localtime"
# if your clock runs that way.
#
HWCLOCK="-u"
## Type:		yesno
## Default:		yes
## Description: Write back system time to the hardware clock
#
# Is set to "yes" write back the system time to the hardware
# clock at reboot or shutdown. Usefull if hardware clock is
# much more inaccurate than system clock.  Set to "no" if
# system time does it wrong due e.g. missed timer interrupts.
# If set to "no" the hardware clock adjust feature is also
# skipped because it is rather useless without writing back
# the system time to the hardware clock.
#
SYSTOHC="yes"
## Type:		string(Europe/Berlin,Europe/London,Europe/Paris)
## ServiceRestart:	boot.clock
#
# Timezone (e.g. CET)
# (this will set /usr/lib/zoneinfo/localtime)
#
TIMEZONE="Asia/Shanghai"
DEFAULT_TIMEZONE="US/Eastern"

```

### 修改时区,3种方法，
*  1,tzselect, 设置环境变量，export TZ='Asia/Shanghai' ,source /etc/profile.
*  2,cp /usr/share/zoneinfo/America/Los_Angeles /etc/localtime 
*  3,ln /usr/share/zoneinfo/Asia/Shanghai /etc/localtime ，报错的话，rm /etc/localtime,
之后 ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
*  4，如果是，Rehat 或者centOS 可以用timeconfig 设置。
