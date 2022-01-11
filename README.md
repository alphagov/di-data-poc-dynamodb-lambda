# di-data-poc-dynamodb-lambda
Proof of concept of using DynamoDB with AWS Lambda

This is split into three projects:
- getdata - AWS lambda
- setdata - AWS lambda
- demo-site - Spring Boot and Thymeleaf demo website

## AWS infrastructure
The key parts of AWS that hang this POC together are as follows:
- DynamoDB Instance
- AWS Lambda for getdata
- AWS Lambda for setdata
- AWS API Gateway for orchestration/authentication

Please note: The demo site was unable to get the Lambdas working via the gateway in the timescale of this POC

## getdata
This AWS Lambda currently retrieves information from the DynamoDB using the AWS libraries within Java.
This can be deployed using the AWS tools within your IDE of choice, or using a clean and package command.
Once that has been done it can be deployed as a ZIP file within the AWS Website.
The Lambda needs sufficient privileges as a role to connect to the Dynamo DB, iterate, and read data


## setdata
This AWS Lambda currently stores information in the DynamoDB using the AWS libraries within Java.
This can be deployed using the AWS tools within your IDE of choice, or using a clean and package command.
Once that has been done it can be deployed as a ZIP file within the AWS Website.
The Lambda needs sufficient privileges as a role to connect to the Dynamo DB, iterate, read, and write data

## demo-site
This has currently not been deployed and is run locally using spring-boot:start
The controllers have not yet been wired up to correctly call the Lambdas, as attempts were originally made to have it talk via the AWS API Gateway, however these were unsuccessful
