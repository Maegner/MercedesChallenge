# MB .io - SINFO Test Drive Challenge API (backend)

This aplication is my answer to the Sinfo test drive challenge API.

## Before getting started
Make sure you have the latest maven and Java versions installed.
Disclaimer: 
    This project was only tested in a linux based system (Ubuntu 16.4) so the commands to run or build the program can differ from OS to OS, altough if you have the corect versions of java and maven installed the app should build and run independently on the Operative System.
    
## Dependencies
Dependency  | Version
------------- | -------------
SpringBoot Framework  | 1.5.10
Junit  | 4.12
Joda time | 2.9.7
JSON Simple | 1.1.1

## Building the application
Just cd to the project folder and run 
```sh
$ mvn install
```
## Getting the API running
cd to the testDrive folder and run
```sh
$ mvn spring-boot:run
```
That will get the API running on http://localhost:8080
To make sure it is running open your browser and go to http://localhost:8080/vehicles/fuels?fuel=GASOLINE and if you recieve a JSON response it means everything is working as expected.

## Running the tests
cd to the project folder and run
```sh
$ mvn test
```

## Seeing the test coverage
In this project i used jacoco to check the test coverage.
To see the results run(if you haven't before)
```sh
$ mvn install
```
Then go to the folder testDrive/target/site/jacoco and open the file index.html.
The web folder that contains the API controlers is not targeted by the unit tests because all they do is call methods that were already tested.

## API Documentation
[read the documentatio](Doc.md)

## Author
- Francisco Aguiar (franciscomaguiar@gmail.com)
