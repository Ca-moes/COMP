#!/bin/bash

~/javacc/bin/jjtree logo_tree.jjt 
cd src 
~/javacc/bin/javacc logo_tree.jj 
rm -f logo_tree.jj
