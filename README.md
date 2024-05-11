# GameHub <img src="logo.png" alt="Gamehub Logo" width="100">


GameHub is an application that replicates an online game store and cover all the functionalities typical of such platforms.
What sets GameHub apart is its unique feature as a store simulator, where new games are automatically fetched and user purchases are simulated.

## Technologies Used

- **Java and Spring:**  used for backend REST server development.
- **React:** JavaScript library used for frontend development.
- **Postgres:** Database used for storing  users data.
- **RAWG.io** Video Game Database


## Quick Overview

https://github.com/DominikDD30/GameShopProject/assets/91913816/a020e599-5467-47d9-aa45-ae33125774ba


## Simulation Features

simulation features are split into into internal and external 
where external are turned off by default and required rawgi apikey to work
internal simulation stands for simulating user purchases and updating the stock quantity for games.
For example, when simulating a high interest and a game runs out of all units, it becomes unavailable until the next update.


## Installation

1. Clone the repository
2. create database flash_learn
3. run backend server
4. Navigate to frontend project directory 
5. Install required dependencies: `npm install`
6. Start frontend application: `npm run dev`
7. go to http://localhost:5180
   
## Docker Installation

1. Clone the repository to your directory
2. open your directory in terminal 
3. type 'docker compose up'
4. go to http://localhost:5180


## Contribution

If you want to contribute to the project, feel free to do so! You can report bugs, suggest new features, or fix existing issues. All contributions are welcome.



## License

This project is licensed under the Apache License, Version 2.0.

