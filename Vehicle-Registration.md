## CRUD API for Vehicle Registration

Description:

Create a project that manages the vehicle registration of the state. Project consist of CRUD API that manages the vehicle registration. It manages new vehicle registration by allocating the vehicle, registration code "numberPlate" for the vehicle.

##### Rules for numberPlate:

-  A new vehicle registered gets a alphanumeric code

- Code is of atleast 9 digit 

- Where first two digit is STATE or Union territory code, e.g. BH, MH, PB, DL, UP 

- Next two digit represent district’s sequential number e.g. MH12 Pune, MH14 Nashik

- Next digit/digits serial combination of alphabet
  
  - that means it starts with A, B,C and as the series end, it begins with AA, AB, same cycle repeats AAA, AAB

- last 4 digit serial combination of number
  
  - that means it starts with 0001, and goes on

##### Business Functionality Expected:

1. **Retrieve Details by numberPlate**:  A user should be able to retreive  his or her vehicle details by passing the vehicle numberPlate code (MUST HAVE)

2. **Register a new vehicle:** A new vehicle registration must be allowed on the details of user whose age is 18+ and owner is not deceased . Registration must include: vehicle model name, vehicle type, vehicle model year, vehicle engine no., owner name, owner DOB, owner aadhar card, city, purchased on date. Post registration a new vehicle numberPlate code is generated (Must have)

3. **Green Check**: If a vehicle registration has completed 15 year and owner has paid green tax, mark the vehicle valid, else red flag the vehicle , hence illegal and guilible for challans

4. **Owner transfer Request:** If a request to change the owner is created, the owner details should be changed and previous history should also be persisted

5. **Seize the Vehicle:** If the vehicle has more than 20 unpaid challans on it and last challans is also 2 years old, in that case the vehicle will be seized. Seizing the vehicle means the numberPlate will be marked archived for its lifecycle and will be illegal to be used 

6. **State Transfer Request**: Allow the vehicle owner to transfer the vehicle to new state, in such case a new registration numberPlate code will be generated respective to the new state and old registration code will be archived for its lifecycle

7. **Retrieve Owner history**: Using number plate code of the vehicle 

**Tables needed:**

- A table for Vehicle list with all vehicle related info, where state related info and owner related info are to be managed by foriegn key

- A state table to maintain state and union teritory code

- A person table to maintain personal information of a person e.g.: name, dob, mobile number, aadhar card

- Any other table you think can be needed, you are free to create

Note:

- Do i need to maintain district code list? No just so by random values 

- You are free to use any java framework or even core java, provided you are using java11 or higher version

- You are free to use any IDE (mostly eclipse or vscode is being used in team)

- You can use Maria DB or MYSQL or PostgreSQL for Database

- REST API is preferred for the implementation and thus you can use postman or any known restapi tool
