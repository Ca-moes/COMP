#!/bin/bash

jjtree postfix_tree.jjt
javacc postfix_tree.jj
javac *.java

java Postfix "${1}"
