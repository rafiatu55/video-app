# Video Platform built with <a href=" https://spring.io/projects/spring-boot /" target="_blank">SpringBoot Rest FrameWork</a> and <a href=" https://www.thymeleaf.org/ " target="_blank"> Thymeleaf </a>



## Overview
The Video Platform is a bespoke web application designed for Paul Leonard, a video creator, to address his branding needs and offer a dedicated platform for his videos. The system supports user registration with account verification, password reset, and email notifications. Users can navigate through video pages, share links to videos, and interact with videos using common control buttons. The platform also features a robust admin interface for video uploads and management, ensuring a seamless experience for both the creator and viewers.


## Project Goal
The goal of the Video Platform project is to provide Paul Leonard with a bespoke video hosting platform tailored to his branding needs. Unlike generic video hosting services, this platform ensures exclusive control over branding and presentation. The platform enables users to seamlessly register, navigate, and share videos while offering Paul a robust admin interface for managing video content. By focusing on these specific requirements, the platform aims to deliver a unique, branded video experience that meets Paul's business objectives and enhances user engagement.


## Major Features 🔑
### User Features
1.	*Signup and Login*: Users can sign up and log in using an email and password. Account verification and a reset password feature for recovering lost passwords are included.
2.	*Video Navigation*: Users can navigate through video pages using next and previous buttons. These buttons are hidden if no further videos are available in the respective direction.
3.	*Video Sharing*: Users can share links to specific video pages.
### Admin Features
1.	*Admin Logo*: Admins can log in with an email and password.
2.	*Video Upload*: Admins can upload videos with a title and description to the platform.
3.	*Video Management*: Admins can manage uploaded videos, including editing titles and descriptions, and deleting videos if necessary.
### Video Page Features
1.	*Single Video Display*: Each video page presents only one video.
2.	*Navigation Button*: Next and previous buttons allow users to navigate through videos. These buttons are hidden if no further videos are available.
3.	*Control Buttons*: Common control buttons (play, pause, volume, etc.) are available for users to interact with the video.
4.	*Branding*: A prominently displayed business logo at the top of the video page.
5.	*Share Button*: Users can share links to specific video pages via a share button.

## System Credentials - Testing ⚙️

*Admin*

-   Email – rafiatuibrahim141@gmail.com
-   Password – admin123





*Users*

-   Email – gabriel.sakyi@stu.ucc.edu.gh
-   Password - user123

---

-   Email – irafiatu44@gmail.com
-   Password - user@123

---

