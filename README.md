
# Java Directory Manager

This project is a Java implemented Directory Manager simulator program on a terminal. Code written in Java version 11 LTS.  

To run download the project into a folder on your computer. Then naviagate to the folder. To run, enter command: 
```java
java .\DirectoryManager.java
```

A user is then presented with a menu of options to run the program.

![Screenshot of initial program options menu.](/assets/images/menu.PNG)

The program contains these important methods/functions:
1. listDirectories : List all the directories -  This is a void method
2. createDirectory: Creates a directory - This is a void method that takes a String path as a parameter
3. moveDirectory: Moves a directory from a source to designation folder - This is a void method that takes source and designation String path parameters
4. deleteDirectory: Deletes a directory - This is a void method that takes a String path as a parameter

## Note: when entering directories path, be sure to enter with "/" such as "fruits/apples/fiji" . Root directories do not need "/" entered. Just the directory name. 
