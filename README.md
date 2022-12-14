# TrudExpert

Каркасное приложение по учету слушателей автономной некоммерческой образовательной организации.
Приложение находится на стадии разработки.

## Общая информация

### Основные сервисы

* Слушатели
* Организации и Контрагенты
* Курсы
* Группы
* Документы

## Техническая информация

### Версии

* Java 17.0.4
* Spring-Boot 2.7.5
* docker-compose 3.7
* Apache Maven 3.8.6

### Сборка проекта

        mvn clean package

После сборки в папке /target появится файл с расширением *.jar, котороый можно запустить с помощью команды:

        java -jar /path/to/*.jar

### Sonar

Для проверки тестового покрытия кода необходимо провести сборку проекта:

        mvn clean install

А затем вызвать sonar с помощью:

        mvn sonar:sonar

Также можно использовать комбинированную команду:

        mvn clean install sonar:sonar

### Docker-контейнер:

Сборка контейнера:

        docker-compose -p trud-expert-service  up -d

Пересобрать контенер при изменении *.jar файла:

        docker-compose build app
        docker-compose -p trud-expert-service  up -d

Сам *.jar файл необходимо скопировать в папку /docker из папки /target при сборке проекта с помоцью:

        mvn clean package
