Selenium the-internet.herokuapp Sample project

Overview
This project is for me to improve my skills with Java/Selenium and to give a sample of my code.
This project uses JUnit 5 for testing.
Prerequisites

    Java 21 (or compatible version)
    Maven for dependency management and build
    ChromeDriver if using Selenium for UI testing
    Browser: Chrome (ensure compatibility with ChromeDriver version)

Setup

    Clone the repository:

git clone (https://github.com/seth-moffitt/SeleniumHeroku)

Install dependencies: Run the following Maven command to install all required dependencies.

    mvn clean install

    Configure ChromeDriver (if using Selenium):
        Download the appropriate ChromeDriver version.
        Add ChromeDriver to your system path or specify its location in the project.

Running Tests

To run all test cases, use the following Maven command:

mvn test

Running Specific Tests

You can run a specific test class or method:

mvn test -Dtest=ClassName
mvn test -Dtest=ClassName#methodName

Project Structure

src
├── main
│   └── java
│       └── org
│           └── seth
└── test
    └── java
        └── org
            └── seth
                ├── pages
                │   ├── FileUploadPage.java          # Page object for file upload
                │   ├── FormAuthPage.java            # Page object for form authentication
                │   └── MainPage.java                # Main page object
                └── tests
                    ├── BasicAuthTest.java           # Test for basic authentication
                    ├── FileUploadTest.java          # Test for file upload functionality
                    └── FormAuthenticationTest.java  # Test for form authentication functionality

Testing Frameworks and Tools

    JUnit 5: Used for writing and running test cases.
    Selenium WebDriver (if applicable): Used for UI tests.
    Maven Surefire Plugin: Manages test execution in Maven.

Adding a New Test

    Create a new test class under src/test/java/org/seth.
    Annotate test methods with @Test.
    Run mvn test to execute all tests or your specific test class.
