#!/bin/bash

mv Runner.java Runner.jav
rm -rf visitor/ syntaxtree/ *.java *.class *.scm jtb.out.jj
mv Runner.jav Runner.java
/usr/local/java15/bin/java -jar /usr/local/jtb/jtb132.jar -scheme -printer phone.jj 
~/javacc/bin/javacc -JDK_VERSION=1.5 jtb.out.jj  
/usr/local/java15/bin/javac *.java 
/usr/local/java15/bin/javac -cp . syntaxtree/*.java 
/usr/local/java15/bin/javac -cp . visitor/*.java
/usr/local/java15/bin/java -cp . Runner "432-789-9876 123-456-7890 888-555-1212"
