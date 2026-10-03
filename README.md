# 味之轻舟 - 后端服务（graduation-backend）

![项目](https://img.shields.io/badge/项目-味之轻舟-E95F3C?style=flat-square) ![角色](https://img.shields.io/badge/角色-后端服务-6DB33F?style=flat-square) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-2.7.3-6DB33F?logo=springboot&logoColor=white&style=flat-square) ![MySQL](https://img.shields.io/badge/MySQL-8.x-4479A1?logo=mysql&logoColor=white&style=flat-square) ![Redis](https://img.shields.io/badge/Redis-缓存-DC382D?logo=redis&logoColor=white&style=flat-square) ![MyBatis](https://img.shields.io/badge/MyBatis-2.2.0-F39C12?style=flat-square)

> 🚀 **「味之轻舟」** —— 一个完整的餐饮外卖点餐系统毕业设计项目，由 **后端服务 + 管理端 + 微信小程序** 三端组成。

## 🌐 项目生态（相关仓库）

本仓库是「味之轻舟」系统的**后端服务**。完整系统由以下三个仓库组成，点击链接可跳转：

| 模块 | 说明 | 技术栈 | 仓库 |
| :---: | --- | --- | :---: |
| ⚙️ **后端服务** | 系统核心（RESTful API + WebSocket） | Spring Boot / MyBatis / MySQL / Redis / JWT | [`graduation-backend`](https://github.com/buqingli666/graduation-backend) ⬅️ **当前** |
| 📊 管理端 | 商家后台（订单 / 菜品 / 统计） | Vue2 / TypeScript / Element UI / ECharts | [`graduation-vue`](https://github.com/buqingli666/graduation-vue) |
| 🛒 用户端 | 小程序点餐端 | uni-app / 微信小程序 / uni-ui | [`graduation-weixin`](https://github.com/buqingli666/graduation-weixin) |

---

## 一、项目简介

本项目是一个面向餐饮外卖场景的后端服务，采用 **多模块 Maven 聚合工程** 组织代码，提供：

- **管理端能力**：员工管理、菜品/套餐/分类管理、订单管理、数据统计与报表导出、工作台概览、营业状态切换。
- **用户端能力**：微信小程序登录、浏览菜品/套餐、购物车、收货地址管理、下单、微信支付、历史订单查询。
- **通用能力**：JWT 双端鉴权、全局异常处理、阿里云 OSS 文件上传、百度地图配送范围校验、WebSocket 来单/催单提醒、订单状态定时处理、运营数据 Excel 报表导出。

## 二、技术栈

| 分类 | 技术选型 |
| --- | --- |
| 核心框架 | Spring Boot 2.7.3、Spring MVC |
| 持久层 | MyBatis、PageHelper 分页、MySQL |
| 缓存 | Redis（Spring Cache + 自定义缓存） |
| 数据源 | Druid 连接池 |
| 鉴权 | JWT（jjwt）+ 拦截器，区分 admin / user 双端令牌 |
| 接口文档 | Knife4j（Swagger 增强） |
| 实时通信 | WebSocket（`javax.websocket`） |
| 文件存储 | 阿里云 OSS SDK |
| 定时任务 | Spring `@Scheduled`（订单状态处理） |
| 第三方 | 微信小程序登录、微信支付 V3、百度地图 AK、Apache POI（报表导出） |
| 工具 | Lombok、Fastjson、commons-lang、AspectJ（AOP 自动填充） |
| 构建 | Maven 多模块聚合工程 |

## 三、项目结构

```
graduation-backend/                 # 父工程（packaging = pom）
├── pom.xml                         # 统一依赖版本管理
├── database/
│   └── graduation.sql              # 数据库初始化脚本（建库 + 建表 + 初始数据）
├── oit-common/                     # 公共模块：常量、枚举、异常、工具类、结果封装、配置属性
├── oit-pojo/                       # 实体与数据传输对象：entity / dto / vo
└── oit-server/                     # 主服务模块（可启动）
    └── src/main/
        ├── java/com/oit/
        │   ├── OitApplication.java          # 启动类
        │   ├── config/                      # Druid / Redis / OSS / WebMvc / WebSocket 配置
        │   ├── controller/
        │   │   ├── admin/                   # 管理端：employee/category/dish/setmeal/order/report/workspace/shop/common
        │   │   ├── user/                    # 用户端：user/addressBook/shoppingCart/dish/setmeal/category/order/shop
        │   │   └── notify/                  # 微信支付回调 PayNotifyController
        │   ├── service/ + service/impl/     # 业务层
        │   ├── mapper/                      # MyBatis Mapper 接口
        │   ├── interceptor/                 # JWT 双端鉴权拦截器
        │   ├── aspect/                      # AOP（公共字段自动填充）
        │   ├── annotation/                  # 自定义注解 @AutoFill
        │   ├── handler/                     # 全局异常处理器
        │   ├── task/                        # 定时任务（订单状态处理）
        │   ├── websocket/                   # WebSocket 服务端
        │   └── ...
        └── resources/
            ├── application.yml              # 主配置（端口 8080、profile=dev）
            ├── application-dev.yml          # 开发环境配置（数据库/OSS/微信/地图等）
            ├── mapper/*.xml                 # MyBatis 映射文件
            └── template/运营数据报表模板.xlsx  # 报表导出模板
```

## 四、核心业务模块

### 数据表（共 11 张）

`address_book`（地址簿）、`category`（分类）、`dish`（菜品）、`dish_flavor`（菜品口味）、`employee`（员工）、`order_detail`（订单明细）、`orders`（订单）、`setmeal`（套餐）、`setmeal_dish`（套餐菜品关系）、`shopping_cart`（购物车）、`user`（用户）。

### 接口前缀

| 端 | 前缀示例 | 说明 |
| --- | --- | --- |
| 管理端 | `/admin/**` | 如 `/admin/employee`、`/admin/dish`、`/admin/order`、`/admin/report`、`/admin/workspace` |
| 用户端 | `/user/**` | 如 `/user/user`、`/user/shoppingCart`、`/user/order`、`/user/addressBook` |
| 支付回调 | `/notify/**` | 微信支付成功异步通知 |

### 核心特性

- **双端鉴权**：admin 与 user 分别使用独立密钥与 Token 名（`token` / `authentication`）。
- **自动填充**：通过 `@AutoFill` 注解 + AOP 自动填充 `create_time` / `update_time` 等公共字段。
- **WebSocket**：`/ws/{sid}` 接入，用于「来单提醒」「客户催单」实时推送至管理端。
- **定时任务**：自动处理超时未支付（取消）与派送中（完成）订单。
- **缓存**：菜品数据使用 Spring Cache（Redis）缓存，提升查询性能。
- **报表**：Apache POI 读取 Excel 模板，导出营业额 / 用户 / 订单等运营数据。
- **配送范围**：调用百度地图 AK 校验用户收货地址是否超出配送范围。

## 五、快速开始

### 环境要求

- JDK 1.8
- Maven 3.6+
- MySQL 8.x
- Redis

### 1. 初始化数据库

```bash
mysql -uroot -p < database/graduation.sql
```

脚本会自动创建 `graduation_project` 数据库、11 张业务表及初始数据。

### 2. 修改配置

编辑 `oit-server/src/main/resources/application-dev.yml`，按需替换以下配置：

- MySQL 连接（`host` / `port` / `username` / `password`）
- Redis 连接（`host` / `port`）
- 阿里云 OSS（`access-key-id` / `access-key-secret` / `bucket-name` / `endpoint`）
- 微信小程序（`appid` / `secret`）与微信支付（`mchid` / `apiV3Key` / 证书路径 / `notifyUrl` 等）
- 百度地图 `ak`
- 门店地址 `shop.address`

> ⚠️ 仓库中的 `application-dev.yml` 含有示例密钥，**请务必替换为您自己的密钥并切勿提交到公开仓库**。

### 3. 构建与启动

```bash
# 在根目录构建全部模块
mvn clean install -DskipTests

# 启动主服务
cd oit-server
mvn spring-boot:run
```

默认端口 **8080**，启动成功后日志输出 `Server Started`。

### 4. 接口文档

启动后访问 Knife4j 文档（如已启用）：

```
http://localhost:8080/doc.html
```

## 六、默认账号

通过初始化脚本注入的管理员账号（如脚本中存在）：

- 账号：`admin`
- 密码：`123456`

> 管理端登录接口：`POST /admin/employee/login`

## 七、相关仓库

本项目为「味之轻舟」系统的后端，配套前端仓库：

- 管理端（Vue）：[graduation-vue](https://github.com/buqingli666/graduation-vue)
- 用户端（微信小程序）：[graduation-weixin](https://github.com/buqingli666/graduation-weixin)

## 八、目录约定与编码规范

- 包名统一为 `com.oit.*`
- Controller 按端拆分（`admin` / `user` / `notify`）
- 公共字段统一使用 `@AutoFill` 注解，由 `AutoFillAspect` 切面处理
- 业务异常统一继承 `BaseException`，由 `GlobalExceptionHandler` 捕获并返回统一 `Result` 结构
