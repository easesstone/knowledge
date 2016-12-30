## 主要是连接两种不同类型的系统的机器。

### 1，所依赖的插件。
```
SSH Agent Plugin
SSH Credentials Plugin
SSH Slaves plugin
等，具体jenkins 会默认安装。
```
### 2，linux 机器
```
通过用户名和密码链接
Lanch slave agents on Unix machine via SSH
还有通过sshkey 的方式。
```
### 3，windows 机器。

```
lanch slave agents via Java Web start.
还有一种通过用户和密码的方式。

windows jnpl 文件启动，
更改代理，以及允许浏览器的一些权限。（最好用1.8jdk）
"jdk_home/bin/javaws"  -viewer
"jdk_home/bin/javaws"  ****.jnpl
```
