# SSM-Parent

#### 项目介绍
Spring + SpringMVC + MyBatis + Redis + MySQL

#### 软件架构
2018年12月15日
#####后端 ：Spring + SpringMVC + MyBatis + Redis + MySQL
#####前端 ：HTML JS LAYUI 
============================================

2018年12月21日
#####集成 Swagger UI 接口文档
============================================

2019年3月27日
##### http请求
##### mySQL 数据量大,索引.
##### java全局变量 使用


Ctrl + F12 查看类的所有方法

1.初始化(生成git隐藏文件)：$ git init
2.查找没有被跟踪的文件(红色的就是)：$ git status
3.把未跟踪文件添加到跟踪文件：$ git add .
4.项目托管到本地git文件：$ git commit -m "init my project"
!注意:第一次提交项目到仓库需要仓库的密码用户名；
先连接码云仓库(运行着两条命令)：
$ git config --global user.name "qiuqiu"
$ git config --global user.email "18132@qq.com"

5.连接远程仓库：$ git remote add origin https://gitee.com/qiuaiyun/vue110.git
6.提交项目：$ git push -u origin master


多线程  发令枪机制

使用的什么缓存?


怎么运行MGB
 从命令提示符 使用 XML 配置文件
 java -jar mybatis-generator-core-x.x.x.jar -configfile generatorConfig.xml
 使用场景：对逆向工程定制较少，项目工程结构比较复杂的情况
 
 
 作为 Maven Plugin
 mvn mybatis-generator:generate
 
 从另一个 Java 程序 使用 XML 配置文件
使用场景：对逆向工程定制较多，项目工程结构比较单一
的情况

MyBatis 缓存使用
一级默认开启, 二级尽量不用.

2019年5月24日
线上服务器出现崩盘现象.很大程度上是服务器硬件不足,RDS数据库连接数被限制.
表现为:


1.首先我们数据库是阿里云的RDS, 2CPU 4G配置 , 数据库版本: MYSQL 5.6 ,支持最大连接数量为1600个.但是在xml配置中,限制为了20个.
2.业务逻辑转账部分,出现了大量的update操作,都是单条update.消耗连接数量,增加搜索条件去优化
减少对库的更新
3.心跳AHA指令,改为全局变量方式.减少库的操作.
4.分账变成手动提现方式去做.

写代码,写的不仅是一种思维方式,更是对客户的负责.
每段代码都用心去写,竭力去优化.做到最好.
加油吧.Bob



2019年5月24日 23:40:23
支付宝 微信 二维码回调.
线上修改完代码后,当大批量二维码回调时还是出现了问题.
定位问题是在微信回调.


2019年6月17日 20:10:54
微信公众号 springboot版
nginx 配置 



【<br>】换行

【#】一级标题
【##】二级标题
【###】三级标题
【####】四级标题
【#####】五级标题
【######】级别标题
1. 链接: [Title](URL)
2. 加粗: **Bold**
3. 斜体字: *Italics*
4. 高亮: ==text==
5. 段落: 段落之间空一行
6. 换行符: 一行结束时输入两个空格
7. 列表: * 添加星号成为一个新的列表项。
8. 引用: > 引用内容
9. 内嵌代码: `alert('Hello World');`
10. 画水平线 (HR): --------
11. 复选框: -[ ]
12. 按钮样式: <kbd>?</kbd>
13. 星星: :sparkles: 
