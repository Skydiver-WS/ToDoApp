FROM openjdk:17-oracle
LABEL authors="Aleksandr Isaev"


# Установите рабочую директорию внутри контейнера
WORKDIR /app

# Скопируйте JAR-файл вашего приложения в контейнер
COPY todoapp-0.0.1-SNAPSHOT.jar todo.jar
COPY application.yml applacation.yml
# Команда ENTRYPOINT определяет исполняемую команду при запуске контейнера
ENTRYPOINT ["java","-jar","todo.jar", "applacation.yml"]