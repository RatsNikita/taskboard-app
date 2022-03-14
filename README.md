# TaskBoard

## Краткое описание
Приложение  представляет из себя сервис для ведения скоупа задач. Реализовано взаимодействие на основе REST запросов, а так же через пользовательский интерфейс. Для работы с приложением требуется авторизация. Приложение позволяет:
+ Создавать задачи с указанием их приоритета и сложности.
+ Удалять ранее созданные собой задачи
+ Принимать задачи созданные другими пользователями
+ Отказываться от выполнения ранее принятых задач
+ Подписываться на email/telegram уведомления в настройках

![task](https://sun9-11.userapi.com/impg/8e6GVdpliZVMfJXnG4HmcrCKK4tkILE89BWaaA/H1-mgqdeStU.jpg?size=291x312&quality=95&sign=10d97e83642bab9d947b538d243d7a50&type=album)
___
## Установка 
### Database connection
Для подключения к базе данных необходимо в файле [application.yml для taskboard-service](https://github.com/NickOne163/TaskBoard/blob/master/taskboard-service/src/main/resources/application.yml) указать имя и пароль для доступа к базе, а также url и driver class name:
```java
datasource:
    url:                      
    username:                 
    password:                 
    driver-class-name: 
```

### Notification service
Для работы сервиса уведомлений требуется указать в файле [application.yml для taskboard-notification-service](https://github.com/NickOne163/TaskBoard/blob/master/taskboard-notification-service/src/main/resources/application.yml) логин и пароль для почты:
```java
mail:
    username:     
    password:     
```
 а также имя и токен для телеграмм бота:
 ```java
 bot:
    name:         
    token:        

```

