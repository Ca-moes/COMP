#!/bin/bash

rm -f *.class
~/javacc/bin/javacc logo_tokenizer.jj
/usr/local/java15/bin/javac *.java
/usr/local/java15/bin/java LogoTokenManager bad_token.logo
