# shareNow API task

## Task Solution
Light-weight api testing solution based on junit. CRUD handling methods were added so that
the framework can be extended and reused it future.

Things that should be improved in the future:
1. object mapper implementation for entities
2. parallel run

## Framework stack

* Java 11
* Rest Assured lib for api call handling
* Maven (Build automation tool)
* JUnit (Unit testing framework and runner)
* Spring (Application and IoC framework)
* Log4j (Logging tool)

## Prerequisites

You need to have following installed and env variables configured::

* Java 11
* Apache Maven 3

## Test configurations

Framework host properties can be found in application.properties. Other properties can be added and assigned if needed

Configure

```
Import a project as maven project, build and import dependencies
Setup projectSDK
```
Run

```
From junit runner classes directly

Go to scr/test/java/tests/ResourcesTests and hit run on the class
or a particular test.
```

```
From terminal with maven command

to run all the tests:
mvn clean test
```

```
With junit runner

setup a junit configuration
```


## Results

Test results can be found in the console. Futher these test results can be linked with a number of reporting tools.
