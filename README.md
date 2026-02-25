# Docker for Dummies

This is a simple and practical example on docker basics.

Be mindful that none of the details explored here are production ready, this project is simply
to introduce docker as a containerization tool.

## Events4All

```EventsApplication``` is a simple event management app with three different endpoints:

<details>
  <summary>
    <b>1. Create a new Event</b><br>
    &nbsp;&nbsp;&nbsp;<code>POST /api/v1/event-management/create</code><br>&nbsp;
  </summary>

Request Example:
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
  <summary>
    <b>2. Fetch All Events</b><br>
    &nbsp;&nbsp;&nbsp;<code>GET&nbsp; /api/v1/event-management/list-all</code><br>&nbsp;
  </summary>

Request:
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
  <summary>
    <b>3. Fetch Events From a Responsible</b><br>
    &nbsp;&nbsp;&nbsp;<code>GET&nbsp; /api/v1/event-management/list-by-responsible/{responsible}</code><br>&nbsp;
  </summary>

Request Example:
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

To run the application without the use of any external database
the profile must be set to `memory`.  
The following file is used to create an image of this application
that runs as a _Single Component Application_.

**`DockerfileSingleApplication`**
```dockerfile
FROM amazoncorretto:17-alpine

WORKDIR /app
COPY build/libs/events-0.0.1-SNAPSHOT.jar application.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/application.jar", "--spring.profiles.active=memory"]
```

Setting up the image _(based on `DockerfileSingleApplication`)_:
```bash
docker build -t singleapp:latest -f DockerfileSingleApplication .
```

Running the created image:
```bash
docker run -p 8080:8080 singleapp:latest
```

## Small changes to Connect them All

Let's now make the application connect to a containerized database.  
We need to change two things:
1. Set up a Database Container
2. Change and Run the Application Image  

### Setting up a Database Container

For our example we have the following database setup:
**`DockerfileDatabase`**
```Dockerfile
FROM gvenzl/oracle-free:23

ENV ORACLE_PASSWORD=mypassword
ENV APP_USER=myuser
ENV APP_USER_PASSWORD=mypassword

EXPOSE 1521
```

Just as we did previously, we can now create this database's image:
``` bash
docker build -t demo-database -f DockerfileDatabase .
```

Running the created image:
```bash
docker run -p 1521:1521 demo-database
```

### connected app yaml

**`DockerfileSingleApp`**
```dockerfile
FROM amazoncorretto:17-alpine

WORKDIR /app
COPY build/libs/events-0.0.1-SNAPSHOT.jar application.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/application.jar", "--spring.profiles.active=oracle"]
```


## One File to Rule them All