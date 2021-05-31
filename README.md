# SoftwareEngineeringSubject
Backend для проекта  
Ссылка: https://converter-se-course.herokuapp.com/  
Endpoints:   
  1)/getAllCurrencies GET  
  2)/register POST Body: login, password  
  3)/login POST Body: login, password  
  4)/converter/exchange POST Body: currencyFrom, currencyTo, userId, input  
  5)/converter/search POST Body: date, currency1, currency2, userId  
Сборка проекта: mvn package  
Сборка документации: mvn site  
Зависимости:  
            <artifactId>spring-boot-starter-web</artifactId>  
            <artifactId>spring-boot-devtools</artifactId>  
            <artifactId>postgresql</artifactId>  
            <artifactId>lombok</artifactId>  
            <artifactId>spring-boot-starter-test</artifactId>  
            <artifactId>spring-security-test</artifactId>  
            <artifactId>spring-data-jpa</artifactId> <version>2.2.6.RELEASE</version>  
            <artifactId>spring-boot-starter-data-jpa</artifactId>  
            <artifactId>jaxb-api</artifactId> <version>2.3.0</version>  
            <artifactId>json</artifactId> <version>20090211</version>   
            <artifactId>spring-security-core</artifactId> <version>5.3.3.RELEASE</version>  
