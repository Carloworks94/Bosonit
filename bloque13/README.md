# Guía Ejercicio Bloque 13 - SonarQube
Para la ejecución de este programa es necesario realizar estos pasos previos:

* Descargar e instalar [SonarQube], se puede hacer de 2 maneras: 
	- A través de un ZIP (https://www.sonarsource.com/products/sonarqube/downloads/)

	- A través de una Docker Image.
	
 	En nuestro caso hemos optado por usar el ZIP, ya que el Docker podría ocasionar problemas con Sonar ya que tenemos nuestro proyecto en local.

* Una vez instalado, ejecutamos Sonar y ya podremos acceder a nuestro puerto http://localhost:9000/

* Necesitamos crear un nuevo proyecto. Al hacerlo, Sonar nos proporcionará unas credenciales para vincular nuestro proyecto local.

* Seguidamente, es necesario meter en nuestro pom.xml el plugin de jacoco para que se nos cree el archivo jacoco.xml (por defecto en /target/site) quien proporcionará a Sonar la información de la cobertura que cubrimos en nuestro proyecto.
	
* Por último, necesitaríamos ejecutar Sonar a través de Maven con las credenciales que obtuvimos anteriormente. Lo ejecutaríamos de la siguiente manera:
	- mvn clean verify sonar:sonar -Dsonar.projectKey="projectKey" -Dsonar.projectName="projectName" -Dsonar.host.url=http://localhost:9000 -Dsonar.token="token"
 


