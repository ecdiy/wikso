工具：mavan

打包命令: mvn clean package  

第三方库:[http://www.jointjs.com/demos/run.html?unit=fsa]

数据库 :mysql 用户名:root 密码：root2011 数据库名:dbitest  数据库表spring data JPA自动创建

tomcat 7.0+

jdk 1.7

开发工具:eclipse+maven

效果URL[http://localhost:88/test/shw.jsp]

加载测试数据与测试:mvn clean test

运行: mvn clean tomcat7:run

如果 mvn clean tomcat7:run 不能运行，  C:\Users\Administrator\.m2\settings.xml 添加如下内容。
..............

 <pluginGroups>      
 	  <pluginGroup>com.jayway.maven.plugins.android.generation2</pluginGroup>
  </pluginGroups>
..........

</settings>


[http://localhost:88/test/index.jsp]

N个点平均分配的计算公式: 坐标 X=r*(1+cos(A) ; Y= r*(1+sin(A)) ;A=360*m/N ;(m=0,n-1);