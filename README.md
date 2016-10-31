# The Checkout SpringBoot Restful Web Service

## Step 1: Shopping cart

- This application is a checkout system for a shop which only sells apples and oranges.

- Apples cost 60p and oranges cost 25p.

- The checkout functionality is implemented as a Spring REST endpoint which takes a list of products scanned at the checkout and outputs the total cost

For example: [ Apple, Apple, Orange, Apple ] => £2.05


## Step 2: Simple offers

The shop also has two new offers

- buy one, get one free on Apples

- 3 for the price of 2 on Oranges

The Update your checkout functions accordingly

# Running your SpringBoot Checkout Restful Web Service

To build/run the springBoot web service ensure your environment or IDE is setup accordingly

- Java Installation is required

- Maven installation is required

- Web Service  is configured to run on port 8123, so ensure port is free!

- Internet access is required for relevant download during build

- Download the checkout app from Git or clone the project

- Unzip project and/or cd to the root project directory i.e. checkoutapp-master

- Build the project as follows > mvn clean install

- Run the spring bootstrap app as follows > mvn spring-boot:run


