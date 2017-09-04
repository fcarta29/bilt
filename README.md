# BILT

##Project Setup

1. Install redis
  * Download http://redis.io/download
  * For Mac OS X use
    * “curl -O http://download.redis.io/releases/redis-3.0.1.tar.gz"
    ```
    $ wget http://download.redis.io/releases/redis-3.0.1.tar.gz
    $ tar xzf redis-3.0.1.tar.gz
    $ cd redis-3.0.1
    $ make
    ```
2. Install Git
  * https://git-scm.com/book/en/v2/Getting-Started-Installing-Git
3. Create GitHub account
4. Fork repo
  * https://github.com/fcarta29/bilt
  * how to fork - https://help.github.com/articles/fork-a-repo/
5. Clone GIt repo to local workspace
  * git clone git@github.com:<your_repo_name>/bilt.git
6. Building Applications
  * "mvn clean install" from /bilt directory to build all

### Build and Run
_*Note: all commands assume repo directory for working dir_

Requirements:
* docker
* Make sure to add the `/data` directory to the list of directories that
can be bind mounted into Docker containers,
* Copy `bilt-data/data-rest-test/dump.rdb` to `/data/bilt/dump.rdb` to seed the database with defaults

To build all the targets:
```
docker run --rm -v "$PWD":/bilt -w /bilt maven:3.3.3-jdk-8 mvn clean install
```
To run:
```
docker-compose up
```
_*Note:_ This command will run using the cached docker images. You need to add --build to regenerate the docker images with new changes
```
docker-compose up --build
```
To run by service type - for example start up redis :
```
docker-compose up redis
```

### Data
To test basic CRUD for the data backend
```
docker-compose up data-rest-test
```
Swagger documentation:
`http://{docker_host}:9090/swagger-ui.html`

### Games Service
Games services for getting and managing game data
```
docker-compose up service-games
```
Swagger documentation:
`http://{docker_host}:9080/swagger-ui.html`

##Project Start/Run (Maven/Java/Spring Way)

1. Debugging
  * export MAVEN_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=y”
2. Starting Applications
  1. Start redis
    * Start reds from reds home dir
      * under src/
      * ./redis-server <path-to-redis-config>/redis.conf
    * (Optional) start up redis cli
      * under src/
      * ./redis-cli
  2. Starting bilt-data
    * mvn spring-boot:run
  3. Starting bilt-app
    * mvn spring-boot:run
  4. Starting bilt-webapp
    * mvn jetty:run
3. REST endpoints on Swagger
  * http://localhost:9080/swagger-ui.html
  * http://localhost:9080/v2/api-docs
4. Using/Testing
  1. Open 2 browser windows to http://localhost:8080
  2. Click Connect
  3. Enter name … tmp until login is ready
