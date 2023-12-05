# interactive-resume-java-backend

The Interactive Resume Backend project is a Java Spring Boot application that allows users to generate, share, and download ATS (Applicant Tracking System) compliant resumes. Additionally, the system helps users manage their job search activities through a table containing information such as date/time of contact with a company, the name of the company, contact person, notification dates, and more.

## Features

- **Resume Generation**: Create and customize resumes that are compatible with Applicant Tracking Systems.

- **Job Search Management**: Keep track of job search activities using a flexible table structure.

- **Notification System**: Receive in-app notifications and email reminders for upcoming tasks and interviews.

- **User Authentication and Authorization**: Securely manage user access and permissions.

- **Integration with PostgreSQL**: Utilizes PostgreSQL as the database for data persistence.

- **Maven Build**: Build and manage project dependencies using Maven.

## Prerequisites

- Java 21 or later
- PostgreSQL
- Maven

## Getting Started

1. Clone the repository:

    ```bash
    git clone https://github.com/dmelia/interactive-resume-java-backend.git
    ```

2. Configure PostgreSQL:

    - Create a new database and update the application.properties file with the database configuration.

3. Build and run the application:

    ```bash
    cd interactive-resume-java-backend
    mvn clean install
    java -jar target/interactive-resume-java-backend.jar
    ```

4. Access the application:

    Open your browser and navigate to http://localhost:8080.

## Configuration

- Update the application.properties file to configure database connection and other application settings.

## Usage

1. **Log in**:
    - Create an account from the top menu of the application.
    - Fill in the required information.
    - Log in to the application.

2. **Resume Generation**:
    - Navigate to the "Resume Generator" section.
    - Fill in the required information and customize your resume.
    - Download the generated ATS-compliant resume.

3. **Job Search Management**:
    - Access the "Job Search Dashboard" to view and manage job search activities.
    - Add new entries to the table, including date/time of contact, company details, and notifications.

4. **Notification System**:
    - Receive in-app notifications for upcoming tasks.
    - Set email preferences to receive reminders via email.

## Contributing

Contributions are welcome! See the [Contributing Guidelines](CONTRIBUTING.md) for more details.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Acknowledgments

- I'd like to thank my legs that have always taken me to where I needed to go, my index fingers for pointing me in the right direction, my hands that I could always count on, my eyes that allowed me to see problems clearly, and finally, other people who brought upon me those problems.
