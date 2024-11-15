# Taxi Management API Project

## Overview

The **Taxi Management API** is a RESTful application designed to digitalize taxi service management. It offers features for handling reservations, managing drivers and vehicles, and generating analytics. The API ensures efficient, scalable, and user-friendly operations for taxi companies aiming to improve their service offerings.

---

## Features

### Core Functionalities:
- **Reservation Management**: Create, update, delete, and display reservations.
- **Driver Management**: Handle driver records, availability, and statuses.
- **Vehicle Management**: Manage vehicles, their statuses, and types.

### Analytics and Reporting:
- **Average Price per Kilometer**: Provides insights into pricing trends.
- **Average Trip Distance**: Monitors typical trip lengths.
- **Reservation Distribution by Time Slots**: Analyze peak and off-peak booking patterns.
- **Most Popular Geographic Zones**: Identify high-demand areas.
- **Driver and Vehicle Utilization Rates**: Optimize resource usage.

---

## Tech Stack

- **Backend**: Java 8, Spring Boot
- **Database**:
    - **H2** (Dev)
    - **MySQL** (QA)
    - **PostgreSQL** (Prod)
- **ORM**: Spring Data JPA
- **API Documentation**: Swagger
- **Testing**: JUnit, Mockito
- **Build Tool**: Maven

---

## Getting Started

### Prerequisites
- **Java 8 JDK**
- **Maven**
- **PostgreSQL/MySQL/H2** (based on the environment)
- **IDE** (e.g., IntelliJ IDEA)

---

### Installation

1. **Clone the Repository**
   ```bash
   git clone https://github.com/Douaesb/taxista.git
   cd taxista


2. **Set Up the Database**
    - Choose a database (**H2**, **MySQL**, or **PostgreSQL**) based on your environment.
    - Configure the database with appropriate credentials and schema.

3. **Update Connection Settings**
    - Modify the following configuration files as needed:
        - `application-dev.yaml`
        - `application-qa.yaml`
        - `application-prod.yaml`

### Build the Project
    
    mvn clean install

4. **Run the Application**

   - Run the main class using your IDE or use Maven:
   ````
    mvn spring-boot:run

### API Endpoints

#### Reservation Endpoints

1. **Create a Reservation**
    - **POST** `/api/reservations`
    - **Request Body**: `ReservationDTO`
    - **Response**: `ReservationDTO`

2. **Get All Reservations**
    - **GET** `/api/reservations`
    - **Response**: `List<ReservationDTO>`

3. **Get Reservation by ID**
    - **GET** `/api/reservations/{id}`
    - **Response**: `ReservationDTO`

4. **Update a Reservation**
    - **PUT** `/api/reservations/{id}`
    - **Request Body**: `ReservationDTO`
    - **Response**: `ReservationDTO`

5. **Delete a Reservation**
    - **DELETE** `/api/reservations/{id}`
    - **Response**: `204 No Content`

#### Analytics

- **GET** `/api/reservations/analytics`
- **Response**:
   ```json
   {
       "averagePricePerKm": 10,
       "averageDistance": 15,
       "reservationsByTimeSlot": {
           "8h-9h": 15,
           "9h-10h": 20
       },
       "popularZones": ["Downtown", "Airport"]
   }

### Driver Endpoints

1. **Create a Driver**
    - **POST** `/api/chauffeurs`
    - **Request Body**: `ChauffeurDTO`
    - **Response**: `ChauffeurDTO`

2. **Get All Drivers**
    - **GET** `/api/chauffeurs`
    - **Response**: `List<ChauffeurDTO>`

3. **Get Driver by ID**
    - **GET** `/api/chauffeurs/{id}`
    - **Response**: `ChauffeurDTO`

4. **Update a Driver**
    - **PUT** `/api/chauffeurs/{id}`
    - **Request Body**: `ChauffeurDTO`
    - **Response**: `ChauffeurDTO`

5. **Delete a Driver**
    - **DELETE** `/api/chauffeurs/{id}`
    - **Response**: `204 No Content`

#### Analytics

- **GET** `/api/chauffeurs/analytics`
- **Response**:
   ```json
   {
       "occupancyRate": 75,
       "availabilityTimeSlots": ["8h-12h", "14h-18h"],
       "statusDistribution": {
           "DISPONIBLE": 10,
           "EN_COURSE": 5,
           "INDISPONIBLE": 2
       }
   }

### Vehicle Endpoints

1. **Create a Vehicle**
    - **POST** `/api/vehicules`
    - **Request Body**: `VehiculeDTO`
    - **Response**: `VehiculeDTO`

2. **Get All Vehicles**
    - **GET** `/api/vehicules`
    - **Response**: `List<VehiculeDTO>`

3. **Get Vehicle by ID**
    - **GET** `/api/vehicules/{id}`
    - **Response**: `VehiculeDTO`

4. **Update a Vehicle**
    - **PUT** `/api/vehicules/{id}`
    - **Request Body**: `VehiculeDTO`
    - **Response**: `VehiculeDTO`

5. **Delete a Vehicle**
    - **DELETE** `/api/vehicules/{id}`
    - **Response**: `204 No Content`

#### Analytics

- **GET** `/api/vehicules/analytics`
- **Response**:
   ```json
   {
       "averageKilometers": {
           "BERLINE": 15000,
           "VAN": 20000,
           "MINIBUS": 30000
       },
       "utilizationRate": {
           "BERLINE": 80,
           "VAN": 60,
           "MINIBUS": 70
       },
       "fleetStatus": {
           "DISPONIBLE": 15,
           "EN_COURSE": 8,
           "INDISPONIBLE": 3
       }
   }

### Testing

Run the tests using Maven:

    mvn test

### API Documentation

Swagger API documentation is available at:

    http://localhost:8080/swagger-ui.html

### Database Configuration

#### Profiles
- **Dev**: H2 in-memory database
- **QA**: MySQL
- **Prod**: PostgreSQL

Liquibase is used for database migrations with scripts stored in `src/main/resources/db/changelog/`.

### Contribution Guide

1. **Fork the Repository**

2. **Create a Branch**
   ````
     git checkout -b feature/your-feature

3. **Commit Changes**
    ````
    git commit -m "Add your feature"

4. **Push to Branch**
    ````
    git push origin feature/your-feature

5. **Submit a Pull Request**


## Contact
For questions or feedback, contact the project team:

- **Name**: Douae sb
- **GitHub**: [Douaesb](https://github.com/Douaesb)

---
- **Name**: Lamiaa Termoussi
- **GitHub**: [Lamiaa Termoussi](https://github.com/TERMOUSSI-LAMIAA)

---

- **Name**: Hafsa ELMOATASSIM BILLAH
- **GitHub**: [Hafsa ELMOATASSIM BILLAH](https://github.com/HAFSA159 )

