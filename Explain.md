# Explanation

## Classes used:

#### Root:

| Field     | Format     | Description |
| :-------- | :--------- | :---------- |
| dealers   | HashMap<String,Dealer> | List of available dealers indexed by the respective dealer id |
| bookings  | HashMap<String,ArrayList<Booking>> | List of bookings made indexed by the vehicle id |

__Reasoning :__ I chose to use hashMaps here because it makes indexing possible and therefore it's faster to search for a dealer to get is information and it is easier to find out if a chosen vehicle is occupied at a certaint date.


#### Dealer:
| Field     | Format     | Description |
| :-------- | :--------- | :---------- |
| id | String | Unique identifier for a dealer |
| name  | String | Name of the Dealer |
| coordinate | Coordinate |Dealer's location  |
| vehicles   | ArrayList<Vehicle>  | List of vehicles in that dealer  |
| closed | ArrayList<String> | List of days that the dealer is closed for  business |

__Reasoning :__ In this class i just followed the chanllenge specifications but chose to use an Object to represent a Coordinate because it made the code easier to read.

#### Vehicle:
| Field     | Format     | Description |
| :-------- | :--------- | :---------- |
| id | String | Unique identifier for a vehicle |
| model  | String  | Vehicle model (eg: 'A-Class', 'C-Class', 'E-Class', 'S-Class')  |
|fuel   | String  | Fuel type of the vehicle (eg: 'petrol', 'diesel', 'hybrid')  |
| transmission   | String  | Vehicle transmission type (eg: 'manual', 'automatic')  |
| availability   | HashMap<String,ArrayList<int>>  | Key/Value pair, with days of the week (keys) and the hours (values) available for booking. This represents the general availability of a vehicle and is not changed when a booking is made.  |

__Reasoning :__ In this class i just followed the chanllenge specifications.

#### Booking:

| Field     | Format     | Description |
| :-------- | :--------- | :---------- |
| id | String | Unique identifier for a booking |
| vehicleId | String | Vehicle identifier |
| firstName | String   | Customer's first name  |
| lastName | String   | Customer's last name  |
| pickupDate   | Datetime  | Day and time of the booking  |  
| createdAt   | Datetime  | Day and Time that the booking entry was created  |  
| cancelledAt   | Datetime  | Day and Time that this booking was cancelled  |  
| cancelledReason   | Text  | Reason for the booking cancelation | 
__Reasoning :__ In this class i just followed the chanllenge specifications.