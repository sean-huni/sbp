# Spring Batch Kubernetes

SBP App configured both as Spring Batch and Spring Cloud Task. The essence of the sbp-app run as a short-lived
spring-batch task that could process batch-transactions per file in parallel. These batch files would then be launched
and executed in parallel via Spring Cloud Data Flow.

## The Experiment Apparatus

The experiment has been broken down into 3 services:

1. The Random Transaction-Data Generator. Generates fake transaction that simulates real world.
2. The Transaction Batch Job Task. ETL (Extract, Transform & Load) the associated transactions per batch-file per pod.
3. The Task-Launcher. Launches the Tasks (Batch-Jobs) & to move the CSV files to the relevant directories.

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

## Aim of Experiment

The previous experiment didn't cover auto-launching the tasks (Spring batch jobs). This experiment aims to fulfil that
gap. Therefore, some kind of a task-launcher that listens to a directory & moves successful/failed batch-files to
different archive/error directories respectively.

# References

[Spring Batch on Kubernetes: Efficient batch processing at scale](https://spring.io/blog/2021/01/27/spring-batch-on-kubernetes-efficient-batch-processing-at-scale)
