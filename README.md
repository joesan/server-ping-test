server-ping-test
================

If a server exposes a WebSocket endpoint, we can use this client to test if the WebSocket endpoint is running healthy

This client works as follows:

1. A batch file triggers a call to the ServerHealthTest.scala which calls the WebSocket endpoint on the server
2. If the server responds with a proper message, the program terminates appropriately. If not, the program terminates with a value -1
3. The batch file checks for this value and if it is -1, you can perform some specific tasks (For example., restart the server)

Here is how you use this client:

1. In the ServerHealthTest.scala, add the message that you want to send to the server in the data variable
2. In the healthtest.bat located under executables, modify the server details (host port an dWebSocketEndpoint)
  For example., to connect to a server running on hostname XXXXXXXX, port 9000 and a WebSocket Endpoint getData, the
  entry in the batch file would look like:
  java -cp wcs-all-1.3.jar;scala-library.jar;health-test-1.0-SNAPSHOT.jar org.q31c.healthtest.ServerHealthTest XXXXXXXX 9000 getData
3. Modify what you want to do if the test fails (i.e., if the ServerHealthTest.scala returns anything other than 0). You can do this by adding additional calls to any batch file that you want in the healthtest.bat, inside the if condition.
