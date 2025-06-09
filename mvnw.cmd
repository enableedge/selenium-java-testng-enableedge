@echo off

:: This script is a wrapper for Maven that ensures the correct version of Maven is used.
:: It will download Maven if it's not already present.

:: Check if Maven wrapper exists
if not exist mvnw.cmd (
    echo Downloading Maven wrapper...
    powershell -Command "Invoke-WebRequest -Uri 'https://raw.githubusercontent.com/takari/maven-wrapper/master/maven-wrapper.bat' -OutFile 'mvnw.cmd'"
)

:: Execute Maven with the specified arguments
call mvnw.cmd %*
