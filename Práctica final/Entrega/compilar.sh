#!/bin/bash


# Limpiar la consola
clear


# Compilar el fichero CUP
echo "================================================================= [CUP] =="
cup PLC.cup

# Compilar el fichero FLEX
echo "=============================================================== [JFLEX] =="
jflex PLC.flex

# Compilar el fichero JAVA
echo "=============================================================== [JAVAs] =="
javac *.java

echo "=========================================================================="
echo ""

