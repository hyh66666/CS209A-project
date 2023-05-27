# Java2 Final Project

### Data collection

​		we use a database (PostgreSQL) to store the data.We created four tables to store our data, which are answer, comment,Owner,question.

![截屏2023-05-27 15.35.48](./Java2 Final Project_image/截屏2023-05-27 15.35.48.png)

​		The question table stores 6 pages of 595 question data crawled down with url such as https://api.stackexchange.com/2.3/questions?order=desc&tagged=java&site=stackoverflow&pagesize=100&page= ' + I. We use the default sorting method to make the data more general and regular.The table has 14 columns, and its properties are shown below.

![截屏2023-05-27 15.37.04](./Java2 Final Project_image/截屏2023-05-27 15.37.04.png)

​		Then we crawl the comments and answers for each question through the url like below, and save the authors of the questions, answers, and comments.In this way, we can get all the information of a thread.

```java
String AnswerapiURL = "https://api.stackexchange.com/2.3/questions/" + question_id +
        "/answers?order=desc&sort=activity&site=stackoverflow"
String CommentapiURL = "https://api.stackexchange.com/2.3/questions/" + question_id +
                            "/comments?order=desc&sort=creation&site=stackoverflow"
  
```

![截屏2023-05-27 15.37.48](./Java2 Final Project_image/截屏2023-05-27 15.37.48.png)



​	When we analyze the data, we can get the analysis results through the SQL statement query.

### Project structure

​		The project uses the structure of the back-end springboot framework and the front-end vue framework.

```java
CS209A-project/
├── back-end1/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/                   # Java源代码
│   │   │   │   └── com.example.demo/   # 后端Java代码包
│   │   │   │       ├── controller/     # 控制器类
│   │   │   │       ├── enity/          # 数据模型类
│   │   │   │       ├── mapper/     		# 数据库访问类
│   │   │   │       ├── service/        # 服务类
│   │   │   │       └── Application.java  # 后端应用程序入口类
│   │   │   └── resources/              # 资源文件
│   │   │       ├── application.properties  # 应用程序配置文件
│   │   │       └── static/            # 静态资源文件（可选）
│   │   └── test/                      # 测试代码
└── front-end-yzd/
    ├── src/
    │   ├── assets/                     # 静态资源文件（图片、样式等）
    │   ├── components/                 # 组件
    │   ├── router/                     # 路由配置
    │   ├── App.vue                     # Vue根组件
    │   └── main.js                     # Vue应用程序入口
    ├── public/                         # 静态资源文件（HTML文件等）
    ├── tests/                          # 测试代码
    ├── babel.config.js                 # Babel配置文件
    ├── package.json                    # 项目依赖配置
    └── vue.config.js                   # Vue配置文件

```

![截屏2023-05-27 16.48.08](./Java2 Final Project_image/截屏2023-05-27 16.48.08.png)

​		In the OwnerController.java file, the code for crawling data is implemented, and the data is inserted by calling the packaged SQL statement in the attribute mapper.

### conclusion

#### Number of Answers	

1.

![截屏2023-05-27 15.48.04](./Java2 Final Project_image/截屏2023-05-27 15.48.04.png)

​	Most of the questions have been answered, and 36.8 % of the questions have not been answered.

2.

![截屏2023-05-27 16.51.05](./Java2 Final Project_image/截屏2023-05-27 16.51.05.png)

平均值：1.917

最大值：66

3.分布如下图所示：

![截屏2023-05-27 16.52.07](./Java2 Final Project_image/截屏2023-05-27 16.52.07.png)

The answer number is mainly concentrated in 0 ~ 3.

#### Accepted Answers

1.

![截屏2023-05-27 16.54.16](./Java2 Final Project_image/截屏2023-05-27 16.54.16.png)

Only about 20 % of the problems have been solved, and most of the problems have not been solved.

2.

![截屏2023-05-27 16.58.06](./Java2 Final Project_image/截屏2023-05-27 16.58.06.png)

Most of the problems are solved at the first time interval.

3.

![截屏2023-05-27 16.59.00](./Java2 Final Project_image/截屏2023-05-27 16.59.00.png)

Only less than 2 % of the unaccepted answers have higher number of likes than the accepted answers, indicating that the correct rate of the accepted answers is relatively high.

#### Tags 

1.

![截屏2023-05-27 17.01.32](./Java2 Final Project_image/截屏2023-05-27 17.01.32.png)

​	Springboot and java tag often appear together, indicating that the springboot framework has been widely used, we are in a heated discussion of it, string is the most common data type in java language, the number of maven tag is also more, indicating that the configuration environment is often faced with great problems.

2.

![截屏2023-05-27 17.04.31](./Java2 Final Project_image/截屏2023-05-27 17.04.31.png)

![截屏2023-05-27 17.04.46](./Java2 Final Project_image/截屏2023-05-27 17.04.46.png)

3.

![截屏2023-05-27 17.05.35](./Java2 Final Project_image/截屏2023-05-27 17.05.35.png)



## ![截屏2023-05-27 17.05.45](./Java2 Final Project_image/截屏2023-05-27 17.05.45.png)



#### Users

1.![截屏2023-05-27 17.06.53](./Java2 Final Project_image/截屏2023-05-27 17.06.53.png)

参与Tread的用户数量集中在2～9人中，大多数的问题并没有得到广泛的讨论

2.![截屏2023-05-27 17.08.01](./Java2 Final Project_image/截屏2023-05-27 17.08.01.png)

![截屏2023-05-27 17.08.11](./Java2 Final Project_image/截屏2023-05-27 17.08.11.png)

3.![截屏2023-05-27 17.08.25](./Java2 Final Project_image/截屏2023-05-27 17.08.25.png)