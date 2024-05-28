### application.yml
```
server:
  port: [PORT]
  username: [SERVER_ADMIN_USERNAME]
  password: [SERVER_ADMIN_PASSWORD]

spring:
  application:
    name: eureka-server

eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
```
