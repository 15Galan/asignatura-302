#!/bin/bash


# Limpiar la consola
clear


# Compilar el fichero CUP
echo "================================================================= [CUP] =="
cup JPLC.cup

# Compilar el fichero FLEX
echo "=============================================================== [JFLEX] =="
jflex JPLC.flex

# Compilar el fichero JAVA
echo "=============================================================== [JAVAs] =="
javac JPLC.java

echo "=========================================================================="
echo ""

