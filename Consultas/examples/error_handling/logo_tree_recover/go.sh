#!/bin/bash

rm -f *.java *.class *.jj
~/javacc/bin/jjtree logo_tree.jjt
~/javacc/bin/javacc logo_tree.jj
/usr/local/java15/bin/javac *.java
/usr/local/java15/bin/java Logo bad_data.logo
