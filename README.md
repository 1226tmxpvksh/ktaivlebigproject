# 

## Model
www.msaez.io/#/56288373/storming/2a8184bf7a3e5d65cb9a57fb20a80849

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd infra
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- user
- auth 
- board
- report
- api gateway


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- user
```
 http :8088/users userId="userId"email="email"password="password"nickname="nickname"
```
- auth 
```
 http :8088/auths authId="authId"userId="userId"updateToken="updateToken"updateExpiredAt="updateExpiredAt"
```
- board
```
 http :8088/boards boardId="	boardId"title="title"content="content"authorId="authorId"viewCount="viewCount"type="type"createdAt="createdAt"updatedAt="updatedAt"
```
- report
```
 http :8088/reports reportId="reportId"title="title"content="content"authorId="authorId"createdAt="createdAt"savedAt="savedAt"
```
- api gateway
```
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```
