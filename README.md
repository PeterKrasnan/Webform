WEBFORM
====

## 1. Overview

This project is a simple web app containing form that can be filled and stored in a database.
Application runs on http://localhost:9080/.

Form contains 5 fields that must be filled before submitting.

### Fields restrictions:

a) Policy number: must contain only alphanumeric characters

b) Name and Surname: must contain only alphabetic characters

c) Request: can contain max. 1000 characters

## 2. Install and Run

To install app run:
```sh
mvn clean install
```

To start the app run:
```sh
mvn spring-boot:run
```

## 3. Technologies

Build:
Maven

plugin worth to mention: frontend-maven-plugin - downloads and installs Node and NPM locally for the project

### Backed:
Java - Spring (Spring Boot)
Derby - in-memory database
All backend is in 'src' folder


### Frontend
ReactJS
All frontend is in 'frontend' folder

## 4. Future improvements

a) Restriction for name and surname could be change to allow them to contain spaces and hyphens (or dash) (e.g. Anna-Maria, Anna Maria)

b) Change derby database for some non in-memory solution

c) Create properties in application.properties for e.g max allowed size of request,...

d) Improve visual for web form

e) Maybe divide web form to several components

f) Write test fo frontend

g) Wire component, integration and E2E test for whole application

h) Error handling for WebFormController. Do not return exception from java to user

i) Resolve error that occurs during loading of web form(visible in web console)

j) Refactor duplicate code in Form.js

k) Separate BE and FE. Create separated modules so build of them can be independent.

## 5. Note

I did not write Javadoc, because the code is just too obvious.

