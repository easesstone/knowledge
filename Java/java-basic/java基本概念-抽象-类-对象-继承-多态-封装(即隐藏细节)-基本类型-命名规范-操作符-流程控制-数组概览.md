### 抽象
1，编程语言的发展历程，是从机器语言，到汇编语言，到高级程序设计语言的抽象。<br/>
2，java 是一种面向对象的抽象语言。<br />
3，java 中的抽象，理解为把一切事物都理解为对象。<br />
4，java 程序是一系列对象的组合。<br />
5，java 对象有自己的空间，可以容纳其他的对象，也就是说，一个对象，可以作为另一个对象的一部分。<br />
6，每个对象都属于某一种类型，所谓的物语类聚的道理。<br />
7，同一个类型具有相同的行为和属性。<br />

### 类与对象
1，类是某一类具有相同属性或者行为的事物的总称，比如鸟类。<br />
2，对象是一个实实在在的事物，比如，鸟类中的凤凰。<br />
3，对象与对象之间的联系，以及对象的属性和行为，可以通过对象对外提供的接口来实现，比如凤凰，对外提供的属性有：<br />
赤红的羽毛，锋利的爪子，深邃的眼睛。对外提供的行为有，你惹我，我就烧死你。而凤凰和其他鸟类的联系，比如，我是<br />
神兽，其他所有的鸟类都要对我敬畏三分。<br/>

### 堆栈与堆
1，对象的引用存在于堆栈中，实际的对象存在堆中。 <br />
2，对于基本的数据类型，其直接存在于堆中。  <br />

### 基本数据类型

<table class="table table-bordered table-striped table-condensed">
<tr><td>基本类型</td><td>大小</td><td>最小值</td><td>最大值</td><td>包装器类型</td><td>在类中时的默认值</td></tr>
    <tr><td>boolean</td><td>-</td><td>-</td><td>-</td><td>Boolean</td><td>false</td></tr>
    <tr><td>char</td><td>16 bits</td><td>Unicode 0</td><td>2的16次方-1</td><td>Char</td><td>'\u0000' null</td></tr>
    <tr><td>byte</td><td>8 bits</td><td>-128</td><td>+127</td><td>Byte</td><td>0</td></tr>
    <tr><td>short</td><td>16 bits</td><td>-2的15次方</td><td>2的15次方-1</td><td>Short</td><td>0</td></tr>
    <tr><td>int</td><td>32 bits</td><td>-2的31次方</td><td>2的31次方-1</td><td>Int</td><td>0</td></tr>
    <tr><td>long</td><td>64 bits</td><td>-2的63次方</td><td>2的31次方-1</td><td>Long</td><td>0L</td></tr>
    <tr><td>float</td><td>32 bits</td><td>IEEE754</td><td>IEEEE754</td><td>Float</td><td>0.0f</td></tr>
    <tr><td>double</td><td>64 bits</td><td>IEEEE754</td><td>IEEEE754</td><td>Double</td><td>0.0d</td></tr>
    <tr><td>void</td><td>-</td><td>-</td><td>-</td><td>Void</td><td>-</td></tr>
</table>  
注意Ll Dd Ff  (0X 0x  --16  进制)  八进制以0开头，小数默认double。  
指数记数法
1.39e-43f 相当于1.39 x 10 的-43 次方。
### 操作符
1，算术运算符+ - * / % (注意+ - 可以作为正负，也可以作为加减)  
2，自增自减运算符  ++ -- （注意a++ 与 ++a 在有些场合的区别。）  
3，关系运算符 >  <  >=  <= == !=  （注意等等对比对象的情况）  
4, 逻辑运算符 ||  &&  !  (注意逻辑|| 和逻辑&& 的短路现象）  
5, 按位操作符， ^ (异或，两个不相同时，返回1) |  & ！
6，移位操作符 >> <<  >>>(无符号右移，高位填充0，注意byte，short 的特殊情况。)


