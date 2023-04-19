## Objective

A simple Spring Batch prototype to use as a starting template. 

Copied the Spring Batch Getting Started [Guide](https://spring.io/guides/gs/batch-processing/) (code [here](https://github.com/spring-guides/gs-batch-processing/tree/main/complete)) and pointed it to a local MySQL instance (instead of an embedded hsqldb).

To enable that, I first created the meta tables described here. And followed the upgrade guide here.

## Technical Summary

This Java project uses:

* Spring Batch - the reference implementation of JSR-352 (https://docs.spring.io/spring-batch/reference/html/jsr-352.html)
* Maven
* MySQL

## Requirements

* MySQL

## Setup

* Clone this repo
* Run mvn clean install
* Startup MySQL, then:
  * create mysql database called *some_schema*
  * test connection (jdbc:mysql://localhost:3306/some_schema)
  * create Spring Batch meta tables (see here, but append tables and sequences with schema)
  * execute schema.sql to create table *applicant*

## Run Instructions

* From Eclipse or other IDE:
  * right click BatchProcessingApplication, Run As Java Application
* Verify rows were inserted into table *people* (and batch meta-tables)
* To run again, delete rows from *people*

## Application Context

Here's a code sample as a placeholder:

```
SpringApplication.run(BatchProcessingApplication.class, args)
```
