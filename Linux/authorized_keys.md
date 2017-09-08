authorized_key 不生效。
```
     ~/.ssh/authorized_keys
             Lists the public keys (DSA/ECDSA/RSA) that can be used for logging in as this user.  The format of
             this file is described above.  The content of the file is not highly sensitive, but the recommended
             permissions are read/write for the user, and not accessible by others.
     
             If this file, the ~/.ssh directory, or the user's home directory are writable by other users, then the
             file could be modified or replaced by unauthorized users.  In this case, sshd will not allow it to be
             used unless the StrictModes option has been set to “no”.
             
             注意文件的权限：
             dr-xr-x---.  33 root root    4096 Sep  8 15:55 root
             drwx------   2 root root    4096 Sep  8 15:51 .ssh
             -rw------- 1 root root  391 Sep  8 15:51 authorized_keys
             -rw------- 1 root root 1675 Sep  8 15:51 id_rsa
             -rw-r--r-- 1 root root  391 Sep  8 15:51 id_rsa.pub
             -rw-r--r-- 1 root root  400 Sep  8 15:51 known_hosts
```
