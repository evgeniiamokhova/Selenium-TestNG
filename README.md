Automation test cases for kdvonline.ru site

Created by using:
Maven
Java
Selenium WebDriver
TestNG
Allure

Steps for running tests:
1) Clone git repository to local machine
2) Open myTest project via IDE
3) Enter command in console for running tests:
mvn clean test -Dsurefire.suiteXmlFiles=src/test/java/testng.xml
4) See repots after building in file: surefire-reports/junitreports/TEST-ChromeDriverTest.xml

