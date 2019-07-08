##Mybatis Generator 自动代码生成工具

MyBatis Generator：MyBatis 的开发团队提供了一个很强大的代码生成器，代码包含了数据库表对应的实体
类 、Mapper 接口类、 Mapper XML 文件和 Example 对象等，这些代码文件中几乎包含了全部的单表操作方
法，使用 MBG 可以极大程度上方便我们使用 MyBatis，还可以减少很多重复操作；

 generatorConfiguration – 根节点
     properties – 用于指定一个需要在配置中解析使用的外部属性文件；
     classPathEntry - 在MBG工作的时候，需要额外加载的依赖包；
     context -用于指定生成一组对象的环境
        • property (0 个或多个） - 设置一些固定属性
        • plugin (0 个或多个）- 定义一个插件，用于扩展或修改通过 MBG 生成的代码
        • commentGenerator (0 个或 1 个） - 该标签用来配置如何生成注释信息
        • jdbcConnection ( 1 个）- 必须要有的，使用这个配置链接数据库
        • javaTypeResolver ( 0 个或 1 个） - 指定 JDBC 类型和 Java 类型如何转换
        • javaModelGenerator ( 1 个） - java模型创建器
        • sqlMapGenerator (0 个或 1 个）- 生成SQL map的XML文件生成器
        • javaClientGenerator (0 个或 1 个）- 生成Mapper接口
        • table ( 1个或多个） -选择一个table来生成相关文件，可以有一个或多个table



所有的实体类都继承一个baseEntity的好处.
baseEntity实现序列化接口.继承后所有所有实体都有了序列化的能力

怎么运行MGB
 从命令提示符 使用 XML 配置文件
 java -jar mybatis-generator-core-x.x.x.jar -configfile generatorConfig.xml
 使用场景：对逆向工程定制较少，项目工程结构比较复杂的情况

 作为 Maven Plugin  mvn mybatis-generator:generate |
                                                     使用场景：对逆向工程定制较多，项目工程结构比较单一的情况
 从另一个 Java 程序 使用 XML 配置文件                  |

***
1.Example 类 尽量不使用. 不适合SQL调优

*****在代码中写分页查询逻辑时，若 count 为 0 应直接返回，避免执行后面的分页语句。 这个可以项目中修改

*****为什么禁止使用存储过程?
因为存储过程难以调试和扩展,更没有移植性.

关联查询
在关系型数据库中，我们经常要处理一对一 、 一对多的关系 。 例如， 一辆汽车需要有一个引擎，这是一对一的
关系。 一辆汽车有 4 个或更多个轮子，这是一对多的关系 。关联元素就是专门用来处理关联关系的；

关联元素
 association 一对一关系
 collection 一对多关系
 discriminator 鉴别器映射

 关联方式
 嵌套结果:使用嵌套结果映射来处理重复的联合结果的子集  (管理软件居多,米泉就是)
 嵌套查询:通过执行另外一个 SQL 映射语句来返回预期的复杂类型




---------------------------------------------------------

一对一 嵌套结果
 association标签 嵌套结果方式 常用属性：
 property ：对应实体类中的属性名，必填项。
 javaType ： 属性对应的 Java 类型 。
 resultMap ： 可以直接使用现有的 resultMap ，而不需要在这里配置映射关系。
 columnPrefix ：查询列的前缀，配置前缀后，在子标签配置 result 的 column 时可以省略前缀
Tips:
1. resultMap可以通过使用extends实现继承关系，简化很多配置工作量；
2. 关联的表查询的类添加前缀是编程的好习惯；
3. 通过添加完整的命名空间，可以引用其他xml文件的resultMap；

---------------------------------------------------------

1.关联查询数据量小没问题,数据量大对系统性能有损耗
2.数据占内存,序列化和反序列速度慢. 网络传输占带宽


************Tips：“N+1 查询问题”**************
概括地讲,N+1 查询问题可以是这样引起的:
 你执行了一个单独的 SQL 语句来获取结果列表(就是“+1”)。
 对返回的每条记录,你执行了一个查询语句来为每个加载细节(就是“N”)。
这个问题会导致成百上千的 SQL 语句被执行。这通常不是期望的。
解决办法：使用“fetchType=lazy”并且全局setting进行改善：

<setting name="aggressiveLazyLoading" value="false"/>

<!--N+1 查询问题 true状态下,会懒加载失效(在sql - xml中的 fetchType="lazy")-->
---------------------------------------------------------

延迟加载.
mybatis-config.xml
lazyLoadingEnabled 延迟加载开关,默认不配置. 默认false 关闭状态
aggressiveLazyLoading

==============================================================

discriminator 鉴别器映射

在特定的情况下使用不同的pojo进行关联， 鉴别器元素就是被设计来处理这个情况的。鉴别器非常容易理解,因为它
的表现很像 Java 语言中的 switch 语句；
 discriminator 标签常用的两个属性如下：
 column：该属性用于设置要进行鉴别比较值的列 。
 javaType：该属性用于指定列的类型，保证使用相同的 Java 类型来比较值。
 discriminator 标签可以有1个或多个 case 标签， case 标签包含以下三个属性 。
 value ： 该值为 discriminator 指定 column 用来匹配的值 。
 resultMap ： 当column的值和value的值匹配时，可以配置使用resultMap指定的映射，resultMap优先级
高于 resultType 。
 resultType ： 当 column 的值和 value 的值匹配时，用于配置使用 resultType指定的映射。

t_user sex值关联到男女体检表

==============================================================

先决条件一：多对多需要一种中间表建立连接关系；
 先决条件二：多对多关系是由两个一对多关系组成的，一对
多可以也可以用两种方式实现；

==============================================================

MyBatis缓存
MyBatis 包含一个非常强大的查询缓存特性，使用缓存可以使应用更快地获取数据，避免频繁的数据库交互 ;
 一级缓存 （也叫应用缓存）：
 一级缓存默认会启用，想要关闭一级缓存可以在select标签上配置flushCache=“true” ; (默认开启,对性能有帮助)
 一级缓存存在于 SqlSession 的生命周期中，在同一个 SqlSession 中查询时， MyBatis 会把执行的方法和
参数通过算法生成缓存的键值，将键值和查询结果存入一个 Map对象中。如果同一个 SqlSession 中执行的
方法和参数完全一致，那么通过算法会生成相同的键值，当 Map 缓存对象中己经存在该键值时，则会返回
缓存中的对象;
 任何的 INSERT 、UPDATE 、 DELETE 操作都会清空一级缓存；


===========================================================
二级缓存 （也叫应用缓存）：
 二级缓存存在于 SqlSessionFactory 的生命周期中，可以理解为跨sqlSession；缓存是以namespace为单
位的，不同namespace下的操作互不影响。
 setting参数 cacheEnabled，这个参数是二级缓存的全局开关，默认值是 true，如果把这个参数设置为
false，即使有后面的二级缓存配置，也不会生效；
 要开启二级缓存,你需要在你的 SQL 映射文件中添加配置：
 字面上看就是这样。这个简单语句的效果如下：
• 映射语句文件中的所有 select 语句将会被缓存。
• 映射语句文件中的所有 insert,update 和 delete 语句会刷新缓存。
• 缓存会使用 Least Recently Used(LRU,最近最少使用的)算法来收回。
• 根据时间表(比如 no Flush Interval,没有刷新间隔), 缓存不会以任何时间顺序 来刷新。
• 缓存会存储列表集合或对象(无论查询方法返回什么)的 512个引用。
• 缓存会被视为是 read/write(可读/可写)的缓存；
<cache eviction="FIFO" flushInterval="60000" size="512" readOnly="true"/>
***
Tips: 使用二级缓存容易出现脏读，建议避免使用二级缓存，在业务层使用可控制的缓存代替更好；

>FIFO 先进先出

二级缓存不建议使用


##基础支撑层源码分析,数据源模块分析
1. 常见的数据源都是实现了javax.sql.DataSource接口;
2. MyBatis不但要能集成第三方数据源组件,自身也提供了数据源的实现;
3. 一般情况下,数据源的初始化过程参数较多.比较复杂;

##工厂模式UML类图
工厂模式（Factory Pattern）属于创建型模式，它提供了一种创建对象的最佳方式。定义一个创建对象的接
口，让其子类自己决定实例化哪一个工厂类，工厂模式使其创建过程延迟到子类进行

抽象工厂模式和工厂模式:
https://www.zhihu.com/question/20367734

###为什么要使用工厂模式:
 创建对象的方式：
 使用new关键字直接创建对象；
 通过反射机制创建对象；
 通过工厂类创建对象；

###缺点
 对象创建和对象使用使用的职责耦合在一起，违反单一
原则；
 当业务扩展时，必须修改代业务代码，违反了开闭原则；
###优点
 把对象的创建和使用的过程分开，对象创建和对象使用使用的职责解耦；
 如果创建对象的过程很复杂，创建过程统一到工厂里管理，既减少了重复代码，也方
便以后对创建过程的修改维护；
 当业务扩展时，只需要增加工厂子类，符合开闭原则；


MyBatis数据源模块:
PooledDataSource            UnpooledDataSource
使用数据库连接池                 不使用数据库连接池
invoke 
close方法.数据源回收入池        
连接池超时时间不要超过数据库超时时间    


###基础支撑层源码分析 缓存模块需求
 Mybatis缓存的实现是基于Map的，从缓存里面读写数据是缓存模块的核心基础功能；
 除核心功能之外，有很多额外的附加功能，如：防止缓存击穿，添加缓存清空策略（fifo、lru）、序列化功
能、日志能力、定时清空能力等；
 附加功能可以以任意的组合附加到核心基础功能之上；

怎么样优雅的为核心功能添加附加能力？
使用继承的办法扩展附加功能？
A：继承的方式是静态的，用户不能控制增加行为的方式和时机。
另外，新功能的存在多种组合，使用继承可能导致大量子类存在；

装饰器模式是一种用于代替继承的技术,无需通过继承增加子类就能扩展对象的新功能.
使用对象的关联关系代替继承关系,更加灵活,同时避免类型体系的快速膨胀.

装饰器模式（Decorator Pattern）允许向一个现有的对象添加新的功能，是一种用于代替继承的技术，无需通过继
承增加子类就能扩展对象的新功能。使用对象的关联关系代替继承关系，更加灵活，同时避免类型体系的快速膨胀；
 组件（Component）：组件接口定义了全部组件类
和装饰器实现的行为；
 组件实现类（ConcreteComponent）:实现
Component接口，组件实现类就是被装饰器装饰的
原始对象，新功能或者附加功能都是通过装饰器添加
到该类的对象上的；
 装饰器抽象类（Decorator）：实现Component接
口的抽象类，在其中封装了一个Component 对象，
也就是被装饰的对象；
 具体装饰器类（ConcreteDecorator）：该实现类
要向被装饰的对象添加某些功能；

 优点
 相对于继承，装饰器模式灵活性更强，扩展性更强；
 灵活性：装饰器模式将功能切分成一个个独立的装饰器，在运行期可以根据需要动态的添加功能，甚至对添加
的新功能进行自由的组合；
 扩展性：当有新功能要添加的时候，只需要添加新的装饰器实现类，然后通过组合方式添加这个新装饰器，无
需修改已有代码，符合开闭原则；

装饰器模式使用举例
 IO中输入流和输出流的设计
 Servlet API中提供了一个request对象的Decorator设计模式的默认实现类HttpServletRequestWrapper，
HttpServletRequestWrapper类增强了request对象的功能。
 Mybatis的缓存组件

                            Cache
               PerpetualCache     BlockingCache

装饰器模式使用举例
 Cache：Cache接口是缓存模块的核心接口，定义了缓存的基
本操作；
 PerpetualCache：在缓存模块中扮演ConcreteComponent
角色，使用HashMap来实现cache的相关操作；
 BlockingCache：阻塞版本的缓存装饰器，保证只有一个线程
到数据库去查找指定的key对应的数据

===================    2019年5月31日 00:44:32 mybatis高级5    ====================

mybatis核心流程三大阶段
初始化阶段           读取XML配置文件和注解中的配置信息，创建配置对象，并完成各个模块的初始化的工作；
代理阶段             封装iBatis的编程模型，使用mapper接口开发的初始化工作；
数据读写阶段          通过SqlSession完成SQL的解析，参数的映射、SQL的执行、结果的解析过程；

1. 嵌套结果, 目前项目中写的,一个sql语句left join 查出所有.

2. 嵌套查询, 延迟加载.
  <resultMap id="RoleandUsers" type="TRole" extends="BaseResultMap">
    <collection property="users" fetchType="lazy"  column="id" select="com.enjoylearning.mybatis.mapper.TUserMapper.selectUserByRoleId"></collection>
  </resultMap>
  
   <select id="selectRoleandUsers" resultMap="RoleandUsers">
    select 
    <include refid="Base_Column_List" />
    from t_role
  </select> 
  
锁粒度问题 粗粒度锁.
  
  
  ORM 框架
  从数据库加载数据  找到映射匹配规则  实例化目标对象  对象属性复制
  
  
 #### SQLxml中的 创建实体调用 ObjectFactory 反射机制.
  
 #### Reflector 对实体的属性进行封装 
  
###  反射的核心类
   ObjectFactory：MyBatis每次创建结果对象的新实例时，它都会使用对象工厂（ObjectFactory）去构建POJO；
   ReflectorFactory：创建Reflector的工厂类，Reflector是mybatis反射模块的基础，每个Reflector对象都对应
  一个类，在其中缓存了反射操作所需要的类元信息；
   ObjectWrapper：对对象的包装，抽象了对象的属性信息，他定义了一系列查询对象属性信息的方法，以及更
  新属性的方法；
   ObjectWrapperFactory： ObjectWrapper 的工厂类，用于创建ObjectWrapper ；
  