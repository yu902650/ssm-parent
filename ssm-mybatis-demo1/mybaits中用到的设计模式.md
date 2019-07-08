
装饰器模式 
建造者模式
策略模式
模版模式

                                            MyBatis中的建造者模式
            
            BaseBuilder类图:
            
                    |----------------------------->  BaseBuilder<--------------------------------|
                    |              ^         ^           ^          ^         ^                  |     
                    |              |         |           |          |         |                  |
                    |      XMLConfigBuilder  |    SqlSourceBuilder  |  XMLScriptBuilder          |
                    |                        |                      |                            |   
                    |                        |                      |                            |   
           XMLMapperBuilder     XMLStatementBuilder  ParameterMappingTokenHandler  MapperBuilderAssistant  |
                    
         XMLConfigBuilder： 主要负责解析mybatis-config.xml；
         XMLMapperBuilder： 主要负责解析映射配置文件；
         XMLStatementBuilder： 主要负责解析映射配置文件中的SQL节点；

         Configuration 类图          
                         |-----mapperRegistry    注册接口的动态代理对象      ???mapper.java
                         |-----loadedResources   填充xml文件资源           mapper.xml 
                         |-----resultMaps        填充resultMaps          <resultMap>  ... </resultMap>
         Configuration   |-----
                         |-----sqlFragements     填充sql元素              <sql id=??>  ... </sql>
                         |-----mappedStatements  填充MappedStatment      <select/update/delete> ... </select/update/delete>
                         |-----keyGenerators     填充KeyGenerator        自动返回生成的id主键
                         
                         
                     mappedStatment图解
                                    
                                    |----id             
                                    |----sqlSource
                     mappedStatment |----parameterMap             
                                    |----resultMaps
                                    |----keyGenerator
                        
        为什么使用mapper接口就能操作数据库？       配置文件解读 +动态代理的增强
        
        binding模块分析
         MapperRegistry ： mapper接口和对应的代理对象工厂的注册中心；
         MapperProxyFactory：用于生成mapper接口动态代理的实例对象；
         MapperProxy：实现了InvocationHandler接口，它是增强mapper接口的实现；
         MapperMethod：封装了Mapper接口中对应方法的信息，以及对应的sql语句的信息；它是mapper接口与映射配置文件中sql语句的桥梁；
        
                    解读MapperMethod
                     SqlCommand ： 从configuration中获取方法的命名空间.方法名以及SQL语句的类型；
                     MethodSignature：封装mapper接口方法的相关信息（入参，返回类型）；
                     ParamNameResolver： 解析mapper接口方法中的入参；              
                    
                    
                                          MyBatis中的策略模式
        策略模式（Builder Pattern）策略模式定义了一系列的算法，并将每一个算法封装起来，而且使他们可以相互替换，让算法独立于使用它的客户而独立变化。
        
        策略模式的使用场景：
         针对同一类型问题的多种处理方式，仅仅是具体行为有差别时；
         出现同一抽象类有多个子类，而又需要使用 if-else 或者 switch-case 来选择具体子类时。
                                                 
                Context ---------> Strategy                                         
                                       ^          
                                       |          
                       ----------------------------------
                       |                                |
                ConcreateStrategy1              ConcreateStrategy2
             
         
          Context：算法调用者，使用setStrategy方法灵活的选择策略（strategy）；
          Strategy：算法的统一接口；
          ConcreteStrategy：算法的具体实现;
         
        SqlSession相关类UML
         SqlSession是MyBaits对外提供的最关键的核心接口，通过它可以执行数据库读写命令、获取映射器、管理事务等;
        
        
                    SqlSessionFactory                           SqlSession
                            |                                        |
                -----------------------------          ---------------------------- 
                |                           |          |                          |
         DefaultSqlSessionFactory        SqlSessionManager               DefaultSqlSession
 
         
                     SqlSessionManager
                      sqlSessionManager同时继承了SqlSession接口和SqlSessionFactroy接口，提供了创建SqlSession对象
                     和操纵数据库的能力；
                      SqlSessionManager有两种获取SqlSession的模式：
                      第一种模式和SqlSessionFactroy 相同，同一个线程每次访问数据库，每次都可以创建新的SqlSession对象；
                      第二种模式，同一个线程每次访问数据库，都是使用同一个SqlSession对象,通过localSqlSession实现;
                     
                    SqlSession查询接口嵌套关系
        
        
        Executor组件分析
         Executor是MyBaits核心接口之一，定义了数据库操作最基本的方法，SqlSession的功能都是基于它来实现的
        
                                    Executor
                                       || 
                            ----------------------------------
                            |                                |              
                       BaseExecutor                     CachingExecutor          
                            |                                |
     ---------------------------------------------------------------------------------                        
     |                      |                        |                               |
  BatchExecutor      SimpelExecutor             ReuseExecutor                   ClosedExecutor
                                           
      
      模板模式（Template Pattern）：一个抽象类公开定义了执行它的方法的方式/模板。
      它的子类可以按需要重写方法实现，但调用将以抽象类中定义的方式进行。
      定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。
      模板方法使得子类可以不改变一个算法的结构即可重定义该算法的某些特定实现；                       
               
               
      模板模式应用场景
      遇到由一系列步骤构成的过程需要执行，这个过程从高层次上看是相同的，但是有些步骤的实现可能不同，这个时候就需要考虑用模板模式了。比如：Executor查询操作流程:
      
      Executor使用了模版模式
      
      Executor的三个实现类解读
       SimpleExecutor：默认配置，使用statement对象访问数据库，每次访问都要创建新的statement对象；
       ReuseExecutor：使用预编译PrepareStatement对象访问数据库，访问时，会重用缓存中的statement对象；
       BatchExecutor：实现批量执行多条SQL语句的能力;
      
      Executor的三个重要小弟
       通过对SimpleExecutor doQuery()方法的解读发现，Executor是个指挥官，它在调度三个小弟工作：
       StatementHandler：它的作用是使用数据库的Statement或PrepareStatement执行操作，启承上启下作用；
       ParameterHandler：对预编译的SQL语句进行参数设置，SQL语句中的的占位符“？”都对应
      BoundSql.parameterMappings集合中的一个元素，在该对象中记录了对应的参数名称以及该参数的相关属性
       ResultSetHandler：对数据库返回的结果集（ResultSet）进行封装，返回用户指定的实体类型；
      
      
      StatementHandler分析
       StatementHandler完成Mybatis最核心的工作，也是Executor实现的基础；功能包括：创建statement对象，
      为sql语句绑定参数，执行增删改查等SQL语句、将结果映射集进行转化；
       BaseStatementHandler：所有子类的抽象父类，定义了初始化statement的操作顺序，由子类实现具体的实例化不同的statement(模板模式)；
       RoutingStatementHandler：Excutor组件真正实例化的子类，使用静态代理模式，根据上下文决定创建哪个具体实体类；
       SimpleStatmentHandler ：使用statement对象访问数据库，无须参数化；
       PreparedStatmentHandler ：使用预编译PrepareStatement对象访问数据库；
       CallableStatmentHandler ：调用存储过程；
                             
      ResultSetHandler分析
      ResultSetHandler将从数据库查询得到的结果按照映射配置文件的映射规则，映射成相应的结果集对象；                                                    
      
      过程:
      查询数据库返回结果集
      找到映射匹配规则
      反射实例化目标对象
      根据规则填充属性值       
      
      Reflector 反射模块. 实体没有get/set方法时,为实体生成.     
      
      ask : 为什么myBatis 使用 mapper接口 就可以操作数据库?
      answer : 配置文件解读 + 动态代理的增强
            
                             
                             
                             
                             
                             
                             
                             
                             
                             
                             
                             
                             
                             
                             
                                                              