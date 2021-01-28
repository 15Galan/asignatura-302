#!/bin/bash


# Limpiar la consola
clear


# Compilar los ficheros necesarios:
# PLXC.cup, PLXC.flex y PLXC.java.
./compilar.sh


# Ejecutar el compilador PLXC con los argumentos:
# $1: Fichero .PLXC a compilar, que genera código intermedio
# $2: Fichero .ctd donde guardar el código generado
java PLXC $1 $2

# Mostrar el contenido de los ficheros
echo "-------------------------- .plxc --"
echo "" ; cat $1 ; echo ""
echo "--------------------------- .ctd --"
echo "" ; cat $2 ; echo ""

# Ejecutar el programa "ctd" con el argumento:
# $2: Fichero .ctd que simula un código ensamblador
echo "-------------------------- final --"
echo "" ; ./ctd $2 ; echo ""
echo "-----------------------------------"

