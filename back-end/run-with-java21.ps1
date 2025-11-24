# Set Java 21 as the active JDK for Maven
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-21.0.2.13-hotspot"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"

Write-Host "Java version configured:" -ForegroundColor Green
java -version

Write-Host "`nRunning Maven with Java 21..." -ForegroundColor Green
mvn clean spring-boot:run
