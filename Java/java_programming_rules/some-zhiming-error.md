# Java十大低级错误
## 1	前言
本文档根据java开发人员在编码过程中容易忽视或经常出错的地方进行了整理，总结了十个比较常见的低级错误点，方便大家学习。　　
##　2	Java十大低级错误
```
1、	不能用“==”比较两个字符串内容相等。
2、	对list做foreach循环时，循环代码中不能修改list的结构。
3、	日志和实际情况不一致；捕获异常后没有在日志中记录异常栈。
4、	魔鬼数字。
5、	空指针异常。
6、	数组下标越界。
7、	将字符串转换为数字时没有捕获NumberFormatException异常。
8、	对文件、IO、数据库等资源进行操作后没有及时、正确进行释放。
9、	循环体编码时不考虑性能，循环体中包含不需要的重复逻辑。
10、	数据类没有重载toString()方法。
```
## 3	解读&案例
### 3.1	不能用“==”比较两个字符串内容相等。
#### 3.1.1	解读
```
两个字符串在比较内容是否相等的时候，如果使用“==”，当两个字符串不是指向内存中同一地址，那么即使这两个字符串内容一样，但是用
“==”比较出来的结果也是false。所以两个字符串在比较内容是否相等的时候一定要使用“equals”方法。
3.1.2	示例
下面就是一个字符串比较的例子：
```
```java
public class Test {
	public static void main(String[] args)
	{
		String a = new String("a");
		String a2 = "a";
		if(a == a2)
		{
			System.out.println("a == a2 return true.");
		}
		else
		{
			System.out.println("a == a2 return false.");
		}
		
		if(a.equals(a2))
		{
			System.out.println("a.equals(a2) return true.");
		}
		else
		{
			System.out.println("a.equals(a2) return false.");
		}
	}
}
```
```
最终输出的结果为：
a == a2 return false.
a.equals(a2) return true.
```
### 3.2	不能在foreach循环中修改list结构
```
3.2.1	解读
在jdk1.5版以上的foreach循环写法中，不能在循环代码中对正在循环的list的结构进行修改，即对list做add、remove等操作，
如果做了这些操作，必须立即退出循环，否则会抛出异常。
3.2.2	示例
```
```java
public class Test {
	public static void main(String[] args) 
	{
		List<Person> list = new ArrayList<Person>();
		Person p1 = new Person("张三", 23);
		Person p2 = new Person("李四", 26);
		Person p3 = new Person("王五", 34);
		Person p4 = new Person("刘二", 15);
		Person p5 = new Person("朱六", 40);

		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		for(Person p : list)
		{
			if("王五".equals(p.getName()))
			{
				list.remove(p); // 不能在此时删除对象。
			}
else if("李四".equals(p.getName()))
			{
				list.remove(p); // 不能在此时删除对象。
			}
		}
		System.out.println(list.size());
	}
}

class Person 
{
	private String name;
	private int age;

	public Person(String name, int age) 
	{
		this.name = name;
		this.age = age;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public int getAge() 
	{
		return age;
	}

	public void setAge(int age) 
	{
		this.age = age;
	}
}
```
```
解决上面代码红色部分的问题，可以通过循环取出对象，然后再循环结束后再进行删除。
		List<Person> list = new ArrayList<Person>();
		Person p1 = new Person(new String("张三"), 23);
		Person p2 = new Person(new String("李四"), 26);
		Person p3 = new Person(new String("王五"), 34);
		Person p4 = new Person(new String("刘二"), 15);
		Person p5 = new Person(new String("朱六"), 40);

		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		
		Person wangwu = null;
		Person lisi = null;
		for(Person p : list)
		{
			if("王五".equals(p.getName()))
			{
				wangwu = p;
			}
			else if("李四".equals(p.getName()))
			{
				lisi = p;
			}
		}
		
		list.remove(wangwu);
		list.remove(lisi);
```
### 3.3	日志规范性
#### 3.3.1	解读
```
日志是定位问题时最重要的依据，业务流程中缺少必要的日志会给定位问题带来很多麻烦，甚至可能造成问题完全无法定位。
异常产生后，必须在日志中以ERROR或以上级别记录异常栈，否则会导致异常栈丢失，无法确认异常产生的位置。
并不需要在每次捕获异常时都记录异常日志，这样可能导致异常被多次重复记录，影响问题的定位。但异常发生后其异常栈必须至少被记录一次。
和注释一样，日志也不是越多越好。无用的冗余日志不但不能帮助定位问题，还会干扰问题的定位。而错误的日志更是会误导问题，必须杜绝。
3.3.2	示例
下面的例子虽然打印了很多日志，但基本上都是无用的日志，难以帮助定位问题。甚至还有错误的日志会干扰问题的定位：
public void saveProduct1(ProductServiceStruct product)
{
    log.debug("enter method: addProduct()");

    log.debug("check product status");
    if (product.getProduct().getProductStatus() != ProductFieldEnum.ProductStatus.RELEASE)
    {
        throw new PMSException(PMSErrorCode.Product.ADD_ERROR);
    }

    log.debug("check tariff");
    BooleanResult result = checkTariff(product.getTariffs());
    if (!result.getResult())
    {
        throw new PMSException(PMSErrorCode.Product.ADD_ERROR);
    }

    log.debug("before add product");
    ProductService prodSrv = (ProductService) ServiceLocator.findService(ProductService.class);
    try
    {
        prodSrv.addProduct(product);
    }
    catch (BMEException e)
    {
        // 未记录异常栈，无法定位问题根源
    }
    log.debug("after add product");

    log.debug("exit method: updateProduct()");  // 错误的日志
}

而下面的例子日志打印的不多，但都是关键信息，可以很好的帮助定位问题：
public void saveProduct2(ProductServiceStruct product)
{
    if (product.getProduct().getProductStatus() != ProductFieldEnum.ProductStatus.RELEASE)
    {
        log.error(
                "product status "
                + product.getProduct().getProductStatus()
                + " error, expect " + ProductFieldEnum.ProductStatus.RELEASE);
        throw new PMSException(PMSErrorCode.Product.ADD_ERROR);
    }

    BooleanResult result = checkTariff(product.getTariffs());
    if (!result.getResult())
    {
        log.error(
                "check product tariff error "
                + result.getResultCode()
                + ": "
                + result.getResultDesc());
        throw new PMSException(PMSErrorCode.Product.ADD_ERROR);
    }

    ProductService prodSrv = (ProductService) ServiceLocator.findService(ProductService.class);
    try
    {
        prodSrv.addProduct(product);
    }
    catch (BMEException e)
    {
        log.error("add product error", e);
        throw new PMSException(PMSErrorCode.Product.ADD_ERROR, e);
    }
}

3.4	魔鬼数字
3.4.1	解读
在代码中使用魔鬼数字（没有具体含义的数字、字符串等）将会导致代码难以理解，应该将数字定义为名称有意义的常量。
将数字定义为常量的最终目的是为了使代码更容易理解，所以并不是只要将数字定义为常量就不是魔鬼数字了。如果常量的名称没有意义
，无法帮助理解代码，同样是一种魔鬼数字。
在个别特殊情况下，将数字定义为常量反而会导致代码更难以理解，此时就不应该强求将数字定义为常量。
3.4.2	示例
public void addProduct(ProductServiceStruct product)
{
    // 魔鬼数字，无法理解3具体代表产品的什么状态
    if (product.getProduct().getProductStatus() != 3)
    {
        throw new PMSException(PMSErrorCode.Product.ADD_ERROR);
    }

    BooleanResult result = checkTariff(product.getTariffs());
    if (!result.getResult())
    {
        throw new PMSException(PMSErrorCode.Product.ADD_ERROR);
    }
}



/**
*产品未激活状态
*/
private static final int UNACTIVATED = 0;
/**
*产品已激活状态
*/
private static final int ACTIVATED = 1;

public void addProduct2(ProductServiceStruct product)
{
    if (product.getProduct().getProductStatus() != ACTIVATED)
    {
        throw new PMSException(PMSErrorCode.Product.ADD_ERROR);
    }

    BooleanResult result = checkTariff(product.getTariffs());
    if (!result.getResult())
    {
        throw new PMSException(PMSErrorCode.Product.ADD_ERROR);
    }
}

3.5	空指针异常
3.5.1	解读
空指针异常是编码过程中最常见的异常，在使用一个对象的时候，如果对象可能为空，并且使用次对象可能会造成空指针异常，
那么需要先判断对象是否为空，再使用这个对象。
在进行常量和变量的相等判断时，建议将常量定义为Java对象封装类型（如将int类型的常量定义为Integer类型），
这样在比较时可以将常量放在左边，调用equals方法进行比较，可以省去不必要的判空。
3.5.2	示例
public class NullPointer
{
    static final Integer RESULT_CODE_OK = 0;
    static final Result RESULT_OK = new Result();

    public void printResult(Integer resultCode)
    {
        Result result = getResult(resultCode);

        // result可能为null，造成空指针异常
        if (result.isValid())
        {
            print(result);
        }
    }

    public Result getResult(Integer resultCode)
    {
        // 即使resultCode为null，仍然可以正确执行，减少额外的判空语句
        if (RESULT_CODE_OK.equals(resultCode))
        {
            return RESULT_OK;
        }
        return null;
    }

    public void print(Result result)
    {
        ...
    }
}

3.6	下标越界
3.6.1	解读
访问数组、List等容器内的元素时，必须首先检查下标是否越界，杜绝下标越界异常的发生。
3.6.2	示例
public class ArrayOver
{
    public void checkArray(String name)
    {
        // 获取一个数组对象
        String[] cIds = ContentService.queryByName(name);
        if(null != cIds)
        {
           // 只是考虑到cids有可能为null的情况，但是cids完全有可能是个0长度的数组，因此cIds[0]有可能数组下标越界
            String cid=cIds[0];
            cid.toCharArray();
        }
    }
}

3.7	字符串转数字
3.7.1	解读
调用Java方法将字符串转换为数字时，如果字符串的格式非法，会抛出运行时异常NumberFormatException。 
3.7.2	示例
错误例子：
public Integer getInteger1(String number)
{
    // 如果number格式非法，会抛出NumberFormatException
    return Integer.valueOf(number);
}
正确的处理方法如下：
public Integer getInteger2(String number)
{
    try
    {
        return Integer.valueOf(number);
    }
    catch (NumberFormatException e)
    {
        ...
	   //记录日志异常信息
        return null;
    }
}

注意：在捕获异常后一定要记录日志。
3.8	资源释放
3.8.1	解读
在使用文件、IO流、数据库连接等不会自动释放的资源时，应该在使用完毕后马上将其关闭。关闭资源的代码应该在
try...catch...finally的finally内执行，否则可能造成资源无法释放。
3.8.2	示例
错误案例如下：
public void writeProduct1(ProductServiceStruct product)
{
    try
    {
        FileWriter fileWriter = new FileWriter("");
        fileWriter.append(product.toString());
        // 如果append()抛出异常，close()方法就不会执行，造成IO流长时间无法释放
        fileWriter.close();
    }
    catch (IOException e)
    {
        ...
    }
}
关闭IO流的正确方法如下：
public void writeProduct2(ProductServiceStruct product)
{
    FileWriter fileWriter = null;
    try
    {
        fileWriter = new FileWriter("");
        fileWriter.append(product.toString());
    }
    catch (IOException e)
    {
        ...
	   //记录日志
    }
    finally
    {
        // 不管前面是否发生异常，finally中的代码一定会执行
        if (fileWriter != null)
        {
            try
            {
                fileWriter.close();
            }
            catch (IOException e)
            {
                ...
		      //记录日志
            }
        }
    }
}
注意：在捕获异常后一定要记录日志。

3.9	循环体性能
3.9.1	解读
循环体是软件中最容易造成性能问题的地方，所以在进行循环体编码时务必考虑性能问题。
在循环体内重复使用且不会变化的资源（如变量、文件对象、数据库连接等），应该在循环体开始前构造并初始化，
避免在循环体内重复和构造初始化造成CPU资源的浪费。
除非业务场景需要，避免在循环体内构造try...catch块，因为每次进入、退出try...catch块都会消耗一定的CPU资源，
将try...catch块放在循环体之外可以节省大量的执行时间。
3.9.2	示例
public void addProducts(List<ProductServiceStruct> prodList)
{
    for (ProductServiceStruct product : prodList)
    {
        // prodSrv在每次循环时都会重新获取，造成不必要的资源消耗
        ProductService prodSrv = (ProductService) ServiceLocator.findService(ProductService.class);

        // 避免在循环体内try...catch，放在循环体之外可以节省执行时间
        try
        {
            prodSrv.addProduct(product);
        }
        catch (BMEException e)
        {
            ...
		  //记录日志
        }
    }
}
在循环体中遇到字符串相加，一定要使用StringBuffer这个类。
3.10	数据类重载toString()方法
3.10.1	解读
数据类如果没有重载toString()方法，在记录日志的时候会无法记录数据对象的属性值，给定位问题带来困难。
3.10.2	示例
public class MdspProductExt
{
    private String key;
    
    private String value;

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}

class BusinessProcess
{
    private DebugLog log = LogFactory.getDebugLog(BusinessProcess.class);
    
    public void doBusiness(MdspProductExt prodExt)
    {
        try
        {
            ...
        }
        catch (PMSException e)
        {
            // MdspProductExt未重载toString()方法，日志中无法记录对象内属性的值，只能记录对象地址
            log.error("error while process prodExt " + prodExt);
        }
    }
}

```
