## State API
The REST API application fetches statistical data from an external API and returns the top 3 US states by population in JSON (state name, population, year of data source).

## Technology stack and data source:
* Java 17.0.4
* Spring Boot 2.7.11 (Web module)
* API - [Data USA API](https://datausa.io/about/api/) (authentication is not required)

## Endpoint
* [localhost:8080/api/v1/states](localhost:8080/api/v1/states)

## Planned features
* New endpoints with parameters
* Data source switching: database / API
