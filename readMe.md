# Spring Batch Kubernetes

SBP App configured both as Spring Batch and Spring Cloud Task. The essence of the sbp-app is to run as a short-lived
spring-batch task that could process batch-transactions per file in parallel. These batch files would then be launched
and executed in parallel via Spring Cloud Data Flow.

## Problem Statement

Suppose we've 4 clients that intend to send batch files at the same exact time. It would be a mistake to launch these
batch transactions purely in a global sequential order through the use of a queue mechanism. Execution on one batch that
belongs to 1 Client should not cause any impact on 3 other clients. Therefore, a global queue mechanism on all batch
files
is not ideal.

## The Experiment Apparatus

The experiment has been broken down into 3 services:

1. The Random Transaction-Data Generator. Generates fake transaction that simulates real world.
2. The Transaction Batch Job Task. ETL (Extract, Transform & Load) the associated transactions per batch-file per pod.
3. The Task-Launcher. Launches the Tasks (Batch-Jobs) & to move the CSV files to the relevant directories.

## Setup

1. Create a Kubernetes Cluster & install Kubernetes client-cli (kubectl) on the client machine.
2. Download the Kubernetes Cluster yml-config into the `~/.kube/`
3. Interact with the Kubernetes Cluster via the kubectl terminal/powershell CLI.

## Validation

Any input data must be validated prior to processing step. Add validation in the ItemReader step to validate all input &
protect the app from potential known attacks. Defensive Programming Techniques to prevent the app from behaving in an
unwanted state.

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

## Docker Packaging

To package & build with the app with the latest Spring Boot (ver +2.7.4) utilise the following command. The image build
steps could be refined & extended in pom.xml.

````
mvn spring-boot:build-image -Dspring-boot.build-image.imageName=${USERNAME/NAME_OF_IMAGE:VERSION}
````

Spring boot will utilise Paketo-Buildpacks to build a docker image ready for deployment.
Example `mvn spring-boot:build-image -Dspring-boot.build-image.imageName=s34n/app-img:1.0.0`

# Aim of Experiment

The previous experiment, the author didn't cover auto-launching the tasks (Spring batch jobs). This experiment aims to
fulfil that gap. Therefore, a task-launcher that listens to a directory & moves successful/failed batch-files to
different
archive/partial/error directories respectively.

## Future Areas of Exploration

- Consider using the step-partitioning for long-running batch jobs. Instead of processing batch jobs of 1_000 per chunk
  consider
  breaking down the batches into batch-chunks of 100 micro-batches using Step-Partition.
- Partially processed batch files have a restartability potential to re-run those jobs with the exception of a few
  erroneous
  transactions. In such a case only exclude the few erroneous transactions & process the rest depending on the business
  case.
- Logging & storing logs in a maintainable strategy without mixing logs that belong to distinctive clients.
  `client-name/log/year/month/day/`
- Storing & archiving batch-files in strategic directories.
- Input Dir: `${client-name}/batch/in/`
- Archive Dir: `${client-name}/batch/arc/year/month/day`
- Error Dir: `${client-name}/batch/err/year/month/day`
- Partial-Error Dir: `${client-name}/perr/year/month/day`
- Output Dir: `${client-name}/batch/out/`

Above & beyond the ultimate goal is to also make use of the Open Source Spring Cloud Data Flow Server to launch &
monitor the resource usage of the Spring Apps running the Kubernetes Cluster in the cloud.

## Resource Utilisation

Supposedly, the batch-job has reached completion status. Should the pod/app-instance remain idle indefinitely? It's a
cost-effective strategy to release resources when not utilised in a bill-per-usage based infrastructure. Hence, Spring
Batch Jobs can be configured as Spring Tasks, once completed successfully, they can be gracefully shutdown.

# References

[Spring Batch on Kubernetes: Efficient batch processing at scale](https://spring.io/blog/2021/01/27/spring-batch-on-kubernetes-efficient-batch-processing-at-scale)
[Creating Docker Images with Spring Boot](https://www.baeldung.com/spring-boot-docker-images)
[Kubernetes Parallel Processing Example](https://kubernetes.io/docs/tasks/job/parallel-processing-expansion/)
[Creating a Kubernetes Cluster](https://dataflow.spring.io/docs/installation/kubernetes/creating-a-cluster/)
[Spring Batch Processing with Spring Cloud Task](https://dataflow.spring.io/docs/batch-developer-guides/batch/spring-task/)
[Spring Cloud Data Flow](https://dataflow.spring.io/docs/installation/kubernetes/kubectl/)