-   Email - mawulegabriel@gmail.com
-   Password - `user@456



### Activities involved in the project:
1.	Developed Unit Tests: Created comprehensive unit tests to cover all edge cases for user authentication, video navigation, and sharing functionalities.
2.	Database Modeling: Designed and configured database models for users, videos, and admin entities to ensure efficient data management and retrieval.
3.	API Implementation: Developed various API endpoints, including user registration, login, password reset, video upload, and video navigation, with all necessary methods for each endpoint.
4.	Admin and User Panel Configuration: Configured the admin panel for video management, including upload, edit, and delete functionalities, and the user panel for seamless video navigation and interaction.
5.	User Interface Design: Designed and implemented a user-friendly interface for both users and admins, ensuring an intuitive and engaging experience.
6.	Security Enhancements: Implemented security measures such as email verification, password hashing, and secure session management to protect user data and platform integrity.
7.	Branding Integration: Incorporated the business logo and branding elements throughout the platform to maintain a consistent and professional appearance.
8.	Video Playback Features: Integrated video control buttons and ensured smooth playback functionality, including play, pause, volume control, and full-screen mode.
9.	Navigation Logic: Developed logic for the next and previous buttons to handle video navigation seamlessly, including edge cases where no further videos are available.
10.	Link Sharing Capability: Enabled link sharing for videos, allowing users to easily share video pages with others.

### API Endpoints
* User Endpoint:
POST /api/users
```
{
    "email": "user@example.com",
    "password": "password"
}
```

* *Verify your account by email*:
GET /api/users/verify/confirm?token={token}

* *Initiate password reset*:
POST /auth/forgotpassword
```
{
    "email": "user@example.com"
}
```

* *Confirm password reset*:
POST /auth/password/resets/confirms
```
{
    "token": "<reset_token>",
    "newPassword": "new-password",
    "confirmPassword": "confirm-password"
}
```

### Video Endpoints
* *Upload a new video (Admin only)*:
POST /api/videos
```
{
    "title": "Video Title",
    "description": "Video Description",
    "file": "<file_binary>"
}
```

* *Get all videos*:
GET /api/videos

* *Get a single video by ID*:
GET /api/videos/{id}

* *Update video details (Admin only)*:
PUT /api/videos/{id}
```
{
    "title": "Updated Title",
    "description": "Updated Description"
}
```

* *Delete a video (Admin only)*:
DELETE /api/videos/{id}

### Navigation Endpoints
* *Get next video*:
GET /api/videos/{id}/next
* *Get previous video*:
GET /api/videos/{id}/previous

### Share Endpoints
* *Share video link*:
POST /api/videos/{id}/share
```
{
    "email": "recipient@example.com"
}
```

### Admin Endpoints
* *Login as admin*:
POST /auth/admin/login
```
{
    "email": "admin@example.com",
    "password": "admin-password"
}
```

### Unit Tests
The application includes unit tests to ensure all edge cases are handled properly. Here are the cases tested in the UserServiceTest and VideoServiceTest classes:
### UserServiceTest
* *createUser*: Test user creation with valid inputs.
* *loginUser*: Test user login with correct credentials.
* *loginUser_withInvalidPassword*: Test user login with incorrect password.
* *confirmVerification*: Test account verification with a valid token.
* *confirmVerification_withExpiredToken*: Test account verification with an expired token.
* *initiatePasswordReset*: Test initiating a password reset with a valid email.
* *confirmPasswordReset*: Test confirming a password reset with a valid token and matching new passwords.
* *confirmPasswordReset_withInvalidToken*: Test confirming a password reset with an invalid token.

### VideoServiceTest
* *uploadVideo*: Test video upload with valid inputs.
* *getAllVideos*: Test retrieving all videos from the database.
* *getVideoById*: Test retrieving a single video by its ID.
* *getVideoById_withInvalidId*: Test retrieving a video with an invalid ID.
* *updateVideo*: Test updating video details with valid inputs.
* *updateVideo_withInvalidId*: Test updating video details with an invalid ID.
* *deleteVideo*: Test deleting a video by its ID.
* *deleteVideo_withInvalidId*: Test deleting a video with an invalid ID.
* *getNextVideo*: Test retrieving the next video in the sequence.
* *getPreviousVideo*: Test retrieving the previous video in the sequence.
* *shareVideoLink*: Test sharing a video link via email with valid inputs.
* *shareVideoLink_withInvalidEmail*: Test sharing a video link with an invalid email address.

### Thymeleaf Frontend

The application uses Thymeleaf templates for the frontend, providing a dynamic and interactive user interface. The following templates are included:


## auth/home -  index.html:
<h3> Homepage with links to Login and Signup pages. </h3>
<hr/>
<a href=" target="_blank" title="Homepage">
<img src="" width="100%" height="400px" />
</a>
<hr/>


## auth/admin - adminPanel.html:
<h3> Admin page. </h3>
<hr/>
<a href="" target="_blank" title="UserPanel">
<img src="" width="100%" height="400px" />
</a>
<hr/>


These templates are styled using Bootstrap for a modern and responsive design.


### Setup

1. *Clone the repository:*
   bash
   git clone https://github.com/rafiatu55/video-app.git
   cd video-app
   


2. *Configure the database:*
   - Ensure PostgreSQL is installed and running.
   - Create a database named postgres.
   - Update the application.properties file with your PostgreSQL credentials.


3. *Update Email Configuration:*
     * spring.mail.host=smtp.gmail.com
     * spring.mail.port=587
     * spring.mail.username=your-email@gmail.com
     * spring.mail.password=your-email-password
     * spring.mail.properties.mail.smtp.auth=true
     * spring.mail.properties.mail.smtp.starttls.enable=true
     * spring.mail.properties.mail.smtp.starttls.required=true
     * spring.mail.properties.mail.smtp.ssl.trust=smtp.gmail.com
     


4. *Build the application:*
```
   bash
   mvn clean install
```
   

5. *Run the application:*
```
   bash
   mvn spring-boot:run
```

   
## Getting Started

### Prerequisites
- JDK 11 or higher
- Maven 3.6.3 or higher
- PostgreSQL 12 or higher


### Using Postman
1. Import the provided Postman collection to access and test all endpoints.
2. Ensure the server is running.
3. Execute requests and verify responses.


### Troubleshooting
- *Mail Server Connection Issues*: Ensure your email configuration is correct and the email server is accessible.
- *Database Connection Issues*: Ensure PostgreSQL is running and the credentials in application.properties are correct..



### ER Diagram
The Entity-Relationship (ER) diagram of the database is included in the project repository under the folder name ER_Diagram_Database.



## Get Involved

I welcome contributions and participation from the community to help make this backend API even better! Whether you're looking to fix bugs, add new features, or improve documentation, your help is greatly appreciated. Here's how you can get involved:


### Reporting Issues 🚩

If you encounter any bugs or issues, please report them using the <a href="https://github.com/youngrafiatu55h/video-app/issues"> Issues</a> section of my GitHub repository. When reporting issues, please include:

-   A clear and descriptive title.
-   A detailed description of the problem, including steps to reproduce it.
-   Any relevant logs or error messages.
    Your environment details (e.g., SprinBoot version, database, etc.).
    

### Contributing Code 💁🏼

I love receiving pull requests from the community! If you have an improvement or a new feature you'd like to add, please feel free to do so 👍


## Contact
For any questions or feedback, please contact me on rafiatu.ibrahim@ucc.stu.edu.gh