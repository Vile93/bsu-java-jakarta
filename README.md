# Fullstack Todo Application

This project is a fullstack application that utilizes **Jakarta** for the backend and **React** for the frontend. Users can manage their tasks (todos) by creating, editing, and deleting tasks, attaching images, and inviting others to view their tasks.

## Technologies Used

### Backend
- **Jakarta**: Framework for building the server-side application.
- **Hibernate**: ORM for database interaction.
- **MariaDB**: Relational database management system.
- **Log4j2**: Logging framework for tracking application behavior.
- **Email Confirmation**: Functionality for user email verification.

### Frontend
- **React**: JavaScript library for building user interfaces.
- **react-router-dom**: Routing library for handling navigation.
- **Vite**: Build tool and development server.
- **RSuite**: UI library for building responsive user interfaces.
- **Tailwind CSS**: Utility-first CSS framework for styling.

## Features
- Create, edit, and delete tasks.
- Attach images to tasks.
- Invite others to view your tasks.
- Email confirmation for user registration.

## Getting Started

To run the project locally, follow these steps:

1. **Clone the repository:**
   ```bash
   https://github.com/Vile93/bsu-java-jakarta.git
   cd bsu-java-jakarta
   ```
2. **Create a `.env` file** in the root directory of the project.

3. **Add the following variables to the `.env` file:**
   ```plaintext
   MAIL_USERNAME=<your-email-address>
   MAIL_PASSWORD=<your-email-password>
   MAIL_HOST=<your-email-host>
   ```
   **Example:**
   ```plaintext
   MAIL_USERNAME=example@gmail.com
   MAIL_PASSWORD=password
   MAIL_HOST=smtp.gmail.com
   ```

4. **Open a terminal and run the following command:**
   ```bash
   docker compose up
   ```

5. **Access the application at:**
   [http://localhost:8080/app](http://localhost:8080/app)
