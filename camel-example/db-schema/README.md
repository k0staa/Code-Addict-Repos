#Docker with MS SQL DB
Launching docker container with ms sql database:
```
docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=QWE45rty' --name camel_example_db -p 1433:1433 -d mcr.microsoft.com/mssql/server:2017-CU8-ubuntu

```
#Running Liquibase scripts
```
 ./gradlew -p db-schema update
```
We need to run this from the main catalog of the project .