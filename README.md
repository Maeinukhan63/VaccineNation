# Vaccine Booking System

A comprehensive vaccine booking system built with Spring Boot. This application allows users to book vaccine appointments, manage patient information, and track vaccination status.

## Features

- **Patient Management:** Add, update, and view patient information.
- **Appointment Booking:** Schedule and manage vaccine appointments.
- **Vaccination Tracking:** Monitor vaccination status of patients.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Spring Boot 3.x
- A compatible database (e.g., MySQL, PostgreSQL)

### Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/Maeinukhan63/VaccineNation.git
    cd VaccineNation
    ```

2. **Configure application properties:**

    Update `src/main/resources/application.properties` with your database configuration and other necessary settings.

3. **Build the project:**

    ```bash
    mvn clean install
    ```

4. **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

    Alternatively, you can run the JAR file created in the `target` directory:

    ```bash
    java -jar target/vaccine-booking-system-0.0.1-SNAPSHOT.jar
    ```

### Usage

- **Access the API:** The application runs on `http://localhost:8080` by default. You can interact with the API using tools like Postman or CURL.
- **Endpoints:**

    - **Patients:**
      - `GET /patients` - Get a list of all patients
      - `GET /patients/{id}` - Get patient details by ID
      - `POST /patients` - Add a new patient
      - `PUT /patients/{id}` - Update patient details
      - `DELETE /patients/{id}` - Delete a patient

    - **Appointments:**
      - `GET /appointments` - Get a list of all appointments
      - `GET /appointments/{id}` - Get appointment details by ID
      - `POST /appointments` - Schedule a new appointment
      - `PUT /appointments/{id}` - Update an appointment
      - `DELETE /appointments/{id}` - Cancel an appointment

### Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes and commit them (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a new Pull Request.


### Contact

For any inquiries, please contact:

- **Name:** Maeinu Khan
- **Email:** khanmaeinuddin19@gmail.com
- **GitHub:** https://github.com/Maeinukhan63

