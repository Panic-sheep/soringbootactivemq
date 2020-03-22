# z-SpringBoot-ActiveMQ

#### 介绍
SpringBoot 整合 ActiveMQ

#### 项目生成
```$xslt
$ /e/CommonUtils/spring-2.1.11.RELEASE/bin/spring init
Using service at https://start.spring.io
Content saved to 'demo.zip'
```

#### 安装 ActiveMQ
> 环境：Windows server 2003 x64

> 版本：C:\apache-activemq-5.15.11

> 说明：默认使用脚本初始化安装服务：
```$xslt
C:\apache-activemq-5.15.11\bin\win64>InstallService.bat
wrapper  | ActiveMQ installed.
```

#### ActiveMQ 默认端口
> 后台管理：http://192.168.80.133:8161/
```$xslt
用户：admin
密码：admin
```

> 服务端口：61616

#### ActiveMQ 集成

1. 引入相关maven依赖
```$xslt
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-activemq</artifactId>
</dependency>
```

2. 创建生产者
3. 创建消费者
4. 测试
```$xslt
地址：http://localhost:60214/queue?msg=...
地址：http://localhost:60214/topic?msg=...
```
