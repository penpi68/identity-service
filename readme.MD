

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

![alt](screenshot/inspect1.png)
### Open mysql shell inside container
`docker exec -it [container-id] sh`

`mysql -u root -p`

![alt](screenshot/mysql-shell.png)
### Application startup log

`docker log [container-id]`
![alt](screenshot/docker-log.png)
 

## Docker guildline with subnet
