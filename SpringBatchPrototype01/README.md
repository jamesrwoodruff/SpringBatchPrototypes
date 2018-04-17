## Objective

This is a simple Spring Batch prototype.

This is based on a recent project where I wrote a new batch process that consumes XML data, parses it and loads it to a database. This Spring Batch process pulls together some of the techniques employed and lessons learned while researching and implementing that project.

I created this to use as a starting template for future Spring Batch processes.

## Technical Summary

This Java project uses the Spring Batch framework, the reference implementation of JSR-352 (https://docs.spring.io/spring-batch/reference/html/jsr-352.html). It takes an xml file of applicants, unmarshalls the data using the Spring OXM Jaxb2Marshaller, and loads it to a MySQL database table. For simplicity, the project structure is 'flat' in that all code is at the same package level. The code features:

* Spring Batchx
* Maven
* MySQL
* logback

## Requirements

* MySQL and connector

## Setup

* Clone this repo
* Run mvn clean install
* Startup MySQL, then:
  * create mysql database called *some_schema*
  * test connection (jdbc:mysql://localhost:3306/some_schema)
  * execute schema-mysql.sql to create Spring Batch meta-tables
  * execute create-applicant-table.sql to create table *applicant*

## Run Instructions

* From Eclipse or other IDE:
  * right click ApplicantLoadJob Launcher, Run As Java Application
* Verify rows were inserted into table *applicant* (and batch meta-tables)
* To run again, delete rows from *applicant*

## Configuration

I opted to use Java configuration (often referred to as **JavaConfig** in Spring terminology), my preference over XML configuration. I spread the configuration in this project across 3 classes:

* ApplicationConfiguration
* BatchConfiguration
* DataSourceConfiguratiaon

## Application Context

Here's a code example from ApplicantLoadJobLauncher.java showing instantation of the Application Context and the job launch:

```
AnnotationConfigApplicationContext context = 
 new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
ApplicantLoadJobLauncher main = (ApplicantLoadJobLauncher) context.getBean(ApplicantLoadJobLauncher.class);

JobParametersBuilder builder = new JobParametersBuilder();
builder.addDate("date", new Date());
main.jobLauncher.run(main.loadApplicantsJob, builder.toJobParameters());
```
