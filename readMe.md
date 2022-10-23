# Spring Batch Kubernetes

SBP App configured both as Spring Batch and Spring Cloud Task. The essence of the sbp-app is to develop a short-lived
spring-batch task that could process batch-transactions per file in parallel. These batch files would then be launched
and executed in parallel via Spring Cloud Data Flow.

## Setup

1. Create a Kubernetes Cluster & install Kubernetes client-cli on the client machine.
2. Download the Kubernetes Cluster yml-config into the `~/.kube`
3. Interact with the Kubernetes Cluster via the kubectl.

# Execution

There are 2 approaches to the deployment & testing.

## 1. Testing locally

1. `docker run -p 3306:3306 --name maria -e MYSQL_ROOT_PASSWORD=DEMO_PASS -d mariadb`
2. `mvn clean install -U`
3. `java "-DDB_HOST=localhost" "-DDB_PORT=3306" "-DDB_NAME=sbp" "-DDB_PASS=DEMO_PASS" \
   "-DDB_USER=root" "-Dfile.encoding=UTF-8" -jar sbp-1.0.1.jar \
   fileName=file:///Users/sean/env/pty/proj/microservices/pay-cons/src/main/resources/samples/100012037_2022-09-17_18.45.48.525454_4174-89FB.csv`

## 2. Remotely

Execute a short-lived job execution via the kubectl-cli

```
JOB_NAME=tx-1 \
  FILE_NAME=${ABSOLUTE_FILE_PATH} \
  envsubst < src/main/resources/kube/sbp-job.yml | kubectl apply -f -
```

## CSV Samples

CSV file samples are located in: `src/main/resources/samples`

# References

[Spring Batch on Kubernetes: Efficient batch processing at scale](https://spring.io/blog/2021/01/27/spring-batch-on-kubernetes-efficient-batch-processing-at-scale)
