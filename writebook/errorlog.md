```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:2.18.1:test (default-test) 
on project zk-helper: Execution default-test of goal org.apache.maven.plugins:maven-surefire-plugin:2.18.1:test failed: 
The forked VM terminated without properly saying goodbye. VM crash or System.exit called?
[ERROR] Command was cmd.exe /X /C ""C:\Program Files\Java\JDK1.7.0_60\jre\bin\java" 
-jar D:\code\Hive_ODBC\zkcode\target\surefire\surefirebooter8035385191175289231.jar
D:\code\Hive_ODBC\zkcode\target\surefire\surefire6459104780331452738tmp 
D:\code\Hive_ODBC\zkcode\target\surefire\surefire_05202842494619497778tmp"

```

* 2.12 - The forked VM terminated without saying properly goodbye. VM crash or System.exit called ? http://jira.codehaus.org/browse/SUREFIRE-827
* 2.12.1 - Unable to locate surefire-booter in the list of plugin artifacts https://jira.codehaus.org/browse/SUREFIRE-896
* 2.12.2 - has a bug when no tests are there in a module it will look for failsafe-summary.xml and fail http://jira.codehaus.org/browse/SUREFIRE-901
