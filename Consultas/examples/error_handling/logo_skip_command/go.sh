#!/bin/bash

rm -f *.java *.class
~/javacc/bin/javacc logo_parser.jj
/usr/local/java15/bin/javac *.java
/usr/local/java15/bin/java Logo bad_data.logo
