PersistentDataStore
===================

Java Socket I/0 Server and Client

=====

This was created using the eclipse IDE.
It implements your basic CRUD operations (well, not update) and also has a "dir" or "ls" or "index" feature.

You must run the Server first by executing Server.class
You can run the JUnit Test, each test individually, or use your own client to wite in.

The i/o schema is:

####Read Operation
>Returns the contents of a named data as N bytes of data.  
#####Request Message
'''
read\n
<name>\n
'''
#####Response Message
'''
<responseCode>\n
<size in bytes>\n
<N bytes of data>
'''
Read Response Codes
ok\n if the file is found else a message describing the problem and no data.
Write Operation
Persists the binary data provided in the message. 
Request Message
write\n
<name>\n
<size in bytes>\n
<N Bytes of data>
Response Message
<response code>\n
Write Response Messages
ok\n if the data was written else a message describing the problem.
Delete Operation
Remove the data with the given name. 
Request Message
delete\n
<name>\n 
Response Message
<response code>\n
Delete Response Codes
ok\n if the data was deleted else a message describing the problem.
Directory Operation
List the names of the files currently managed by the service. 
Request Message
directory\n
Response Message
ok\n
<number of file names>\n
<name1>\n
<name2>\n
etc. 
