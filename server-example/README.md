# SpringCloudServer 
![](https://img.shields.io/badge/SpringBoot-2.1.5.RELEASE-brightgreen.svg)
![](https://img.shields.io/badge/SpringCloud-Greenwich.SR1-blue.svg)
![](https://img.shields.io/badge/jdk-1.8.0_151-9cf.svg)
![](https://img.shields.io/badge/maven-3.5.0-ff69b4.svg)

- 该项目功能
  - 提供配置中心使用说明
  - 验证配置中心是否正常
  - 提供接口给 Ribbon 服务进行调用
  - 

--- 
# 微服务使用 SpringCloudConfig 获取配置步骤
1.引入jar包
```xml
     <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>
```
- 版本要和整个 SpringCloud 的项目一致

---

2.把原来的 application.properties 文件改成 bootstrap.properties 或者 bootstrap.yml
   - 数据库或者第三方会在启动的时候，先去加载 application 的配置信息，你如果把数据库或其他启动需要信息存储在配置中心，就会出现启动失败的情况，因为这时候项目未启动。
   - 如果我们有 bootstrap.yml 文件，则项目启动的时候去先去配置中心获取配置再继续启动项目

---

3.添加配置信息
```yaml
spring:
  application:
    name: config-client
  cloud:
    config:
      discovery:
        enabled: true
        service-id: CONFIG   # 注册中心里面配置中心的名字
      profile: dev  #配置的环境，你可以起多个配置环境，然后用不同的配置文件
server:
  port: 8000
eureka:
  client:
    service-url:
      defaultZone: http://127.0.0.1:8761/eureka/,http://127.0.0.1:8762/eureka/,http://127.0.0.1:8763/eureka/  #向注册中心注册
```

---

4.上传配置文件到git参考
- 例如我配置文件仓库地址为 https://github.com/MrXuan3168/Spring-Cloud-Config-Repo.git
- 里面 config-client-dev.yml 的内容为
```yaml
configuration:
  environment: dev
  
test:
  variable: "测试下配置中心是否获取到配置"
```
- 启动配置中心，打开 http://localhost:8090/config-client-dev.yml 可以获取到配置文件，说明配置中心正常

---

5.启动项目
- 访问url http://localhost:8000/Config (该接口是在Controller 里面自己定义的)
- 获取到数据说明项目正常接入了配置中心。
