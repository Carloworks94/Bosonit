# Guía Docker
Para completar este ejercicio ha sido necesario realizar una serie de pasos externos que no se ven
simplemente subiendo este proyecto. Por ello voy a enumerar todos los pasos que han sido necesarios de realizar:

* Primero, creamos el Dockerfile con la información que contiene para crear la imagen de nuestro proyecto.

* Segundo, modificamos el application.properties con la información de la nueva base de datos que vamos
  a utilizar. Además, modificaremos el pom.xml añadiendo la dependencia necesaria para utilizar PostgreSQL

* Tercero, ejecutamos el proyecto con Maven, para crear nuestro proyecto.jar

* Cuarto, creamos nuestra network en la terminal con el comando "docker network create mynetwork"

* Quinto, creamos la imagen de PostgreSQL a través del terminal con ciertos parámetros personalizados, el
  comando que nosotros hemos usado es "**docker run --network mynetwork --name postgres_test -e POSTGRES_USER=postgres -e
  POSTGRES_PASSWORD=contrasena -e POSTGRES_DB=test -p5432:5432 postgres**"
  (cabe destacar que estas variables de POSTGRES tienen que coincidir con las que hemos añadido en el application.properties)

* Sexto, utilizaremos el comando "**docker build -t spring .**" desde la terminal (ruta de la carpeta raiz del proyecto) para
  crear la imagen de nuestro proyecto.

* Séptimo y último, tendremos que ejecutar el comando "**docker run --network mynetwork --name programa_spring -p8080:8080 spring**" 
  para crear el contenedor de nuestro proyecto con la imagen de nuestro proyecto empaquetado y la imagen de la base de datos de PostgreSQL
  en la network especificada.


