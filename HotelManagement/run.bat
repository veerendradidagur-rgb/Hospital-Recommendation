@echo off
echo Compiling Hotel Management System...
javac -cp "lib\mysql-connector-j-9.6.0.jar" -d bin src\*.java src\*\*.java src\*\*\*.java

echo Starting Hotel Management System...
java -cp "bin;lib\mysql-connector-j-9.6.0.jar" Main

pause
