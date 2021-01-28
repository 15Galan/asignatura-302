#!/bin/bash


# Limpiar la consola
clear


# Compilar el fichero CUP
echo "================================================================= [CUP] =="
cup PLXC.cup

# Compilar el fichero FLEX
echo "=============================================================== [JFLEX] =="
jflex PLXC.flex

# Compilar el fichero JAVA
echo "=============================================================== [JAVAs] =="
javac *.java

echo "=========================================================================="
echo ""

