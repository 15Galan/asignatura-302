# Manual de Git casero


Este documento es una guía básica y simple escrita por mí y para mis amigos, con información acerca de Git y su uso entre varias personas, en concreto para esta asignatura (pequeño proyecto personal).

Lo más interesante creo que sería la sección de [trabajo en equipo](#trabajar-en-equipo-en-este-respositorio), así que dejo un enlace ahí para consultarla rápidamente.

Descargas:
* Última versión de [Git](https://git-scm.com/downloads).
* Recomiendo la GUI que ofrece [GitKraken](https://www.gitkraken.com/) para principiantes que quieran «ver» el estado del repositorio.

**Notas:**

* Todos los comandos que aparecen durante este documento están descritos en la sección de [comandos](#lista-de-comandos) al final del mismo.
* Los argumentos en los comandos vienen indicados con `<>`, no es que deban escribirse, literalmente, como `<argumento>`.



## Introducción

### ¿Qué es todo esto?

Para empezar, hay que aclarar que Git y GitHub no son lo mismo, aunque por supuesto, están conectados entre sí.

| Git | GitHub |
| --- | ------ |
| Software de control de versiones desarrollado para aumentar la eficiencia y la productividad entre usuarios que trabajen sobre un mismo proyecto. | Sitio web -y servicio en la nube- en el que almacenar, administrar y llevar un registro y control de cualquier cambio sobre un proyecto. |
| *Local y privado* | *Online y público* |

Al mismo tiempo, hay que diderenciar entre *Git GUI* y *Git Bash*, las formas de usar Git una vez se instala:
* *Git GUI* proporciona una interfaz gráfica con la que interactuar.
* *Git Bash* ofrece una consola de comandos propia.

***Este documento está orientado al uso de Git Bash.***


### ¿Cómo funciona Git?

En palabras simples, Git realiza un seguimiento de los archivos de forma que permite conservar varios estados de un mismo fichero, que reflejan cómo era en diferentes puntos del tiempo.

No obstante, Git se organiza internamente según esta estructura:
![Funcionamiento](https://pixelpioneers.co/imager/uploads/photos/271/git-local-remote_25c2603fcd80fc2aa4a784bfc41e1600.png)

**Áreas:**

* **Área de Trabajo (*Working Area*):** la ubicación de los archivos en el ordenador (el escritorio, una carpeta...).
* **Área de Preparación (*Staging Area*):** una zona de la memoria en la que se almacenan los archivos en un estado concreto -versión- antes de ser confirmados.
* **Repositorio Local (*Local Repo*):** una zona de la memoria con archivos confirmados; básicamente, es una versión del repositorio pero que aún no ha sido publicada en GitHub.
* **Repositorio Remoto (*Remote Repo*):** el repositorio que aparece en GitHub, correspondiente a la última versión que se haya publicado del Repositorio Local.

**Comandos:**

Los comandos de la imagen vienen detallados en la sección de [comandos básicos](#básicos) del documento.



## Inicialización

### Sincronizar tu usuario

Una vez instalado, abre *Git Bash* y ejecuta los siguientes comandos:

1. `git config --global user.name "<nombre>"` para establecer tu alias en el sistema.
   * No tiene por qué ser tu nombre de usuario de GitHub.

2. `git config --global user.email <email>` para sincronizar tu cuenta de GitHub con el software local.
   * Esto agilizará el proceso de identificación a la hora de publicar cambios.
   * Debe ser el correo asociado a la cuenta de GitHub que quieras sincronizar.

Esto es lo básico para poder usar Git y GitHub de forma síncrona y, opcionalmente, con varios participantes.


### Sincronizar un repositorio

**Si no lo tienes:**

1. Abre *Git Bash* en la ubicación del *Working Area* donde quieras guardar el repositorio (con click derecho).
2. Usa `git clone <URL este repositorio>` para descargar este repositorio y tener una copia local.
3. Desplázate a la carpeta del repositorio con `cd <carpeta>` y ejecuta el resto de comandos ahí.

**Si ya lo tienes:**

1. Abre *Git Bash* con click derecho dentro del repositorio.
2. Usa `git pull` para actualizar el Repositorio Local si crees que está desactualizado.
   * Deberías ejecutarlo siempre antes de empezar a trabajar (quizás alguien haya hecho algo importante).



## Trabajar en equipo en este respositorio

### Consideraciones

Basadas en la asignatura en específico.

**Objetivos:**

* Reservar la rama *master* para publicar todo lo «definitivo» o corregido: entregas, apuntes, ejercicios...
* Trabajar en ramas individuales para evitar conflictos de sincronización y fomentar la concurrencia.

**Acciones:**

* Aportar contenido de una rama individual a *master* en caso de que sea útil o necesario, y viceversa.
* Aportar contenido de la rama de un participante a la rama de otro y viceversa.


### Hacer un cambio

1. Muévete a tu rama o crea una rama temporal para evitar conflictos al fusionar los cambios con otra.
   * Una «rama temporal» no es más que una rama que creas para una modificación en concreto y luego la borras.

2. Realiza tus cambios del repositorio en esa rama y añádelos al *Staging Area* con `git add <archivos>`.
   * Puedes usar `git status` antes y después de `git add` para comprobar tus acciones.


### Guardar un cambio

Usa `git commit` para confirmar y guardar los cambios localmente.

Esto abrirá un editor en la consola para completar el commit (título y cuerpo). Suponiendo que el editor es Vim (lo normal):

1. Presiona `I` para habilitar la escritura.
   * El título del commit se detectará en la primera línea (amarillo).
   * La segunda línea debe estar vacía (si se escribe en ella aparecerá en rojo).
   * La tercera línea y las demás se detectarán como el cuerpo del commit (gris).
2. Añade el título (como mínimo) y el cuerpo que quieras.
3. Presiona `ESC` para dejar de escribir y teclea el comando `:wq` (que aparecerá en la parte baja del editor).
4. Presiona `ENTER` para salir y guardar los cambios.

Un commit puede abortarse saliendo del editor con su título y cuerpo vacíos.


### Fusionar cambios (ramas)

1. Cambia a la rama que quieras con `git checkout <rama destino>` para poder traer los cambios desde tu rama (origen).
2. Ejecuta `git merge <rama origen>` para traer los cambios de tu rama a la rama en la que estás y no al revés (podría producir un error).
3. No olvides volver a tu rama de nuevo o podrías meterte otro día y modificar archivos en la rama equivocada sin darte cuenta.
   * Si era una rama temporal, elimínala con `git branch -d <rama>`. No pasa nada si no se borra, pero es recomendale.


### Publicar un cambio

Simplemente haz `git push`, esto hará que el Repositorio Remoto se actualice para todos los miembros.
* Si alguien intenta publicar cambios recibirá un aviso indicando que su Repositorio Local está desactualizado y que haga `git pull`.



## Lista de comandos

### Básicos

```
git status
```
Muestra el estado de los archivos del repositorio:
* En rojo, los archivos modificados que no han sido añadidos al *Staging Area*.
* En verde, los archivos modificados que sí fueron añadidos al *Staging Area*.
* Los archivos que no han sido modificados no aparecen.

Además, delante del nombre de cada archivo se indica si ha sido renombrado, modificado o eliminado.
____

```
git add <archivo>
```
Añade al *Staging Area* el archivo indicado, también pueden añadirse varios a la vez separándolos con espacios o indicando una ruta.

* `git add -A` añade todos los archivos nuevos, modificados y eliminados.
* `git add *` añade todos los archivos nuevos y modificados, excepto los que cuyo nombre empiecen por «.».
* `git add -u` añade todos los archivos modificados y eliminados.
____

```
git commit
```
Crea un commit para guardar las modificaciones almacenadas en el *Staging Area*.

Los commits deben tener un título, pero además pueden tener un cuerpo; ambos se usan para diferenciar distintos commits y describir brevemente los cambios.

* `git commit -m "<título>"` genera un commit con el título indicado, pero sin cuerpo.
____


```
git push
```
Publica en el Repositorio Remoto los commits realizados en el Repositorio Local y que no estén publicados.

Tanto los commits como la última versión (último commit) del Repositorio Remoto serán visibles en GitHub.
____

```
git fetch
```
Descarga en el Repositorio Local los archivos del Repositorio Remoto (no produce cambios locales, no como `git pull`).
____

```
git pull
```
Actualiza el Repositorio Local con los archivos del Repositorio Remoto (produce cambios locales, no como `git fetch`).

Si hay modificaciones en el Repositorio Local se producirá un conflicto que puede arreglarse con un `merge`.

Si se quiere hacer un `pull` de otra rama, basta con moverse a esa rama y ejecutar el comando en ella.

***Personalmente prefiero hacer un `git pull` a un `git fetch` debido a lo indicado en la metodología de trabajo.***
____

```
git clone <URL repositorio>
```
Descarga una copia local y sincronizada del Repositorio Remoto alojado en la URL indicada.

Este comando se usa únicamente en el primer paso para descargar el repositorio con el que se quiere trabajar.

`git pull` actualiza un Repositorio Local existente, `git clone` descarga un repositorio para usarlo localmente.
____


### Intermedios

```
git chekcout <nombre>
```
Salta a la rama local o remota con el nombre indicado.

Si la rama es remota se actualizará localmente (como un `git pull`) y saltará a ella.
* `git checkout -b <rama>` crea una rama y salta a ella.
____

```
git branch
```
Muestra una lista de las ramas locales existentes.

Si una rama no aparece, lo más probable es que no hayas descargado el contenido de la misma.
* `git branch <nombre>` crea una rama local con el nombre indicado.
* `git branch -d <rama>` elimina la rama local indicada.
____

```
git merge <rama origen>
```
Combina los cambios de la rama indicada con la rama en la que te encuentres.
____

### Avanzados

```
git reset <archivo>
```
Saca del *Staging Area* el archivo indicado, también pueden sacarse varios a la vez separándolos con espacios o indicando una ruta.
* Funciona igual que `git add`, pero hace lo contrario.
____

### Otros

```
git log
```
Muestra el historial de commits en la consola. Se cierra como el editor de commits (`ESC` + `:wq`).
* `git log --oneline` muestra únicamente los títulos de forma más compacta.
____

```
git config --global alias.lg "log --oneline --decorate --all --graph"
git lg
```
ALIAS - Muestra el historial de commits de forma compacta y con una estructura que representa el flujo de trabajo (ramas).
____

```
git config --global alias.s "status -s -b"
git s
```
ALIAS - Muestra el estado del repositorio, compacto y ordenado por ramas.
____
