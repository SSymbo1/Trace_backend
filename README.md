<div align="center">
	<h1>Trace</h1>
	<img src="./recover/vite.svg" width="150" align="center" />
	<br/> <br/>
	<strong>重要产品追溯管理系统后端</strong>
</div>

### 介绍
哈尔滨学院21级毕业设计，基于SpringBoot2的溯源平台项目

### 部署前的准备

请确认你的 JDK，Maven，MySQL 版本与本项目一致
- JDK 1.8
- Maven 3.8.1
- MySQL 8.0.32

在使用 IntelliJ IDEA 克隆本项目时，Maven将自动构建本项目，但可能需要更改 项目结构 中的 SDK 为 1.8 来避免启动项目时报错的问题

#### 1.数据库

在项目根目录下的 recover 文件夹中的 database 里可以找到本项目的数据库sql文件，您需要在MySQL的命令行窗口内执行初始化

```bash
mysql -u 你的数据库用户名 -p 你的数据库密码 < ./recover/database/trace.sql
```

当然，如果您有数据库可视化管理软件( 如 Navicat ),在选中的数据库右键选择 运行SQL文件 选择 trace.sql 点击执行则可初始化数据库

注意：不要忘记在 application-dev.yml 或 application-prod.yml 中更改数据库的配置（链接，账号，密码）

#### 2.静态资源

在项目根目录下的 recover 文件夹中的 resources 文件夹中可以找到本项目所需要的静态资源文件夹 trace ，项目运行时将根据部署环境初始化静态资源位置

- windows: D:/trace/
- linux: /media/vdc/trace/

将 trace 文件夹下的内容粘贴至初始化的位置中，确保静态资源正确的加载

### 部署项目
当前项目还在开发中，预计以后将支持 shell脚本启用停用项目，目前在 IntelliJ IDEA 中运行 TraceBackendApplication类即可启动

### 接口文档
本项目使用 Knife4j 自动生成接口文档，当启动项目时，控制台将显示接口文档地址

### 服务监控

本项目使用 SpringBootAdmin 监控服务，当启动项目时，控制台将显示 SpringBootAdmin 的地址

### 项目结构

#### trace_backend_job为溯源业务模块

src下

- config ( 配置类 )
- controller ( 控制器 )
- cron ( 定时任务 )
- entity ( 实体类 )
- global ( 自定义框架相关 )
- mapper ( 数据库操作接口 )
- report ( 报表业务相关 )
- service ( 业务逻辑 )
- strategy ( 统计策略 )
- utils ( 工具类 )

resources下

- application.yml ( 配置文件 )
- application-dev.yml ( 开发环境配置文件 )
- application-prod.yml ( 生产环境配置文件 )
- banner.txt ( 项目启动时控制台打印的图标 )
- logback-spring.xml ( logback日志配置文件 )

#### trace_backend_monitor为溯源服务监控模块

src下

- global ( 自定义框架相关 )

resources下

- application.yml ( 配置文件 )
- application-dev.yml ( 开发环境配置文件 )
- application-prod.yml ( 生产环境配置文件 )
- logback-spring.xml ( logback日志配置文件 )

