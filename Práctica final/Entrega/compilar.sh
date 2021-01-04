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


# Ejecutar el compilador PLC con los argumentos:
# $1: Fichero .pl a compilar, que genera código intermedio
# $2: Fichero .ctd donde guardar el código generado
java PLC $1 $2

# Mostrar el contenido de los ficheros
echo "---------------------------- .pl --"
echo "" ; cat $1 ; echo ""
echo "--------------------------- .ctd --"
echo "" ; cat $2 ; echo ""

# Ejecutar el programa "ctd" con el argumento:
# $2: Fichero .ctd que simula un código ensamblador
echo "-------------------------- final --"
echo "" ; ./ctd $2 ; echo ""
echo "-----------------------------------"

