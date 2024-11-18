
# Important Docker commands
## 1. `docker images`
To list all the docekr images present in the docekr server
![alt](screenshot\docker-images.png)
## 2. `docker images inspect [image-id]`
Display detailed image infor for a given id
![alt](screenshot/docker-image-inspect.png)
 
 `docker image inspect [repository:tag]`--> `docker image inspect mysql:8.0`
## 3. `docker image rm [image-id]`
Remove image 
![alt](screenshot/docker-image-rm.png)

## 4. `docker pull [repository:tag] `
![alt](screenshot/docker-image-pull.png)
## 5. `docker build . -t [image-name]`
Generate a docker image based on a Dockerfile

`docker build -t identity-service:0.0.1 .`


## 6. `docker run -p [hostport]:[containerport] [image-name]`
Start docker container

`docker run --network quynh-network --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:8.0`
## 7. `docker ps`
Show all running container

`docker ps -a`

`docker ps --format "table {{.ID}}\t{{.Status}}\t{{.Names}}"`

`docker ps --format '{{ .ID }} {{ .Names }} {{ json .Networks }}'`
## 8. `docker container start [container-id]`

`docker container pause [container-id]`
`docker container unpause [container-id]`
`docker container stop [container-id]`
`docker container kill [container-id]`
## 9. `docker container inspect [container-id]`

`docker inspect --format='{{range .Config.Env}}{{println .}}{{end}}' 575ed3040056 `

## 10. `docker container logs [container-id]`

 
## 11. `docker exec -it [container-id] sh`
Open a sell inside a running container

## 12. `docker [...] prune`
Remove all 
`docker container prune`
## 13. `docker compose up`
`docker compose up -d --build -f docker-compose-sub.yaml `


# Docker guideline for identity-service


### Create init folder inside proj folder
+ Create init.sql file to create a database 
![alt](screenshot/init-folder.png)
### Build images and run with docker-compose

`docker compose up -d --build`

![alt](screenshot/docker-compose-up.png)


### Check database using adminer
`Start-Process "http://localhost:9080"`

![alt text](screenshot/check-adminer.png)
![alt text](screenshot/check-adminer2.png)

### Inspect network
![alt](screenshot/inspect-network.png)

![alt](screenshot/inspect1.png)
### mysql command 
`docker exec -it [container-id] bash`

`mysql -u root -p`

![alt](screenshot/mysql-shell.png)
#### show database
 
