```
Linux对于每个用户，系统限制其最大进程数。为提高性能，可以根据设备资源情况，
设置各linux 用户的最大进程数
可以用ulimit -a 来显示当前的各种用户进程限制。
下面我把某linux用户的最大进程数设为10000个：
     ulimit -u 10240
     对于需要做许多 socket 连接并使它们处于打开状态的 Java 应用程序而言，
     最好通过使用 ulimit -n xx 修改每个进程可打开的文件数，缺省值是 1024。
     ulimit -n 4096 将每个进程可以打开的文件数目加大到4096，缺省为1024
     其他建议设置成无限制（unlimited）的一些重要设置是：
     数据段长度：ulimit -d unlimited
     最大内存大小：ulimit -m unlimited
     堆栈大小：ulimit -s unlimited
     CPU 时间：ulimit -t unlimited
     虚拟内存：ulimit -v unlimited
　　
     暂时地，适用于通过 ulimit 命令登录 shell 会话期间。
     永久地，通过将一个相应的 ulimit 语句添加到由登录 shell 读取的文件中，
     即特定于 shell 的用户资源文件，如：
1)、解除 Linux 系统的最大进程数和最大文件打开数限制：
        vi /etc/security/limits.conf
        # 添加如下的行
        * soft noproc 11000
        * hard noproc 11000
        * soft nofile 4100
        * hard nofile 4100
       说明：* 代表针对所有用户，noproc 是代表最大进程数，nofile 是代表最大文件打开数
2)、让 SSH 接受 Login 程式的登入，方便在 ssh 客户端查看 ulimit -a 资源限制：
        a、vi /etc/ssh/sshd_config
             把 UserLogin 的值改为 yes，并把 # 注释去掉
        b、重启 sshd 服务：
              /etc/init.d/sshd restart
3)、修改所有 linux 用户的环境变量文件：
    vi /etc/profile
    ulimit -u 10000
    ulimit -n 4096
    ulimit -d unlimited
    ulimit -m unlimited
    ulimit -s unlimited
    ulimit -t unlimited
    ulimit -v unlimited
 保存后运行#source /etc/profile 使其生效
/**************************************
有时候在程序里面需要打开多个文件，进行分析，系统一般默认数量是1024，
（用ulimit -a可以看到）对于正常使用是够了，但是对于程序来讲，就太少了。
修改2个文件。
 
1./etc/security/limits.conf
vi /etc/security/limits.conf
加上：
* soft nofile 8192
* hard nofile 20480
 
2./etc/pam.d/login
session required /lib/security/pam_limits.so
/**********
另外确保/etc/pam.d/system-auth文件有下面内容
session required /lib/security/$ISA/pam_limits.so
这一行确保系统会执行这个限制。
 
/***********
3.一般用户的.bash_profile
#ulimit -n 1024
重新登陆ok
 
ulimit 的作用
  =======================
 
ulimit：显示（或设置）用户可以使用的资源的限制（limit），这限制分为软限制（当前限制）
和硬限制（上限），其中硬限制是软限制的上限值，应用程序在运行过
程中使用的系统资源不超过相应的软限制，任何的超越都导致进程的终止。
 
参数 描述
ulimited 不限制用户可以使用的资源，但本设置对可打开的最大文件数（max open files）
和可同时运行的最大进程数（max user processes）无效
-a 列出所有当前资源极限
-c 设置core文件的最大值.单位:blocks
-d 设置一个进程的数据段的最大值.单位:kbytes
-f Shell 创建文件的文件大小的最大值，单位：blocks
-h 指定设置某个给定资源的硬极限。如果用户拥有 root 用户权限，可以增大硬极限。任何用户均可减少硬极限
-l 可以锁住的物理内存的最大值
-m 可以使用的常驻内存的最大值,单位：kbytes
-n 每个进程可以同时打开的最大文件数
-p 设置管道的最大值，单位为block，1block=512bytes
-s 指定堆栈的最大值：单位：kbytes
-S 指定为给定的资源设置软极限。软极限可增大到硬极限的值。如果 -H 和 -S 标志均未指定，极限适用于以上二者
-t 指定每个进程所使用的秒数,单位：seconds
-u 可以运行的最大并发进程数
-v Shell可使用的最大的虚拟内存，单位：kbytes
-x
范例1：
[root@localhost proc]# ulimit -a
core file size (blocks, -c) 100
data seg size (kbytes, -d) unlimited
file size (blocks, -f) unlimited
pending signals (-i) 2047
max locked memory (kbytes, -l) 32
max memory size (kbytes, -m) unlimited
open files (-n) 1024
pipe size (512 bytes, -p) 8
POSIX message queues (bytes, -q) 819200
stack size (kbytes, -s) 8192
cpu time (seconds, -t) unlimited
max user processes (-u) 2047
virtual memory (kbytes, -v) unlimited
file locks (-x) unlimited
[root@localhost proc]#
输出的每一行由资源名字、（单位，ulimit命令的参数）、软限制组成。详细解释：
参数 描述
core file size core文件的最大值为100 blocks，
data seg size 进程的数据段可以任意大
file size 文件可以任意大
pending signals 最多有2047个待处理的信号
max locked memory 一个任务锁住的物理内存的最大值为32kB
max memory size 一个任务的常驻物理内存的最大值
open files 一个任务最多可以同时打开1024的文件
pipe size 管道的最大空间为4096字节
POSIX message queues POSIX的消息队列的最大值为819200字节
stack size 进程的栈的最大值为8192字节
cpu time 进程使用的CPU时间
max user processes 当前用户同时打开的进程(包括线程)的最大个数为2047
virtual memory 没有限制进程的最大地址空间
file locks 所能锁住的文件的最大个数没有限制
范例2：通过ulimit命令来限制文件的大小，从而导致拷贝命令的失败
[root@localhost]ls temp.txt
ls: temp.txt: 没有那个文件或目录
[root@localhost]ulimit -f 1 #设置创建文件的最大块(一块=512字节)
[root@localhost]cat a.c > temp.txt
文件大小超出限制
文件a.c的大小是5002字节,而我们设定的创建文件的大小是512字节x1块=512字节 
 
 
1、修改用戶進程可打開文件數限制
 
在Linux平台上，無論編寫客戶端程序還是服務端程序，在進行高並發TCP連接處理時，
最高的並發數量都要受到系統對用戶單一進程同時可打開文件數量的限制(
這是因為系統為每個TCP連接都要創建一個socket句柄，每個socket句柄同時也是
一個文件句柄)。可使用ulimit命令查看系統允許當前用戶進程打開的文件數限制：
 
[speng@as4 ~]$ ulimit -n
 
1024
 
這表示當前用戶的每個進程最多允許同時打開1024個文件，這1024個文件中還得除去每個進程必然打開
的標準輸入，標準輸出，標準錯誤，服務器監聽socket，進程間通訊的unix域socket等文件，那麼剩
下的可用於客戶端socket連接的文件數就只有大概1024-10=1014個左右。也就是說缺省情況下，
基於Linux的通訊程序最多允許同時1014個TCP並發連接。
 
對於想支持更高數量的TCP並發連接的通訊處理程序，就必須修改Linux對當前用戶的進程同時打開的
文件數量的軟限制(soft limit)和硬限制(hardlimit)。其中軟限制是指Linux在當前系統能夠承受的
範圍內進一步限制用戶同時打開的文件數；硬限制則是根據系統硬件資源狀況(主要是系統內存)計算出來
的系統最多可同時打開的文件數量。通常軟限制小於或等於硬限制。
 
修改上述限制的最簡單的辦法就是使用ulimit命令：
 
[speng@as4 ~]$ ulimit -n<file_num>
 
上述命令中，在<file_num>中指定要設置的單一進程允許打開的最大文件數。如果系統回顯類似於
"Operation notpermitted"之類的話，說明上述限制修改失敗，實際上是因為在<file_num>中
指定的數值超過了Linux系統對該用戶打開文件數的軟限製或硬限制。因此，就需要修改Linux系統對用戶的關於打開文件數的軟限制和硬限制。
 
第一步，修改/etc/security/limits.conf文件，在文件中添加如下行：
 
speng soft nofile 10240
 
speng hard nofile 10240
 
其中speng指定了要修改哪個用戶的打開文件數限制，可用'*'號表示修改所有用戶的限制；
soft或hard指定要修改軟限制還是硬限制；10240則指定了想要修改的新的限制值，
即最大打開文件數(請注意軟限制值要小於或等於硬限制)。修改完後保存文件。
 
第二步，修改/etc/pam.d/login文件，在文件中添加如下行：
 
session required /lib/security/pam_limits.so
 
這是告訴Linux在用戶完成系統登錄後，應該調用pam_limits.so模塊來設置系統對該用戶
可使用的各種資源數量的最大限制(包括用戶可打開的最大文件數限制)，而pam_limits.so
模塊就會從/etc/security/limits.conf文件中讀取配置來設置這些限制值。修改完後保存此文件。
 
第三步，查看Linux系統級的最大打開文件數限制，使用如下命令：
 
[speng@as4 ~]$ cat /proc/sys/fs/file-max
 
12158
 
這表明這台Linux系統最多允許同時打開(即包含所有用戶打開文件數總和)12158個文件，
是Linux系統級硬限制，所有用戶級的打開文件數限制都不應超過這個數值。通常這個系統級硬限制
是Linux系統在啟動時根據系統硬件資源狀況計算出來的最佳的最大同時打開文件數限制，
如果沒有特殊需要，不應該修改此限制，除非想為用戶級打開文件數限制設置超過此限制的值。
修改此硬限制的方法是修改/etc/rc.local腳本，在腳本中添加如下行：
 
echo 22158 > /proc/sys/fs/file-max
 
這是讓Linux在啟動完成後強行將系統級打開文件數硬限制設置為22158。修改完後保存此文件。
 
完成上述步驟後重啟系統，一般情況下就可以將Linux系統對指定用戶的單一進程允許同時打開的最大
文件數限制設為指定的數值。如果重啟後用ulimit-n命令查看用戶可打開文件數限制仍然低於上述步驟中
設置的最大值，這可能是因為在用戶登錄腳本/etc/profile中使用ulimit -n命令已經將用戶可同時打開的
文件數做了限制。由於通過ulimit-n修改系統對用戶可同時打開文件的最大數限制時，新修改的值只能小於或
等於上次ulimit-n設置的值，因此想用此命令增大這個限制值是不可能的。所以，如果有上述問題存在，
就只能去打開/etc/profile腳本文件，在文件中查找是否使用了ulimit-n限制了用戶可同時打開的最大文件數量，
如果找到，則刪除這行命令，或者將其設置的值改為合適的值，然後保存文件，用戶退出並重新登錄系統即可。
 
通過上述步驟，就為支持高並發TCP連接處理的通訊處理程序解除關於打開文件數量方面的系統限制。
 
2、修改網絡內核對TCP連接的有關限制
 
在Linux上編寫支持高並發TCP連接的客戶端通訊處理程序時，有時會發現儘管已經解除了
系統對用戶同時打開文件數的限制，但仍會出現並發TCP連接數增加到一定數量時，
再也無法成功建立新的TCP連接的現象。出現這種現在的原因有多種。
 
第一種原因可能是因為Linux網絡內核對本地端口號範圍有限制。此時，進一步分析為什麼無法建立TCP連接
，會發現問題出在connect()調用返回失敗，查看系統錯誤提示消息是
"Can't assign requestedaddress"。同時，如果在此時用tcpdump工具監視網絡，
會發現根本沒有TCP連接時客戶端發SYN包的網絡流量。這些情況說明問題在於本地Linux系統內核中有限制
。其實，問題的根本原因在於Linux內核的TCP/IP協議實現模塊對系統中所有的客戶端
TCP連接對應的本地端口號的範圍進行了限制(例如，內核限製本地端口號的範圍為1024~32768之間)。
當系統中某一時刻同時存在太多的TCP客戶端連接時，由於每個TCP客戶端連接都要佔用一個
唯一的本地端口號(此端口號在系統的本地端口號範圍限制中)，如果現有的TCP客戶端連接已將所
有的本地端口號佔滿，則此時就無法為新的TCP客戶端連接分配一個本地端口號了，因此系統會在這
種情況下在connect()調用中返回失敗，並將錯誤提示消息設為"Can't assignrequested 
address"。有關這些控制邏輯可以查看Linux內核源代碼，以linux2.6內核為例，可以查看tcp_ipv4.c文件中如下函數：
 
static int tcp_v4_hash_connect(struct sock *sk)
 
請注意上述函數中對變量sysctl_local_port_range的訪問控制。
變量sysctl_local_port_range的初始化則是在tcp.c文件中的如下函數中設置：
 
void __init tcp_init(void)
 
內核編譯時默認設置的本地端口號範圍可能太小，因此需要修改此本地端口範圍限制。
 
第一步，修改/etc/sysctl.conf文件，在文件中添加如下行：
 
net.ipv4.ip_local_port_range = 1024 65000
 
這表明將系統對本地端口範圍限制設置為1024~65000之間。請注意，本地端口範
圍的最小值必須大於或等於1024；而端口範圍的最大值則應小於或等於65535。修改完後保存此文件。
 
第二步，執行sysctl命令：
 
[speng@as4 ~]$ sysctl -p
 
如果系統沒有錯誤提示，就表明新的本地端口範圍設置成功。如果按上述端口範圍進
行設置，則理論上單獨一個進程最多可以同時建立60000多個TCP客戶端連接。
 
第二種無法建立TCP連接的原因可能是因為Linux網絡內核的IP_TABLE防火牆對最大
跟踪的TCP連接數有限制。此時程序會表現為在connect()調用中阻塞，如同死機，如
果用tcpdump工具監視網絡，也會發現根本沒有TCP連接時客戶端發SYN包的網絡流量。
由於IP_TABLE防火牆在內核中會對每個TCP連接的狀態進行跟踪，跟踪信息將會放在位於內核
內存中的conntrackdatabase中，這個數據庫的大小有限，當系統中存在過多的TCP連接時，
數據庫容量不足，IP_TABLE無法為新的TCP連接建立跟踪信息，於是表現為在connect()調用中阻塞
。此時就必須修改內核對最大跟踪的TCP連接數的限制，方法同修改內核對本地端口號範圍的限制是類似的：
 
第一步，修改/etc/sysctl.conf文件，在文件中添加如下行：
 
net.ipv4.ip_conntrack_max = 10240
 
這表明將系統對最大跟踪的TCP連接數限制設置為10240。請注意，此限制值要盡量小，以節省對內核內存的佔用。
 
第二步，執行sysctl命令：
 
[speng@as4 ~]$ sysctl -p
 
如果系統沒有錯誤提示，就表明系統對新的最大跟踪的TCP連接數限制修改成功。
如果按上述參數進行設置，則理論上單獨一個進程最多可以同時建立10000多個TCP客戶端連接。
 
*******注意*******
 
sysctl -p 報錯net.ipv4.ip_conntrack_max" is an unknown key 則：modprobe ip_conntrack

```
