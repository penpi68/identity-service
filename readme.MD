# Identity service
This microservice is responsible for:
* Onboarding users
* Roles and permissions
* Authentication

## Tech stack
* Build tool: maven >= 3.9.5
* Java: 21
* Framework: Spring boot 3.2.x
* DBMS: MySQL

## Prerequisites
* Java SDK 21
* A MySQL server

## Start application
`mvn spring-boot:run`

## Build application
`mvn clean package`


## Docker guideline
### Check images
docker images

### Build docker image
`docker build -t <account>/identity-service:0.9.0 .`

`docker build -t identity-service:0.0.1 .`


### Push docker image to Docker Hub
`docker image push <account>/identity-service:0.9.0`
### Create network:
`docker network create quynh-network`
### Remove network 
`docker rm create quynh-network`
### Start MySQL in quynh-network
`docker run --network quynh-network --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:8.0.36-debian`
#### Start mysql on port
`docker run --name mysql-8.0.36 -p 3307:3307 -e MYSQL_ROOT_PASSWORD=Passmysql118 -d mysql:8.0.36-debian`

`docker run --network quynh-net --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:8.0`
### mysql command 
`docker exec -it mysql-8.0.36 bash`

`mysql -u root -p`
-->Enter password:

#### show database
 `
### Run adminer 
`docker run --link mysql:mysql -p 9080:8080 -e ADMINER_DESIGN='nette' -d adminer:latest`

### Run your application in quynh-network
`docker run --name identity-service --network quynh-network -p 8081:8081 -e DBMS_CONNECTION=jdbc:mysql://localhost:3307/identity_service identity-service:0.0.1`

`docker run --name identity-service-01 --network quynh-net -p 8081:8081 -e SPRING_DATASOURCE_URL=jdbc:mysql://172.18.0.2:3306/identity_service identity-service`

## Install Docker on ubuntu

# Add Docker's official GPG key:
sudo apt-get update
sudo apt-get install ca-certificates curl
sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc

# Add the repository to Apt sources:
echo \
"deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
$(. /etc/os-release && echo "$VERSION_CODENAME") stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

sudo apt-get update

sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

sudo docker run hello-world
