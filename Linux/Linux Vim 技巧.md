### 复制剪切粘贴
复制粘贴  
在Esc 模式下，在所处光标的一行，连续按两次y  
即yy  
然后移动到某一行，按p  
则会在光标后的一行里进行插入复制的一行   
复制多行n+yy  
剪切的命令则是  
dd或者n+dd  

### vim 显示缩进等设置。
set tabstop=4   tab默认空格数  
set softtabstop=4   
set shiftwidth=4   
set noexpandtab   
set nu 展示行号   
set autoindent  自动缩进  
set cindent  针对c语言的自动缩进  


### 搜索字符
/或者？  加要找的字符。  
快速回到行首或者行尾  home end
快速回到首行或尾行shift+g gg
