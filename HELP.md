# class-assistant数据库设计

### 一、用户表（user）

| 序号 |    字段     |   类型   | 长度 |     注释     |
| ---- | ----------- | -------- | ---- | ---- |
|  1   |     id      |   int    |  11  |   主键自增   |
|  2   |  username   | varchar  |  20  | 登陆时用户名 |
|  3   |   passwod   | varchar  |  50  |     密码     |
|  4   | create_time | datetime |  0   | 用户创建时间 |

### 二、大学专业表（special）

| 序号 |  字段  |  类型   | 长度 |   注释   |
| ---- | ----------- | -------- | ---- | ---- |
|  1   |   id   |   int   |  11  | 主键自增 |
|  2   |  name  | varchar |  20  | 专业名称 |
|  3   | parent |   int   |  11  | 父专业id |

### 三、个人中心菜单表（menu）

| 序号 |    字段     |   类型   | 长度 |       注释       |
| ---- | ----------- | -------- | ---- | ---- |
|  1   |     id      |   int    |  11  |     主键自增     |
|  2   |    name     | varchar  |  10  |     菜单名称     |
|  3   |     url     | varchar  |  10  | 菜单对应资源路径 |
|  4   |   parent    |   int    |  11  |     父菜单id     |
|  5   |  icon_type  | varchar  |  10  |     图标图形     |
|  6   | is_disable  | tinyint  |  4   |   是否可以使用   |
|  7   | create_time | datetime |  0   |     创建时间     |

### 四、图标表（icon）

| 序号 | 字段 |  类型   | 长度 |   注释   |
| ---- | ----------- | -------- | ---- | ---- |
|  1   |  id  |   int   |  11  | 主键自增 |
|  2   | name | varchar |  10  | 图标名称 |

### 五、相册目录表（type_tree）

| 序号 | 字段        | 类型     | 长度 | 注释       |
| ---- | ----------- | -------- | ---- | ---------- |
| 1    | id          | int      | 11   | 主键自增   |
| 2    | user_id     | int      | 11   | 所属用户id |
| 3    | name        | varchar  | 15   | 目录名称   |
| 4    | parent      | int      | 11   | 父目录id   |
| 5    | create_time | datetime | 0    | 创建时间   |

### 六、文章表（article）

| 序号 | 字段            | 类型     | 长度 | 注释           |
| ---- | --------------- | -------- | ---- | -------------- |
| 1    | id              | int      | 11   | 主键自增       |
| 2    | user_id         | int      | 11   | 文章所属用户id |
| 3    | title           | varchar  | 30   | 文章标题       |
| 4    | description     | varchar  | 50   | 文章描述       |
| 5    | article_content | longtext | 0    | 文章内容       |
| 6    | create_time     | datetime | 0    | 创建时间       |
| 7    | stars           | int      | 11   | 获赞数         |
| 8    | reads           | int      | 11   | 阅读数         |
| 9    | comments        | int      | 11   | 评论数         |

### 七、点赞表（stars）

| 序号 | 字段       | 类型 | 长度 | 注释   |
| ---- | ---------- | ---- | ---- | ------ |
| 1    | user_id    | int  | 11   | 用户id |
| 2    | article_id | int  | 11   | 文章id |

### 八、评论表（comment）

| 序号 | 字段       | 类型    | 长度 | 注释       |
| ---- | ---------- | ------- | ---- | ---------- |
| 1    | id         | int     | 11   | 主键自增   |
| 2    | user_id    | int     | 11   | 用户id     |
| 3    | article_id | int     | 11   | 文章id     |
| 4    | comment    | varchar | 255  | 评论内容   |
| 5    | parent     | int     | 11   | 父级评论id |

### 九、收藏表（collect）

| 序号 | 字段       | 类型 | 长度 | 注释   |
| ---- | ---------- | ---- | ---- | ------ |
| 1    | user_id    | int  | 11   | 用户id |
| 2    | article_id | int  | 11   | 文章id |

### 十、浏览足迹表（trail）

| 序号 | 字段       | 类型 | 长度 | 注释   |
| ---- | ---------- | ---- | ---- | ------ |
| 1    | user_id    | int  | 11   | 用户id |
| 2    | article_id | int  | 11   | 文章id |

### 十一、相册表（photos）

| 序号 | 字段    | 类型    | 长度 | 注释     |
| ---- | ------- | ------- | ---- | -------- |
| 1    | id      | int     | 11   | 主键自增 |
| 2    | type_id | int     | 11   | 目录id   |
| 3    | url     | varcahr | 255  | 图片路径 |

