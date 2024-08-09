# Kata Números romanos

### Fase 1

Desarrollar un componente que permita convertir números enteros a romanos y viceversa según el siguiente esquema: 

* 1 ➔ I
* 2 ➔ II
* 3 ➔ III
* 4 ➔ IV
* 5 ➔ V
* 9 ➔ IX
* 21 ➔ XXI
* 50 ➔ L
* 100 ➔ C
* 500 ➔ D
* 1000 ➔ M


En ambos métodos de conversión, el componente debe validar si se ingresa un valor no permitido y responder con una excepción personalizada. 

**Plus Fase 1:** Aplicar TDD o al menos hacer Tests unitarios del componente probando al menos 2 border cases para cada método de conversión.


### Fase 2 

Exponer el método del componente que convierte valores numéricos arábigos a romanos en un endpoint (GET) 
Exponer el método del componente que convierte valores numéricos romanos a arábigos en un endpoint (GET)

**Plus Fase 2:** Aplicar TDD (Test de integración usando la suite de Spring). 


### Requerimientos/Restricciones

**Fase 1 y 2:** Usar Java 17 o superior. Maven o Gradle para la gestión de dependencias. 
Para los puntos plus de cada fase, en lo relacionado a la infraestructura de tests se pueden usar las siguientes herramientas JUnit5+Mockito o Spock y Spring Boot Testing. 
**Fase 2:** Usar Spring boot 3+.



# Kata Números Romanos - Desarrollado

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.3.2**
- **Spring Boot Test** para pruebas unitarias e integración
  - **JUnit 5** como framework de pruebas
  - **Mockito** para la creación de mocks y simulación de comportamientos
- **Maven** para la gestión de dependencias y construcción
- **Postman** para pruebas de la API REST

## Descripción del Proyecto

El proyecto "Kata Números Romanos" es una API REST que permite convertir números enteros a números romanos y viceversa. Expone estos servicios a través de endpoints REST y cuenta con una serie de pruebas para asegurar su correcto funcionamiento.

## Requisitos Previos

Se deben tener instalados los siguientes componentes en el sistema:

- **Java Development Kit (JDK) 17**: Necesario para compilar y ejecutar el proyecto. Puedes descargarlo desde [la página oficial de Oracle](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) o usar una distribución alternativa como OpenJDK.
- **Apache Maven**: Herramienta para la gestión de dependencias y construcción del proyecto. Puedes descargarlo desde [la página oficial de Maven](https://maven.apache.org/install.html).
- **IDE o Editor de Texto**: Recomendado para facilitar el desarrollo y ejecución del proyecto. Puedes usar IntelliJ IDEA, Eclipse, Visual Studio Code, entre otros.

## Construir el Proyecto

### 1. Clonar el Repositorio

Primero, clona el repositorio del proyecto desde GitHub:

```bash
git clone https://github.com/tu-usuario/tu-repositorio.git
```

### 2. Abrir el Proyecto en un IDE

Abre el proyecto clonado en tu IDE preferido. Si estás usando IntelliJ IDEA:

Abre IntelliJ IDEA.
Selecciona "Open" y navega hasta la carpeta del proyecto clonado.
Selecciona el archivo pom.xml y haz clic en "Open".

### 3. Configurar Dependencias

El proyecto utiliza Maven para la gestión de dependencias. Asegúrate de que Maven esté correctamente configurado en tu IDE:
En IntelliJ IDEA, Maven debería detectar automáticamente el archivo pom.xml y descargar las dependencias necesarias.

### 4. Construir el proyecto

Construye el proyecto utilizando Maven. Abre una terminal en la raíz del proyecto y ejecuta:

```
mvn clean install 
```
Esto compilará el código, ejecutará las pruebas y empaquetará el proyecto en un archivo JAR.


**Opción 1**

1. En consola, navega al directorio target donde se encuentra el archivo JAR empaquetado. Por ejemplo: 
**..\conversor-numeros-romanos\target**

2. Ejecuta el archivo JAR con el siguiente comando:
```
java -jar conversor-numeros-romanos-0.0.1.jar
```

- La aplicación se iniciará y estará disponible en http://localhost:8080. 

**Opción 2**

 Puedes ejecutar el proyecto con el comando de maven:
```
mvn spring-boot:run
```


## Ejecución de Pruebas
Las pruebas unitarias se ejecutan automáticamente durante la construcción del proyecto utilizando Maven. Para ejecutar las pruebas de forma manual, puedes utilizar el comando:

```
mvn test
```

## Probar los Endpoints

Una vez que la aplicación está en funcionamiento, puedes utilizar los endpoints para realizar conversiones de números romanos y arábigos. A continuación, se describen dos métodos comunes para probar la API: utilizando un navegador web o con Postman.

### 1. Probar en un Navegador Web

#### Convertir un Número Entero a Número Romano

1. Abre tu navegador web preferido (como Chrome, Firefox, Edge).
2. En la barra de direcciones, ingresa la siguiente URL:

http://localhost:8080/api/convert-to-roman/14

**Respuesta esperada**: El navegador mostrará `XIV` en la pantalla.

#### Convertir un Número Romano a Número Entero

1. En la barra de direcciones del navegador, ingresa la siguiente URL:
http://localhost:8080/api/convert-to-arabic/XIV

**Respuesta esperada**: El navegador mostrará `14` en la pantalla.

#### Manejo de Errores en el Navegador

1. Intenta ingresar un número fuera del rango permitido, como `4000`:

http://localhost:8080/api/convert-to-roman/4000

**Respuesta esperada**: El navegador mostrará un mensaje de error en formato JSON, como:

```json
{
"message": "Número fuera de rango: 4000"
}
```

### 2. Probar con Postman

#### Paso 1: Descargar e Instalar Postman
1. Ve a [la página oficial de Postman](https://www.postman.com/downloads/) para descargar la versión correspondiente a tu sistema operativo.

2. Sigue las instrucciones de instalación para configurar Postman en tu máquina.


#### Paso 2: Configurar una Nueva Solicitud

1. Abre Postman y crea una nueva solicitud.

2. Configura el método HTTP a GET.

3. Ingresa la URL del endpoint que deseas probar, agregando al final el número arábico o número romano a convertir según la ruta:

**Para convertir un número entero a romano**
```
http://localhost:8080/api/convert-to-roman/14
```

**Para convertir un número romano a entero**
```
http://localhost:8080/api/convert-to-arabic/XIV
```

4. Click en **send** para ejecutar la solicitud.
Verás la respuesta esperada en la sección de resultados de Postman, mostrando **XIV**, **14** o un **mensaje de error**, dependiendo de la solicitud y el valor ingresado.


