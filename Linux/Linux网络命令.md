## 网络知识
### 1） ifconfig
```
      网络接口的作用,见名知义，用于连接网络的接口。
      类unix系统中，网络接口的命名遵循eth0,eth1 这种命名惯例。
      其他的网络接口，如usb0,wlan0,则是usb 网路接口，无线lan网络接口。
      
      ifconfig 用于显示网络接口，子网掩码等详细信息。
      一些系统中，输入ifconfig 会提示“conmand not found”, 这是因为，ifconfig 位于/sbin/ifconfig, 而sbin 没有加入到环境变量中。
      
      例如：
      输入ifconfig 后，显示的详细信息。（有些机器只有eth0和lo,有些机器还有eth1,即大小网之分。）
      例如：
      eth0      Link encap:Ethernet  HWaddr 30:D1:7E:73:46:08  ---------------------硬件地址：MAC地址 
          inet addr:9.91.8.155  Bcast:9.91.255.255  Mask:255.255.0.0   ------inet addr:9.91.8.155 小网ip, Bcast:9.91.255.255 广播
          inet6 addr: fe80::32d1:7eff:fe73:4608/64 Scope:Link                 Mast:255.255.0.0 子网掩码
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:1444281476 errors:0 dropped:0 overruns:0 frame:0
          TX packets:1701156101 errors:0 dropped:4 overruns:0 carrier:0
          collisions:0 txqueuelen:1000 
          RX bytes:1343304202584 (1281074.7 Mb)  TX bytes:2242548175232 (2138660.5 Mb)
          Interrupt:26 

      eth1      Link encap:Ethernet  HWaddr 30:D1:7E:73:46:09  
          inet addr:10.175.102.178  Bcast:10.175.103.255  Mask:255.255.252.0
          inet6 addr: fe80::32d1:7eff:fe73:4609/64 Scope:Link
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:4183101611 errors:0 dropped:522 overruns:0 frame:54
          TX packets:3135385807 errors:0 dropped:12 overruns:0 carrier:0
          collisions:0 txqueuelen:1000 
          RX bytes:1630686411994 (1555143.7 Mb)  TX bytes:2897162660838 (2762949.6 Mb)
          Interrupt:28 

      lo        Link encap:Local Loopback  
          inet addr:127.0.0.1  Mask:255.0.0.0
          inet6 addr: ::1/128 Scope:Host
          UP LOOPBACK RUNNING  MTU:16436  Metric:1
          RX packets:1226681 errors:0 dropped:0 overruns:0 frame:0
          TX packets:1226681 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:0 
          RX bytes:5422029957 (5170.8 Mb)  TX bytes:5422029957 (5170.8 Mb)
          
      常用技巧，
      ifconfig  | cut -c -10  | tr -d ' ' | tr -s '\n'
      注释：作用，打印接口列表，
      ifconfig 显示接口详细信息，cut -c -10 ，截取每一行前10个字符，包括第十个。 tr -d ' ' ，删除每行的所有空格，tr  -s '\n',压缩重复的换行符。
      
      显示特定网络接口的详细信息：
      ifconfig eth0
      
      设置网络接口的ip
      ifconfig eth0 10.120.181.150
      
      设置网络ip 地址的子掩网码。
      ifconfig eth0 10.120.181.150 netmask 255.255.255.204
      
      
      Mac 地址欺骗：
      ifconfig eth0 hw ether 31:D1:7E:73:46:09 
      
      网络中最根本的寻址方式是ip 地址。
      网络中的网站的url 对应的是一个ip 地址。
      把ip地址映射成url 的技术称为DNS(Domain Name Service域名服务。)
      
      一个域名，可以分配多个ip 地址。
      
      traceroute 10.120.181.150  检查到达10.120.181.150 经过的路由或网关。
      
      
           
          
```
