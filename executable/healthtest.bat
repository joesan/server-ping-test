@echo off
java -cp wcs-all-1.3.jar;scala-library.jar;health-test-1.0-SNAPSHOT.jar com.egc.healthtest.ServerHealthTest localhost 9000 getData
set exitCode=%ERRORLEVEL%
if %exitCode%==0 echo "Server healthy! Not restarting"
if not %exitCode%==0 (
  call ../stop
  call ../start
)