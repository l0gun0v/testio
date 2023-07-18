You can run server in docker container. It consists of db and application.

```shell
    sudo docker compose up --build
```

You will have server launched on port 80


To build and start server locally you need db:

```shell
   sudo docker compose create db
   sudo docker compose start db
```

Or your local postgres, you need to paste your database user and password on [application.properties](src/resources/application.properties)

And start server:

```shell
  ./gradle build 
  ./gradlew bootRun
```






