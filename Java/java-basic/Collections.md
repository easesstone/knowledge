```

http://www.cnblogs.com/skywang12345/p/3498454.html#a2
1. List的实现类主要有: LinkedList, ArrayList, Vector, Stack。

(01) LinkedList是双向链表实现的双端队列；它不是线程安全的，只适用于单线程。
(02) ArrayList是数组实现的队列，它是一个动态数组；它也不是线程安全的，只适用于单线程。
(03) Vector是数组实现的矢量队列，它也一个动态数组；不过和ArrayList不同的是，Vector是线程安全的，它支持并发。
(04) Stack是Vector实现的栈；和Vector一样，它也是线程安全的。

 

2. Set的实现类主要有: HastSet和TreeSet。

(01) HashSet是一个没有重复元素的集合，它通过HashMap实现的；HashSet不是线程安全的，只适用于单线程。
(02) TreeSet也是一个没有重复元素的集合，不过和HashSet不同的是，TreeSet中的元素是有序的；它是通过TreeMap实现的；
TreeSet也不是线程安全的，只适用于单线程。

 

3.Map的实现类主要有: HashMap，WeakHashMap, Hashtable和TreeMap。

(01) HashMap是存储“键-值对”的哈希表；它不是线程安全的，只适用于单线程。
(02) WeakHashMap是也是哈希表；和HashMap不同的是，HashMap的“键”是强引用类型，而WeakHashMap的“键”是弱引用类型，
也就是说当WeakHashMap 中的某个键不再正常使用时，会被从WeakHashMap中被自动移除。WeakHashMap也不是线程安全的，只适用于单线程。
(03) Hashtable也是哈希表；和HashMap不同的是，Hashtable是线程安全的，支持并发。
(04) TreeMap也是哈希表，不过TreeMap中的“键-值对”是有序的，它是通过R-B Tree(红黑树)实现的；TreeMap不是线程安全的，只适用于单线程。
更多关于这些集合类的介绍，可以参考“Java 集合系列目录(Category)”。

 

为了方便，我们将前面介绍集合类统称为”java集合包“。java集合包大多是“非线程安全的”，虽然可以通过Collections工具类中的方
法获取java集合包对应的同步类，但是这些同步类的并发效率并不是很高。为了更好的支持高并发任务，并发大师Doug Lea在JUC
(java.util.concurrent)包中添加了java集合包中单线程类的对应的支持高并发的类。例如，ArrayList对应的高并发类是
CopyOnWriteArrayList，HashMap对应的高并发类是ConcurrentHashMap，等等。
JUC包在添加”java集合包“对应的高并发类时，为了保持API接口的一致性，使用了”Java集合包“中的框架。
例如，CopyOnWriteArrayList实现了“Java集合包”中的List接口，HashMap继承了“java集合包”中的AbstractMap类，
等等。得益于“JUC包使用了Java集合包中的类”，如果我们了解了Java集合包中的类的思想之后，理解JUC包中的类也相对容易；
理解时，最大的难点是，对JUC包是如何添加对“高并发”的支持的！

```
