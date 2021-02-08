#!/bin/bash


# Limpiar la consola
clear


# Compilar los ficheros necesarios:
# JPLC.cup, JPLC.flex y JPLC.java.
./compilar.sh


# Ejecutar el compilador JPLC con los argumentos:
# $1: Fichero .jpl a compilar, que genera código intermedio
# $2: Fichero .j donde guardar el código generado
java JPLC $1 $2

# Mostrar el contenido de los ficheros
echo "-------------------------- .jplc --"
echo "" ; cat $1 ; echo ""
echo "----------------------------- .j --"
echo "" ; cat $2 ; echo ""

