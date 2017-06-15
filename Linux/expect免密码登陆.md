http://blog.chinaunix.net/uid-20465760-id-3064252.html
```shell
#!/usr/bin/expect 
if { $argc < 3} {
puts stdout "pleae input ip, username and password."
exit 1
}
set ip [lindex $argv 0]
set username [lindex $argv 1]
set password [lindex $argv 2]
set timeout 10
spawn ssh $username@$ip "ls /home" 
expect {
 "*yes/no" {send "yes\n";exp_continue}
 "*Password:" {send "$password\n"}
}
interact
```
```shell
#!/usr/bin/expect 
if { $argc < 3} {
puts stdout "pleae input ip, username and password."
exit 1
}
set ip [lindex $argv 0]
set username [lindex $argv 1]
set password [lindex $argv 2]
set timeout 10
spawn ssh $username@$ip  
expect {
 "*yes/no" {send "yes\n";exp_continue}
 "*Password:" {send "$password\n"}
}

expect "*#" 
send "ls /\n"
send "cd /home\n"
send "ls /\n"
send "exit\n"
expect eof
exit
```
