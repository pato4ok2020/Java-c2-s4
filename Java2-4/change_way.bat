@echo off
setlocal

copy /Y target\book-app.war %CATALINA_HOME%\webapps\

@REM http://localhost:8080/book-app/