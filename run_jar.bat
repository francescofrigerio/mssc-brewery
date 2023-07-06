REM Add Maven Plugin to POM.XML. ...
REM Build Spring Boot Project with Maven maven package. ...
REM Run Spring Boot app using Maven: mvn spring-boot:run.
REM [optional] Run Spring Boot app with java -jar command java -jar 
REM target/mywebserviceapp-0.0.1-SNAPSHOT.jar.

SET JAVA_8_BIN="C:\Program Files\Java\jdk1.8.0_231\bin"
SET JAVA_11_BIN="C:\Program Files\Java\jdk-11.0.12\bin"
REM CLASSPATH=D:\MyWork\Progetti\SourceSpringBootJetBrains\Bennet\spring5REM -convert-html2pdf
REM dir %CLASSPATH%
REM pause
REM SET DIRNAME="C:\\friz\\templates\\"
REM SET FILENAME=report_incassi

%JAVA_11_BIN%\java -jar target\mssc-brewery-0.0.1-SNAPSHOT.jar 1>run_jar.log 2>&1
pause