# MySQL Docker Setup for Project

This guide explains how to run MySQL in Docker for this project and how to connect to it.

## 1. Run MySQL in Docker

Make sure you have Docker installed.

### Using Docker CLI
```bash
docker run --name system-management-mysql-1 \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=system_management \
  -p 56561:3306 \
  -d mysql:latest
```

- `--name system-management-mysql-1` : Container name
- `-e MYSQL_ROOT_PASSWORD=root` : MySQL root password
- `-e MYSQL_DATABASE=system_management` : Database name to create
- `-p 56561:3306` : Map port 56561 on host to port 3306 in container
- `-d mysql:latest` : Run MySQL latest version in detached mode

### Using Docker Compose
If you have `docker-compose.yml` in your project, run:
```bash
docker compose up -d
```

## 2. Check MySQL container status
```bash
docker ps
```
You should see something like:
```
CONTAINER ID   IMAGE          COMMAND                  CREATED          STATUS          PORTS                                NAMES
3b3affa6a437   mysql:latest   "docker-entrypoint.s…"   10 minutes ago   Up 10 minutes   33060/tcp, 0.0.0.0:56561->3306/tcp   system-management-mysql-1
```

## 3. Connect to MySQL inside the container
```bash
docker exec -it system-management-mysql-1 mysql -u root -p
```
- Enter password (`root` in this example) when prompted.

## 4. Common MySQL commands
Inside MySQL shell:
```sql
SHOW DATABASES;
USE system_management;
SHOW TABLES;
SELECT * FROM users;
```

## 5. Stop and start the container
```bash
docker stop system-management-mysql-1
docker start system-management-mysql-1
```

## 6. Remove the container (if needed)
```bash
docker rm -f system-management-mysql-1
```
