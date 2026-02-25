# Events4all

This is a simple example of some docker basics, so you can start working with it.

```EventsApplication``` is a simple event management app with three different endpoints:

<details>
  <summary>1. <code>POST /api/v1/event-management/create</code></summary>
Endpoint used to create a new Event.

Request example:
``` bash
curl -X POST http://localhost:8080/api/v1/event-management/create \
-H "Content-Type: application/json" \
-d '{
  "eventName": "New Docker for Dummies Presentation!!",
  "start": "2026-03-15T09:00:00",
  "end": "2026-03-15T20:00:00",
  "responsible": "PCruz",
  "local": "@company"
}'
```

Response Example:
```json
{
"uuid": "44653082-d139-49e5-8028-2b4e80fd587f",
"eventName": "New Docker for Dummies Presentation!!",
"start": "2026-03-15T09:00:00",
"end": "2026-03-15T20:00:00",
"responsible": "PCruz",
"local": "@company"
}
```
</details>
<details>
  <summary>2. <code>GET&nbsp; /api/v1/event-management/list-all</code></summary>
Endpoint used to fetch all events.

Request example:
``` bash
curl http://localhost:8080/api/v1/event-management/list-all'
```

Response Example:
  ```json
  {
    "events" : [
    {
      "uuid" : "44653082-d139-49e5-8028-2b4e80fd587f",
      "eventName" : "Docker for Dummies Presentation, Part 1",
      "start" : "2026-03-15T10:00:00",
      "end" : "2026-03-15T11:00:00",
      "responsible" : "PCruz",
      "local" : "@company"
    }, {
      "uuid" : "a13f2064-3ea3-4d2d-9aa6-b08e4e0a6e47",
      "eventName" : "Docker for Dummies Presentation, Part 2",
      "start" : "2026-03-15T14:00:00",
      "end" : "2026-03-15T15:00:00",
      "responsible" : "@any_other_dev",
      "local" : "@remote_call"
    }]
}
  ```
</details>
<details>
  <summary>3. <code>GET&nbsp; /api/v1/event-management/list-by-responsible/{responsible}</code></summary>
Endpoint used to fetch all events from one given person.

Request example:
``` bash
curl http://localhost:8080/api/v1/event-management/list-by-responsible/PCruz'
```

Response Example:
  ```json
  {
    "events" : [
    {
      "uuid" : "44653082-d139-49e5-8028-2b4e80fd587f",
      "eventName" : "Docker for Dummies Presentation, Part 1",
      "start" : "2026-03-15T10:00:00",
      "end" : "2026-03-15T11:00:00",
      "responsible" : "Pedro Cruz",
      "local" : "@company"
    }]
}
  ```
</details>

## Running it as a Single Component

This version of the application runs without any database, thus being a single component app.

**`DockerfileApplication`**
```dockerfile
FROM amazoncorretto:17-alpine

WORKDIR /app
COPY build/libs/events-0.0.1-SNAPSHOT.jar application.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/application.jar", "--spring.profiles.active=memory"]
```

Setting up the image _(based on `DockerfileSingleApp`)_:
```bash
docker build -t sampleapp:latest -f DockerfileApplication .
```

Running the created image:
```bash
docker run -p 8080:8080 sampleapp:latest
```

## Setting up a Database Container

Temos o ficheiro da base de dados preparado já


**`DockerfileDatabase`**
```Dockerfile
FROM gvenzl/oracle-free:23

ENV ORACLE_PASSWORD=mypassword
ENV APP_USER=myuser
ENV APP_USER_PASSWORD=mypassword

EXPOSE 1521
```

Correr 
``` bash
docker build -t oracle-db:23 -f DockerfileDatabase .
```


## Small changes to Connect them All

???adjksdsajnkadsnjkadsjn???


## One File to Rule them All