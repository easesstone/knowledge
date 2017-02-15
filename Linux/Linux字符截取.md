```shell
enhaenha:/test/spark/jars # var=http://www.aaa.com/123.html
enhaenha:/test/spark/jars # echo ${var#*/}                 // 从左边开始，删除第一个出现的/ 以及其左边的字符
/www.aaa.com/123.html
enhaenha:/test/spark/jars # echo ${var##*/}                 // 从左边开始，删除最后一个出现的/ 以及其左边的字符
123.html
enhaenha:/test/spark/jars # echo ${var%/*}                  // 从右边开始，删除第一个出现的/ 以及其右边的字符
http://www.aaa.com
enhaenha:/test/spark/jars # echo ${var%%/*}                  // 从右边开始，删除最后一个出现的/ 以及其右边的字符
http:
enhaenha:/test/spark/jars # echo ${var:0:5}                  //截取0到第五个字符
http:
enhaenha:/test/spark/jars # echo ${var:7}                      //截取第七个到最后一个的字符。
www.aaa.com/123.html
enhaenha:/test/spark/jars # echo ${var:0-7:3}                    // 截取最后七个字符中的前3个字符
23.
SHA1000051223:/test/spark/jars # echo ${var:0-7}                 // 截取后七个字符
23.html
```
