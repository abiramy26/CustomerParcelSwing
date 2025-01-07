# Parcel Processing Application

## Overview
This Java application simulates a depot worker processing customers in a queue for collecting parcels. The system uses various software architecture patterns, including **MVC (Model-View-Controller)** and **Singleton**, to demonstrate architectural principles and implement a responsive graphical user interface (GUI) using Swing.

## Features
- **Queue Management**: Customers are added to and removed from a queue.
- **Parcel Management**: Parcels are loaded, processed, and stored in a map data structure.
- **Fee Calculation**: A fee is calculated for each parcel being processed.
- **Logging**: All events (e.g., customer joins queue, parcel processed) are logged using a Singleton Log class.
- **Interactive GUI**:
  - Displays the queue of customers.
  - Shows a list of parcels yet to be processed.
  - Highlights the current parcel being processed by a worker.

## Class Structure
1. **Parcel**: Represents a parcel with attributes and methods.
2. **Customer**: Represents a customer object.
3. **QueueOfCustomers**: Manages the queue of customers.
4. **ParcelMap**: Uses a map data structure to store parcel objects.
5. **Log**: Implements the Singleton pattern to log system events.
6. **Worker**: Contains logic for processing customers and parcels.
7. **Manager**: Driver class responsible for initializing the application, reading data files, and instantiating other components.

## Technologies Used
- **Java Swing** for GUI.
- **Collections Framework** for data structures (Lists, Maps).
- **Design Patterns**: MVC and Singleton.

## How to Run
1. Clone the repository:
   ```bash
   git clone <repository-url>
