# API Documentation
## Vehicles
### Searching for Vehicles with a certaint atribute
__Request type :__ POST <br />
__Return values:__ List of Vehicles matching de description <br />
__URI :__ localhost:8080/vehicles <br />
__Required Headers :__ Content-type: application/json <br />
__Required Body :__ A json object containing the optional atributes present in the example bellow <br />
__Body example :__
```json
{
  "dealerId":"846679bd-5831-4286-969b-056e9c89d74c",
  "fuel":"GASOLINE",
  "transmission":"MANUAL",
  "model":"AMG"
}
```
__Or simply__
```json
{
  #This querie will return all of the existing vehicles
}
```

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

## Bookings
### Book a test drive.
__Request type :__ POST <br />
__Return values:__ The result booking object <br />
__URI :__ localhost:8080/bookings/new <br />
__Required Headers :__ Content-type: application/json <br />
__Required Body :__ A json object containing __all__ the atributes present in the example bellow <br />
__Body example :__
```json
{
   "dealerId":"846679bd-5831-4286-969b-056e9c89d74c",
   "vehicleId":768a73af-4336-41c8-b1bd-76bd700378ce",
   "firstName":"Francisco",
   "lastName":"Aguiar",
   "pickupDate":"2018-03-03T10:30:00"
}
```
__Obs :__ 
* The date format has to be the following "yyyy-mm-ddThh:mm:ss.SSS" the SSS field is optional. 
* The vehicle must bellong to the dealer referenced by the dealer id.
### Cancel a test drive.
__Request type :__ PUT <br />
__Return values:__ NONE <br />
__URI :__ localhost:8080/bookings/cancel <br />
__Required query string :__ bookingId <br />
__Required Headers :__ Content-type: application/json <br />
__Required Body :__ String containing the cancelation reason <br />
__Body example :__
```json
{
   "reason": "reason for cancelation"
}
```