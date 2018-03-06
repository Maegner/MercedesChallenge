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
All the query strings in this controller are optinal, if not provided the query string value will be considered to be "any", therefore matching with all vehicles.
### Get the closest dealer to the provided location that has a vehicle with specific atributes.
__Request type :__ POST <br />
__Return values:__ 0 or 1 Dealer Object <br />
__URI :__ localhost:8080/dealers/get-closest <br />
__Optional query strings :__ model,fuel,transmission <br />
__Required Headers :__ Content-type: application/json <br />
__Required Body :__ Coordinate Object <br />
__Body example :__
```json
{
   "latitude":42.333,
   "longitude":0
}
```
### Get the dealers sorted by distance according to the provided location that have a vehicle with specific attributes.
__Request type :__ POST <br />
__Return values:__ Array containg 0 or more Dealer Objects <br />
__URI :__ localhost:8080/dealers/search <br />
__Optional query strings :__ model,fuel,transmission <br />
__Required Headers :__ Content-type: application/json <br />
__Required Body :__ Coordinate Object <br />
__Body example :__
```json
{
   "latitude":42.333,
   "longitude":0
}
```

### Get the dealers inside a given polygon that have a vehicle with specific attributes.
__Request type :__ POST <br />
__Return values:__ Array containg 0 or more Dealer Objects <br />
__URI :__ localhost:8080/dealers/search <br />
__Optional query strings :__ model,fuel,transmission <br />
__Required Headers :__ Content-type: application/json <br />
__Required Body :__ Two Coordinate Objects representing the poligon's bottom left corner and top right corner <br />
__Body example :__
```json
{
    "bottomLeft":{
        "latitude":38.888,
        "longitude":-10.55
    },
    "topRight":{
        "latitude":42.4,
        "longitude":0
    }
}
```