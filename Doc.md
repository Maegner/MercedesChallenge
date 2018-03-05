# API Documentation
## Vehicles
### Searching for Vehicles with a certaint atribute
URI  | QueryString/Atribute |Request type
----- | -----------|---------
localhost:8080/vehicles/models  |model| GET
localhost:8080/vehicles/fuels  |fuel |GET
localhost:8080/vehicles/transmissions |transmission |GET
localhost:8080/vehicles/dealers |dealer |GET

## Dealers
### Searching for dealers based on positioning and owned Vehicles
* All the query strings in this method are optinal, if not provided the query string value will be considered to be "any", therefore matching with all vehicles.
